package com.amneziac.galaga.fleets;

import com.amneziac.galaga.objects.Bug2;
import com.amneziac.galaga.formations.Formation;
import com.amneziac.galaga.galaga.Game;
import com.amneziac.galaga.galaga.Level1;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.Timer;
import com.amneziac.galaga.objects.*;
import com.amneziac.galaga.objects.ammo.Ammo;

public class BugFleet extends FighterFleet {
    
    final static int W = Game.PANEL_SIZE.width;
    final static int H = Game.PANEL_SIZE.height;
    
    protected boolean createBug = true;
    protected int startX;
    protected int fleetSize;
    protected int bugCount = 0;
    protected Formation formation;
    protected int period;  // Period between bug instantiations
    protected int bugType = 0;

    public BugFleet(int bugType, int fleetSize, Formation formation, Level1 parent)
    {
        super(parent);
        spacer = 40;
        this.bugType = bugType;
        this.fleetSize = fleetSize;
        this.formation = formation;
        period = this.formation.get(0).getDx();
        
        Game.timer.addActionListener(new TimerListener());
//        startX = (int)(W*xStartRatio);
        
//        galaga.Game.timer.addActionListener(this);
    }
    
    public BugFleet(int bugType, int fleetSize, int period, Formation formation, Level1 parent)
    {
        super(parent);
        spacer = 40;
        
        this.bugType = bugType;
        this.fleetSize = fleetSize;
        this.formation = formation;
        this.period = period;
        
        Game.timer.addActionListener(new TimerListener());
//        startX = (int)(W*xStartRatio);
        
//        galaga.Game.timer.addActionListener(this);
    }
    
    private void addBug()
    {
        Point startingLocation;
        startingLocation = new Point (formation.getStart().x, formation.getStart().y - Bug1.DEFAULT_SIZE.height);
        
        switch (bugType)
        {
            case 1:
                add(new Bug1(startingLocation, this));
                break;
            case 2:
                add(new Bug2(startingLocation, this));
                break;
            case 3:
                add(new Bug3(startingLocation, this));
                break;
        }
        
        bugCount++;
    }
    
    public void setTimeSpacer(int period)
    {
        this.period = period;
    }
    
    public Formation getFormation()
    {
        return formation;
    }

    @Override
    public void actionPerformed()
    {
        super.actionPerformed();
        
        Rectangle marginRectangle = new Rectangle(spacer, spacer, W - spacer, H - spacer);
        
//        if (count == 0 ||
//                (count < fleetSize && marginRectangle.contains(get(size()-1))))
        if (createBug)
        {
            addBug();
            createBug = false;
        }
        
        for (int i = 0; i < size(); i++)
        {
            Bug bug = (Bug) get(i);

            for (int j = 0; j < parent.fleets.get(1).size(); j++)
            {
                Ship ship = (Ship) parent.fleets.get(1).get(j);

                ArrayList<Ammo> ammoArray = Ship.class.cast(ship).getAmmo();

                for(int k = 0; k < ammoArray.size(); k++)
                {
                    Ammo ammo = ammoArray.get(k);

                    if (Game.collision(bug, ammo))
                    {
                        Bug.class.cast(bug).collisionDetected(ammo);
                        Level1.enemiesDestroyed++;
                    }
                }
            }
        }
    }
    
    private class TimerListener implements ActionListener {

        private int time = 0;
        public TimerListener(){}

        @Override
        public void actionPerformed(ActionEvent ae)
        {
            time++;
            
            if (time % period == 0 && bugCount < fleetSize)
                createBug = true;
        }
    }

}
