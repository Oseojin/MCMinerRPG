package org.osj.minerrpg.USER;

import org.bukkit.Bukkit;
import org.osj.minerrpg.DATAMANAGE.DB_Connect;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

public class User
{
    private UUID uuid;
    private String name;
    private String prefix;
    private List<Boolean> illustratedBookList;

    public User(UUID uuid, String name, String prefix)
    {
        this.uuid = uuid;
        this.name = name;
        this.prefix = prefix;
        illustratedBookList = new ArrayList<>();
    }

    public UUID getUUID()
    {
        return uuid;
    }
    public void setUUID(UUID uuid)
    {
        this.uuid = uuid;
        DB_Connect.getInstance().SetUUID(uuid);
    }
    public void loadUUID(UUID uuid)
    {
        this.uuid = uuid;
    }
    public String getName()
    {
        return name;
    }
    public void setName(String name)
    {
        this.name = name;
        DB_Connect.getInstance().SetName(uuid, name);
    }
    public void loadName(String name)
    {
        this.name = name;
    }
    public String getPrefix()
    {
        return prefix;
    }
    public void setPrefix(String prefix)
    {
        this.prefix = prefix;
        DB_Connect.getInstance().SetPrefix(uuid, prefix);
    }
    public void loadPrefix(String prefix)
    {
        this.prefix = prefix;
    }
}
