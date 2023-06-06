package com.braquen.more_magic_mod.misc;

import com.braquen.more_magic_mod.More_Magic_Mod;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.EntityDamageSource;
import net.minecraft.world.entity.LivingEntity;

import javax.annotation.Nullable;

public class ModDamageSource {
    public static DamageSource karma(@Nullable LivingEntity entity) {
        return new EntityDamageSource(More_Magic_Mod.MODID + "_karma", entity).bypassArmor().bypassEnchantments().setMagic();
    }
}
