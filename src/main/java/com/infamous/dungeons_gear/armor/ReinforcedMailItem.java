package com.infamous.dungeons_gear.armor;

import com.infamous.dungeons_gear.DungeonsGear;
import com.infamous.dungeons_gear.armor.models.ReinforcedMailModel;
import com.infamous.dungeons_gear.armor.models.StalwartArmorModel;
import com.infamous.dungeons_gear.init.DeferredItemInit;
import com.infamous.dungeons_gear.interfaces.IArmor;
import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Rarity;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.List;

public class ReinforcedMailItem extends ArmorItem implements IArmor {
    private final boolean unique;

    public ReinforcedMailItem(IArmorMaterial armorMaterial, EquipmentSlotType slotType, Properties properties, boolean unique) {
        super(armorMaterial, slotType, properties);
        this.unique = unique;
    }


    @Override
    public String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlotType slot, String type) {
        if(stack.getItem() == DeferredItemInit.REINFORCED_MAIL.get() || stack.getItem() == DeferredItemInit.REINFORCED_MAIL_HELMET.get()){
            return DungeonsGear.MODID + ":textures/models/armor/reinforced_mail.png";
        }
        else if(stack.getItem() == DeferredItemInit.STALWART_ARMOR.get() || stack.getItem() == DeferredItemInit.STALWART_ARMOR_HELMET.get()){
            return DungeonsGear.MODID + ":textures/models/armor/stalwart_armor.png";
        }
        else return "";
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    @Override
    @OnlyIn(Dist.CLIENT)
    public <A extends BipedModel<?>> A getArmorModel(LivingEntity entityLiving, ItemStack stack, EquipmentSlotType armorSlot, A _default) {
        if(stack.getItem() == DeferredItemInit.REINFORCED_MAIL.get() || stack.getItem() == DeferredItemInit.REINFORCED_MAIL_HELMET.get()){
            return (A) new ReinforcedMailModel<>(1.0F, slot, entityLiving);
        }
        if(stack.getItem() == DeferredItemInit.STALWART_ARMOR.get() || stack.getItem() == DeferredItemInit.STALWART_ARMOR_HELMET.get()){
            return (A) new StalwartArmorModel<>(1.0F, slot, entityLiving);
        }
        return null;
    }

    @Override
    public Rarity getRarity(ItemStack itemStack){
        if(this.unique) return Rarity.RARE;
        return Rarity.UNCOMMON;
    }

    @Override
    public void addInformation(ItemStack stack, World world, List<ITextComponent> list, ITooltipFlag flag) {
        super.addInformation(stack, world, list, flag);

        if (this.unique) {
            list.add(new StringTextComponent(TextFormatting.WHITE + "" + TextFormatting.ITALIC + "This reliable armor is sturdy enough to be passed down for generations."));
            //if(this.slot == EquipmentSlotType.CHEST){
                list.add(new StringTextComponent(TextFormatting.GREEN + "Using Health Potions Boosts Defense (Potion Barrier I)"));
            //}
        }
        else{
            list.add(new StringTextComponent(TextFormatting.WHITE + "" + TextFormatting.ITALIC + "Mostly worn by casual adventurers, Reinforced Mail is a common sight throughout the land."));
        }
    }

    @Override
    public double getChanceToNegateHits() {
        return 15;
    }

    @Override
    public double getLongerJumpAbilityCooldown() {
        return 50;
    }
}
