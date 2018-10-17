package examplemod.relics;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.core.EnergyManager;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.megacrit.cardcrawl.rooms.MonsterRoomElite;


public class RappingBowl extends AbstractRelic {
    public static final String ID = "RappingBowl";


    public RappingBowl() {
        super("RappingBowl", "RappingBowl.png", RelicTier.RARE, LandingSound.HEAVY);
    }


    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0]; // DESCRIPTIONS pulls from your localization file
    }



    @Override
    public void atBattleStart() {
        int amountToGain = AbstractDungeon.player.masterDeck.size() / 5;
        if (amountToGain > 5){amountToGain = 5;}
        AbstractDungeon.actionManager.addToTop(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new DexterityPower(AbstractDungeon.player, 5-amountToGain), 5-amountToGain));
        AbstractDungeon.actionManager.addToTop(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new StrengthPower(AbstractDungeon.player, 5-amountToGain), 5-amountToGain));

        AbstractDungeon.actionManager.addToTop(new RelicAboveCreatureAction(AbstractDungeon.player, this));
    }




    @Override
    public AbstractRelic makeCopy() { // always override this method to return a new instance of your relic
        return new RappingBowl();
    }


}
