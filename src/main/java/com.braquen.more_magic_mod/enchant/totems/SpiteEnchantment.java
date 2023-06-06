package com.braquen.more_magic_mod.enchant.totems;

import com.braquen.more_magic_mod._init.EnchantmentInit;
import com.braquen.more_magic_mod.misc.ModDamageSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingUseTotemEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

public class SpiteEnchantment extends TotemEnchantment{
    public SpiteEnchantment(){ super(Rarity.VERY_RARE); }

    @Override
    public boolean isAllowedOnBooks() {
        return true;
    }

    @Override
    public boolean isTradeable(){
        return true;
    }

    @Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.FORGE)
    public static class PopHandler {
        @SubscribeEvent(priority = EventPriority.LOWEST)
        public static void punishAttacker(LivingUseTotemEvent event) {

            LivingEntity entity = event.getEntity();
            ItemStack totem = event.getTotem();

            int level = totem.getEnchantmentLevel(EnchantmentInit.SPITE.get());

            if (level > 0 && event.getSource().getEntity() instanceof LivingEntity instigator) {
                System.out.println("Punishing attacker");
                instigator.hurt(ModDamageSource.karma(entity), 60);
            }
        }
    }
}
