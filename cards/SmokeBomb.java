package examplemod.cards;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.ExhaustAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.powers.IntangiblePlayerPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.vfx.combat.InflameEffect;
import com.megacrit.cardcrawl.vfx.combat.SmokeBombEffect;

public class SmokeBomb
        extends CustomCard {
    public static final String ID = "SmokeBomb";
    public static final String NAME = "Smoke Bomb";
    public static final String DESCRIPTION = "Gain 1 Intangible and Exhaust all cards in your hand. Exhaust.";
    public static final String IMG_PATH = "img/SmokeBomb.png";
    private static final int COST = 1;




    public SmokeBomb() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION,
                CardType.SKILL, CardColor.GREEN,
                CardRarity.COMMON, CardTarget.SELF);

        this.baseMagicNumber = 10;
        this.exhaust = true;



        this.setBackgroundTexture("img/custom_background_small.png", "img/custom_background_large.png");

        this.setOrbTexture("img/custom_orb_small.png", "img/custom_orb_large.png");

        this.setBannerTexture("img/custom_banner_large.png", "img/custom_banner_large.png");
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(new VFXAction(p, new SmokeBombEffect(0,0), 1.0F));



        int i;

        for(i = 0; i < this.baseMagicNumber; ++i) {
            AbstractDungeon.actionManager.addToTop(new ExhaustAction(AbstractDungeon.player, AbstractDungeon.player, 1, true, true));
        }

        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new IntangiblePlayerPower(p, 1), 1));
    }

    @Override
    public AbstractCard makeCopy() {
        return new SmokeBomb();
    }


    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.rawDescription = "Gain 1 Intangible and Exhaust 3 random cards in your hand. Exhaust.";
            this.baseMagicNumber = 3;
            this.initializeDescription();

        }
    }
}
