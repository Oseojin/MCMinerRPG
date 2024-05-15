package org.osj.minerrpg.MINER;

import dev.lone.itemsadder.api.CustomStack;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import java.util.Random;

public class BlockManager
{
    Random random = new Random();

    private Component setFailureText(String mainText, String randomText)
    {
        double randNum = random.nextDouble();
        Bukkit.getConsoleSender().sendMessage(randNum + "");
        Component failureText;
        if(randNum <= 0.01) // 1%
        {
            failureText = Component.text().color(TextColor.color(255, 49, 44)).content(mainText + "\n" + randomText).build();
        }
        else
        {
            failureText = Component.text().color(TextColor.color(255, 49, 44)).content(mainText).build();
        }

        return failureText;
    }
    public boolean aquamarineBreak(Player player, CustomStack tool)
    {
        if(tool != null)
        {
            if(tool.getId().equals("silver_pickaxe"))
            {
                // 채굴 성공
                return true;
            }
        }
        // 채굴 실패: tool == null 이거나 적합한 곡괭이가 아니거나
        player.setRemainingAir(0); // 이거 아직 작동 안하는듯?
        Component failureText = setFailureText("호흡 부족으로 시야가 흐려져 광물을 놓쳤다...", "무언가의 저주일까?");
        player.sendActionBar(failureText);
        return false;
    }

    public boolean burningStoneBreak(Player player, CustomStack tool)
    {
        if(tool != null)
        {
            if(tool.getId().equals("aquamarine_pickaxe"))
            {
                // 채굴 성공
                return true;
            }
        }
        // 채굴 실패: tool == null 이거나 적합한 곡괭이가 아니거나
        player.setFireTicks(100);
        Component failureText = setFailureText("광석이 불타 사라졌다...", "광석을 식힐 방법이 있을까?");
        player.sendActionBar(failureText);
        return false;
    }

    public boolean steelBreak(Player player, CustomStack tool)
    {
        if(tool != null)
        {
            if(tool.getId().equals("burning_pickaxe"))
            {
                // 채굴 성공
                return true;
            }
        }
        // 채굴 실패: tool == null 이거나 적합한 곡괭이가 아니거나
        Component failureText = setFailureText("광석이 깨져 사용할 수 없을 것 같다.", "조금 녹여서 채굴하면 깨지지 않게 할 수 있지 않을까?");
        player.sendActionBar(failureText);
        return false;
    }

    public boolean ancientDebrisBreak(Player player, CustomStack tool)
    {
        if(tool != null)
        {
            if(tool.getId().equals("unkillable_pickaxe"))
            {
                // 채굴 성공
                return true;
            }
        }
        // 채굴 실패: tool == null 이거나 적합한 곡괭이가 아니거나
        Component failureText = setFailureText("광석이 깨져 사용할 수 없을 것 같다.", "조금 녹여서 채굴하면 깨지지 않게 할 수 있지 않을까?");
        player.sendActionBar(failureText);
        return false;
    }
}
