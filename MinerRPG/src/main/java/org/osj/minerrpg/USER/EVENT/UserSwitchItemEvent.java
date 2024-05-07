package org.osj.minerrpg.USER.EVENT;

import dev.lone.itemsadder.api.CustomStack;
import net.kyori.adventure.text.Component;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemHeldEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.osj.minerrpg.MinerRPG;
import org.osj.minerrpg.USER.User;

import java.util.List;

public class UserSwitchItemEvent implements Listener
{
    @EventHandler
    public void onSwitchItem(PlayerItemHeldEvent event)
    {
        Player player = event.getPlayer();
        ItemStack tool = player.getInventory().getItemInMainHand();
        if(tool == null)
        {
            return;
        }
        CustomStack stack = CustomStack.byItemStack(tool);
        if(stack == null || !stack.getPermission().equals("ia.minerrpg:pickaxe"))
        {
            return;
        }

        ItemMeta itemMeta = tool.getItemMeta();

        List<String> loreList = itemMeta.getLore();
        if(loreList.size() <= 5)
        {
            return;
        }
        User user = MinerRPG.getUserManager().getUserData(player.getUniqueId());
        user.setEffectList(loreList);
    }
}
