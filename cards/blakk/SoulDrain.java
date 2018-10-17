package examplemod.cards.blakk;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.unique.MalaiseAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.FrailPower;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.megacrit.cardcrawl.powers.WeakPower;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;
import examplemod.patches.AbstractCardEnum;

public class SoulDrain
        extends CustomCard {
    public static final String ID = "SoulDrain";
    public static final String NAME = "Soul Drain";
    public static final String DESCRIPTION = "Apply X Weak, Vulnerable, and Frail to you and the target. Exhaust.";
    public static final String IMG_PATH = "img/SoulBloom.png";
    private static final int COST = -1;



    public SoulDrain() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION,
                CardType.SKILL, AbstractCardEnum.BLAKK_COLOR,
                CardRarity.RARE, CardTarget.SELF_AND_ENEMY);
        this.baseMagicNumber = 0;
        this.magicNumber = this.baseMagicNumber;

        this.exhaust = true;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {


            if (this.energyOnUse < EnergyPanel.totalCount) {
                this.energyOnUse = EnergyPanel.totalCount;
            }
            this.energyOnUse += this.magicNumber;
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(m, p, new WeakPower(m, this.energyOnUse, false), this.energyOnUse));
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(m, p, new VulnerablePower(m, this.energyOnUse, false), this.energyOnUse));

        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new WeakPower(p, this.energyOnUse, false), this.energyOnUse));
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new FrailPower(p, this.energyOnUse, false), this.energyOnUse));
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new VulnerablePower(p, this.energyOnUse, false), this.energyOnUse));
        AbstractDungeon.actionManager.addToBottom(new GainEnergyAction(-this.energyOnUse));


    }

    @Override
    public AbstractCard makeCopy() {
        return new SoulDrain();
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.rawDescription = "Apply X+1 Weak, Vulnerable, and Frail to you and the target. Exhaust.";
            this.baseMagicNumber = 1;

            this.initializeDescription();

        }
    }
}
