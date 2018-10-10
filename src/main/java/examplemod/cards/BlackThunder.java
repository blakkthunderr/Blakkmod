package examplemod.cards;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.orbs.Dark;
import com.megacrit.cardcrawl.orbs.Lightning;

public class BlackThunder
        extends CustomCard {
    public static final String ID = "BlackThunder";
    public static final String NAME = "Black Thunder";
    public static final String DESCRIPTION = "Channel 3 Lightning and 1 Dark. Exhaust.";
    public static final String IMG_PATH = "img/BlackThunder.png";
    private static final int COST = 3;



    public BlackThunder() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION,
                CardType.SKILL, CardColor.BLUE,
                CardRarity.RARE, CardTarget.SELF);
        this.exhaust = true;


        this.setBackgroundTexture("img/custom_background_small.png", "img/custom_background_large.png");

        this.setOrbTexture("img/custom_orb_small.png", "img/custom_orb_large.png");

        this.setBannerTexture("img/custom_banner_large.png", "img/custom_banner_large.png");
    }

    @Override

    public void use(AbstractPlayer p, AbstractMonster m) {


        AbstractDungeon.player.channelOrb(new Lightning());
        AbstractDungeon.player.channelOrb(new Lightning());
        AbstractDungeon.player.channelOrb(new Lightning());

        AbstractDungeon.player.channelOrb(new Dark());

    }

    @Override
    public AbstractCard makeCopy() {
        return new BlackThunder();
    }


    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeBaseCost(2);

        }
    }
}
