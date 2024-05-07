package org.osj.minerrpg.WORLD;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.List;

public class WorldManager
{
    private World baseWorld;
    private World minerWorld;
    private List<Vector> minerWorldSpawnPosList;
    private int maxMine = 5;

    public WorldManager()
    {
        baseWorld = Bukkit.getWorld("base");
        minerWorld = Bukkit.getWorld("miner");
        minerWorldSpawnPosList = new ArrayList<>();
        initMinerPos();
    }

    private void initMinerPos()
    {
        Vector pos = new Vector(0, 0, 0);
        for(int i = 0; i < maxMine; i++)
        {
            minerWorldSpawnPosList.add(pos);
            if(i % 5 == 0)
            {
                pos.add(new Vector(16 * 4, 0, 16 * 4));
            }
            else
            {
                pos.add(new Vector(16 * 4, 0, 0));
            }
        }
    }

    public World getBaseWorld()
    {
        return baseWorld;
    }
    public World getMinerWorld()
    {
        return minerWorld;
    }
    public Vector getMinerSpawnPos(int index)
    {
        return minerWorldSpawnPosList.get(index);
    }
}
