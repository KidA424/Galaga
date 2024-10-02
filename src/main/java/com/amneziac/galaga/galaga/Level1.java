package com.amneziac.galaga.galaga;

import curves.Line;
import curves.Parabola;
import curves.RandomPath;
import fleets.BugFleet;
import fleets.ExplosionFleet;
import fleets.Fleet;
import fleets.ShipFleet;
import formations.Formation;
import java.applet.Applet;
import java.applet.AudioClip;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import objects.Ship;


public class Level1 implements Runnable {
    
    public static Game game;
    public static Thread levelThread;
    private static Thread thread;
    
    public static AudioClip audio;
    private static String audioURL = "file:" + Game.path + "Lords.wav";
    
    public static ArrayList<Fleet> fleets = new ArrayList();
    private static Formation formation;
    private static double xStartRatio;
    private static double yStartRatio;
    private static int bugType;
    private static int fleetSize;
    private static int period;
    
    public static int enemiesDestroyed = 0;
    
    public static boolean loop;
    
    public Level1 (Game game)
    {
        this.game = game;
        levelThread = new Thread(this);
        levelThread.start();
    }
    
    @Override
    public void run() {
        
        try
        {
            audio = Applet.newAudioClip(new URL(audioURL));
        } catch (MalformedURLException ex) {}
        
        audio.play();
        
        fleets.add(new ExplosionFleet(this));
        
        fleets.add(new ShipFleet(this));
        ((ShipFleet)fleets.get(1)).addShip(game);
        assignment5.Main.gameFrame.addKeyListener(Ship.class.cast(fleets.get(1).get(0)));
        
        Game.timer.start();
        
        while(true)
        {
            new Thread1(this);   
            allowThreadFinish();
    //        try {levelThread.sleep(10000);} catch (InterruptedException ex) {}

            new Thread2(this);
            new Thread3(this);
            allowThreadFinish();
            
            new Thread6(this);        
            new Thread5(this);
            allowThreadFinish();
            
            new Thread6(this);
            new Thread7(this);
            allowThreadFinish();
            
            new Thread8(this);           
        }
        
    }
    
    public static void allowThreadFinish()
    {
        loop = true;
        
        while(loop)
        {
            if (fleets.size() == 2)
                    loop = false;
            
            try
            {
                levelThread.sleep(1000);
            } catch (InterruptedException ex) {System.out.println("Interrupted");}
        }

    }
    
    private static void instantiateFormation()
    {
        formation = new Formation(xStartRatio, yStartRatio);
    }
    
    private class Thread1 implements Runnable
    {
        Level1 level;
        Thread thread1;
        
        public Thread1(){}
        
        public Thread1 (Level1 level)
        {
            this.level = level;
            thread1 = new Thread(this);
            thread1.start();
        }
        
        @Override
        public void run()
        {
            xStartRatio = .25;
            yStartRatio = 0;
            bugType = 1;
            fleetSize = 10;

            instantiateFormation();

            int dx = 8; // 4
            double dyRatio = .05; // .025

            formation.add(new Parabola(dx, dyRatio));

            level.fleets.add(new BugFleet(bugType, fleetSize, formation, level));
        }
    }
    
    private class Thread2 implements Runnable
    {
        Level1 level;
        Thread thread1;
        
        public Thread2(){}
        
        public Thread2 (Level1 level)
        {
            this.level = level;
            thread1 = new Thread(this);
            thread1.start();
        }
        
        @Override
        public void run()
        {
            xStartRatio = 0;
            yStartRatio = 1;
            bugType = 2;
            fleetSize = 10;

            instantiateFormation();

            int dx = 9;
            double slope = .9;

            formation.add(new Line(dx, slope));

            level.fleets.add(new BugFleet(bugType, fleetSize, formation, level));
        }
    }

    private class Thread3 implements Runnable
    {
        Level1 level;
        Thread thread1;
        
        public Thread3(){}
        
        public Thread3 (Level1 level)
        {
            this.level = level;
            thread1 = new Thread(this);
            thread1.start();
        }
        
        @Override
        public void run()
        {
            xStartRatio = 1;
            yStartRatio = 1;
            bugType = 3;
            fleetSize = 10;

            instantiateFormation();

            int dx = -9;
            double slope = -.9;

            formation.add(new Line(dx, slope));

            level.fleets.add(new BugFleet(bugType, fleetSize, formation, level));
        }
    }

    private class Thread4 implements Runnable
    {
        Level1 level;
        Thread thread;
        
        public Thread4(){}
        
        public Thread4 (Level1 level)
        {
            this.level = level;
            thread = new Thread(this);
            thread.start();
        }
        
        @Override
        public void run()
        {
            xStartRatio = .5;
            yStartRatio = .5;
            bugType = 2;
            fleetSize = 5;
            period = 20;

            instantiateFormation();

            formation.add(new RandomPath());

            level.fleets.add(new BugFleet(bugType, fleetSize, period, formation, level));
        }
    }
    
    private class Thread5 implements Runnable
    {
        Level1 level;
        Thread thread1;
        
        public Thread5(){}
        
        public Thread5 (Level1 level)
        {
            this.level = level;
            thread1 = new Thread(this);
            thread1.start();
        }
        
        @Override
        public void run()
        {
            xStartRatio = 1;
            yStartRatio = .3;
            bugType = 3;
            fleetSize = 5;

            instantiateFormation();

            int dx = -16;
            double slope = 0;

            formation.add(new Line(dx, slope));

            level.fleets.add(new BugFleet(bugType, fleetSize, formation, level));
        }
    }
    
    private class Thread6 implements Runnable
    {
        Level1 level;
        Thread thread1;
        
        public Thread6(){}
        
        public Thread6 (Level1 level)
        {
            this.level = level;
            thread1 = new Thread(this);
            thread1.start();
        }
        
        @Override
        public void run()
        {
            xStartRatio = 0;
            yStartRatio = 1;
            bugType = 2;
            fleetSize = 10;

            instantiateFormation();

            int dx = 10; // 4
            double dyRatio = -.02; // .025

            formation.add(new Parabola(dx, dyRatio));

            level.fleets.add(new BugFleet(bugType, fleetSize, formation, level));
        }
    }
    
    private class Thread7 implements Runnable
    {
        Level1 level;
        Thread thread1;
        
        public Thread7(){}
        
        public Thread7 (Level1 level)
        {
            this.level = level;
            thread1 = new Thread(this);
            thread1.start();
        }
        
        @Override
        public void run()
        {
            xStartRatio = 1;
            yStartRatio = 1;
            bugType = 3;
            fleetSize = 10;

            instantiateFormation();

            int dx = -10; // 4
            double dyRatio = .02; // .025

            formation.add(new Parabola(dx, dyRatio));

            level.fleets.add(new BugFleet(bugType, fleetSize, formation, level));
        }
    }
    
        private class Thread8 implements Runnable
    {
        Level1 level;
        Thread thread1;
        
        public Thread8(){}
        
        public Thread8 (Level1 level)
        {
            this.level = level;
            thread1 = new Thread(this);
            thread1.start();
        }
        
        @Override
        public void run()
        {
            xStartRatio = .75;
            yStartRatio = 0;
            bugType = 3;
            fleetSize = 10;

            instantiateFormation();

            int dx = -8; // 4
            double dyRatio = -.05; // .025

            formation.add(new Parabola(dx, dyRatio));

            level.fleets.add(new BugFleet(bugType, fleetSize, formation, level));
        }
    }
}
