package examplemod.cards.blakk;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.unique.RemoveDebuffsAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import examplemod.patches.AbstractCardEnum;

public class AlphaShield
        extends CustomCard {
    public static final String ID = "AlphaShield";
    public static final String NAME = "Alpha Shield";
    public static final String DESCRIPTION = "Gain !B! block. Remove all of your debuffs.";
    public static final String IMG_PATH = "img/BlakkDefend.png";
    private static final int COST = 4;
    private static final int BLOCK_AMT = 20;


    public AlphaShield() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION,
                CardType.SKILL, AbstractCardEnum.BLAKK_COLOR,
                CardRarity.RARE, CardTarget.SELF);
        this.baseBlock = 20;
        this.block = this.baseBlock;

    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {

            AbstractDungeon.actionManager.addToBottom(new GainBlockAction(p, p, this.block));
            AbstractDungeon.actionManager.addToBottom(new RemoveDebuffsAction(p));


    }


    @Override
    public AbstractCard makeCopy() {
        return new AlphaShield();
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.baseBlock = 30;


        }
    }
}
