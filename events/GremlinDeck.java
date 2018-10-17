package examplemod.events;

import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.events.AbstractImageEvent;
import com.megacrit.cardcrawl.localization.EventStrings;
import com.megacrit.cardcrawl.unlock.UnlockTracker;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndObtainEffect;
import examplemod.cards.FiftyTwoCardPickup;
import examplemod.cards.Rust;


public class GremlinDeck extends AbstractImageEvent {
    public static final String ID = "Gremlin Deck";

    private int screenNum = 0;

    public GremlinDeck() {
        super(ID, "Out of nowhere, you stumble upon a deck of cards. Perhaps it belongs to a certain sneaky gremlin.. You could take it, probably.", "img/DeckEvent.png");


        //This is where you would create your dialog options
        this.imageEventText.setDialogOption("[Snatch] Take 52 Damage. Obtain the deck."); //This adds the option to a list of options
        this.imageEventText.setDialogOption("[Ignore]");
    }

    public void onEnterRoom() {
        CardCrawlGame.music.playTempBGM("SHRINE");
    }


    @Override
    protected void buttonEffect(int buttonPressed) {
        switch (this.screenNum) {
            case 0:
                switch (buttonPressed) {
                    case 0:
                        this.imageEventText.updateBodyText("Upon picking up the deck of 52 cards, you receive a paper cut on every single one. How annoying..");
                        this.imageEventText.updateDialogOption(0, "[Leave]");
                        this.imageEventText.clearRemainingOptions();
                        AbstractDungeon.effectList.add(new ShowCardAndObtainEffect(new FiftyTwoCardPickup(), (float) Settings.WIDTH / 2.0F, (float)Settings.HEIGHT / 2.0F));
                        AbstractDungeon.player.damage(new DamageInfo((AbstractCreature)null, 52, DamageInfo.DamageType.HP_LOSS));

                        this.screenNum = 2;

                        return;
                    case 1:
                        this.screenNum = 2;
                        this.imageEventText.updateBodyText("You ignore the deck.");
                        this.imageEventText.updateDialogOption(0, "[Leave]");
                        this.imageEventText.clearRemainingOptions();

                        return;
                    default:
                        return;
                }
            case 1:
                this.screenNum = 2;
                break;
            case 2:
                this.openMap();
        }

    }

}

