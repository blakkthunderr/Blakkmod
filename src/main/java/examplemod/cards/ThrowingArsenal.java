package examplemod.cards;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import examplemod.powers.BloodthirstPower;
import examplemod.powers.ThrowingArsenalPower;

public class ThrowingArsenal
        extends CustomCard {
    public static final String ID = "ThrowingArsenal";
    public static final String NAME = "Throwing Arsenal";
    public static final String DESCRIPTION = "Whenever you play an Attack that Exhausts, add a copy of it to your draw pile.";
    public static final String IMG_PATH = "img/ThrowingArsenal.png";
    private static final int COST = 2;



    public ThrowingArsenal() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION,
                CardType.POWER, CardColor.GREEN,
                CardRarity.UNCOMMON, CardTarget.SELF);
        this.exhaust = true;


        this.setBackgroundTexture("img/custom_background_small.png", "img/custom_background_large.png");

        this.setOrbTexture("img/custom_orb_small.png", "img/custom_orb_large.png");

        this.setBannerTexture("img/custom_banner_large.png", "img/custom_banner_large.png");
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new ThrowingArsenalPower(p, 1), 1));
    }

    @Override
    public AbstractCard makeCopy() {
        return new ThrowingArsenal();
    }


    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.isInnate = true;
            this.rawDescription = "Innate. Whenever you play an Attack that Exhausts, add a copy of it to your draw pile.";
            this.initializeDescription();

        }
    }
}
