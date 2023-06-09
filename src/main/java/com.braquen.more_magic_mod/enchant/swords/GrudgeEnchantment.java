package com.braquen.more_magic_mod.enchant.swords;

import com.braquen.more_magic_mod._init.EnchantmentInit;
import com.braquen.more_magic_mod.misc.ModDamageSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

public class GrudgeEnchantment extends Enchantment {
    public GrudgeEnchantment() {
        super(Rarity.UNCOMMON, EnchantmentCategory.WEAPON, new EquipmentSlot[]{EquipmentSlot.MAINHAND});
    }

    @Override
    public int getMaxLevel() {
        return 3;
    }

    @Override
    public boolean isTreasureOnly() {
        return true;
    }

    //handler for revenge multiplier
    @Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.FORGE) //Dopostattack doesn't work bc it doesn't differentiate between slots
    public static class AttackHandler {
        @SubscribeEvent
        public static void takeRevenge(LivingAttackEvent event){
            if (!(event.getSource().getEntity() instanceof LivingEntity attacker)
                    || attacker.getLastDamageSource() == null
                    || event.getSource().isMagic())
                return;

            int level = EnchantmentHelper.getEnchantmentLevel(EnchantmentInit.GRUDGE.get(), attacker);

            if(level <= 0 || !(attacker.getLastDamageSource().getEntity() instanceof LivingEntity instigator)) return;
            //Note: using getLastDamageSource() means that taking damage from a non-LivingEntity will remove the revenge bonus: the last player/mob that hit you will NOT be the last damage source
            //Using getLastMobHurt and getLastPlayerHurt might be better

            LivingEntity victim = event.getEntity();

            if(victim == instigator){
                float punishment = 0.25f * level * event.getAmount();// <level> * 25% more damage
                victim.hurt(ModDamageSource.karma(attacker), punishment);
            }
        }
    }

}
