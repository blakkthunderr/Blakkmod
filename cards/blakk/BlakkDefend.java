package examplemod.cards.blakk;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import examplemod.patches.AbstractCardEnum;

public class BlakkDefend
        extends CustomCard {
    public static final String ID = "BlakkDefend";
    public static final String NAME = "Defend";
    public static final String DESCRIPTION = "Gain !B! block.";
    public static final String IMG_PATH = "img/BlakkDefend.png";
    private static final int COST = 1;
    private static final int BLOCK_AMT = 5;


    public BlakkDefend() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION,
                CardType.SKILL, AbstractCardEnum.BLAKK_COLOR,
                CardRarity.SPECIAL, CardTarget.SELF);
        this.baseBlock = 5;

    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        if (Settings.isDebug) {
            AbstractDungeon.actionManager.addToBottom(new GainBlockAction(p, p, 50));
        } else {
            AbstractDungeon.actionManager.addToBottom(new GainBlockAction(p, p, this.block));
        }

    }


    @Override
    public AbstractCard makeCopy() {
        return new BlakkDefend();
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.baseBlock = 8;
            this.rawDescription = "Gain !B! block.";
            this.initializeDescription();

        }
    }
}
