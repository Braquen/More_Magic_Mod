package com.braquen.more_magic_mod.enchant.totems;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraftforge.event.entity.living.LivingUseTotemEvent;

public abstract class TotemEnchantment extends Enchantment {
    static EnchantmentCategory TOTEM = EnchantmentCategory.create("totem", item -> item == Items.TOTEM_OF_UNDYING.asItem());

    public TotemEnchantment(Enchantment.Rarity rarity) {
        super(rarity, TOTEM, new EquipmentSlot[]{EquipmentSlot.MAINHAND, EquipmentSlot.OFFHAND});
    }
}
