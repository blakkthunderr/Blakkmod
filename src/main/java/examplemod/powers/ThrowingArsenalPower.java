//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package examplemod.powers;


import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDiscardAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.*;


public class ThrowingArsenalPower extends AbstractPower
{
    public static final String POWER_ID = "ThrowingArsenal";
    private static final PowerStrings powerStrings;
    public static final String NAME;
    public static final String[] DESCRIPTIONS;
    private int baseamount;

    public ThrowingArsenalPower(AbstractCreature owner, int newAmount) {
        this.name = NAME;
        this.ID = "ThrowingArsenal";
        this.owner = owner;
        this.amount = newAmount;
        this.updateDescription();
        this.img = ImageMaster.loadImage("img/ThrowingArsenalPower.png");
    }

    public void updateDescription() {
        this.description = DESCRIPTIONS[0];
    }





    /*public void stackPower(int stackAmount) {
        this.fontScale = 8.0F;
        this.amount += stackAmount;

    }*/

    public void onUseCard(AbstractCard card, UseCardAction action) {
        if (card.type == AbstractCard.CardType.ATTACK) {
            if (card.exhaust || card.exhaustOnUseOnce) {
                this.flash();

                AbstractDungeon.actionManager.addToBottom(new MakeTempCardInDrawPileAction(card.makeStatEquivalentCopy(), 1, true, false));
            }
        }

    }



    static {
        powerStrings = CardCrawlGame.languagePack.getPowerStrings("ThrowingArsenal");
        NAME = powerStrings.NAME;
        DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    }
}
