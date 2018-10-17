package examplemod.cards.blakk;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.FrailPower;
import examplemod.patches.AbstractCardEnum;
import examplemod.powers.SoulPower;

public class SoulLabor
        extends CustomCard {
    public static final String ID = "SoulLabor";
    public static final String NAME = "Soul Labor";
    public static final String DESCRIPTION = "Draw cards equal to the amount of Souls you have. Exhaust.";
    public static final String IMG_PATH = "img/BlakkDefend.png";
    private static final int COST = 1;



    public SoulLabor() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION,
                CardType.SKILL, AbstractCardEnum.BLAKK_COLOR,
                CardRarity.COMMON, CardTarget.SELF);

        this.exhaust = true;

    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {



            if (p.hasPower(SoulPower.POWER_ID)) {
                this.magicNumber = (p.getPower(SoulPower.POWER_ID).amount);
            } else {
                this.magicNumber = 0;
            }
        AbstractDungeon.actionManager.addToBottom(new DrawCardAction(p, magicNumber));



    }


    @Override
    public AbstractCard makeCopy() {
        return new SoulLabor();
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeBaseCost(0);


        }
    }
}
