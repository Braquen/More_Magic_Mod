package com.braquen.more_magic_mod.enchant.totems;

import com.braquen.more_magic_mod._init.EnchantmentInit;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingUseTotemEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

public class RebreatherEnchantment extends TotemEnchantment{
    public RebreatherEnchantment(){ super(Rarity.VERY_RARE); }

    @Override
    public boolean isAllowedOnBooks() {
        return true;
    }

    @Override
    public boolean isTradeable(){ return true; }

    @Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.FORGE)
    public static class PopHandler {
        @SubscribeEvent
        public static void grantWaterBreathing(LivingUseTotemEvent event) {

            LivingEntity entity = event.getEntity();
            ItemStack totem = event.getTotem();

            int level =  totem.getEnchantmentLevel(EnchantmentInit.REBREATHER.get());

            if (level > 0){
                MobEffectInstance water_breathing = new MobEffectInstance(MobEffects.WATER_BREATHING, 60, 0, true,true);
                entity.addEffect(water_breathing);

                System.out.println("adding water breathing");
                System.out.println(water_breathing);
            }
        }
    }
}
