package examplemod.cards.blakk;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.actions.defect.SunderAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import examplemod.actions.SoulScytheAction;
import examplemod.patches.AbstractCardEnum;
import examplemod.powers.SoulPower;

public class SoulScythe
        extends CustomCard {
    public static final String ID = "SoulScythe";
    public static final String NAME = "Soul Scythe";
    public static final String DESCRIPTION = "Deal !D! damage. If this kills an enemy, gain 1 Soul.";
    public static final String IMG_PATH = "img/SoulStrike.png";
    private static final int COST = 2;
    private static final int ATTACK_DMG = 16;


    public SoulScythe() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION,
                CardType.ATTACK, AbstractCardEnum.BLAKK_COLOR,
                CardRarity.UNCOMMON, CardTarget.ENEMY);

        this.baseMagicNumber = 2;
        this.magicNumber = this.baseMagicNumber;
        this.damage=this.baseDamage = ATTACK_DMG;

        //this.setBackgroundTexture("img/custom_background_small.png", "img/custom_background_large.png");

        //this.setOrbTexture("img/custom_orb_small.png", "img/custom_orb_large.png");

        //this.setBannerTexture("img/custom_banner_large.png", "img/custom_banner_large.png");
    }




    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {

        //AbstractDungeon.actionManager.addToBottom(new com.megacrit.cardcrawl.actions.common.DamageAction(m,
                //new DamageInfo(p, this.damage, this.damageTypeForTurn),
                //AbstractGameAction.AttackEffect.SLASH_HEAVY));

        AbstractDungeon.actionManager.addToBottom(new SoulScytheAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), p));



    }

    @Override
    public AbstractCard makeCopy() {
        return new SoulScythe();
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeDamage(4);

        }
    }
}
