
package curves;

import java.awt.Point;
import java.awt.Rectangle;
import java.awt.image.ImageObserver;
import java.util.Random;

public class RandomPath extends Curve {
    
    int d2x, d2y;
    Random random;
    Rectangle marginRectangle;
    
    public RandomPath ()
    {        
        random = new Random();
        dx = random.nextInt(10) - 5;
        dy = random.nextInt(10) - 5;
        d2x = random.nextInt(4) - 2;
        d2y = random.nextInt(4) - 2;
        
        maxMoveCount = 300;
        
        marginRectangle = new Rectangle(75, 75, W - 75, H - 75);
    }

    @Override
    public Point nextLocation(Point location)
    {
        if (location.x < 200 || location.x > W - 200 || location.y < 200 || location.y > H - 200)
        {
            if (location.x < 200 || location.x > W - 200)
            {
                dx = 0 - dx;
                d2x = 0;
            }

            if (location.y < 200 || location.y > H - 200)
            {
                dy = 0 - dy;
                d2y = 0;
            }
        }
        
        else if(moveCount >= maxMoveCount)
        {
            dx = random.nextInt(10) - 5;
            dy = random.nextInt(10) - 5;
            d2x = random.nextInt(2) - 2;
            d2y = random.nextInt(2) - 2;
            
            moveCount = 0;
        }
        
        else if (moveCount % 5 == 5)
        {
            dx += d2x;
            dy += d2y;
        }
        
        moveCount++;
        
        return new Point (location.x + dx, location.y + dy);
    }
}
