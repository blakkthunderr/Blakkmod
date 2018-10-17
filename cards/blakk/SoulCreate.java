package examplemod.cards.blakk;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import examplemod.patches.AbstractCardEnum;
import examplemod.powers.SoulPower;

public class SoulCreate
        extends CustomCard {
    public static final String ID = "SoulCreate";
    public static final String NAME = "Soul Create";
    public static final String DESCRIPTION = "Gain 2 Souls.";
    public static final String IMG_PATH = "img/SoulBloom.png";
    private static final int COST = 1;



    public SoulCreate() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION,
                CardType.POWER, AbstractCardEnum.BLAKK_COLOR,
                CardRarity.UNCOMMON, CardTarget.SELF);
        this.baseMagicNumber = 2;
        this.magicNumber = this.baseMagicNumber;

    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {

        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new SoulPower(p, this.magicNumber), this.magicNumber));



    }

    @Override
    public AbstractCard makeCopy() {
        return new SoulCreate();
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.rawDescription = "Gain 3 Souls.";
            this.baseMagicNumber = 3;
            this.initializeDescription();

        }
    }
}
