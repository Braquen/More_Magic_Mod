//Author: Braquen
package com.braquen.more_magic_mod.init;

import com.braquen.more_magic_mod.enchant.shields.ReboundEnchantment;
import com.braquen.more_magic_mod.enchant.swords.GrudgeEnchantment;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import com.braquen.more_magic_mod.More_Magic_Mod;

import com.braquen.more_magic_mod.enchant.shields.ReflectionEnchantment;

public class EnchantmentInit {
    public static final DeferredRegister<Enchantment> ENCHANTMENTS = DeferredRegister.create(ForgeRegistries.ENCHANTMENTS, More_Magic_Mod.MODID);

    public static final RegistryObject<Enchantment> REFLECTION = ENCHANTMENTS.register("reflection", ReflectionEnchantment::new);
    public static final RegistryObject<Enchantment> REBOUND = ENCHANTMENTS.register("rebound", ReboundEnchantment::new);

    public static final RegistryObject<Enchantment> GRUDGE = ENCHANTMENTS.register("grudge", GrudgeEnchantment::new);
}