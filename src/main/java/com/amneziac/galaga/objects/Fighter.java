
package com.amneziac.galaga.objects;

import com.amneziac.galaga.io.AudioPlayer;
import com.amneziac.galaga.fleets.FighterFleet;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;
import com.amneziac.galaga.objects.ammo.Ammo;
import com.amneziac.galaga.objects.guns.Gun;

public abstract class Fighter extends VisibleObject {
    
    protected int healthPoints;
    protected FighterFleet parent;
    protected ArrayList<Ammo> ammo;
    protected ArrayList<Gun> guns;
    protected int ammoVelocity;
    protected static AudioPlayer hitSound;
    protected static String hitSoundURL = "hit.wav";
    
    public Fighter (Point startingLocation, FighterFleet parent)
    {
        super(startingLocation);
        ammo = new ArrayList();
        guns = new ArrayList();
        this.parent = parent;
    }
    
    public Fighter(Point startingLocation, FighterFleet parent, Dimension size)
    {
        super(startingLocation, size);
        ammo = new ArrayList();
        guns = new ArrayList();
        this.parent = parent;
    }
    
    public int getHealth()
    {
        return healthPoints;
    }
    
    @Override
    public void paint(Graphics g)
    {
        super.paint(g);
        paintAmmo(g);
    }
    
    public void paintAmmo(Graphics g)
    {
        for(int i = 0; i < ammo.size(); i++)
        {
            Ammo a = ammo.get(i);
            a.paint(g);
        }
    }
    
    public ArrayList<Ammo> getAmmo()
    {
        return ammo;
    }
    
    public void removeDeadAmmo()
    {
        for(int i = ammo.size() - 1; i >= 0; i--)
        {
            if(ammo.get(i).isDestroyed())
                ammo.remove(i);
        }
    }
    
    public void fire()
    {
        for (Gun gun : guns)
        {
            gun.fire();
        }
    }
    
    public void collisionDetected(Ammo ammo)
    {
        healthPoints -= ammo.getDamage();
        if(healthPoints <= 0)
        {
            parent.fighterDestroyed(this);
        }
        
        else
            hitSound.play();
        
        ammo.setDestroyed(true);
    }
    
    @Override
    public void actionPerformed()
    {
        for (Ammo i : ammo)
        {
            i.actionPerformed();
        }
    }
    
    public static void initializeAudio()
    {
        hitSound = AudioPlayer.newUnchecked(hitSoundURL);
    }
}
