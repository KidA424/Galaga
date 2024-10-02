
package curves;

import java.awt.Point;


public class Line extends Curve {
    
    protected double slope;
    
    public Line(int dx, double slope)
    {
        this.dx = dx;
        this.slope = 0 - slope;
        dy = (int)(getSlope()*dx);
    }
    
    public Line(int dx, double slope, int maxMoveCount)
    {
        super(maxMoveCount);
        this.dx = dx;
        this.slope = 0 - slope;
        dy = (int)(getSlope()*dx);
    }
    
    public double getSlope()
    {
        return slope;
    }

    @Override
    public Point nextLocation(Point location)
    {
        return new Point (location.x + dx, location.y + dy);
    }
}
