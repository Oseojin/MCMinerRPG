package org.osj.minerrpg.DATAMANAGE;

import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.osj.minerrpg.MinerRPG;

import java.util.HashMap;

public class ConfigManager
{
    private final String path = MinerRPG.getServerInstance().getDataFolder().getAbsolutePath();

    private HashMap<String, ConfigMaker> configSet = new HashMap<>();


    // config 전체 재시작
    public ConfigManager()
    {
        configSet.put("whitelist", new ConfigMaker(path, "whitelist.yml"));
        configSet.put("chunkownership", new ConfigMaker(path, "chunkownership.yml"));
        loadSettings();
        saveConfigs();
    }


    // config 전체 리로드
    public void reloadConfigs()
    {
        for(String key : configSet.keySet())
        {
            MinerRPG.getServerInstance().getLogger().info(key);
            configSet.get(key).reloadConfig();
        }
    }


    // 특정 config 리로드
    public void reloadConfig(String fileName)
    {
        configSet.get(fileName).reloadConfig();
    }


    // config 전체 저장
    public void saveConfigs()
    {
        for(String key : configSet.keySet())
        {
            configSet.get(key).saveConfig();
        }
    }


    // 특정 config 저장
    public void saveConfig(String fileName)
    {
        configSet.get(fileName).saveConfig();
    }


    // config 가져오기
    public FileConfiguration getConfig(String fileName)
    {
        return configSet.get(fileName).getConfig();
    }

    // config 컬러챗 가져오기??
    public String getConfigColorString(String fileName, String path)
    {
        return ChatColor.translateAlternateColorCodes('&', getConfig(fileName).getString(path));
    }


    // config 기본 세팅
    public void loadSettings()
    {
        FileConfiguration whitelistConfig = getConfig("whitelist");
        FileConfiguration chunkownershipConfig = getConfig("chunkownership");

        whitelistConfig.options().copyDefaults(true);
        chunkownershipConfig.options().copyDefaults(true);
    }
}
