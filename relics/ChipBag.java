package examplemod.relics;

import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;


public class ChipBag extends AbstractRelic {
    public static final String ID = "ChipBag";


    public ChipBag() {
        super("ChipBag", "ChipBag.png", RelicTier.SHOP, LandingSound.SOLID);
    }


    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0]; // DESCRIPTIONS pulls from your localization file
    }
    @Override
    public void onEquip() {
        this.counter = 10;
    }


    public void atBattleStart() {
        if (this.counter > 0) {
            this.counter -= 1;
            this.flash();
            this.flash();
            AbstractDungeon.actionManager.addToTop(new RelicAboveCreatureAction(AbstractDungeon.player, this));
            AbstractDungeon.actionManager.addToTop(new HealAction(AbstractDungeon.player, AbstractDungeon.player, 4));
        }

    }


    @Override
    public AbstractRelic makeCopy() { // always override this method to return a new instance of your relic
        return new ChipBag();
    }


}
