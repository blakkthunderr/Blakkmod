package examplemod.cards.blakk;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import examplemod.patches.AbstractCardEnum;
import examplemod.powers.GlassSkinPower;
import examplemod.powers.SoulPower;

public class GlassSkin
        extends CustomCard {
    public static final String ID = "GlassSkin";
    public static final String NAME = "Glass Skin";
    public static final String DESCRIPTION = "At the end of your turn, gain 5 block and 1 Frail.";
    public static final String IMG_PATH = "img/SoulBloom.png";
    private static final int COST = 1;



    public GlassSkin() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION,
                CardType.POWER, AbstractCardEnum.BLAKK_COLOR,
                CardRarity.UNCOMMON, CardTarget.SELF);
        this.baseMagicNumber = 2;
        this.magicNumber = this.baseMagicNumber;

    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {

        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new GlassSkinPower(p, 1), 1));



    }

    @Override
    public AbstractCard makeCopy() {
        return new GlassSkin();
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeBaseCost(0);

        }
    }
}
