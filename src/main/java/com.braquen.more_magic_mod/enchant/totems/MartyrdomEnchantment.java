package com.braquen.more_magic_mod.enchant.totems;

import com.braquen.more_magic_mod._init.EnchantmentInit;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.event.entity.living.LivingUseTotemEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

public class MartyrdomEnchantment extends TotemEnchantment {
    public MartyrdomEnchantment() {super(Rarity.RARE);}

    @Override
    public boolean isAllowedOnBooks() {
        return true;
    }

    @Override
    public boolean isTradeable(){ return true; }

    @Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.FORGE)
    public static class PopHandler {
        @SubscribeEvent
        public static void doDeathExplosion(LivingUseTotemEvent event) {
            LivingEntity entity = event.getEntity();
            ItemStack totem = event.getTotem();

            int level =  totem.getEnchantmentLevel(EnchantmentInit.MARTYRDOM.get());

            if (level > 0) {
                Vec3 position = entity.position();
                entity.getLevel().explode(entity,position.x,position.y,position.z, 4.0F,false, Level.ExplosionInteraction.BLOCK);//TNT Sized
                System.out.println("KABOOM");
            }
        }
    }
}
