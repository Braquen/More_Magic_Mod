package com.braquen.more_magic_mod.event;

import com.braquen.more_magic_mod.More_Magic_Mod;
import com.braquen.more_magic_mod.enchant.helmets.WardenSenseEnchantment.PlayerVibrationProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = More_Magic_Mod.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ModEvents {
    @SubscribeEvent
    public static void onAttachCapabilitiesPlayer(AttachCapabilitiesEvent<Entity> event) {
        if(event.getObject() instanceof Player player){
            if(!player.getCapability(PlayerVibrationProvider.PLAYER_VIBRATION_SENSE).isPresent()) {
                event.addCapability(new ResourceLocation(More_Magic_Mod.MODID, "properties"), new PlayerVibrationProvider());
            }
            player.getCapability(PlayerVibrationProvider.PLAYER_VIBRATION_SENSE).ifPresent(sense -> {sense.setPlayer(player);});
        }
    }
    @SubscribeEvent
    public static void onPlayerCloned(PlayerEvent.Clone event){
        if(event.isWasDeath()) {
            event.getOriginal().reviveCaps();
            //
            event.getEntity().getCapability(PlayerVibrationProvider.PLAYER_VIBRATION_SENSE).ifPresent(sense -> {sense.setPlayer(event.getEntity());});
        }
    }
    @SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent event){
        if (event.side.isClient()) return;

        //Warden Sense Ticker
        event.player.getCapability(PlayerVibrationProvider.PLAYER_VIBRATION_SENSE).ifPresent(sense -> {
            sense.tick();
        });
    }
}
