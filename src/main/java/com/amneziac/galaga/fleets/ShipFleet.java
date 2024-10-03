
package com.amneziac.galaga.fleets;

import com.amneziac.galaga.assignment5.Main;
import com.amneziac.galaga.galaga.Game;
import com.amneziac.galaga.galaga.Level1;
import java.awt.Point;
import java.util.ArrayList;
import javax.swing.JApplet;
import com.amneziac.galaga.objects.Bug;
import com.amneziac.galaga.objects.Ship;
import com.amneziac.galaga.objects.ammo.Ammo;

public class ShipFleet extends FighterFleet {
    
    public ShipFleet (Level1 parent)
    {
        super(parent);
        spacer = 4;
    }
    
    public void addShip(Game game)
    {
        Ship ship;
        Point startingLocation;
        
        if (size() == 0)
        {
            startingLocation = new Point(galaga.Game.PANEL_SIZE.width/2 - Ship.DEFAULT_SIZE.width/2,
                                         galaga.Game.PANEL_SIZE.height  - Ship.DEFAULT_SIZE.height);
        }
        
        else
        {
            int x = (int) (get(size()-1).getX() + Ship.DEFAULT_SIZE.width + spacer);
            int y = (int) get(size()-1).getY();

            startingLocation = new Point(x, y);
        }
            
        ship = new Ship(startingLocation, this);
        game.addKeyListener(ship);
        add(ship);
    }
    
    @Override
    public void removeDeadObjects()
    {
        super.removeDeadObjects();
        
        if (isEmpty())
        {
//            try {Thread.sleep(1000);} catch (InterruptedException ex) {}
            
            Main.endGalaga(Level1.game.getScore());
        }
    }
    
    @Override
    public void actionPerformed()
    {
        super.actionPerformed();
        
        for (int i = 0; i < size(); i++)
        {
            Ship ship = (Ship) get(i);
            
            for (int j = 2; j < parent.fleets.size(); j++)
            {
                BugFleet bugFleet = (BugFleet) (parent.fleets.get(j));
                
                for (int k = 0; k < bugFleet.size(); k++)
                {
                    Bug bug = (Bug)bugFleet.get(k);                    
                    ArrayList<Ammo> ammoArray = Bug.class.cast(bug).getAmmo();

                    for(int n = 0; n < ammoArray.size(); n++)
                    {
                        Ammo ammo = ammoArray.get(n);

                        if (Game.collision(ship, ammo))
                        {
                            Ship.class.cast(ship).collisionDetected(ammo);
                        }
                    }
                }
            }
        }
    }
}
