package examplemod.relics;

import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.rewards.RewardItem;


public class NeowsEye extends AbstractRelic {
    public static final String ID = "NeowsEye";

    public NeowsEye() {
        super("NeowsEye", "NeowsEye.png", RelicTier.BOSS, LandingSound.HEAVY);
    }


    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0]; // DESCRIPTIONS pulls from your localization file
    }

    public void onEquip() {

        AbstractDungeon.combatRewardScreen.open();
        AbstractDungeon.combatRewardScreen.rewards.clear();
        AbstractDungeon.combatRewardScreen.rewards.add(new RewardItem(AbstractDungeon.returnRandomScreenlessRelic(RelicTier.RARE)));
        AbstractDungeon.combatRewardScreen.positionRewards();
        AbstractDungeon.overlayMenu.proceedButton.setLabel("Continue");
    }





    @Override
    public AbstractRelic makeCopy() { // always override this method to return a new instance of your relic
        return new NeowsEye();
    }


}
