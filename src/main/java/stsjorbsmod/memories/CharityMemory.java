package stsjorbsmod.memories;

import com.megacrit.cardcrawl.cards.DamageInfo.DamageType;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import stsjorbsmod.powers.IGoldListenerPower;

public class CharityMemory extends AbstractMemory implements IGoldListenerPower {
    public static final StaticMemoryInfo STATIC = StaticMemoryInfo.Load(CharityMemory.class);

    private static final int STRENGTH_PER_GOLD_THRESHOLD = 1;
    private static final int GOLD_THRESHOLD = 100;

    public CharityMemory(final AbstractCreature owner, boolean isClarified) {
        super(STATIC, MemoryType.VIRTUE, owner, isClarified);
        setDescriptionPlaceholder("!S!", STRENGTH_PER_GOLD_THRESHOLD);
        setDescriptionPlaceholder("!G!", GOLD_THRESHOLD);
    }

    @Override
    public float atDamageGive(float originalDamage, DamageType type) {
        int extraDamage = (AbstractDungeon.player.gold / GOLD_THRESHOLD) * STRENGTH_PER_GOLD_THRESHOLD;
        return type == DamageType.NORMAL ? originalDamage + extraDamage : originalDamage;
    }

    @Override
    public void onGoldModified(AbstractPlayer player) {
        flashWithoutSound();
    }
}
