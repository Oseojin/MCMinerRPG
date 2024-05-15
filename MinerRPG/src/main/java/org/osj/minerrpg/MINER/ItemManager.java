package org.osj.minerrpg.MINER;

import dev.lone.itemsadder.api.CustomBlock;
import dev.lone.itemsadder.api.CustomStack;
import org.bukkit.Bukkit;
import org.bukkit.block.Block;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class ItemManager
{
    public ItemManager()
    {

    }
    public boolean isPickaxe(ItemStack stack)
    {
        if(stack == null)
        {
            return false;
        }
        CustomStack customStack = CustomStack.byItemStack(stack);
        if(customStack == null)
        {
            return false;
        }
        if(customStack.getPermission().equals("ia.minerrpg:pickaxe"))
        {
            return true;
        }
        return false;
    }
    public boolean isMineral(ItemStack stack)
    {
        if(stack == null)
        {
            return false;
        }
        CustomStack customStack = CustomStack.byItemStack(stack);
        if(customStack == null)
        {
            return false;
        }
        if(customStack.getPermission().equals("ia.minerrpg:mineral"))
        {
            return true;
        }
        return false;
    }
    public boolean isMineralBlock(Block block)
    {
        if(block == null)
        {
            return false;
        }
        CustomBlock customBlock = CustomBlock.byAlreadyPlaced(block);
        if(customBlock == null) // 커스텀 아이템 아님
        {
            return false;
        }
        if(customBlock.getPermission().equals("ia.minerrpg:mineral_ore"))
        {
            return true;
        }
        return false;
    }
}
