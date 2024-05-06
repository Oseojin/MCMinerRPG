package org.osj.minerrpg.MINER.EVENT;

import dev.lone.itemsadder.api.CustomStack;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;
import org.osj.minerrpg.MinerRPG;

public class UsePortalKey implements Listener
{
    @EventHandler
    public void onUsePortalKey(PlayerInteractEvent event)
    {
        Player player = event.getPlayer();
        ItemStack itemStack = event.getItem();
        Action action = event.getAction();

        if(action.isRightClick())
        {
            if(itemStack.isEmpty())
            {
                return;
            }

            CustomStack portalKey = CustomStack.byItemStack(itemStack);
            if(portalKey == null || !portalKey.getPermission().equals("ia.minerrpg:portal_key"))
            {
                return;
            }

            int index = MinerRPG.getItemManager().getPortalKeyConnect(portalKey.getId());
            Vector minerTargetPos = MinerRPG.getWorldManager().getMinerSpawnPos(index);
            Location targetLoc = new Location(MinerRPG.getWorldManager().getMinerWorld(), minerTargetPos.getX(), minerTargetPos.getY(), minerTargetPos.getZ());

            player.teleport(targetLoc);
        }
    }
}
