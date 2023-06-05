package com.braquen.more_magic_mod.enchant.totems;

import com.braquen.more_magic_mod.init.EnchantmentInit;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LightningBolt;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ShieldItem;
import net.minecraftforge.event.entity.item.ItemEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.living.LivingUseTotemEvent;
import net.minecraftforge.event.entity.living.ShieldBlockEvent;
import net.minecraftforge.event.entity.player.PlayerDestroyItemEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

public class BerserkEnchantment extends TotemEnchantment{
    public BerserkEnchantment(){
        super(Rarity.VERY_RARE);
    }

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
        @SubscribeEvent
        public static void grantStrength(LivingUseTotemEvent event) {

            LivingEntity entity = event.getEntity();
            ItemStack totem = event.getTotem();

            int level =  totem.getEnchantmentLevel(EnchantmentInit.BERSERK.get());

            if (level > 0){
                MobEffectInstance damage_boost = new MobEffectInstance(MobEffects.DAMAGE_BOOST, 40, 0, true,true);
                entity.addEffect(damage_boost);

                System.out.println("GO BERSERK");
                System.out.println(damage_boost);
            }
        }
    }
}
