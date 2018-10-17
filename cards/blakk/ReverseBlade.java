package examplemod.cards.blakk;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.FrailPower;
import com.megacrit.cardcrawl.powers.WeakPower;
import examplemod.patches.AbstractCardEnum;

public class ReverseBlade
        extends CustomCard {
    public static final String ID = "ReverseBlade";
    public static final String NAME = "Reverse Blade";
    public static final String DESCRIPTION = "Deal !D! damage for every stack of Weak you have.";
    public static final String IMG_PATH = "img/BlakkDefend.png";
    private static final int COST = 1;
    //private static final int BLOCK_AMT = 6;


    public ReverseBlade() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION,
                CardType.ATTACK, AbstractCardEnum.BLAKK_COLOR,
                CardRarity.COMMON, CardTarget.ENEMY);
        this.baseDamage = 6;
        this.magicNumber = 0;
        this.damage=this.baseDamage;


    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        if (Settings.isDebug) {
            //AbstractDungeon.actionManager.addToBottom(new GainBlockAction(p, p, 50));
        } else {
            int i;

            if (p.hasPower(WeakPower.POWER_ID)) {
                this.magicNumber = (p.getPower(WeakPower.POWER_ID).amount);
            } else {
                this.magicNumber = 0;
            }
            for (i = 0; i < this.magicNumber; ++i) {
                AbstractDungeon.actionManager.addToBottom(new com.megacrit.cardcrawl.actions.common.DamageAction(m,
                        new DamageInfo(p, this.damage, this.damageTypeForTurn),
                        AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
            }
        }

    }


    @Override
    public AbstractCard makeCopy() {
        return new ReverseBlade();
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.baseDamage = 9;
            this.rawDescription = "Deal !D! damage for every stack of Weak you have.";
            this.initializeDescription();

        }
    }
}
