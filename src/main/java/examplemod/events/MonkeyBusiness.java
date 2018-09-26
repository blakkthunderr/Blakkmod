package examplemod.events;

import com.megacrit.cardcrawl.cards.curses.Doubt;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.events.AbstractImageEvent;
import com.megacrit.cardcrawl.events.city.Colosseum;
import com.megacrit.cardcrawl.events.city.MaskedBandits;
import com.megacrit.cardcrawl.events.exordium.Sssserpent;
import com.megacrit.cardcrawl.helpers.MonsterHelper;
import com.megacrit.cardcrawl.helpers.RelicLibrary;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.unlock.UnlockTracker;
import com.megacrit.cardcrawl.vfx.RainingGoldEffect;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndObtainEffect;
import examplemod.relics.MonkeysToyBox;
import examplemod.relics.TwilightShard;

public class MonkeyBusiness extends AbstractImageEvent {


    //This isn't technically needed but it becomes useful later
    public static final String ID = "Monkey Business";

    public MonkeyBusiness(){
        super(ID, "A strange monkey swinging from place to place stops to hang out. 'Hey There! Wanna see something cooooool?' The monkey stares at you from a distance, and begins visibly mutating.", "img/monkeyevent.png");


        //This is where you would create your dialog options
        this.imageEventText.setDialogOption("[Look]"); //This adds the option to a list of options
        this.imageEventText.setDialogOption("[Leave]");
    }

    @Override
    protected void buttonEffect(int buttonPressed) {


                if (buttonPressed == 0) {


                    AbstractDungeon.getCurrRoom().monsters = MonsterHelper.getEncounter("Monkey");
                    AbstractDungeon.getCurrRoom().rewards.clear();
                    AbstractDungeon.getCurrRoom().addRelicToRewards(RelicLibrary.getRelic(MonkeysToyBox.ID).makeCopy());
                    this.enterCombatFromImage();
                    AbstractDungeon.lastCombatMetricKey = "Monkey";


                } else {

                    this.openMap();

                }






    }
}
