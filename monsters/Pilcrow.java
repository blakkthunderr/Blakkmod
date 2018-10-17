package examplemod.monsters;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.AnimateSlowAttackAction;
import com.megacrit.cardcrawl.actions.animations.TalkAction;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.status.Burn;
import com.megacrit.cardcrawl.cards.status.Slimed;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.MonsterStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.*;
import com.megacrit.cardcrawl.unlock.UnlockTracker;
import examplemod.powers.ComplexityPower;

public class Pilcrow extends AbstractMonster
{
    public static final String ID = "Pilcrow";
    private static final MonsterStrings monsterStrings;
    public static final String NAME;
    public static final String[] MOVES;
    public static final String[] DIALOG;
    private static final int HP = 175;
    private static final int A_HP = 200;
    private static final int BASH_DMG = 11;
    private static final int RUSH_DMG = 10;
    private static final int A_BASH_DMG = 13;
    private static final int A_RUSH_DMG = 12;
    private static final int DEBUFF_AMT = 2;
    private static final int METAL_AMT = 1;
    private static final int A_METAL_AMT = 2;
    private static final int THORNS_AMT = 1;
    private static final int A_THORNS_AMT = 2;
    private int bashDmg;
    private int rushDmg;
    private int metalAmt;
    private int thornsAmt;
    private int applyFrail;
    private int dexAmt = 0;
    private static final byte BULL_RUSH = 1;
    private static final byte SKULL_BASH = 2;
    private static final byte BELLOW = 3;
    private static final int ANGRY_LEVEL = 3;
    private boolean usedBellow;

    public Pilcrow() {
        super(Pilcrow.NAME, "Pilcrow", 175, -70.0f, -10.0f, 270.0f, 380.0f, "img/Pilcrow.png");
        this.intentOffsetX = +10.0f * Settings.scale;
        this.type = EnemyType.ELITE;
        this.dialogX = -60.0f * Settings.scale;
        this.dialogY = 50.0f * Settings.scale;
        if (AbstractDungeon.ascensionLevel >= 8) {
            this.setHp(Pilcrow.A_HP);
        }
        else {
            this.setHp(Pilcrow.HP);
        }
        if (AbstractDungeon.ascensionLevel >= 3) {
            this.bashDmg = A_BASH_DMG;
            this.rushDmg = A_RUSH_DMG;
        }
        else {
            this.bashDmg = BASH_DMG;
            this.rushDmg = RUSH_DMG;
        }

        if (AbstractDungeon.ascensionLevel >= 18) {
            this.metalAmt = A_METAL_AMT;
            this.thornsAmt = A_THORNS_AMT;
            this.applyFrail = 2;

        }
        else {
            this.metalAmt = METAL_AMT;
            this.thornsAmt = THORNS_AMT;
            this.applyFrail = 1;

        }
        this.damage.add(new DamageInfo(this, this.rushDmg));
        this.damage.add(new DamageInfo(this, this.bashDmg));
        //this.loadAnimation("images/monsters/fadingForest/nobGremlin/skeleton.atlas", "images/monsters/fadingForest/nobGremlin/skeleton.json", 1.0f);
        // final AnimationState.TrackEntry e = this.state.setAnimation(0, "animation", true);
        //e.setTime(e.getEndTime() * MathUtils.random());
    }

    @Override
    public void usePreBattleAction() {


        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(this, this, new ComplexityPower(this, 1), 1));

    }

    @Override
    public void takeTurn() {
        switch (this.nextMove) {


            case BELLOW: { // move 2, byte 3

                AbstractDungeon.actionManager.addToBottom(new AnimateSlowAttackAction(this));
                AbstractDungeon.actionManager.addToBottom(new DamageAction(AbstractDungeon.player, this.damage.get(1), AbstractGameAction.AttackEffect.BLUNT_HEAVY));
                AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(AbstractDungeon.player, this, new WeakPower(AbstractDungeon.player, applyFrail, true), applyFrail));


                this.setMove(Pilcrow.MOVES[1], (byte)2, Intent.ATTACK, this.rushDmg, 2, true);
                break;
            }
            case SKULL_BASH: { // move 0, byte 2
                for(int i = 0; i < 2; ++i) {
                    AbstractDungeon.actionManager.addToBottom(new AnimateSlowAttackAction(this));
                    AbstractDungeon.actionManager.addToBottom(new DamageAction(AbstractDungeon.player, this.damage.get(1), AbstractGameAction.AttackEffect.BLUNT_HEAVY));
                }

                this.setMove(Pilcrow.MOVES[2], (byte)1, Intent.ATTACK_BUFF, this.bashDmg, 3, true);
                break;

            }
            case BULL_RUSH: { // move 1, byte 1
                AbstractDungeon.actionManager.addToBottom(new TalkAction(this, Pilcrow.DIALOG[0], 1.0f, 3.0f));
                for(int i = 0; i < 3; ++i) {
                    AbstractDungeon.actionManager.addToBottom(new AnimateSlowAttackAction(this));
                    AbstractDungeon.actionManager.addToBottom(new DamageAction(AbstractDungeon.player, this.damage.get(0), AbstractGameAction.AttackEffect.FIRE));
                }

                AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(this, this, new StrengthPower(this, metalAmt), metalAmt));
                AbstractDungeon.actionManager.addToBottom(new MakeTempCardInDiscardAction(new Burn(), 1+this.applyFrail));

                this.setMove(Pilcrow.MOVES[0], (byte)3, Intent.ATTACK_DEBUFF, this.rushDmg, 1, false);
                break;

            }
        }
        AbstractDungeon.actionManager.addToBottom(new RollMoveAction(this));
    }


    @Override
    protected void getMove(final int num) {
        if (!this.usedBellow) {
            this.usedBellow = true;
            this.setMove(Pilcrow.MOVES[0], (byte)3, Intent.ATTACK_DEBUFF, this.rushDmg, 1, false);
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
        monsterStrings = CardCrawlGame.languagePack.getMonsterStrings("Pilcrow");
        NAME = Pilcrow.monsterStrings.NAME;
        MOVES = Pilcrow.monsterStrings.MOVES;
        DIALOG = Pilcrow.monsterStrings.DIALOG;
    }
}
