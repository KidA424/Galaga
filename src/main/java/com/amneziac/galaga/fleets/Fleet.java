
package com.amneziac.galaga.fleets;

import com.amneziac.galaga.galaga.Level1;
import java.util.ArrayList;
import com.amneziac.galaga.objects.VisibleObject;


public abstract class Fleet extends ArrayList<VisibleObject> {
    
    protected Level1 parent;
    
    public Fleet(Level1 parent)
    {
        super();
        this.parent = parent;
    }
    
    public void actionPerformed()
    {
        for(int i = 0; i < size(); i++)
        {
            get(i).actionPerformed();
        }
    }
    
    public abstract void removeDeadObjects();
}