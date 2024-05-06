package org.osj.minerrpg.USER.COMMAND;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;
import org.osj.minerrpg.MinerRPG;

public class AddWhiteListCommand implements CommandExecutor
{
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args)
    {
        String inviteName = args[0].toLowerCase();
        MinerRPG.getConfigManager().getConfig("whitelist").set("players." + inviteName, true);
        MinerRPG.getConfigManager().saveConfig("whitelist");
        MinerRPG.getConfigManager().reloadConfig("whitelist");
        return false;
    }
}
