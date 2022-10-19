import java.util.ArrayList;

import java.awt.Color;

public class Snake {
    private Color color;
    private ArrayList <Point> body = new ArrayList<Point>();
    private int speed;

    public Snake(){
        body.add(new Point(5, 2));
        body.add(new Point(4, 2));
        body.add(new Point(3, 2));
        color = Color.GREEN;
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
    public void setColor(Color color) {
        this.color = color;
    }
    public ArrayList<Point> getBody() {
        return body;
    }
    public void setBody(ArrayList<Point> body) {
        this.body = body;
    }



    

}
