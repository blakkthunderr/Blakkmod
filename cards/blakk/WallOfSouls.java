package examplemod.cards.blakk;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.ArtifactPower;
import com.megacrit.cardcrawl.powers.WeakPower;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;
import examplemod.patches.AbstractCardEnum;
import examplemod.powers.SoulPower;

public class WallOfSouls
        extends CustomCard {
    public static final String ID = "WallOfSouls";
    public static final String NAME = "Wall of Souls";
    public static final String DESCRIPTION = "Gain !B! block X times. Lose X Souls.";
    public static final String IMG_PATH = "img/BlakkDefend.png";
    private static final int COST = -1;
    private static final int BLOCK_AMT = 5;


    public WallOfSouls() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION,
                CardType.SKILL, AbstractCardEnum.BLAKK_COLOR,
                CardRarity.UNCOMMON, CardTarget.SELF);
        this.baseBlock = 12;
        this.block = this.baseBlock;

    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        if (this.energyOnUse < EnergyPanel.totalCount) {
            this.energyOnUse = EnergyPanel.totalCount;
        }

        int i;
        for (i = 0; i < this.energyOnUse; ++i) {
            if (p.hasPower (SoulPower.POWER_ID)){
            this.magicNumber = (p.getPower(SoulPower.POWER_ID).amount);
            if (this.magicNumber >= 1) {
                AbstractDungeon.actionManager.addToBottom(new GainEnergyAction(-1));

                AbstractDungeon.actionManager.addToBottom(new GainBlockAction(p, p, this.block));
                AbstractDungeon.actionManager.addToBottom(new ReducePowerAction(p,p,SoulPower.POWER_ID,1));

            }
            }

        }

    }


    @Override
    public AbstractCard makeCopy() {
        return new WallOfSouls();
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.baseBlock = 15;
            this.rawDescription = "Gain !B! block X times. Lose X Souls.";
            this.initializeDescription();

        }
    }
}
