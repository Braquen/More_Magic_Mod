package com.braquen.more_magic_mod.enchant.helmets.WardenSenseEnchantment;

import com.braquen.more_magic_mod.More_Magic_Mod;
import com.braquen.more_magic_mod._init.EnchantmentInit;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.GameEventTags;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.warden.Warden;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.gameevent.*;
import net.minecraft.world.level.gameevent.vibrations.VibrationListener;
import net.minecraftforge.client.event.sound.SoundEvent;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.VanillaGameEvent;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingEquipmentChangeEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import javax.annotation.Nullable;
import java.util.Dictionary;
import java.util.Enumeration;
import java.util.Map;
import java.util.UUID;
import java.util.function.Consumer;

public class WardenSenseEnchantment extends Enchantment {
    public WardenSenseEnchantment(){
        super(Rarity.VERY_RARE, EnchantmentCategory.ARMOR_HEAD, new EquipmentSlot[]{EquipmentSlot.HEAD}); }

    @Override
    public int getMaxLevel() {
        return 3;
    }

    @Override
    public boolean isTreasureOnly() {
        return true;
    }


    @Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.FORGE)
    public static class WardenSenseEventHandler {

        @SubscribeEvent //Alternate solution: check for entities near a vibration with the enchantment on
        public static void updatePlayerVibration(LivingEquipmentChangeEvent event) {
            if(!(event.getEntity() instanceof Player player)) return;
            int level = event.getTo().getEnchantmentLevel(EnchantmentInit.WARDEN_SENSE.get());

            player.getCapability(PlayerVibrationProvider.PLAYER_VIBRATION_SENSE).ifPresent(sense -> {
                if (level > 0) {
                    sense.setPlayer(player);
                    sense.setRange(12 + level * 4);
                }
                else
                    sense.setRange(0);
            });
        }
    }
}
