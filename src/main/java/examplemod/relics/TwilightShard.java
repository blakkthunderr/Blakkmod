package examplemod.relics;

import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;


public class TwilightShard extends AbstractRelic {
    public static final String ID = "TwilightShard";


    public TwilightShard() {
        super("TwilightShard", "TwilightShard.png", RelicTier.UNCOMMON, LandingSound.MAGICAL);
    }


    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0]; // DESCRIPTIONS pulls from your localization file
    }
    @Override
    public void onEquip() {
        this.counter = 20;
    }
    @Override
    public void atTurnStart() {
        if (this.counter > 0) {
            this.counter -= 1;
            this.flash();
            AbstractDungeon.actionManager.addToBottom(new RelicAboveCreatureAction(AbstractDungeon.player, this));
            AbstractDungeon.actionManager.addToBottom(new GainEnergyAction(1));
        }


    }


    @Override
    public AbstractRelic makeCopy() { // always override this method to return a new instance of your relic
        return new TwilightShard();
    }


}
