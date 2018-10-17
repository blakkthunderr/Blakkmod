package examplemod.cards.blakk;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.defect.ChannelAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.orbs.Frost;
import examplemod.patches.AbstractCardEnum;
import examplemod.powers.SoulPower;

import java.util.Iterator;

public class SoulHarvest
        extends CustomCard {
    public static final String ID = "SoulHarvest";
    public static final String NAME = "Soul Harvest";
    public static final String DESCRIPTION = "Innate. Gain Souls equal to the amount of enemies on the field. Exhaust.";
    public static final String IMG_PATH = "img/SoulBloom.png";
    private static final int COST = 0;



    public SoulHarvest() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION,
                CardType.SKILL, AbstractCardEnum.BLAKK_COLOR,
                CardRarity.RARE, CardTarget.SELF);
        this.baseMagicNumber = 1;
        this.magicNumber = this.baseMagicNumber;
        this.isInnate = true;
        this.exhaust = true;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        int count = 0;
        Iterator var4 = AbstractDungeon.getMonsters().monsters.iterator();

        while(var4.hasNext()) {
            AbstractMonster mon = (AbstractMonster)var4.next();
            if (!mon.isDeadOrEscaped()) {
                ++count;
            }
        }

        for(int i = 0; i < count; ++i) {
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new SoulPower(p, this.magicNumber), this.magicNumber));
        }




    }

    @Override
    public AbstractCard makeCopy() {
        return new SoulHarvest();
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.rawDescription = "Innate. Gain Souls equal to 2 times the amount of enemies on the field. Exhaust.";
            this.baseMagicNumber = 2;
            this.initializeDescription();

        }
    }
}
