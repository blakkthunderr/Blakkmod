package examplemod.relics;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import examplemod.powers.SoulPower;


public class LostSoul extends AbstractRelic {
    public static final String ID = "LostSoul";


    public LostSoul() {
        super("LostSoul", "LostSoul.png", RelicTier.STARTER, LandingSound.HEAVY);
    }


    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0]; // DESCRIPTIONS pulls from your localization file
    }



    @Override
    public void atBattleStart() {

        AbstractDungeon.actionManager.addToTop(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new SoulPower(AbstractDungeon.player, 1), 1));

    }




    @Override
    public AbstractRelic makeCopy() { // always override this method to return a new instance of your relic
        return new LostSoul();
    }


}
