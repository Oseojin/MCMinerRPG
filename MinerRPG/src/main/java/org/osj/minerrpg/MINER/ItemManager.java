package org.osj.minerrpg.MINER;

import dev.lone.itemsadder.api.CustomStack;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class ItemManager
{
    private List<Material> mineralBlockList = new ArrayList<>();
    private List<ItemStack> mineralList = new ArrayList<>();
    private List<String> pickaxeIDList = new ArrayList<>();
    private List<String> portalKeyList = new ArrayList<>();

    public ItemManager()
    {
        initMineralBlockList();
        initMineralList();
        initPickaxeIDList();
        initPortalKeyList();
    }

    private void initMineralBlockList()
    {
        mineralBlockList.add(Material.STONE);
        mineralBlockList.add(Material.COPPER_ORE);
        mineralBlockList.add(Material.IRON_ORE);
        mineralBlockList.add(Material.GOLD_ORE);
        mineralBlockList.add(Material.REDSTONE_ORE);
        mineralBlockList.add(Material.LAPIS_ORE);
        mineralBlockList.add(Material.DIAMOND_ORE);
        mineralBlockList.add(Material.AMETHYST_CLUSTER);
        mineralBlockList.add(Material.EMERALD_ORE);
    }
    private void initMineralList()
    {
        mineralList.add(new ItemStack(Material.COBBLESTONE));
        mineralList.add(new ItemStack(Material.RAW_COPPER));
        mineralList.add(new ItemStack(Material.RAW_IRON));
        mineralList.add(new ItemStack(Material.RAW_GOLD));
        mineralList.add(new ItemStack(Material.REDSTONE));
        mineralList.add(new ItemStack(Material.LAPIS_LAZULI));
        mineralList.add(new ItemStack(Material.DIAMOND));
        mineralList.add(new ItemStack(Material.AMETHYST_SHARD));
        mineralList.add(new ItemStack(Material.EMERALD));

        CustomStack stack = CustomStack.getInstance("minerrpg:deep_water");
        mineralList.add(stack.getItemStack());
    }

    private void initPickaxeIDList()
    {
        pickaxeIDList.add("wooden_pickaxe");
    }

    private void initPortalKeyList()
    {
        portalKeyList.add("iron_portal_key");
    }

    public boolean isContain(Material target)
    {
        return mineralBlockList.contains(target);
    }

    public int getMineralBlockTier(Material target)
    {
        if(target.equals(Material.DEEPSLATE_COPPER_ORE))
        {
            target = Material.COPPER_ORE;
        }
        else if(target.equals(Material.DEEPSLATE_IRON_ORE))
        {
            target = Material.IRON_ORE;
        }
        else if(target.equals(Material.DEEPSLATE_GOLD_ORE))
        {
            target = Material.GOLD_ORE;
        }
        else if(target.equals(Material.DEEPSLATE_REDSTONE_ORE))
        {
            target = Material.REDSTONE_ORE;
        }
        else if(target.equals(Material.DEEPSLATE_LAPIS_ORE))
        {
            target = Material.LAPIS_ORE;
        }
        else if(target.equals(Material.DEEPSLATE_DIAMOND_ORE))
        {
            target = Material.DIAMOND_ORE;
        }
        else if(target.equals(Material.DEEPSLATE_EMERALD_ORE))
        {
            target = Material.EMERALD_ORE;
        }

        return mineralBlockList.indexOf(target) + 1;
    }

    public int getPickaxeTier(String id)
    {
        return pickaxeIDList.indexOf(id);
    }

    public int getPortalKeyConnect(String id)
    {
        return portalKeyList.indexOf(id);
    }

    public CustomStack isPickaxe(ItemStack stack)
    {
        if(stack == null)
        {
            return null;
        }
        CustomStack customStack = CustomStack.byItemStack(stack);
        if(customStack == null)
        {
            return null;
        }

        if(customStack.getPermission().equals("ia.minerrpg:pickaxe"))
        {
            return customStack;
        }
        else
        {
            return null;
        }
    }

    public CustomStack isPortalKey(ItemStack stack)
    {
        if(stack == null)
        {
            return null;
        }
        CustomStack customStack = CustomStack.byItemStack(stack);
        if(customStack == null)
        {
            return null;
        }

        if(customStack.getPermission().equals("ia.minerrpg:portal_key"))
        {
            return customStack;
        }
        else
        {
            return null;
        }
    }
}
