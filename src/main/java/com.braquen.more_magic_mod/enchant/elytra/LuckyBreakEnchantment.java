package com.braquen.more_magic_mod.enchant.elytra;

import com.braquen.more_magic_mod._init.EnchantmentInit;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ElytraItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.item.ItemEvent;
import net.minecraftforge.event.entity.living.LivingEquipmentChangeEvent;
import net.minecraftforge.event.entity.living.LivingFallEvent;
import net.minecraftforge.event.entity.player.PlayerFlyableFallEvent;
import net.minecraftforge.event.level.BlockEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import static com.braquen.more_magic_mod.More_Magic_Mod.MODID;

public class LuckyBreakEnchantment extends ElytraEnchantment{
    public LuckyBreakEnchantment(){super(Rarity.VERY_RARE);};

    @Override
    public boolean isTradeable() {return false;}

    @Override
    public boolean isTreasureOnly() { return true; }

    //Event handler for not breaking
    @Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
    public static class BreakHandler {

        @SubscribeEvent
        public static void preventElytraBreak(TickEvent.PlayerTickEvent.PlayerTickEvent event){//runs if the player has an elytra in their inventory and falls
            if(event.phase == TickEvent.Phase.START || event.player.level.isClientSide() || !event.player.isFallFlying()) return;

            Player player = event.player;
            ItemStack elytra = player.getItemBySlot(EquipmentSlot.CHEST);

            if(elytra.getItem() == Items.ELYTRA ){

                int level =  elytra.getEnchantmentLevel(EnchantmentInit.LUCKY_BREAK.get());

                if(level > 0) {
                    if(elytra.getDamageValue() == elytra.getMaxDamage() - 1){//1 away from broken

                        System.out.println("boutta break");
                        elytra.setDamageValue(elytra.getDamageValue() - 1);
                    }
                }
            }
        }

        @SubscribeEvent
        public static void doElytraBreak(LivingFallEvent event){
            if(event.getEntity().level.isClientSide() || !(event.getEntity() instanceof Player player)) return;
            System.out.println("touchdown!");

            ItemStack elytra = player.getItemBySlot(EquipmentSlot.CHEST);

            if(elytra.getItem() == Items.ELYTRA ){

                int level =  elytra.getEnchantmentLevel(EnchantmentInit.LUCKY_BREAK.get());

                if(level > 0) {
                    if(elytra.getDamageValue() >= elytra.getMaxDamage() - 2){//2 away from broken
                        System.out.println("break that");
                        elytra.setDamageValue(elytra.getMaxDamage() - 1); //it is now broken
                    }
                }
            }
        }
    }
}
