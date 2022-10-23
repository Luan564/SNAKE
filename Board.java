import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Graphics;

import java.awt.Color;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.Timer;
import javax.swing.WindowConstants;
import javax.swing.plaf.ColorChooserUI;
import javax.swing.plaf.ColorUIResource;
import javax.swing.plaf.DimensionUIResource;


public class Board extends JPanel implements ActionListener, KeyListener{
    Snake snake;
    Food food;
    int PTS=0;
    int VIDAS =3;
    
    public static void main(String args[]){
        Board b = new Board();
        
    }

    public Board(){
        snake = new Snake();
        food = new Food(Config.SIZE_SEG, Color.RED, new Point(5, 6));
        Timer t = new Timer(80, this);{
        t.start();
        }
        setPreferredSize(new DimensionUIResource(Config.SIZE_WIN_W, Config.SIZE_TAB+Config.SIZE_WIN_H));
        setBackground(Color.GRAY);

        JFrame window = new JFrame("SNAKE");
        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);    
        window.setSize(Config.SIZE_WIN_W, Config.SIZE_WIN_H);      
        window.add(this);   
        window.addKeyListener(this);                  
        window.pack();                                
        window.setResizable(false);          
        window.setLocationRelativeTo(null);         
        window.setVisible(true);       

        
        window.add(this);
    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        ArrayList<Point> body = new ArrayList<Point>();
        body =snake.getBody();

        //Draw body
        for(int i=1 ; i<body.size() ; i++){
            Point p = body.get(i);
            g.setColor(snake.getColor());
            g.fillOval(p.getX()*Config.SIZE_SEG , 
            p.getY()*Config.SIZE_SEG, 
            Config.SIZE_SEG , 
            Config.SIZE_SEG);   
        }
        //Repain the Head
        Point p = body.get(Config.HEAD);
        g.setColor(snake.getColorHead());
        g.fillOval(p.getX()*Config.SIZE_SEG , 
        p.getY()*Config.SIZE_SEG, 
        Config.SIZE_SEG , 
        Config.SIZE_SEG); 
        
        //Repain the colita
        Point c = body.get(body.size()-1);
        g.setColor(snake.getColorColita());
        g.fillOval(c.getX()*Config.SIZE_SEG , 
        c.getY()*Config.SIZE_SEG, 
        Config.SIZE_SEG , 
        Config.SIZE_SEG); 
    
        //Draw lines

        for(int i = 0; i<Config.SIZE_WIN_W;){
            i += Config.SIZE_SEG;{
                g.setColor(Color.black);
                g.drawLine(i, 0, i, Config.SIZE_WIN_H);
            }
        }
        for(int i = 0; i<Config.SIZE_WIN_H;){
            i += Config.SIZE_SEG;{
                g.setColor(Color.black);
                g.drawLine(0, i, Config.SIZE_WIN_W, i);
            }
        }

        //Draw food
        food.drawFood(g);

        //Draw tab
        g.setColor(new ColorUIResource(110, 201, 56));
        g.fillRect(0, 500, 600, 100);
        g.setColor(Color.BLACK);
        g.drawString("Puntos: "+PTS, 100, 550);
        g.drawString("Vidas: "+VIDAS, 400, 550);
       
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
  
        ArrayList<Point> body = new ArrayList<Point>();
        body = snake.getBody();

        //Move to the snake
        for(int i = body.size()-1; i>0 ; i--){
            
            int x = body.get(i-1).getX();
            body.get(i).setX(x);
            int y = body.get(i-1).getY();
            body.get(i).setY(y);
        }

        
        //Change the direction 
        int y = body.get(Config.HEAD).getY();
        int x = body.get(Config.HEAD).getX();
        switch(snake.getDir()){
            case Snake.UP:
            y--;
            break;
            case Snake.DOWN:
            y++;
            break;
            case Snake.LEFT:
            x--;
            break;
            case Snake.RIGHT:
            x++;
            break;
        }
        body.get(Config.HEAD).setX(x);
        body.get(Config.HEAD).setY(y);

        //Kill the snake
        
        for(int i = 4; i<body.size() ; i++){
        if(body.get(Config.HEAD).getX()==(body.get(i).getX()) &&
           body.get(Config.HEAD).getY()==(body.get(i).getY())   ){
            VIDAS--;
            //System.out.println(VIDAS+ " Vidas Restantes");
            snake = new Snake();
            body = snake.getBody();
            if(VIDAS == 0){
                System.exit(0);
            }
            }
        }
        

        //Increase the size of the snake for the food 
        if( body.get(Config.HEAD).areTheSame(food.getPoint()) == true ){
            body.add(new Point(food.getPoint().getX(),food.getPoint().getY()));
            food.randomNewFood();
            PTS=PTS + 10;   //Falta mostrarlo en el Frame
            //System.out.println(PTS);
        }


        //Tunnel effect at end frame
        if( body.get(Config.HEAD).getX()>=Config.SIZE_WIN_W/Config.SIZE_SEG){
            body.get(Config.HEAD).setX(0);
        }
        if( body.get(Config.HEAD).getX()<0){
            body.get(Config.HEAD).setX((Config.SIZE_WIN_W/Config.SIZE_SEG)-1);
        }
        if( body.get(Config.HEAD).getY()>=Config.SIZE_WIN_H/Config.SIZE_SEG){
            body.get(Config.HEAD).setY(0);
        }
        if( body.get(Config.HEAD).getY()<0){
            body.get(Config.HEAD).setY((Config.SIZE_WIN_H/Config.SIZE_SEG)-1);
        }


        
        
        
        repaint();
        
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void keyPressed(KeyEvent e) {
        //Detected the Keyboard
        int codeKey = e.getKeyCode();
        switch(codeKey){
            case KeyEvent.VK_W:
            snake.setDir(Snake.UP);
            break;
            case KeyEvent.VK_S:
            snake.setDir(Snake.DOWN);
            break;
            case KeyEvent.VK_A:
            snake.setDir(Snake.LEFT);
            break;
            case KeyEvent.VK_D:
            snake.setDir(Snake.RIGHT);
            break;
        }
       // System.out.println(snake.getDir());
        
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // TODO Auto-generated method stub
        
    }

}