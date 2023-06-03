package com.braquen.more_magic_mod.enchant.shields;

import com.braquen.more_magic_mod.init.EnchantmentInit;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.Fireball;
import net.minecraft.world.entity.projectile.FireworkRocketEntity;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.event.entity.living.ShieldBlockEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

public class ReflectionEnchantment extends ShieldEnchantment{
    public ReflectionEnchantment() {
        super(Rarity.RARE);
    }

    @Override
    public int getMaxLevel() {
        return 3;
    }

    @Override
    public boolean isAllowedOnBooks() {
        return true;
    }

    @Override
    public boolean isTradeable(){
        return true;
    }

    //Event handler for reflecting projectiles
    @Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.FORGE)
    public static class GuardHandler {
        @SubscribeEvent(priority = EventPriority.HIGHEST)
        public static void reflectProjectile(ShieldBlockEvent event){

            Entity projectile = event.getDamageSource().getDirectEntity();
            LivingEntity blocker = event.getEntity();
            int level = EnchantmentHelper.getEnchantmentLevel(EnchantmentInit.REFLECTION.get(), blocker);

            if (projectile instanceof Projectile && level > 0) { //Reflection only does something if the attack in question is a projectile

                projectile.playSound(SoundEvents.COPPER_BREAK, 10f, 1f);

                double speed = projectile.getDeltaMovement().length();
                Vec3 angle = blocker.getLookAngle();

                Vec3 newVelocity = angle.scale(speed * level * -3);

                projectile.setDeltaMovement(newVelocity);

                System.out.println("projectile reflected");
            }
        }
    }
}
