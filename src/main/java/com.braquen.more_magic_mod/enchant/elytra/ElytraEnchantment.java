package com.braquen.more_magic_mod.enchant.elytra;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;

public abstract class ElytraEnchantment extends Enchantment {

    static EnchantmentCategory ELYTRA = EnchantmentCategory.create("elytra", item -> item == Items.ELYTRA.asItem());

    public ElytraEnchantment(Enchantment.Rarity rarity) {
        super(rarity, ELYTRA, new EquipmentSlot[]{EquipmentSlot.CHEST});
    }
}
