package org.osj.minerrpg.MINER;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

import java.util.Random;

public class RandomTest implements CommandExecutor
{

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args)
    {
        int cnt = 0;
        Random random = new Random();
        double probability = Double.parseDouble(args[0]) / 100.0;
        int loopNum = Integer.parseInt(args[1]);
        sender.sendMessage("" + probability);
        for(int i = 0; i < loopNum; i++)
        {
            double randDouble = random.nextDouble();
            if(randDouble <= probability)
            {
                sender.sendMessage("" + randDouble);
                cnt++;
            }
        }
        
        double percent = (double)cnt/(double)loopNum;

        sender.sendMessage(probability*100 + "% / " + loopNum + "회 반복 / 평균 확률: " + percent + "%");
        return false;
    }
}
