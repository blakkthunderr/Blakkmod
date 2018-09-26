package examplemod.relics;

import basemod.abstracts.CustomRelic;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.curses.Injury;
import com.megacrit.cardcrawl.cards.curses.Pride;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.EnergyManager;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.RelicStrings;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.megacrit.cardcrawl.rooms.MonsterRoomElite;
import com.megacrit.cardcrawl.rooms.TreasureRoom;
import com.megacrit.cardcrawl.unlock.UnlockTracker;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndObtainEffect;


public class GoldMedallion extends AbstractRelic {
    public static final String ID = "GoldMedallion";


    public GoldMedallion() {
        super("GoldMedallion", "GoldMedallion.png", AbstractRelic.RelicTier.BOSS, AbstractRelic.LandingSound.HEAVY);
    }


    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0]; // DESCRIPTIONS pulls from your localization file
    }

    @Override
    public void justEnteredRoom(AbstractRoom room) {
        if (room instanceof MonsterRoomElite) {
            this.flash();
            this.pulse = true;
        } else {
            this.pulse = false;
        }

    }

    @Override
    public void onVictory() {
        if (AbstractDungeon.getCurrRoom() instanceof MonsterRoomElite) {
            this.flash();
            this.pulse = false;
            AbstractDungeon.effectList.add(new ShowCardAndObtainEffect(new Pride(), (float)Settings.WIDTH / 2.0F, (float)Settings.HEIGHT / 2.0F));
            UnlockTracker.markCardAsSeen("Pride");
        }

    }
    @Override
    public void onEquip() {
        final EnergyManager energy = AbstractDungeon.player.energy;
        ++energy.energyMaster;
    }

    @Override
    public void onUnequip() {
        final EnergyManager energy = AbstractDungeon.player.energy;
        --energy.energyMaster;
    }


    @Override
    public AbstractRelic makeCopy() { // always override this method to return a new instance of your relic
        return new GoldMedallion();
    }


}
