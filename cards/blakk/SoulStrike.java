package examplemod.cards.blakk;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.WeakPower;
import examplemod.patches.AbstractCardEnum;
import examplemod.powers.SoulPower;

public class SoulStrike
        extends CustomCard {
    public static final String ID = "SoulStrike";
    public static final String NAME = "Soul Strike";
    public static final String DESCRIPTION = "Deal !D! damage. Does +2 damage for every Soul you have.";
    public static final String IMG_PATH = "img/SoulStrike.png";
    private static final int COST = 2;
    private static final int ATTACK_DMG = 14;


    public SoulStrike() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION,
                CardType.ATTACK, AbstractCardEnum.BLAKK_COLOR,
                CardRarity.COMMON, CardTarget.ENEMY);

        this.baseMagicNumber = 2;
        this.magicNumber = this.baseMagicNumber;
        this.damage=this.baseDamage = ATTACK_DMG;

        //this.setBackgroundTexture("img/custom_background_small.png", "img/custom_background_large.png");

        //this.setOrbTexture("img/custom_orb_small.png", "img/custom_orb_large.png");

        //this.setBannerTexture("img/custom_banner_large.png", "img/custom_banner_large.png");
    }




    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.damage = ATTACK_DMG;
        if (p.hasPower (SoulPower.POWER_ID)){
            this.damage +=  (p.getPower(SoulPower.POWER_ID).amount*this.magicNumber);
        }
        AbstractDungeon.actionManager.addToBottom(new com.megacrit.cardcrawl.actions.common.DamageAction(m,
                new DamageInfo(p, this.damage, this.damageTypeForTurn),
                AbstractGameAction.AttackEffect.SLASH_HEAVY));


    }

    @Override
    public AbstractCard makeCopy() {
        return new SoulStrike();
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.rawDescription = "Deal !D! damage. Does +4 damage for every Soul you have.";
            this.baseMagicNumber = 4;
            this.initializeDescription();

        }
    }
}
