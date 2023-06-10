package com.braquen.more_magic_mod.enchant.helmets.WardenSenseEnchantment;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Unit;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.Brain;
import net.minecraft.world.entity.ai.memory.MemoryModuleType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.gameevent.DynamicGameEventListener;
import net.minecraft.world.level.gameevent.EntityPositionSource;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.gameevent.GameEventListener;
import net.minecraft.world.level.gameevent.vibrations.VibrationListener;
import net.minecraftforge.common.capabilities.AutoRegisterCapability;

import javax.annotation.Nullable;

@AutoRegisterCapability
public class PlayerVibrationListener implements VibrationListener.VibrationListenerConfig {

    private DynamicGameEventListener<VibrationListener> dynamicGameEventListener = null;
    private Player player = null;
    private int range = 0;

    private final int MAX_COOLDOWN = 40;

    private int cooldown = 40;

    public void setPlayer(Player player){
        this.player = player;
        createVibrationListener();
    }

    public int getRange(){
        return range;
    };

    //The issue where the listener doesn't get created is because FIRST the range is loaded from nbt and set to 24, but the listener isn't created bc the player is null.
    //NEXT the range is set to 24 by the equip change event, but the listener isn't created bc of line 48
    public void setRange(int newRange){
        System.out.println("setting new range");
        if(range != newRange) {
            this.range = Math.max(newRange, 0);
            createVibrationListener();
        }
        System.out.println(range);
    };

    public void saveNBTData(CompoundTag nbt){
        nbt.putInt("warden_sense_range", range);
    }

    public void loadNBTData(CompoundTag nbt){
        setRange(nbt.getInt("warden_sense_range"));
    }

    public void tick(){
        if (this.dynamicGameEventListener == null) return;
        this.getListener().tick(player.level);
        if(cooldown > 0) {
//            System.out.print(cooldown);
//            System.out.print(' ');
            -- cooldown;
        }
    }

    private void createVibrationListener(){
        if(this.player == null) return;
        if(!(this.dynamicGameEventListener == null))
            this.dynamicGameEventListener.remove((ServerLevel) player.level);

        if(range > 0) {
            this.dynamicGameEventListener = new DynamicGameEventListener<>(new VibrationListener(new EntityPositionSource(player, player.getEyeHeight() - 0.5f), range, this));
            this.dynamicGameEventListener.add((ServerLevel)player.level);
        }
        else{
            this.dynamicGameEventListener = null;
        }

    }
    @Nullable
    public VibrationListener getListener(){
        if (this.dynamicGameEventListener == null) return null;
        return this.dynamicGameEventListener.getListener();
    }

    //Vibrations
    //TODO- allow sculk sensor detection
    public boolean shouldListen(ServerLevel serverLevel, GameEventListener gameEventListener, BlockPos blockPos, GameEvent event, GameEvent.Context context){
        if (cooldown <= 0 && context.sourceEntity() instanceof LivingEntity && context.sourceEntity() != player) {
            return true;
        }
        else
            return false;
    }

    public void onSignalReceive(ServerLevel serverLevel, GameEventListener gameEventListener, BlockPos blockPos, GameEvent event, @Nullable Entity noiseMaker, @Nullable Entity projectile, float idk ){
        if (!player.isDeadOrDying() && noiseMaker instanceof LivingEntity target) {
            player.level.playSound(null, player.blockPosition(), SoundEvents.WARDEN_TENDRIL_CLICKS, SoundSource.AMBIENT,  2.0F, player.getVoicePitch());
            MobEffectInstance highlight = new MobEffectInstance(MobEffects.GLOWING, 60, 1);
            target.addEffect(highlight);
            cooldown = MAX_COOLDOWN;
//            System.out.println("Signal recieved");
        }
    }

//    public void onSignalSchedule(){
//        System.out.println("Warden sense sensed smth...");
//    }

}
