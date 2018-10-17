package examplemod;

import basemod.helpers.RelicType;
import basemod.interfaces.*;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.evacipated.cardcrawl.modthespire.lib.SpireInitializer;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.Exordium;
import com.megacrit.cardcrawl.dungeons.TheBeyond;
import com.megacrit.cardcrawl.dungeons.TheCity;
import com.megacrit.cardcrawl.helpers.CardHelper;
import com.megacrit.cardcrawl.localization.MonsterStrings;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.localization.RelicStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.monsters.MonsterGroup;
import com.megacrit.cardcrawl.monsters.MonsterInfo;
import com.megacrit.cardcrawl.monsters.exordium.GremlinNob;
import com.megacrit.cardcrawl.rooms.AbstractRoom;


import basemod.BaseMod;
import examplemod.cards.*;
import examplemod.cards.blakk.*;
import examplemod.characters.BlakkKnight;
import examplemod.events.CoolPool;
import examplemod.events.GremlinDeck;
import examplemod.events.MonkeyBusiness;
import examplemod.monsters.*;
import examplemod.patches.AbstractCardEnum;
import examplemod.patches.MyPlayerClassEnum;
import examplemod.relics.*;

import java.nio.charset.StandardCharsets;

import static examplemod.patches.AbstractCardEnum.BLAKK_COLOR;

