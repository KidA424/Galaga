
package curves;

import java.awt.Point;

public class Parabola extends Curve
{
    protected double dyRatio;

    public Parabola (double dx, double dyRatio)
    {
        this.dx = (int)dx;
        this.dyRatio = dyRatio;
    }
    
    public Parabola (double dx, double dyRatio, int maxMoveCount)
    {
        super(maxMoveCount);
        this.dx = (int)dx;
        this.dyRatio = dyRatio;
    }

    @Override
    public Point nextLocation(Point location)
    {
        dy = (int) (-1*(location.x - W/2)*dyRatio);

        return new Point (location.x + dx, location.y + dy);
    }
    
//    public static Formation getParabolaFormation(double xStartRatio, double yStartRatio, double dx, double dyRatio)
//    {
//        Formation form = new Formation(xStartRatio, yStartRatio);
//        form.add(CurveFactory.getParabola(dx, dyRatio));
//        return form;
//    }
}