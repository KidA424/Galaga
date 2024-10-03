/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.amneziac.galaga.objects;

import com.amneziac.galaga.fleets.BugFleet;
import java.awt.Dimension;
import java.awt.Point;
import java.util.Random;
import com.amneziac.galaga.objects.ammo.RedLaser;
import com.amneziac.galaga.objects.guns.Gun;

public abstract class Bug extends Enemy {
    
    public static Random random = new Random();
    protected int fireFrequency = 8;
    
    public Bug(Point startingLocation, BugFleet parent)
    {        
        super(startingLocation, parent);
        healthPoints = 1;
        guns.add(new Gun(new Point(width/2, height), 1, RedLaser.class, this));
    }
    
    public Bug(Point startingLocation, BugFleet parent, Dimension size)
    {        
        super(startingLocation, parent, size);
        healthPoints = 1;
        guns.add(new Gun(new Point(width/2, height), 1, RedLaser.class, this));
    }
    
    @Override
    public void actionPerformed ()
    {
        super.actionPerformed();
        
        BugFleet.class.cast(parent).getFormation().move(this);
        
        if (random.nextInt(1000/fireFrequency) == 1)
        {
            guns.get(0).setCanFire(true);
            fire();
        }
    }

}