package examplemod.cards;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.ArtifactPower;
import com.megacrit.cardcrawl.powers.PoisonPower;
import com.megacrit.cardcrawl.powers.WeakPower;

public class Corrode
        extends CustomCard {
    public static final String ID = "Corrode";
    public static final String NAME = "Corrode";
    public static final String DESCRIPTION = "Remove all Artifacts from the target. Apply 3 Poison.";
    public static final String IMG_PATH = "img/Corrode.png";
    private static final int COST = 1;






    public Corrode() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION,
                CardType.SKILL, CardColor.GREEN,
                CardRarity.UNCOMMON, CardTarget.ENEMY);

        this.baseMagicNumber = 3;
        this.magicNumber = this.baseMagicNumber;



        this.setBackgroundTexture("img/custom_background_small.png", "img/custom_background_large.png");

        this.setOrbTexture("img/custom_orb_small.png", "img/custom_orb_large.png");

        this.setBannerTexture("img/custom_banner_large.png", "img/custom_banner_large.png");
    }




    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {


        AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(m, p,  ArtifactPower.POWER_ID));

        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(m, p, new PoisonPower(m, p, this.magicNumber), this.magicNumber, AbstractGameAction.AttackEffect.POISON));



    }

    @Override
    public AbstractCard makeCopy() {
        return new Corrode();
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.rawDescription = "Remove all Artifacts from the target. Apply 6 Poison.";
            this.baseMagicNumber = 6;
            this.initializeDescription();

        }
    }
}
