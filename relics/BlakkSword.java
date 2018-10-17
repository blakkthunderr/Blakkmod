package examplemod.relics;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.megacrit.cardcrawl.rooms.MonsterRoomElite;


public class BlakkSword extends AbstractRelic {
    public static final String ID = "BlakkSword";


    public BlakkSword() {
        super("BlakkSword", "BlakkSword.png", RelicTier.BOSS, LandingSound.MAGICAL);
    }


    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0]; // DESCRIPTIONS pulls from your localization file
    }
    @Override
    public void onEquip() {
        this.counter = 3;
    }

    @Override
    public void atBattleStart()
    {
        if (counter <= 0) {
            return;
        }
        else{
            if (AbstractDungeon.getCurrRoom() != null && AbstractDungeon.getCurrRoom().phase == AbstractRoom.RoomPhase.COMBAT) {
                this.flash();

                for (int i = 0; i < this.counter; ++i) {
                    AbstractDungeon.actionManager.addToBottom(new RelicAboveCreatureAction(AbstractDungeon.player, this));
                    AbstractDungeon.actionManager.addToBottom(new DamageAllEnemiesAction((AbstractCreature) null, DamageInfo.createDamageMatrix(10, true), DamageInfo.DamageType.THORNS, AbstractGameAction.AttackEffect.SLASH_HEAVY));
                }
                this.counter -= 1;
            }
        }
    }

    public void onVictory() {
        if (AbstractDungeon.getCurrRoom() instanceof MonsterRoomElite) {
            this.counter += 3;
        }
        else{

        }
    }

        @Override
    public AbstractRelic makeCopy() { // always override this method to return a new instance of your relic
        return new BlakkSword();
    }


}
