package com.braquen.more_magic_mod.enchant.totems;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;

public abstract class TotemEnchantment extends Enchantment {
    static EnchantmentCategory TOTEM = EnchantmentCategory.create("totem", item -> item == Items.TOTEM_OF_UNDYING.asItem());
    public TotemEnchantment(net.minecraft.world.item.enchantment.Enchantment.Rarity rarity) {
        super(rarity, TOTEM, new EquipmentSlot[]{EquipmentSlot.MAINHAND, EquipmentSlot.OFFHAND});
    }
}
