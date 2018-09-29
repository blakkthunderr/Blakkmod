package examplemod.monsters;

import com.megacrit.cardcrawl.monsters.*;
import com.megacrit.cardcrawl.monsters.exordium.*;
import com.megacrit.cardcrawl.localization.*;
import com.megacrit.cardcrawl.dungeons.*;
import com.megacrit.cardcrawl.cards.*;
import com.badlogic.gdx.math.*;
import com.esotericsoftware.spine.*;
import com.megacrit.cardcrawl.core.*;
import com.megacrit.cardcrawl.actions.*;
import com.megacrit.cardcrawl.actions.animations.*;
import com.megacrit.cardcrawl.powers.*;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.actions.utility.*;
import com.megacrit.cardcrawl.unlock.UnlockTracker;

public class TwilightGuardian extends AbstractMonster
{
    public static final String ID = "TwilightGuardian";
    private static final MonsterStrings monsterStrings;
    public static final String NAME;
    public static final String[] MOVES;
    public static final String[] DIALOG;
    private static final int HP = 400;
    private static final int A_HP = 450;
    private static final int BASH_DMG = 20;
    private static final int RUSH_DMG = 10;
    private static final int A_BASH_DMG = 25;
    private static final int A_RUSH_DMG = 12;
    private static final int DEBUFF_AMT = 2;
    private static final int METAL_AMT = 10;
    private static final int A_METAL_AMT = 15;
    private static final int THORNS_AMT = 2;
    private static final int A_THORNS_AMT = 3;
    private int bashDmg;
    private int rushDmg;
    private int metalAmt;
    private int thornsAmt;
    private int applyFrail;
    private static final byte BULL_RUSH = 1;
    private static final byte SKULL_BASH = 2;
    private static final byte BELLOW = 3;
    private static final int ANGRY_LEVEL = 3;
    private boolean usedBellow;

    public TwilightGuardian() {
        super(TwilightGuardian.NAME, "TwilightGuardian", 300, -70.0f, -10.0f, 270.0f, 380.0f, "img/TwilightGuardian.png");
        this.intentOffsetX = -1.0f * Settings.scale;
        this.type = EnemyType.BOSS;
        this.dialogX = -60.0f * Settings.scale;
        this.dialogY = 50.0f * Settings.scale;
        if (AbstractDungeon.ascensionLevel >= 9) {
            this.setHp(TwilightGuardian.A_HP);
        }
        else {
            this.setHp(TwilightGuardian.HP);
        }
        if (AbstractDungeon.ascensionLevel >= 4) {
            this.bashDmg = A_BASH_DMG;
            this.rushDmg = A_RUSH_DMG;
        }
        else {
            this.bashDmg = BASH_DMG;
            this.rushDmg = RUSH_DMG;
        }

        if (AbstractDungeon.ascensionLevel >= 19) {
            this.metalAmt = A_METAL_AMT;
            this.thornsAmt = A_THORNS_AMT;
            this.applyFrail = 1;

        }
        else {
            this.metalAmt = METAL_AMT;
            this.thornsAmt = THORNS_AMT;
            this.applyFrail = 0;

        }
        this.damage.add(new DamageInfo(this, this.rushDmg));
        this.damage.add(new DamageInfo(this, this.bashDmg));
        //this.loadAnimation("images/monsters/fadingForest/nobGremlin/skeleton.atlas", "images/monsters/fadingForest/nobGremlin/skeleton.json", 1.0f);
       // final AnimationState.TrackEntry e = this.state.setAnimation(0, "animation", true);
        //e.setTime(e.getEndTime() * MathUtils.random());
    }

    @Override
    public void usePreBattleAction() {
        CardCrawlGame.music.unsilenceBGM();
        AbstractDungeon.scene.fadeOutAmbiance();
        AbstractDungeon.getCurrRoom().playBgmInstantly("BOSS_BEYOND");
        UnlockTracker.markBossAsSeen("TWILIGHT");
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(this, this, new MetallicizePower(this, metalAmt), metalAmt));
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(this, this, new ThornsPower(this, thornsAmt), thornsAmt));
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(this, this, new ArtifactPower(this, 1), 1));

    }

    @Override
    public void takeTurn() {
        switch (this.nextMove) {
            case BELLOW: {

                //AbstractDungeon.actionManager.addToBottom(new TalkAction(this, TwilightGuardian.DIALOG[0], 1.0f, 3.0f));
                AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(this, this, new PlatedArmorPower(this, 5), 5));
                AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(this, this, new ThornsPower(this, thornsAmt), thornsAmt));
                AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(this, this, new ArtifactPower(this, 1), 1));

                this.setMove(TwilightGuardian.MOVES[0], (byte)2, Intent.ATTACK, this.bashDmg, 1, false);
                break;
            }
            case SKULL_BASH: { // move 0, byte 2
                AbstractDungeon.actionManager.addToBottom(new AnimateSlowAttackAction(this));
                AbstractDungeon.actionManager.addToBottom(new DamageAction(AbstractDungeon.player, this.damage.get(1), AbstractGameAction.AttackEffect.BLUNT_HEAVY));
                AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(AbstractDungeon.player, this, new WeakPower(AbstractDungeon.player, TwilightGuardian.DEBUFF_AMT, true), TwilightGuardian.DEBUFF_AMT));
                if (this.applyFrail == 1) {
                    AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(AbstractDungeon.player, this, new FrailPower(AbstractDungeon.player, applyFrail, true), applyFrail));
                }
                this.setMove(TwilightGuardian.MOVES[1], (byte)1, Intent.ATTACK, this.rushDmg, 4, true);
                break;
            }
            case BULL_RUSH: { // move 1, byte 1
                for(int i = 0; i < 4; ++i) {
                    AbstractDungeon.actionManager.addToBottom(new AnimateSlowAttackAction(this));
                    AbstractDungeon.actionManager.addToBottom(new DamageAction(AbstractDungeon.player, this.damage.get(0), AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));
                }
                this.setMove(TwilightGuardian.MOVES[2], (byte)3, Intent.BUFF);
                break;

            }
        }
        AbstractDungeon.actionManager.addToBottom(new RollMoveAction(this));
    }


    @Override
    protected void getMove(final int num) {
        if (!this.usedBellow) {
            this.usedBellow = true;
            this.setMove((byte)3, Intent.BUFF);
            return;

        }/*
        if (num < 33) {
            this.setMove(TwilightGuardian.MOVES[0], (byte)2, Intent.ATTACK_DEBUFF, this.damage.get(1).base);
            return;
        }
        if (this.lastTwoMoves((byte)1)) {
            this.setMove(TwilightGuardian.MOVES[0], (byte)2, Intent.ATTACK_DEBUFF, this.damage.get(1).base);
        }
        else {
            this.setMove((byte)1, Intent.ATTACK, this.damage.get(0).base);
        }*/
    }
    /*
    @Override
    public void die() {
        super.die();
        AbstractDungeon.scene.fadeInAmbiance();
        CardCrawlGame.music.fadeOutTempBGM();
    }
    */
    static {
        monsterStrings = CardCrawlGame.languagePack.getMonsterStrings("TwilightGuardian");
        NAME = TwilightGuardian.monsterStrings.NAME;
        MOVES = TwilightGuardian.monsterStrings.MOVES;
        DIALOG = TwilightGuardian.monsterStrings.DIALOG;
    }
}
