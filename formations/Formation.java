package formations;

import curves.Curve;
import java.awt.Point;
import java.util.ArrayList;
import objects.Bug;

public class Formation extends ArrayList<Curve> {
    
    final static int W = galaga.Game.PANEL_SIZE.width;
    final static int H = galaga.Game.PANEL_SIZE.height;
    protected Point start;
    protected int currentCurveIndex = 0;
    
    public Formation(double xStartRatio, double yStartRatio)
    {
        start = new Point((int)(W*xStartRatio), (int)(H*yStartRatio));
    }
    
    public Point getStart()
    {
        return start;
    }
    
    public void addCurve(Curve curve)
    {
        add(curve);
    }
    
    public void move(Bug bug)
    {
        // Check if bug is done with route
        if (currentCurveIndex == size() - 1 && bug.getAmmo().isEmpty()
                && !bug.isOnScreen() && get(currentCurveIndex).getMoveCount() > 1000)
            bug.setDestroyed(true);
        
        bug.setLocation(get(currentCurveIndex).nextLocation(bug.getLocation()));
        get(currentCurveIndex).incrementMoveCount();
        
    }
}