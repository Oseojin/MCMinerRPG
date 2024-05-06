package org.osj.minerrpg.MINER;

import org.bukkit.Material;

import java.util.ArrayList;
import java.util.List;

public class ItemManager
{
    private List<Material> mineralList = new ArrayList<>();
    private List<String> pickaxeIDList = new ArrayList<>();
    private List<String> portalKeyList = new ArrayList<>();

    public ItemManager()
    {
        initMineralList();
        initPickaxeIDList();
        initPortalKeyList();
    }

    private void initMineralList()
    {
        mineralList.add(Material.STONE);
        mineralList.add(Material.COPPER_ORE);
        mineralList.add(Material.IRON_ORE);
        mineralList.add(Material.GOLD_ORE);
        mineralList.add(Material.REDSTONE_ORE);
        mineralList.add(Material.LAPIS_ORE);
        mineralList.add(Material.DIAMOND_ORE);
        mineralList.add(Material.AMETHYST_CLUSTER);
        mineralList.add(Material.EMERALD_ORE);
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
        return mineralList.contains(target);
    }

    public int getMineralTier(Material target)
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

        return mineralList.indexOf(target) + 1;
    }

    public int getPickaxeTier(String id)
    {
        return pickaxeIDList.indexOf(id);
    }

    public int getPortalKeyConnect(String id)
    {
        return portalKeyList.indexOf(id);
    }
}
