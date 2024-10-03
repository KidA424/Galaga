package com.amneziac.galaga.fleets;

import com.amneziac.galaga.galaga.Level1;
import com.amneziac.galaga.objects.VisibleObject;

public class ExplosionFleet extends Fleet {
    
    public ExplosionFleet (Level1 parent)
    {
        super(parent);
    }
    
    @Override
    public void removeDeadObjects()
    {
        for (int i = size() - 1; i >= 0; i--)
        {
            VisibleObject obj = get(i);
            
            if (obj.isDestroyed())
                remove(obj);
        }
    }
    
}