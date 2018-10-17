package examplemod.relics;

import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.relics.WarpedTongs;
import com.megacrit.cardcrawl.rewards.RewardItem;


public class MonkeysToyBox extends AbstractRelic {
    public static final String ID = "MonkeysToyBox";
    int timer = 1000;

    public MonkeysToyBox() {
        super("MonkeysToyBox", "MonkeysToyBox.png", RelicTier.SPECIAL, LandingSound.HEAVY);
    }


    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0]; // DESCRIPTIONS pulls from your localization file
    }

    public void onEquip() {

        AbstractDungeon.getCurrRoom().spawnRelicAndObtain((float)(Settings.WIDTH / 2), (float)(Settings.HEIGHT / 2), AbstractDungeon.returnRandomRelic(RelicTier.COMMON));
        AbstractDungeon.getCurrRoom().spawnRelicAndObtain((float)(Settings.WIDTH / 2), (float)(Settings.HEIGHT / 2), AbstractDungeon.returnRandomRelic(RelicTier.COMMON));
        AbstractDungeon.getCurrRoom().spawnRelicAndObtain((float)(Settings.WIDTH / 2), (float)(Settings.HEIGHT / 2), AbstractDungeon.returnRandomRelic(RelicTier.COMMON));

    }





    @Override
    public AbstractRelic makeCopy() { // always override this method to return a new instance of your relic
        return new MonkeysToyBox();
    }


}
