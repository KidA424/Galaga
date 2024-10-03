
package com.amneziac.galaga.objects;

import com.amneziac.galaga.fleets.FighterFleet;
import com.amneziac.galaga.fleets.Fleet;
import java.awt.Dimension;
import java.awt.Point;

public abstract class Enemy extends Fighter {
    
    public Enemy (Point startingLocation, FighterFleet parent)
    {
        super(startingLocation, parent);
    }
    
    public Enemy (Point startingLocation, FighterFleet parent, Dimension size)
    {
        super(startingLocation, parent, size);
    }
}