package examplemod.relics;

import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.relics.AbstractRelic;

import java.util.Iterator;


public class CigarettePack extends AbstractRelic {
    public static final String ID = "CigarettePack";
    int timer = 1000;

    public CigarettePack() {
        super("CigarettePack", "CigarettePack.png", RelicTier.SHOP, LandingSound.HEAVY);
    }


    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0]; // DESCRIPTIONS pulls from your localization file
    }

    public void onEquip() {
        AbstractDungeon.player.increaseMaxHp(-5, true);
    }

    public void atBattleStart() {
        Iterator var1 = AbstractDungeon.getMonsters().monsters.iterator();

        while(var1.hasNext()) {
            AbstractMonster m = (AbstractMonster)var1.next();
            AbstractDungeon.actionManager.addToTop(new RelicAboveCreatureAction(m, this));
            m.addPower(new StrengthPower(m, -1));
        }

    }



    @Override
    public AbstractRelic makeCopy() { // always override this method to return a new instance of your relic
        return new CigarettePack();
    }


}
