package com.infamous.dungeons_gear.artifacts;

import com.infamous.dungeons_gear.utilties.AreaOfEffectHelper;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.*;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

import java.util.List;

public class WindHornItem extends ArtifactItem {
    public WindHornItem(Properties properties) {
        super(properties);
    }

    public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
        ItemStack itemstack = playerIn.getHeldItem(handIn);

        worldIn.playSound((PlayerEntity)null, playerIn.getPosX(), playerIn.getPosY(), playerIn.getPosZ(), SoundEvents.EVENT_RAID_HORN, SoundCategory.BLOCKS, 64.0F, 1.0F);

        //((ServerPlayerEntity)playerIn).connection.sendPacket(new SPlaySoundEffectPacket(SoundEvents.EVENT_RAID_HORN, SoundCategory.NEUTRAL, d0, playerIn.posY, d1, 64.0F, 1.0F));


        AreaOfEffectHelper.knockbackNearbyEnemies(worldIn, playerIn);
        if(!playerIn.isCreative()){
            itemstack.damageItem(1, playerIn, (entity) -> {
                entity.sendBreakAnimation(handIn);
            });
        }
        ArtifactItem.setArtifactCooldown(playerIn, itemstack.getItem(), 200);
        return new ActionResult<>(ActionResultType.SUCCESS, itemstack);
    }

    @Override
    public void addInformation(ItemStack stack, World world, List<ITextComponent> list, ITooltipFlag flag)
    {
        super.addInformation(stack, world, list, flag);

            list.add(new StringTextComponent(TextFormatting.WHITE + "" + TextFormatting.ITALIC +
                    "When the Wind Horn echoes throughout the forests of the Overworld the creatures of the night tremble with fear."));
            list.add(new StringTextComponent(TextFormatting.GREEN +
                    "Pushes enemies away from you and slows them briefly."));
            list.add(new StringTextComponent(TextFormatting.BLUE +
                    "5 Blocks Pushed"));
            list.add(new StringTextComponent(TextFormatting.BLUE +
                    "10 Seconds Cooldown"));
    }
}
