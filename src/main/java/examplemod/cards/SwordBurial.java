package examplemod.cards;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.vfx.combat.FlameBarrierEffect;
import com.megacrit.cardcrawl.vfx.combat.InflameEffect;
import examplemod.actions.MakeCardInHandExhaustAction;

public class SwordBurial
        extends CustomCard {
    public static final String ID = "SwordBurial";
    public static final String NAME = "Sword Burial";
    public static final String DESCRIPTION = "Add 3 Attack cards to your hand. They cost 0 and Exhaust. Exhaust.";
    public static final String IMG_PATH = "img/SwordBurial.png";
    private static final int COST = 3;




    public SwordBurial() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION,
                CardType.SKILL, CardColor.RED,
                CardRarity.RARE, CardTarget.SELF);

        this.baseMagicNumber = 3;
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
            c.setCostForTurn(0);
            AbstractDungeon.actionManager.addToBottom(new MakeCardInHandExhaustAction(p,p,c,1));
        }
    }

    @Override
    public AbstractCard makeCopy() {
        return new SwordBurial();
    }


    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.rawDescription = "Add 3 Attack cards to your hand. They cost 0 and Exhaust.";
            this.exhaust = false;
            this.initializeDescription();

        }
    }
}
