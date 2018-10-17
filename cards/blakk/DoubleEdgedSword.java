package examplemod.cards.blakk;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import examplemod.patches.AbstractCardEnum;
import examplemod.powers.SoulPower;

public class DoubleEdgedSword
        extends CustomCard {
    public static final String ID = "DoubleEdgedSword";
    public static final String NAME = "Double Edged Sword";
    public static final String DESCRIPTION = "Deal !D! damage. Gain and Apply 2 Vulnerable.";
    public static final String IMG_PATH = "img/SoulStrike.png";
    private static final int COST = 1;
    private static final int ATTACK_DMG = 10;


    public DoubleEdgedSword() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION,
                CardType.ATTACK, AbstractCardEnum.BLAKK_COLOR,
                CardRarity.UNCOMMON, CardTarget.ENEMY);

        this.baseMagicNumber = 2;
        this.magicNumber = this.baseMagicNumber;
        this.damage=this.baseDamage = ATTACK_DMG;

        //this.setBackgroundTexture("img/custom_background_small.png", "img/custom_background_large.png");

        //this.setOrbTexture("img/custom_orb_small.png", "img/custom_orb_large.png");

        //this.setBannerTexture("img/custom_banner_large.png", "img/custom_banner_large.png");
    }




    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {

        AbstractDungeon.actionManager.addToBottom(new com.megacrit.cardcrawl.actions.common.DamageAction(m,
                new DamageInfo(p, this.damage, this.damageTypeForTurn),
                AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));

        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(m, p, new VulnerablePower(m, 2, false), 2));
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new VulnerablePower(p, 2, false), 2));


    }

    @Override
    public AbstractCard makeCopy() {
        return new DoubleEdgedSword();
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeDamage(4);

        }
    }
}
