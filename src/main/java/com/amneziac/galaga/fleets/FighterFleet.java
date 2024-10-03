package com.amneziac.galaga.fleets;

import com.amneziac.galaga.galaga.Level1;
import com.amneziac.galaga.objects.Explosion;
import com.amneziac.galaga.objects.Fighter;

public abstract class FighterFleet extends Fleet {
    
    protected static int spacer;
    
    public FighterFleet(Level1 parent)
    {
        super(parent);
    }
    
    public void fighterDestroyed(Fighter fighter)
    {
        parent.fleets.get(0).add(new Explosion (fighter, this));
        fighter.setDestroyed(true);
    }
    
    @Override
    public void removeDeadObjects()
    {
        for (int i = size() - 1; i >= 0; i--)
        {
            Fighter obj = (Fighter)get(i);
            
            if (obj.isDestroyed())
                remove(obj);
            else
                obj.removeDeadAmmo();
        }
    }
}