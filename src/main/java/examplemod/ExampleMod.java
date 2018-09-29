package examplemod;

import basemod.helpers.RelicType;
import com.badlogic.gdx.Gdx;
import com.evacipated.cardcrawl.modthespire.lib.SpireInitializer;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.Exordium;
import com.megacrit.cardcrawl.dungeons.TheBeyond;
import com.megacrit.cardcrawl.dungeons.TheCity;
import com.megacrit.cardcrawl.localization.MonsterStrings;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.localization.RelicStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.monsters.MonsterGroup;
import com.megacrit.cardcrawl.monsters.MonsterInfo;
import com.megacrit.cardcrawl.monsters.city.Healer;
import com.megacrit.cardcrawl.monsters.city.Snecko;
import com.megacrit.cardcrawl.monsters.exordium.GremlinNob;
import com.megacrit.cardcrawl.rooms.AbstractRoom;

import basemod.BaseMod;
import basemod.interfaces.PostBattleSubscriber;
import basemod.interfaces.PostDungeonInitializeSubscriber;
import basemod.interfaces.PostInitializeSubscriber;
import basemod.interfaces.PostExhaustSubscriber;
import basemod.interfaces.EditCardsSubscriber;
import basemod.interfaces.EditStringsSubscriber;
import basemod.interfaces.EditRelicsSubscriber;
import examplemod.cards.*;
import examplemod.events.MonkeyBusiness;
import examplemod.monsters.*;
import examplemod.relics.*;

import java.nio.charset.StandardCharsets;

@SpireInitializer
public class ExampleMod implements PostExhaustSubscriber,
        PostBattleSubscriber, PostDungeonInitializeSubscriber, EditCardsSubscriber, EditRelicsSubscriber, PostInitializeSubscriber, EditStringsSubscriber {

    private int count, totalCount;

    private void resetCounts() {
        totalCount = count = 0;
    }

    public ExampleMod() {
        BaseMod.subscribe(this);
        resetCounts();
    }

    public static void initialize() {
        new ExampleMod();

    }

    @Override
    public void receivePostExhaust(AbstractCard c) {
        count++;
        totalCount++;
    }

    @Override
    public void receivePostInitialize() {
        BaseMod.addEvent(MonkeyBusiness.ID, MonkeyBusiness.class, Exordium.ID);

        // Add a multi-monster encounter
        BaseMod.addMonster("LordSnecko", () -> new MonsterGroup(new AbstractMonster[]{
                new Snecko(),
                new Healer(-300, 0)
        }));

        BaseMod.addBoss(Exordium.ID, "LordSnecko",
                "img/LordSnecko.png",
                "img/LordSnecko.png");

        // Add a multi-monster encounter
        BaseMod.addMonster("GremlinKing", () -> new MonsterGroup(new AbstractMonster[]{
                new GremlinKing(),
                new GremlinNob(-300, 0),
                new GremlinNob(+300, 0)
        }));

        BaseMod.addBoss(TheCity.ID, "GremlinKing",
                "img/GremlinKingPic.png",
                "img/GremlinKingPic.png");


        BaseMod.addMonster("Monkey", () -> new MonsterGroup(new AbstractMonster[]{
                new Monkey(),

        }));

        BaseMod.addMonster("Pilcrow", () -> new MonsterGroup(new AbstractMonster[]{
                new Pilcrow(),

        }));

        BaseMod.addMonster("Paranoia", () -> new MonsterGroup(new AbstractMonster[]{
                new Paranoia(),

        }));


        BaseMod.addMonsterEncounter(Exordium.ID, new MonsterInfo("Monkey", 0));

        BaseMod.addEliteEncounter(TheCity.ID, new MonsterInfo("Pilcrow", 1));


        BaseMod.addEliteEncounter(TheBeyond.ID, new MonsterInfo("Paranoia", 1));

        BaseMod.addMonster("TwilightGuardian", () -> new MonsterGroup(new AbstractMonster[]{
                new TwilightGuardian(),

        }));


        BaseMod.addBoss(TheBeyond.ID, "TwilightGuardian",
                "img/TwilightGuardianPic.png",
                "img/TwilightGuardianPic.png");

    }



    @Override
    public void receivePostBattle(AbstractRoom r) {
        System.out.println(count + " cards were exhausted this battle, " +
                totalCount + " cards have been exhausted so far this act.");
        count = 0;
    }

    @Override
    public void receivePostDungeonInitialize() {
        resetCounts();
    }



    public void receiveEditCards() {
        //BASIC
        BaseMod.addCard(new DualStrike()); //Attack
        BaseMod.addCard(new GlassCannon()); //Attack
        BaseMod.addCard(new GlassHammer()); //Attack
        BaseMod.addCard(new CriticalStrike()); //Attack
        BaseMod.addCard(new LvlUp()); //Skill
        BaseMod.addCard(new ShadowCall()); //Skill
        BaseMod.addCard(new Bloodthirst()); //power
        BaseMod.addCard(new ThrowingArsenal()); //power
        BaseMod.addCard(new DeadlyAura()); //skill

        BaseMod.addCard(new Rust()); //curse

    }

    @Override
    public void receiveEditRelics(){

        BaseMod.addRelic(new GoldMedallion(), RelicType.SHARED);
        BaseMod.addRelic(new Megaphone(), RelicType.SHARED);
        BaseMod.addRelic(new YinYang(), RelicType.SHARED);
        BaseMod.addRelic(new TheDonut(), RelicType.SHARED);

        BaseMod.addRelic(new Ruby(), RelicType.RED);
        BaseMod.addRelic(new Emerald(), RelicType.GREEN);
        BaseMod.addRelic(new Sapphire(), RelicType.BLUE);

        BaseMod.addRelic(new TwilightShard(), RelicType.SHARED);
        BaseMod.addRelic(new MonkeysToyBox(), RelicType.SHARED);
        BaseMod.addRelic(new ChipBag(), RelicType.SHARED);
        BaseMod.addRelic(new RappingBowl(), RelicType.SHARED);
        BaseMod.addRelic(new CowardCrown(), RelicType.SHARED);
        BaseMod.addRelic(new CigarettePack(), RelicType.SHARED);
}

    @Override
    public void receiveEditStrings() {

        //RelicStrings
        String relicStrings = Gdx.files.internal("relicStrings.json").readString(
                String.valueOf(StandardCharsets.UTF_8));
        BaseMod.loadCustomStrings(RelicStrings.class, relicStrings);

        //PowerStrings
        String powerStrings = Gdx.files.internal("powerStrings.json").readString(
                String.valueOf(StandardCharsets.UTF_8));
        BaseMod.loadCustomStrings(PowerStrings.class, powerStrings);

        //monsterStrings
        String monsterStrings = Gdx.files.internal("monsterStrings.json").readString(
                String.valueOf(StandardCharsets.UTF_8));
        BaseMod.loadCustomStrings(MonsterStrings.class, monsterStrings);



    }

}
