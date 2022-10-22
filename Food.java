import java.awt.Color;
import java.awt.Graphics;

public class Food{
    private Color color;        //this
    private int size;
    private Point point;

    public Food(int size, Color color, Point p){
        this.size=size;
        this.color=color;
        point=p;
    }
    
    public void setPoint(Point p){
        point = p;
    }
    public Point getPoint(){
        return point;
    }
    void randomNewFood(){
    double x = Math.random()*Config.SIZE_X_SEG;
        double y = Math.random()*Config.SIZE_Y_SEG;
        point.setX((int)x);
        point.setY((int)y);
    }
    public void drawFood(Graphics g){
 //       randomNewFood();
        g.setColor(color);
        g.fillOval(point.getX()*Config.SIZE_SEG, point.getY()*Config.SIZE_SEG, size, size);


    }

}