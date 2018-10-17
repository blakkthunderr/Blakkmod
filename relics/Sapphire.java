package examplemod.relics;

import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;


public class Sapphire extends AbstractRelic {
    public static final String ID = "Sapphire";


    public Sapphire() {
        super("Sapphire", "Sapphire.png", RelicTier.RARE, LandingSound.MAGICAL);
    }


    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0]; // DESCRIPTIONS pulls from your localization file
    }



    public void onUseCard(AbstractCard card, UseCardAction action) {
        if (card.type == AbstractCard.CardType.POWER) {
            ++this.counter;
            if (this.counter == 4) {
                this.counter = 0;
                this.flash();
                AbstractDungeon.actionManager.addToBottom(new RelicAboveCreatureAction(AbstractDungeon.player, this));
                AbstractCard c = AbstractDungeon.returnTrulyRandomCardInCombat(AbstractCard.CardType.POWER).makeCopy();

                AbstractDungeon.actionManager.addToBottom(new MakeTempCardInHandAction(c, true));
                AbstractDungeon.actionManager.addToBottom(new GainEnergyAction(2));
            }
        }

    }


    @Override
    public AbstractRelic makeCopy() { // always override this method to return a new instance of your relic
        return new Sapphire();
    }


}
