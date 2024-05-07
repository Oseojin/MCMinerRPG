package org.osj.minerrpg.GUI;

import dev.lone.itemsadder.api.CustomStack;
import dev.lone.itemsadder.api.FontImages.FontImageWrapper;
import dev.lone.itemsadder.api.FontImages.TexturedInventoryWrapper;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.osj.minerrpg.MinerRPG;

import java.util.ArrayList;
import java.util.List;

public class EnhancePickaxeGUI implements Listener
{
    private ItemStack enhanceBtn = new ItemStack(Material.STONE);
    private static TexturedInventoryWrapper inventory;
    public EnhancePickaxeGUI()
    {

    }

    private void setBtnLore(double percent, int mineralNum)
    {
        List<String> lore = new ArrayList<>();
        ItemMeta btnMeta = enhanceBtn.getItemMeta();
        if(percent < 0)
        {
            btnMeta.displayName(Component.text().color(TextColor.color(255, 35, 19)).content("[강화불가]").build());
            lore.add("곡괭이: 0/1");
        }
        else
        {
            btnMeta.displayName(Component.text().color(TextColor.color(26, 129, 255)).content("[강화]").build());
            lore.add("성공확률: " + percent * 100 + "%");
            lore.add("곡괭이: 1/1");
        }

        lore.add("필요광물: " + mineralNum + "/64");
        btnMeta.setLore(lore);
        enhanceBtn.setItemMeta(btnMeta);
        inventory.getInternal().setItem(49, enhanceBtn);
    }

    public void open(Player player)
    {
        inventory = new TexturedInventoryWrapper(
                null,
                54,
                "enhance_gui",
                new FontImageWrapper("minerrpg:test_menu")
        );

        setBtnLore(-1, 0);
        player.sendMessage("강화!");
        inventory.showInventory(player);
    }

    @EventHandler
    public void onClick(InventoryClickEvent event)
    {
        Player player = (Player) event.getWhoClicked();
        if(!event.getView().getOriginalTitle().equals("IA_CUST_GUI"))
        {
            return;
        }

        if(event.getClickedInventory() == player.getInventory())
        {
            return;
        }

        int slotIndex = event.getSlot();

        if(slotIndex == 22) // 광석 올리는 곳
        {

        }
        else if(slotIndex == 31) // 곡괭이 올리는 곳
        {
            // 버튼 lore 설정
            ItemStack currItem = event.getCurrentItem();
            CustomStack pickaxe = MinerRPG.getItemManager().isPickaxe(currItem);
            if(pickaxe == null)
            {
                setBtnLore(-1, event.getClickedInventory().getItem(22).getAmount());
                return;
            }
            int pickaxeTier = MinerRPG.getItemManager().getPickaxeTier(pickaxe.getId());
            double percent = Math.pow(0.9, pickaxeTier);

            //setBtnLore(percent);
        }
        else if(slotIndex == 49) // 확인 버튼
        {
            event.setCancelled(true);
        }
        else
        {
            event.setCancelled(true);
        }
    }
}
