
package objects;

import fleets.FighterFleet;
import fleets.Fleet;
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