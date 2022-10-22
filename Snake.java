import java.util.ArrayList;

import javax.swing.plaf.ColorUIResource;

import java.awt.Color;

public class Snake {
    private Color color;
    private Color colorHead;
    private Color colorColita;
    private ArrayList <Point> body = new ArrayList<Point>();
    private int speed;
    //public enum Direction {UP, DOWN, LEFT, RIGHT}
    //private Direction dir = Direction.DOWN ;
    public static final int UP =0, DOWN = 1, LEFT = 2, RIGHT = 3; 
    private int dir = DOWN;
    

    

    public Snake(){
        body.add(new Point(5, 2));
        body.add(new Point(4, 2));
        body.add(new Point(3, 2));
        color = Color.GREEN;
        colorHead = new ColorUIResource(57, 130, 5);
        colorColita = new ColorUIResource(104, 188, 45);
        speed = 1;
    }

    public int getSpeed() {
        return speed;
    }
    public void setSpeed(int speed) {
        this.speed = speed;
    }
    public Color getColor() {
        return color;
    }
    public Color getColorHead() {
        return colorHead;
    }
    public Color getColorColita() {
        return colorColita;
    }
    public void setColor(Color color) {
        this.color = color;
    }
    public ArrayList<Point> getBody() {
        return body;
    }
    public void setBody(ArrayList<Point> body) {
        this.body = body;
    }
    public int getDir() {
        return dir;
    }
    public void setDir(int dir) {
        this.dir = dir;
    }

}
