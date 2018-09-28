package examplemod.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;


import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.powers.FrailPower;
import com.megacrit.cardcrawl.powers.IntangiblePower;
import com.megacrit.cardcrawl.powers.WeakPower;

public class CriticalStrike
        extends CustomCard {
    public static final String ID = "CriticalStrike";
    public static final String NAME = "Critical Strike";
    public static final String DESCRIPTION = "Deal !D! damage. Does 3 more damage for every stack of Weak on the enemy.";
    public static final String IMG_PATH = "img/CriticalStrike.png";
    private static final int COST = 1;
    private static final int ATTACK_DMG = 6;





    public CriticalStrike() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION,
                AbstractCard.CardType.ATTACK, AbstractCard.CardColor.GREEN,
                AbstractCard.CardRarity.RARE, AbstractCard.CardTarget.ENEMY);

        this.baseMagicNumber = 3;
        this.magicNumber = this.baseMagicNumber;
        this.damage=this.baseDamage = ATTACK_DMG;

        this.setBackgroundTexture("img/custom_background_small.png", "img/custom_background_large.png");

        this.setOrbTexture("img/custom_orb_small.png", "img/custom_orb_large.png");

        this.setBannerTexture("img/custom_banner_large.png", "img/custom_banner_large.png");
    }



    public void calculateCardDamage(AbstractMonster mo) {
        if (mo.hasPower (WeakPower.POWER_ID)){
            this.baseDamage +=  (mo.getPower(WeakPower.POWER_ID).amount*this.magicNumber);
        }
        super.calculateCardDamage(mo);
        this.baseDamage = ATTACK_DMG;
    }


    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {


        AbstractDungeon.actionManager.addToBottom(new com.megacrit.cardcrawl.actions.common.DamageAction(m,
                new DamageInfo(p, this.damage, this.damageTypeForTurn),
                AbstractGameAction.AttackEffect.SLASH_VERTICAL));



    }

    @Override
    public AbstractCard makeCopy() {
        return new CriticalStrike();
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.rawDescription = "Deal !D! damage. Does 4 more damage for every stack of Weak on the enemy.";
            this.baseMagicNumber = 4;
            this.initializeDescription();

        }
    }
}
