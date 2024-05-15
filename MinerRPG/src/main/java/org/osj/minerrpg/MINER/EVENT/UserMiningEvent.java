package org.osj.minerrpg.MINER.EVENT;

import dev.lone.itemsadder.api.CustomBlock;
import dev.lone.itemsadder.api.CustomStack;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.osj.minerrpg.MINER.BlockManager;
import org.osj.minerrpg.MinerRPG;

public class UserMiningEvent implements Listener
{
    @EventHandler
    public void onBlockBreak(BlockBreakEvent event)
    {
        Player player = event.getPlayer();
        Block targetBlock = event.getBlock();
        CustomBlock customBlock = CustomBlock.byAlreadyPlaced(targetBlock);
        if(player.getGameMode().equals(GameMode.CREATIVE))
        {
            return;
        }
        CustomStack tool = CustomStack.byItemStack(player.getInventory().getItemInMainHand());

        BlockManager blockManager = MinerRPG.getBlockManager();
        boolean isSuccess;

        if(customBlock == null)
        {
            if(targetBlock.getType().equals(Material.ANCIENT_DEBRIS))
            {
                isSuccess = blockManager.ancientDebrisBreak(player, tool);
            }
            else
            {
                return;
            }
        }
        else
        {
            switch (customBlock.getId())
            {
                case "silver_ore":
                    isSuccess = true;
                    break;
                case "aquamarine_ore":
                    isSuccess = blockManager.aquamarineBreak(player, tool);
                    break;
                case "burningstone_ore":
                    isSuccess = blockManager.burningStoneBreak(player, tool);
                    break;
                case "steel_ore":
                    isSuccess = blockManager.steelBreak(player, tool);
                    break;
                default:
                    isSuccess = false;
            }
        }

        if(!isSuccess)
        {
            event.setCancelled(true);
        }
    }
}
