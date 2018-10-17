package examplemod.cards.blakk;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDiscardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.status.Void;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import examplemod.patches.AbstractCardEnum;

public class ShadowDodge
        extends CustomCard {
    public static final String ID = "ShadowDodge";
    public static final String NAME = "Shadow Dodge";
    public static final String DESCRIPTION = "Gain !B! block. Add a Void into your discard pile.";
    public static final String IMG_PATH = "img/BlakkDefend.png";
    private static final int COST = 1;
    private static final int BLOCK_AMT = 10;


    public ShadowDodge() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION,
                CardType.SKILL, AbstractCardEnum.BLAKK_COLOR,
                CardRarity.COMMON, CardTarget.SELF);
        this.baseBlock = 10;

    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {

            AbstractDungeon.actionManager.addToBottom(new GainBlockAction(p, p, this.block));
            AbstractDungeon.actionManager.addToBottom(new MakeTempCardInDiscardAction(new Void(), 1));


    }


    @Override
    public AbstractCard makeCopy() {
        return new ShadowDodge();
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.baseBlock = 13;


        }
    }
}
