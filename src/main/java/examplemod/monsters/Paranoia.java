package examplemod.monsters;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.AnimateSlowAttackAction;
import com.megacrit.cardcrawl.actions.animations.TalkAction;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.colorless.Madness;
import com.megacrit.cardcrawl.cards.curses.Doubt;
import com.megacrit.cardcrawl.cards.curses.Normality;
import com.megacrit.cardcrawl.cards.curses.Pain;
import com.megacrit.cardcrawl.cards.curses.Regret;
import com.megacrit.cardcrawl.cards.status.Burn;
import com.megacrit.cardcrawl.cards.status.Dazed;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.MonsterStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.IntangiblePower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.powers.WeakPower;
import examplemod.powers.ComplexityPower;

public class Paranoia extends AbstractMonster
{
    public static final String ID = "Paranoia";
    private static final MonsterStrings monsterStrings;
    public static final String NAME;
    public static final String[] MOVES;
    public static final String[] DIALOG;
    private static final int HP = 175;
    private static final int A_HP = 200;
    private static final int BASH_DMG = 20;
    private static final int RUSH_DMG = 20;
    private static final int A_BASH_DMG = 30;
    private static final int A_RUSH_DMG = 30;
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

    public Paranoia() {
        super(Paranoia.NAME, "Paranoia", 175, -70.0f, -10.0f, 270.0f, 380.0f, "img/Paranoia.png");
        this.intentOffsetX = +10.0f * Settings.scale;
        this.type = EnemyType.ELITE;
        this.dialogX = -60.0f * Settings.scale;
        this.dialogY = 50.0f * Settings.scale;
        if (AbstractDungeon.ascensionLevel >= 8) {
            this.setHp(Paranoia.A_HP);
        }
        else {
            this.setHp(Paranoia.HP);
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
            this.applyFrail = 3;

        }
        else {
            this.metalAmt = METAL_AMT;
            this.thornsAmt = THORNS_AMT;
            this.applyFrail = 2;

        }
        this.damage.add(new DamageInfo(this, this.rushDmg));
        this.damage.add(new DamageInfo(this, this.bashDmg));
        //this.loadAnimation("images/monsters/fadingForest/nobGremlin/skeleton.atlas", "images/monsters/fadingForest/nobGremlin/skeleton.json", 1.0f);
        // final AnimationState.TrackEntry e = this.state.setAnimation(0, "animation", true);
        //e.setTime(e.getEndTime() * MathUtils.random());
    }

    @Override
    public void usePreBattleAction() {

        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(this, this, new IntangiblePower(this, this.applyFrail), this.applyFrail));


    }

    @Override
    public void takeTurn() {
        switch (this.nextMove) {


            case BELLOW: { // move 2, byte 3

                AbstractDungeon.actionManager.addToBottom(new AnimateSlowAttackAction(this));
                AbstractDungeon.actionManager.addToBottom(new MakeTempCardInDiscardAndDeckAction(new Madness()));
                AbstractDungeon.actionManager.addToBottom(new MakeTempCardInDiscardAction(new Regret(), 1));
                AbstractDungeon.actionManager.addToBottom(new MakeTempCardInDiscardAndDeckAction(new Doubt()));
                AbstractDungeon.actionManager.addToBottom(new MakeTempCardInDiscardAndDeckAction(new Dazed()));




                this.setMove(Paranoia.MOVES[1], (byte)2, Intent.ATTACK, this.rushDmg, 1, false);
                break;
            }

            case SKULL_BASH: { // move 0, byte 2

                    AbstractDungeon.actionManager.addToBottom(new AnimateSlowAttackAction(this));
                    AbstractDungeon.actionManager.addToBottom(new DamageAction(AbstractDungeon.player, this.damage.get(1), AbstractGameAction.AttackEffect.BLUNT_HEAVY));


                this.setMove(Paranoia.MOVES[0], (byte)3, Intent.STRONG_DEBUFF, this.bashDmg, 3, true);
                break;

            }

        }
        AbstractDungeon.actionManager.addToBottom(new RollMoveAction(this));
    }


    @Override
    protected void getMove(final int num) {
        if (!this.usedBellow) {
            this.usedBellow = true;
            this.setMove(Paranoia.MOVES[0], (byte)3, Intent.STRONG_DEBUFF, this.rushDmg, 1, false);
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
        monsterStrings = CardCrawlGame.languagePack.getMonsterStrings("Paranoia");
        NAME = Paranoia.monsterStrings.NAME;
        MOVES = Paranoia.monsterStrings.MOVES;
        DIALOG = Paranoia.monsterStrings.DIALOG;
    }
}
