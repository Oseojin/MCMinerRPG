package org.osj.minerrpg.MINER;

import org.osj.minerrpg.DATAMANAGE.DB_Connect;

public class EffectManager
{
    private static EffectManager instance;

    public static EffectManager getInstance()
    {
        if(instance == null)
        {
            synchronized (EffectManager.class)
            {
                instance = new EffectManager();
            }
        }

        return instance;
    }

    public static enum Effects
    {

    }
}
