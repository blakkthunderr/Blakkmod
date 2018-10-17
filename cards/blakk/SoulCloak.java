package examplemod.cards.blakk;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.FrailPower;
import examplemod.patches.AbstractCardEnum;
import examplemod.powers.SoulPower;

public class SoulCloak
        extends CustomCard {
    public static final String ID = "SoulCloak";
    public static final String NAME = "Soul Cloak";
    public static final String DESCRIPTION = "Gain block equal to the amount of Souls you have.";
    public static final String IMG_PATH = "img/BlakkDefend.png";
    private static final int COST = 0;
    private static final int BLOCK_AMT = 6;


    public SoulCloak() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION,
                CardType.SKILL, AbstractCardEnum.BLAKK_COLOR,
                CardRarity.COMMON, CardTarget.SELF);
        this.baseBlock = 0;
        this.block = this.baseBlock;
        this.baseMagicNumber = 0;
        this.magicNumber = baseMagicNumber;

    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {



            if (p.hasPower(SoulPower.POWER_ID)) {
                this.block = (p.getPower(SoulPower.POWER_ID).amount);
            } else {
                this.block = 0;
            }

                AbstractDungeon.actionManager.addToBottom(new GainBlockAction(p, p, this.block+this.magicNumber));



    }


    @Override
    public AbstractCard makeCopy() {
        return new SoulCloak();
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.rawDescription = "Gain block equal to the amount of Souls you have +3.";
            this.baseMagicNumber = 3;

            this.initializeDescription();


        }
    }
}
