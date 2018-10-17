package examplemod.cards.blakk;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import examplemod.patches.AbstractCardEnum;
import examplemod.powers.SoulPower;
import examplemod.powers.ThrowingArsenalPower;

public class SoulBloom
        extends CustomCard {
    public static final String ID = "SoulBloom";
    public static final String NAME = "Soul Bloom";
    public static final String DESCRIPTION = "Gain 1 Soul. Exhaust.";
    public static final String IMG_PATH = "img/SoulBloom.png";
    private static final int COST = 1;



    public SoulBloom() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION,
                CardType.SKILL, AbstractCardEnum.BLAKK_COLOR,
                CardRarity.SPECIAL, CardTarget.SELF);
        this.exhaust = true;

    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {

        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new SoulPower(p, 1), 1));


    }

    @Override
    public AbstractCard makeCopy() {
        return new SoulBloom();
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeBaseCost(0);

        }
    }
}
