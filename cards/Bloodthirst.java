package examplemod.cards;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import examplemod.powers.BloodthirstPower;

public class Bloodthirst
        extends CustomCard {
    public static final String ID = "Bloodthirst";
    public static final String NAME = "Bloodthirst";
    public static final String DESCRIPTION = "Whenever you deal unblocked attack damage, heal 1 HP.";
    public static final String IMG_PATH = "img/Bloodthirst.png";
    private static final int COST = 3;



    public Bloodthirst() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION,
                CardType.POWER, CardColor.RED,
                CardRarity.UNCOMMON, CardTarget.SELF);
        this.exhaust = true;


        this.setBackgroundTexture("img/custom_background_small.png", "img/custom_background_large.png");

        this.setOrbTexture("img/custom_orb_small.png", "img/custom_orb_large.png");

        this.setBannerTexture("img/custom_banner_large.png", "img/custom_banner_large.png");
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new BloodthirstPower(p, 1), 1));
    }

    @Override
    public AbstractCard makeCopy() {
        return new Bloodthirst();
    }


    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeBaseCost(2);

        }
    }
}
