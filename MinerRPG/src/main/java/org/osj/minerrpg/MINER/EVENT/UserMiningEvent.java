package org.osj.minerrpg.MINER.EVENT;

import dev.lone.itemsadder.api.CustomStack;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import org.osj.minerrpg.MinerRPG;

import java.util.Random;

public class UserMiningEvent implements Listener
{
    @EventHandler
    public void onBlockBreak(BlockBreakEvent event)
    {
        Player player = event.getPlayer();
        Block targetBlock = event.getBlock();
        Material targetMaterial = targetBlock.getType();
        if(player.getWorld().equals(MinerRPG.getWorldManager().getMinerWorld()) && !MinerRPG.getItemManager().isContain(targetMaterial))
        {
            player.sendActionBar(Component.text().color(TextColor.color(0xFF2313)).content("이곳에서는 지정된 블록만 채광할 수 있습니다.").build());
            event.setCancelled(true);
            return;
        }
        if(!MinerRPG.getItemManager().isContain(targetMaterial))
        {
            return;
        }

        ItemStack tool = player.getInventory().getItemInMainHand();
        if(tool.isEmpty())
        {
            event.setCancelled(true);
            return;
        }
        CustomStack stack = CustomStack.byItemStack(tool);
        if(stack == null || !stack.getPermission().equals("ia.minerrpg:pickaxe"))
        {
            event.setCancelled(true);
            return;
        }

        int pickaxeTier = MinerRPG.getItemManager().getPickaxeTier(stack.getId());
        int blockTier = MinerRPG.getItemManager().getMineralTier(targetMaterial);

        if(pickaxeTier < blockTier - 1)
        {
            event.setCancelled(true);
            player.sendActionBar(Component.text().color(TextColor.color(0xFF2313)).content("더 높은 등급의 곡괭이가 필요합니다.").build());
        }

        Random random = new Random();
        double randMineral = random.nextDouble();
        player.sendMessage("" + randMineral);
        if(randMineral <= 0.05)
        {
            //event.getBlock().getDrops().add()
        }
    }
}