@SpireInitializer
public class ExampleMod implements PostExhaustSubscriber,
        PostBattleSubscriber, PostDungeonInitializeSubscriber, EditCardsSubscriber, EditRelicsSubscriber, PostInitializeSubscriber, EditStringsSubscriber, EditCharactersSubscriber {


    private static final Color BLACK = CardHelper.getColor(0.0f, 0.0f, 0.0f);

    // card backgrounds
    private static final String ATTACK_BLACK = "img/512/bg_attack_black.png";
    private static final String SKILL_BLACK = "img/512/bg_skill_black.png";
    private static final String POWER_BLACK = "img/512/bg_power_black.png";
    private static final String ENERGY_ORB_BLACK = "img/512/card_red_orb.png";

    private static final String ATTACK_BLACK_PORTRAIT = "img/1024/bg_attack_black.png";
    private static final String SKILL_BLACK_PORTRAIT = "img/1024/bg_skill_black.png";
    private static final String POWER_BLACK_PORTRAIT = "img/1024/bg_power_black.png";
    private static final String ENERGY_ORB_BLACK_PORTRAIT = "img/1024/card_red_orb.png";

    private static final String ENERGY_ORB_IN_DESCRIPTION = "img/energy/energyOrbInDescription.png";


    private static final String BLAKK_BUTTON = "img/char/Button.png";
    private static final String BLAKK_PORTRAIT = "img/char/Portrait.jpg";


    public ExampleMod() {

        BaseMod.subscribe(this);

        BaseMod.addColor(BLAKK_COLOR,
                BLACK,
                ATTACK_BLACK, SKILL_BLACK, POWER_BLACK,
                ENERGY_ORB_BLACK,
                ATTACK_BLACK_PORTRAIT, SKILL_BLACK_PORTRAIT, POWER_BLACK_PORTRAIT,
                ENERGY_ORB_BLACK_PORTRAIT, ENERGY_ORB_IN_DESCRIPTION);

    }

    public static void initialize() {
        new ExampleMod();

    }


    @Override
    public void receiveEditCharacters() {

        BaseMod.addCharacter(new BlakkKnight("Blakk Knight", MyPlayerClassEnum.BLAKK_KNIGHT),
                BLAKK_COLOR,
                BLAKK_BUTTON,
                BLAKK_PORTRAIT,
                MyPlayerClassEnum.BLAKK_KNIGHT);




    }



    private int count, totalCount;

    private void resetCounts() {
        totalCount = count = 0;
    }





    @Override
    public void receivePostExhaust(AbstractCard c) {
        count++;
        totalCount++;
    }

    @Override
    public void receivePostInitialize() {
        BaseMod.addEvent(MonkeyBusiness.ID, MonkeyBusiness.class, Exordium.ID);

        BaseMod.addEvent(CoolPool.ID, CoolPool.class, TheCity.ID);

        BaseMod.addEvent(GremlinDeck.ID, GremlinDeck.class, TheBeyond.ID);



        // Add a multi-monster encounter
        BaseMod.addMonster("RustBeast", () -> new MonsterGroup(new AbstractMonster[]{
                new RustBeast(),

        }));

        BaseMod.addBoss(Exordium.ID, "RustBeast",
                "img/RustBeastPic.png",
                "img/RustBeastPic.png");

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
        BaseMod.addCard(new SwordBurial()); //Skill
        BaseMod.addCard(new GlassHammer()); //Attack
        BaseMod.addCard(new CriticalStrike()); //Attack
        BaseMod.addCard(new LvlUp()); //Skill
        BaseMod.addCard(new BlackThunder()); //Skill
        BaseMod.addCard(new ShadowCall()); //Skill
        BaseMod.addCard(new Bloodthirst()); //power
        BaseMod.addCard(new ThrowingArsenal()); //power
        BaseMod.addCard(new SmokeBomb()); //skill
        BaseMod.addCard(new Corrode()); //skill
        BaseMod.addCard(new DeadlyAura()); //skill

        BaseMod.addCard(new FiftyTwoCardPickup()); //skill

        BaseMod.addCard(new Rust()); //curse

        ///blakk cards
        BaseMod.addCard(new BlakkStrike()); //Attack
        BaseMod.addCard(new BlakkDefend()); //Skill
        BaseMod.addCard(new SoulStrike()); //Attack
        BaseMod.addCard(new LegSlice()); //Attack
        BaseMod.addCard(new SoulScythe()); //Attack
        BaseMod.addCard(new SoulBloom()); //Skill
        BaseMod.addCard(new SoulCloak()); //Skill
        BaseMod.addCard(new ReverseShield()); //Skill
        BaseMod.addCard(new ReverseWounds()); //Skill
        BaseMod.addCard(new ReverseBlade()); //Attack
        BaseMod.addCard(new SoulCreate()); //Power
        BaseMod.addCard(new GlassSkin()); //Power
        BaseMod.addCard(new SoulDrain()); //Skill
        BaseMod.addCard(new WallOfSouls()); //Skill
        BaseMod.addCard(new DoubleEdgedSword()); //Attack
        BaseMod.addCard(new OmegaBlade()); //Attack
        BaseMod.addCard(new SoulHarvest()); //Skill
        BaseMod.addCard(new AllOutBlow()); //Attack
        BaseMod.addCard(new ShadowDodge()); //Skill
        BaseMod.addCard(new ShadowBlade()); //Attack
        BaseMod.addCard(new SpeedBlade()); //Attack
        BaseMod.addCard(new SoulLabor()); //Skill
        BaseMod.addCard(new FullDefend()); //Skill
        BaseMod.addCard(new AlphaShield()); //Skill
        BaseMod.addCard(new DarkStep()); //Skill




    }

    @Override
    public void receiveEditRelics(){

        BaseMod.addRelic(new GoldMedallion(), RelicType.SHARED);
        BaseMod.addRelic(new Megaphone(), RelicType.SHARED);
        BaseMod.addRelic(new YinYang(), RelicType.SHARED);
        BaseMod.addRelic(new TheDonut(), RelicType.SHARED);
        BaseMod.addRelic(new NeowsEye(), RelicType.SHARED);
        BaseMod.addRelic(new BlakkSword(), RelicType.SHARED);

        BaseMod.addRelic(new Ruby(), RelicType.RED);
        BaseMod.addRelic(new Emerald(), RelicType.GREEN);
        BaseMod.addRelic(new Sapphire(), RelicType.BLUE);

        BaseMod.addRelic(new TwilightShard(), RelicType.SHARED);
        BaseMod.addRelic(new MonkeysToyBox(), RelicType.SHARED);
        BaseMod.addRelic(new ChipBag(), RelicType.SHARED);
        BaseMod.addRelic(new RappingBowl(), RelicType.SHARED);
        BaseMod.addRelic(new CowardCrown(), RelicType.SHARED);
        BaseMod.addRelic(new CigarettePack(), RelicType.SHARED);

        BaseMod.addRelicToCustomPool(new LostSoul(), BLAKK_COLOR);
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



