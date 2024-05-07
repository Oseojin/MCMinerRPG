package org.osj.minerrpg;

import org.bukkit.plugin.java.JavaPlugin;
import org.osj.minerrpg.CHUNKOWNERSHIP.COMMAND.ChunkPurchase;
import org.osj.minerrpg.CHUNKOWNERSHIP.COMMAND.ChunkRemove;
import org.osj.minerrpg.CHUNKOWNERSHIP.ChunkManager;
import org.osj.minerrpg.CHUNKOWNERSHIP.EVENT.ChunkInteractEvent;
import org.osj.minerrpg.DATAMANAGE.ConfigManager;
import org.osj.minerrpg.GUI.EnhancePickaxeGUI;
import org.osj.minerrpg.GUI.GUIOpenTestCommand;
import org.osj.minerrpg.MINER.EVENT.UsePortalKey;
import org.osj.minerrpg.MINER.EVENT.UserMiningEvent;
import org.osj.minerrpg.MINER.ItemManager;
import org.osj.minerrpg.MINER.RandomTest;
import org.osj.minerrpg.USER.COMMAND.AddWhiteListCommand;
import org.osj.minerrpg.USER.EVENT.UserJoinEvent;
import org.osj.minerrpg.USER.EVENT.UserSwitchItemEvent;
import org.osj.minerrpg.USER.UserManager;
import org.osj.minerrpg.WORLD.WorldManager;

public final class MinerRPG extends JavaPlugin
{
    private static MinerRPG serverInstance;
    private static UserManager userManager;
    private static ConfigManager configManager;
    private static ChunkManager chunkManager;
    private static WorldManager worldManager;
    private static ItemManager itemManager;
    @Override
    public void onEnable()
    {
        // Plugin startup logic
        serverInstance = this;
        configManager = new ConfigManager();
        worldManager = new WorldManager();
        userManager = new UserManager();
        chunkManager = new ChunkManager();
        itemManager = new ItemManager();

        registerEvent();
        registerCommand();
    }

    @Override
    public void onDisable()
    {
        // Plugin shutdown logic

    }

    private void registerEvent()
    {
        getServer().getPluginManager().registerEvents(new UserJoinEvent(), serverInstance);
        getServer().getPluginManager().registerEvents(new ChunkInteractEvent(), serverInstance);
        getServer().getPluginManager().registerEvents(new UserMiningEvent(), serverInstance);
        getServer().getPluginManager().registerEvents(new UsePortalKey(), serverInstance);
        getServer().getPluginManager().registerEvents(new UserSwitchItemEvent(), serverInstance);
        getServer().getPluginManager().registerEvents(new EnhancePickaxeGUI(), serverInstance);
    }
    private void registerCommand()
    {
        serverInstance.getServer().getPluginCommand("purchaseChunk").setExecutor(new ChunkPurchase());
        serverInstance.getServer().getPluginCommand("removeChunk").setExecutor(new ChunkRemove());
        serverInstance.getServer().getPluginCommand("invite").setExecutor(new AddWhiteListCommand());
        serverInstance.getServer().getPluginCommand("randtest").setExecutor(new RandomTest());
        serverInstance.getServer().getPluginCommand("guitest").setExecutor(new GUIOpenTestCommand());
    }

    public static MinerRPG getServerInstance()
    {
        return serverInstance;
    }
    public static UserManager getUserManager()
    {
        return userManager;
    }
    public static ConfigManager getConfigManager()
    {
        return configManager;
    }
    public static ChunkManager getChunkManager()
    {
        return chunkManager;
    }
    public static WorldManager getWorldManager()
    {
        return worldManager;
    }
    public static ItemManager getItemManager()
    {
        return itemManager;
    }
}
