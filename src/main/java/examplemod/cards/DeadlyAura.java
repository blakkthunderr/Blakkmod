package examplemod.cards;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.unique.LoseEnergyAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.CleaveEffect;

public class DeadlyAura
        extends CustomCard {
    public static final String ID = "DeadlyAura";
    public static final String NAME = "Deadly Aura";
    public static final String DESCRIPTION = "Unplayable. At the end of your turn, deal !D! damage to ALL enemies.";
    public static final String IMG_PATH = "img/DeadlyAura.png";
    private static final int COST = -2;
    private static final int ATTACK_DMG = 5;
    private static final int UPGRADE_PLUS_DMG = 3;


    public DeadlyAura() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION,
                CardType.SKILL, CardColor.COLORLESS,
                CardRarity.UNCOMMON, CardTarget.ALL_ENEMY);
        this.damage=this.baseDamage = ATTACK_DMG;
        this.isMultiDamage = true;

        this.setBackgroundTexture("img/custom_background_small.png", "img/custom_background_large.png");

        this.setOrbTexture("img/custom_orb_small.png", "img/custom_orb_large.png");

        this.setBannerTexture("img/custom_banner_large.png", "img/custom_banner_large.png");
    }


    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
    }

        @Override
    public void triggerOnEndOfPlayerTurn() {
        super.triggerOnEndOfPlayerTurn();
        AbstractDungeon.actionManager.addToBottom(new SFXAction("ATTACK_HEAVY"));
        AbstractDungeon.actionManager.addToBottom(new DamageAllEnemiesAction(AbstractDungeon.player,  this.multiDamage, this.damageTypeForTurn, AbstractGameAction.AttackEffect.NONE));

    }




    @Override
    public AbstractCard makeCopy() {
        return new DeadlyAura();
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeDamage(UPGRADE_PLUS_DMG);

        }
    }
}
