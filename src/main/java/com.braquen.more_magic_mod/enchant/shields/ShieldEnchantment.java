package com.braquen.more_magic_mod.enchant.shields;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ShieldItem;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;

public abstract class ShieldEnchantment extends Enchantment {
    static EnchantmentCategory SHIELD = EnchantmentCategory.create("shield", item -> item.getClass() == ShieldItem.class);
    public ShieldEnchantment(net.minecraft.world.item.enchantment.Enchantment.Rarity rarity) {
        super(rarity, SHIELD, new EquipmentSlot[]{EquipmentSlot.MAINHAND, EquipmentSlot.OFFHAND});
    }
}


