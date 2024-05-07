package org.osj.minerrpg.GUI;

import dev.lone.itemsadder.api.CustomStack;
import dev.lone.itemsadder.api.FontImages.FontImageWrapper;
import dev.lone.itemsadder.api.FontImages.TexturedInventoryWrapper;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.osj.minerrpg.MinerRPG;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

// 이놈 굉장히 불안정함
// 전체적으로 함 뜯어고쳐야 할듯...
// 우선 클릭했을때 광물 개수 반영되는거 제대로 고쳐야함
// + shift 클릭하면 아무데나 들어가는 것
// + 가끔식 이상한 곳에 클릭해도 들어가는 것
public class EnhancePickaxeGUI implements Listener
{
    private ItemStack enhanceBtn = new ItemStack(Material.STONE);
    private static TexturedInventoryWrapper inventory;
    public EnhancePickaxeGUI()
    {

    }

    private void setBtnLore(ItemStack mineral, ItemStack pickaxe)
    {
        String mineralID;
        int mineralNum;
        CustomStack customMineral = MinerRPG.getItemManager().isMineral(mineral);

        if(mineral == null || mineral.getType().equals(Material.AIR)) // 아무것도 안 들고 있음
        {
            mineralID = "None";
            mineralNum = 0;
        }
        else if(customMineral == null) // 뭔가 들고 있긴 한데 광물 커스텀은 아님
        {
            mineralID = mineral.getType().name();
            mineralNum = mineral.getAmount();
        }
        else // 뭔가 들고 있고 광물 커스텀임
        {
            mineralID = customMineral.getId();
            mineralNum = mineral.getAmount();
        }
        int mineralTier = MinerRPG.getItemManager().getMineralTier(mineralID);

        CustomStack customPickaxe = MinerRPG.getItemManager().isPickaxe(pickaxe);
        int pickaxeTier = 0;
        double percent;
        if(customPickaxe == null)
        {
            percent = -1;
        }
        else
        {
            pickaxeTier = MinerRPG.getItemManager().getPickaxeTier(customPickaxe.getId());
            percent = Math.pow(0.9, pickaxeTier);
        }

        Bukkit.getConsoleSender().sendMessage(pickaxeTier + " " + mineralTier);

        if(pickaxeTier != mineralTier - 1)
        {
            mineralNum = 0;
        }

        List<String> lore = new ArrayList<>();
        ItemMeta btnMeta = enhanceBtn.getItemMeta();
        if(percent < 0)
        {
            btnMeta.displayName(Component.text().color(TextColor.color(255, 35, 19)).content("[강화불가]").build());
            lore.add("곡괭이: 0/1");
        }
        else
        {
            if(mineralNum >= 64)
            {
                btnMeta.displayName(Component.text().color(TextColor.color(26, 129, 255)).content("[강화]").build());
                lore.add(ChatColor.RED + "[주의: 강화에 성공하면 적용된 효과가 초기화됩니다.]");
                lore.add("성공확률: " + percent * 100 + "%");
                lore.add("곡괭이: 1/1");
            }
            else
            {
                btnMeta.displayName(Component.text().color(TextColor.color(255, 35, 19)).content("[강화불가]").build());
                lore.add("곡괭이: 1/1");
            }
        }

        lore.add("필요광물: " + mineralNum + "/64");
        btnMeta.setLore(lore);
        enhanceBtn.setItemMeta(btnMeta);
        inventory.getInternal().setItem(49, enhanceBtn);
    }

    private void upgrade(Inventory inv, double percent, ItemStack itemStack)
    {
        Random random = new Random();
        double randNum = random.nextDouble();
        Bukkit.getConsoleSender().sendMessage(randNum + "");
        inv.setItem(22, new ItemStack(Material.AIR));
        if(randNum <= percent)
        {
            // 강화 성공
            CustomStack pickaxe = MinerRPG.getItemManager().isPickaxe(itemStack);
            int currPickaxeTier = MinerRPG.getItemManager().getPickaxeTier(pickaxe.getId());
            String nextPickaxeID = MinerRPG.getItemManager().getPickaxeID(currPickaxeTier + 1);
            pickaxe = CustomStack.getInstance("minerrpg:"+nextPickaxeID);

            inv.setItem(31, pickaxe.getItemStack());
        }
        else
        {
            // 강화 실패
        }
    }

    public void open(Player player)
    {
        inventory = new TexturedInventoryWrapper(
                null,
                54,
                "enhance_gui",
                new FontImageWrapper("minerrpg:test_menu")
        );

        setBtnLore(null, null);
        inventory.showInventory(player);
    }

    @EventHandler
    public void onClick(InventoryClickEvent event)
    {
        int slotIndex = event.getSlot();
        Player player = (Player) event.getWhoClicked();
        if(!event.getView().getOriginalTitle().equals("IA_CUST_GUI"))
        {
            return;
        }

        if(event.getClickedInventory() == player.getInventory())
        {
            return;
        }

        if(slotIndex == 22) // 광물
        {
            setBtnLore(event.getCursor(), event.getClickedInventory().getItem(31));
        }
        else if(slotIndex == 31)
        {
            setBtnLore(event.getClickedInventory().getItem(22), event.getCursor());
        }
        else if(slotIndex == 49) // 확인 버튼
        {
            List<String> loreList = enhanceBtn.getItemMeta().getLore();
            if(loreList.size() == 4)
            {
                double percent = Double.parseDouble(loreList.get(1).replace("성공확률: ", "").replace("%", ""));
                upgrade(event.getClickedInventory(), percent, event.getClickedInventory().getItem(31));
            }
            event.setCancelled(true);
        }
        else
        {
            event.setCancelled(true);
        }
    }
}
