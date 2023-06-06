//Author: Braquen
package com.braquen.more_magic_mod._init;

import com.braquen.more_magic_mod.enchant.elytra.LuckyBreakEnchantment;
import com.braquen.more_magic_mod.enchant.shields.ReboundEnchantment;
import com.braquen.more_magic_mod.enchant.swords.GrudgeEnchantment;
import com.braquen.more_magic_mod.enchant.totems.BerserkEnchantment;
import com.braquen.more_magic_mod.enchant.totems.HeatWaveEnchantment;
import com.braquen.more_magic_mod.enchant.totems.MartyrdomEnchantment;
import com.braquen.more_magic_mod.enchant.totems.RebreatherEnchantment;
import com.braquen.more_magic_mod.enchant.totems.SpiteEnchantment;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import com.braquen.more_magic_mod.More_Magic_Mod;

import com.braquen.more_magic_mod.enchant.shields.ReflectionEnchantment;

public class EnchantmentInit {
    public static final DeferredRegister<Enchantment> ENCHANTMENTS = DeferredRegister.create(ForgeRegistries.ENCHANTMENTS, More_Magic_Mod.MODID);

    public static final RegistryObject<Enchantment> LUCKY_BREAK = ENCHANTMENTS.register("lucky_break", LuckyBreakEnchantment::new);

    public static final RegistryObject<Enchantment> REFLECTION = ENCHANTMENTS.register("reflection", ReflectionEnchantment::new);
    public static final RegistryObject<Enchantment> REBOUND = ENCHANTMENTS.register("rebound", ReboundEnchantment::new);

    public static final RegistryObject<Enchantment> GRUDGE = ENCHANTMENTS.register("grudge", GrudgeEnchantment::new);

    public static final RegistryObject<Enchantment> BERSERK = ENCHANTMENTS.register("berserk", BerserkEnchantment::new);
    public static final RegistryObject<Enchantment> HEATWAVE = ENCHANTMENTS.register("heatwave", HeatWaveEnchantment::new);
    public static final RegistryObject<Enchantment> MARTYRDOM = ENCHANTMENTS.register("martyrdom", MartyrdomEnchantment::new);
    public static final RegistryObject<Enchantment> REBREATHER = ENCHANTMENTS.register("rebreather", RebreatherEnchantment::new);
    public static final RegistryObject<Enchantment> SPITE = ENCHANTMENTS.register("spite", SpiteEnchantment::new);

}