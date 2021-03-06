package examplemod.actions;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndAddToHandEffect;

public class MessyHandAction extends com.megacrit.cardcrawl.actions.AbstractGameAction {
    private AbstractCard cardToMake;

    public MessyHandAction(AbstractCreature target, AbstractCreature source, AbstractCard card,
                           int amount) {
        setValues(target, source, amount);
        this.actionType = ActionType.CARD_MANIPULATION;
        this.duration = 0.5F;
        this.cardToMake = card;
    }

    public void update() {


            if (this.amount < 6) {
                for (int i = 0; i < this.amount; i++) {
                    AbstractCard c = this.cardToMake.makeStatEquivalentCopy();
                    if (!c.exhaust) {
                        c.exhaust = true;
                        c.rawDescription = "Exhaust. " + c.rawDescription;
                        c.initializeDescription();
                    }
                    AbstractDungeon.effectList.add(new ShowCardAndAddToHandEffect(c, Settings.WIDTH / 2.0F - (AbstractCard.IMG_WIDTH * 0.5F), Settings.HEIGHT / 2.0F));


                }


            this.duration -= com.badlogic.gdx.Gdx.graphics.getDeltaTime();
        }

        tickDuration();
    }
}
