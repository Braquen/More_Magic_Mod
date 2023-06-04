package com.braquen.more_magic_mod.enchant.totems;

import com.braquen.more_magic_mod.init.EnchantmentInit;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.entity.EntityTypeTest;
import net.minecraft.world.phys.AABB;
import net.minecraftforge.event.entity.living.LivingUseTotemEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class HeatWaveEnchantment extends TotemEnchantment{
    public HeatWaveEnchantment() { super(Rarity.VERY_RARE); }

    @Override
    public boolean isAllowedOnBooks() {
        return true;
    }

    @Override
    public boolean isTradeable(){ return true; }

    @Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.FORGE)
    public static class PopHandler {
        @SubscribeEvent
        public static void burnAttackers(LivingUseTotemEvent event) {

            LivingEntity entity = event.getEntity();
            ItemStack totem = event.getTotem();

            int level =  totem.getEnchantmentLevel(EnchantmentInit.HEATWAVE.get());

            if (level > 0){

                AABB AOE = entity.getBoundingBox().inflate(9);// 9 blocks in every direction
                Predicate<LivingEntity> isAttacker = (target) -> {
                        return (target.canAttack(entity)); //Anything that can hurt us
                };
                Consumer<LivingEntity> burn = (livingEntity) -> {
                        livingEntity.setSecondsOnFire(10);
                };

                List<LivingEntity> targets = entity.level.getEntitiesOfClass(LivingEntity.class, AOE, isAttacker);
                targets.forEach(burn);
            }
        }
    }
}
