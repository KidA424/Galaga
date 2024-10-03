package com.amneziac.galaga.objects.ammo;

import com.amneziac.galaga.galaga.Game;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import com.amneziac.galaga.objects.VisibleObject;

public abstract class Ammo extends VisibleObject {
    
    public static final Dimension DEFAULT_SIZE = new Dimension(8,20);
    protected ArrayList<ActionListener> listeners = new ArrayList();
    
    protected int damage = 1;
    protected int dy = 12;
    protected int dx = 0;
    protected ArrayList<Ammo> parentArray;
    protected int element;
    
    public Ammo (Point startingLocation, int velocityRatio, ArrayList<Ammo> array)
    {
        super(startingLocation, DEFAULT_SIZE);
        
        setLocation(new Point((int)x, (int)y - (int)height));
        parentArray = array;
//        galaga.Game.timer.addActionListener(this);
        dy = dy*velocityRatio;
    }
    
    public Ammo (Point startingLocation, int velocityRatio, ArrayList<Ammo> array, Dimension size)
    {
        super(startingLocation, size);
        
        setLocation(new Point((int)x, (int)y - (int)height));
        parentArray = array;
//        galaga.Game.timer.addActionListener(this);
        dy = dy*velocityRatio;
    }
    
    public int getDamage()
    {
        return damage;
    }
    
    @Override
    public void actionPerformed() {
//        if (y + height < 0 || y - height > galaga.Game.APPLET_SIZE.height)
        if (!Game.RECTANGLE.contains(this))
            setDestroyed(true);
        else
            y += dy;
    }
}