package examplemod.events;

import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.curses.Injury;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.events.AbstractImageEvent;
import com.megacrit.cardcrawl.helpers.MonsterHelper;
import com.megacrit.cardcrawl.helpers.RelicLibrary;
import com.megacrit.cardcrawl.unlock.UnlockTracker;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndObtainEffect;
import examplemod.cards.Rust;
import examplemod.relics.MonkeysToyBox;


public class CoolPool extends AbstractImageEvent {

    private int healAmt;

    //This isn't technically needed but it becomes useful later
    public static final String ID = "Cool Pool";



    public CoolPool(){
        super(ID, "You come across a nice looking pool built into the Spire. Going in would be refreshing, however it may not work out well for your gear.", "img/poolevent.png");
        if (AbstractDungeon.ascensionLevel >= 15) {
            this.healAmt = 40;
        } else {
            this.healAmt = 30;
        }

        //This is where you would create your dialog options
        this.imageEventText.setDialogOption("[Swim] Heal 40 HP. Cursed - Rust."); //This adds the option to a list of options
        this.imageEventText.setDialogOption("[Leave]");
    }

    @Override
    protected void buttonEffect(int buttonPressed) {


                if (buttonPressed == 0) {


                    AbstractDungeon.player.heal(this.healAmt);

                    AbstractDungeon.effectList.add(new ShowCardAndObtainEffect(new Rust(), (float) Settings.WIDTH / 2.0F, (float)Settings.HEIGHT / 2.0F));
                    UnlockTracker.markCardAsSeen("Rust");

                    this.openMap();

                } else {

                    this.openMap();

                }






    }
}
