package examplemod.characters;

import basemod.abstracts.CustomPlayer;
import examplemod.cards.blakk.BlakkDefend;
import examplemod.cards.blakk.BlakkStrike;
import examplemod.cards.blakk.SoulBloom;
import examplemod.cards.blakk.SoulStrike;
import examplemod.patches.MyPlayerClassEnum;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.MathUtils;
import com.esotericsoftware.spine.AnimationState;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.EnergyManager;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.helpers.ScreenShake;
import com.megacrit.cardcrawl.screens.CharSelectInfo;
import examplemod.relics.LostSoul;
import examplemod.relics.RappingBowl;

import java.util.ArrayList;

public class BlakkKnight extends CustomPlayer {

    private static final int ENERGY_PER_TURN = 3;

    private static final String BLACKBEARD_SKELETON_ATLAS_PATH = "img/char/skeleton.atlas";
    private static final String BLACKBEARD_SKELETON_JSON_PATH = "img/char/skeleton.json";
    private static final String BLACKBEARD_SHOULDER_1 = "img/char/shoulder.png";
    private static final String BLACKBEARD_SHOULDER_2 = "img/char/shoulder2.png";
    private static final String BLACKBEARD_CORPSE = "img/char/corpse.png";


    public BlakkKnight(String name, PlayerClass setClass) {
        super(name, setClass, null, null, (String) null, null);

        initializeClass(null, BLACKBEARD_SHOULDER_2, BLACKBEARD_SHOULDER_1, BLACKBEARD_CORPSE,
                getLoadout(), 20.0F, -10.0F, 220.0F, 290.0F, new EnergyManager(ENERGY_PER_TURN));

        this.loadAnimation(BLACKBEARD_SKELETON_ATLAS_PATH, BLACKBEARD_SKELETON_JSON_PATH, 1.0f);
        AnimationState.TrackEntry e = this.state.setAnimation(0, "animation", true);
        e.setTime(e.getEndTime() * MathUtils.random());
    }

    public ArrayList<String> getStartingDeck() {
        ArrayList<String> startingDeck = new ArrayList<>();
        startingDeck.add(BlakkStrike.ID);
        startingDeck.add(BlakkStrike.ID);
        startingDeck.add(BlakkStrike.ID);
        startingDeck.add(BlakkStrike.ID);
        startingDeck.add(BlakkStrike.ID);
        startingDeck.add(BlakkDefend.ID);
        startingDeck.add(BlakkDefend.ID);
        startingDeck.add(BlakkDefend.ID);
        startingDeck.add(BlakkDefend.ID);
        startingDeck.add(BlakkDefend.ID);

        startingDeck.add(SoulBloom.ID);

        return startingDeck;
    }

    public ArrayList<String> getStartingRelics() {
        ArrayList<String> startingRelics = new ArrayList<>();
        startingRelics.add(LostSoul.ID);

        return startingRelics;
    }

    public CharSelectInfo getLoadout() {
        return new CharSelectInfo("Blakk Knight",
                "A   NL " +
                        "Ts",
                80, 80, 0, 99, 5,
                this, getStartingRelics(), getStartingDeck(), false);
    }


    @Override
    public String getTitle(PlayerClass playerClass) {
        return "Blakk Knight";
    }

    @Override
    public AbstractCard getStartCardForEvent() {
        return new SoulBloom();
    }

    @Override
    public Color getCardColor() {
        return Color.BLACK;
    }


    @Override
    public Color getCardTrailColor() {
        return Color.BLACK;
    }

    @Override
    public int getAscensionMaxHPLoss() {
        return 4;
    }

    @Override
    public BitmapFont getEnergyNumFont() {
        return FontHelper.energyNumFontRed;
    }

    @Override
    public void doCharSelectScreenSelectEffect() {
        CardCrawlGame.sound.playA("ATTACK_HEAVY", MathUtils.random(-0.2f, 0.2f));
        CardCrawlGame.screenShake.shake(ScreenShake.ShakeIntensity.MED, ScreenShake.ShakeDur.SHORT, true);
    }

    @Override
    public String getCustomModeCharacterButtonSoundKey() {
        return "ATTACK_HEAVY";
    }

    @Override
    public String getLocalizedCharacterName() {
        return "Blakk Knight";
    }

    @Override
    public AbstractPlayer newInstance() {
        return new BlakkKnight("Blakk Knight", MyPlayerClassEnum.BLAKK_KNIGHT);
    }

}
