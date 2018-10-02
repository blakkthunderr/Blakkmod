package examplemod.monsters;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.AnimateSlowAttackAction;
import com.megacrit.cardcrawl.actions.animations.TalkAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDiscardAction;
import com.megacrit.cardcrawl.actions.common.RollMoveAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.curses.Regret;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.MonsterStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.*;
import com.megacrit.cardcrawl.unlock.UnlockTracker;
import examplemod.cards.Rust;

public class RustBeast extends AbstractMonster
{
    public static final String ID = "RustBeast";
    private static final MonsterStrings monsterStrings;
    public static final String NAME;
    public static final String[] MOVES;
    public static final String[] DIALOG;
    private static final int HP = 230;
    private static final int A_HP = 260;
    private static final int BASH_DMG = 6;
    private static final int RUSH_DMG = 5;
    private static final int A_BASH_DMG = 12;
    private static final int A_RUSH_DMG = 8;
    private static final int DEBUFF_AMT = 2;
    private static final int METAL_AMT = 7;
    private static final int A_METAL_AMT = 10;
    private static final int THORNS_AMT = 1;
    private static final int A_THORNS_AMT = 2;
    private int bashDmg;
    private int rushDmg;
    private int metalAmt;
    private int thornsAmt;
    private int applyFrail;
    private static final byte BULL_RUSH = 1;
    private static final byte SKULL_BASH = 2;
    private static final byte BELLOW = 3;
    private static final byte RUST = 4;

    private static final int ANGRY_LEVEL = 3;
    private boolean usedBellow;

    public RustBeast() {
        super(RustBeast.NAME, "RustBeast", 200, -80.0f, -10.0f, 270.0f, 380.0f, "img/RustMonster.png");
        this.intentOffsetX = -1.0f * Settings.scale;
        this.type = EnemyType.BOSS;
        this.dialogX = -60.0f * Settings.scale;
        this.dialogY = 50.0f * Settings.scale;
        if (AbstractDungeon.ascensionLevel >= 9) {
            this.setHp(RustBeast.A_HP);
        }
        else {
            this.setHp(RustBeast.HP);
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
        CardCrawlGame.music.unsilenceBGM();
        AbstractDungeon.scene.fadeOutAmbiance();
        AbstractDungeon.getCurrRoom().playBgmInstantly("BOSS_BOTTOM");
        UnlockTracker.markBossAsSeen("RUST");
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(this, this, new PlatedArmorPower(this, metalAmt), metalAmt));

    }

    @Override
    public void takeTurn() {
        switch (this.nextMove) {
            case BELLOW: {

                AbstractDungeon.actionManager.addToBottom(new TalkAction(this, RustBeast.DIALOG[0], 1.0f, 3.0f));
                AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(this, this, new StrengthPower(this, this.thornsAmt), this.thornsAmt));

                this.setMove(RustBeast.MOVES[0], (byte)2, Intent.ATTACK, this.bashDmg, 1, false);
                break;
            }
            case SKULL_BASH: {
                AbstractDungeon.actionManager.addToBottom(new AnimateSlowAttackAction(this));
                AbstractDungeon.actionManager.addToBottom(new DamageAction(AbstractDungeon.player, this.damage.get(1), AbstractGameAction.AttackEffect.BLUNT_HEAVY));

                    AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(AbstractDungeon.player, this, new VulnerablePower(AbstractDungeon.player, applyFrail, true), applyFrail));

                this.setMove(RustBeast.MOVES[1], (byte)1, Intent.ATTACK, this.rushDmg, 2, true);
                break;
            }
            case BULL_RUSH: {
                for(int i = 0; i < 2; ++i) {
                    AbstractDungeon.actionManager.addToBottom(new AnimateSlowAttackAction(this));
                    AbstractDungeon.actionManager.addToBottom(new DamageAction(AbstractDungeon.player, this.damage.get(0), AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));
                }
                this.setMove(RustBeast.MOVES[2], (byte)4, Intent.STRONG_DEBUFF);
                break;

            }
            case RUST: {
                AbstractDungeon.actionManager.addToBottom(new MakeTempCardInDiscardAction(new Rust(), 1));

                this.setMove(RustBeast.MOVES[3], (byte)3, Intent.BUFF);
                break;

            }
        }
        AbstractDungeon.actionManager.addToBottom(new RollMoveAction(this));
    }

    @Override
    public void die() {
        this.useFastShakeAnimation(5.0F);
        CardCrawlGame.screenShake.rumble(4.0F);
        ++this.deathTimer;
        super.die();
        this.onBossVictoryLogic();

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
        monsterStrings = CardCrawlGame.languagePack.getMonsterStrings("RustBeast");
        NAME = RustBeast.monsterStrings.NAME;
        MOVES = RustBeast.monsterStrings.MOVES;
        DIALOG = RustBeast.monsterStrings.DIALOG;
    }
}
