package examplemod.cards;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import examplemod.actions.MakeCardInHandExhaustAction;

public class FiftyTwoCardPickup
        extends CustomCard {
    public static final String ID = "52CardPickup";
    public static final String NAME = "52 Card Pickup";
    public static final String DESCRIPTION = "Throw 52 cards all over.";
    public static final String IMG_PATH = "img/52CardPickup.png";
    private static final int COST = 2;




    public FiftyTwoCardPickup() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION,
                CardType.SKILL, CardColor.COLORLESS,
                CardRarity.SPECIAL, CardTarget.SELF);

        this.baseMagicNumber = 26;
        this.exhaust = true;


        this.setBackgroundTexture("img/custom_background_small.png", "img/custom_background_large.png");

        this.setOrbTexture("img/custom_orb_small.png", "img/custom_orb_large.png");

        this.setBannerTexture("img/custom_banner_large.png", "img/custom_banner_large.png");
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        int i;

        for(i = 0; i < this.baseMagicNumber; ++i) {

            AbstractCard c = AbstractDungeon.returnTrulyRandomCardInCombat(CardType.ATTACK).makeCopy();

            AbstractDungeon.actionManager.addToBottom(new MakeCardInHandExhaustAction(p,p,c,1));

            AbstractCard s = AbstractDungeon.returnTrulyRandomCardInCombat(CardType.SKILL).makeCopy();

            AbstractDungeon.actionManager.addToBottom(new MakeCardInHandExhaustAction(p,p,s,1));

        }
    }

    @Override
    public AbstractCard makeCopy() {
        return new FiftyTwoCardPickup();
    }


    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeBaseCost(1);

        }
    }
}
