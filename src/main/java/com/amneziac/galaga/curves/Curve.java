
package com.amneziac.galaga.curves;

import com.amneziac.galaga.galaga.Game;

import java.awt.Point;

public abstract class Curve
{
    final static int W = Game.PANEL_SIZE.width;
    final static int H = Game.PANEL_SIZE.height;
    protected int dx, dy;
    protected int moveCount = 0;
    protected int maxMoveCount = -1;

    public Curve(){}
    
    public Curve(int maxMoveCount)
    {
        this.maxMoveCount = maxMoveCount;
    }
    
    public abstract Point nextLocation(Point currentLocation);
    
    public int getDx()
    {
        return dx;
    }
    
    public int getMoveCount()
    {
        return moveCount;
    }
    
    public void incrementMoveCount()
    {
        moveCount++;
    }
    
    public boolean isDone()
    {
        if(maxMoveCount >= 0 && moveCount >= maxMoveCount)
            return true;
        else
            return false;
    }
}