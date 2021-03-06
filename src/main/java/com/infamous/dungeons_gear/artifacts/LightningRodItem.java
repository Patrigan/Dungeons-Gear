package com.infamous.dungeons_gear.artifacts;

import com.infamous.dungeons_gear.interfaces.ISoulGatherer;
import com.infamous.dungeons_gear.utilties.AreaOfEffectHelper;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

import java.util.List;

public class LightningRodItem extends ArtifactItem implements ISoulGatherer {
    public LightningRodItem(Properties properties) {
        super(properties);
    }

    public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
        ItemStack itemstack = playerIn.getHeldItem(handIn);
        World world = playerIn.getEntityWorld();

        if(playerIn.experienceTotal >= 15 || playerIn.isCreative()){
            if(!playerIn.isCreative()){
                playerIn.giveExperiencePoints(-15);
            }
            //AbilityUtils.castLightningBoltAtBlockPos(itemUseContextPlayer, blockPos);
            AreaOfEffectHelper.electrifyNearbyEnemies(playerIn, 5, 5, Integer.MAX_VALUE);
            if(!playerIn.isCreative()){
                itemstack.damageItem(1, playerIn, (entity) -> {
                    entity.sendBreakAnimation(handIn);
                });
            }
            //itemUseContextPlayer.getCooldownTracker().setCooldown(itemUseContextItem.getItem(), 40);
        }

        return new ActionResult<>(ActionResultType.SUCCESS, itemstack);
    }

    @Override
    public void addInformation(ItemStack stack, World world, List<ITextComponent> list, ITooltipFlag flag)
    {
        super.addInformation(stack, world, list, flag);

            list.add(new StringTextComponent(TextFormatting.WHITE + "" + TextFormatting.ITALIC +
                    "Crafted by Illager Geomancers, this item is enchanted with the power of a storming sky."));
            list.add(new StringTextComponent(TextFormatting.GREEN +
                    "You can spend XP to attack nearby enemies with a lightning strike."));
            list.add(new StringTextComponent(TextFormatting.LIGHT_PURPLE +
                    "+1 XP Gathering"));
            list.add(new StringTextComponent(TextFormatting.BLUE +
                    "Requires 15 XP"));
    }

    @Override
    public int getGatherAmount(ItemStack stack) {
        return 1;
    }
}
