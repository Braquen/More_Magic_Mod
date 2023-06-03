package com.braquen.more_magic_mod.enchant.shields;

import com.braquen.more_magic_mod.init.EnchantmentInit;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.event.entity.living.LivingKnockBackEvent;
import net.minecraftforge.event.entity.living.ShieldBlockEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

public class ReboundEnchantment extends ShieldEnchantment{

    @Override
    public int getMaxLevel() {
        return 2;
    }

    @Override
    public boolean isAllowedOnBooks() {
        return true;
    }

    @Override
    public boolean isTradeable(){
        return true;
    }

    public ReboundEnchantment() {
        super(Rarity.RARE);
    }

    @Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.FORGE)
    public static class GuardHandler {
        @SubscribeEvent(priority = EventPriority.LOWEST)
        public static void bounceAttacker(ShieldBlockEvent event){

            LivingEntity blocker = event.getEntity();
            Entity attacker = event.getDamageSource().getDirectEntity();
            int level = EnchantmentHelper.getEnchantmentLevel(EnchantmentInit.REBOUND.get(), blocker);


            if (attacker instanceof LivingEntity && level > 0) {

                System.out.println("Valid attacker");
                attacker.playSound(SoundEvents.SLIME_BLOCK_FALL, 10f, 1f);

                Vec3 angle = blocker.position().subtract(attacker.position());

                ((LivingEntity) attacker).knockback(level, angle.x, angle.z); //Need to check if these need to be normalized

                event.setShieldTakesDamage(true);
                System.out.println("Attacker rebounded");
                System.out.println(angle);
            }
        }
    }
}
