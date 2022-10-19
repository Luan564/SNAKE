import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Graphics;

import java.awt.Color;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.Timer;
import javax.swing.WindowConstants;
import javax.swing.plaf.DimensionUIResource;


public class Board extends JPanel implements ActionListener{
    Snake snake;
    
    public static void main(String args[]){
        Board b = new Board();
        
    }

    public Board(){
        snake = new Snake();
        Timer t = new Timer(500, this);{
        t.start();
        }

        
        setPreferredSize(new DimensionUIResource(Config.SIZE_WIN_W, Config.SIZE_WIN_H));
        setBackground(Color.GRAY);

        JFrame window = new JFrame("SNAKE");
        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);    
        window.setSize(Config.SIZE_WIN_W, Config.SIZE_WIN_H);      
        window.add(this);                     
        window.pack();                                
        window.setResizable(false);          
        window.setLocationRelativeTo(null);         
        window.setVisible(true);                    
        
        window.add(this);
    }

    @Override
    public void paintComponent(Graphics g){
        g.setColor(snake.getColor());
        for(Point p : snake.getBody()){
            g.fillOval(p.getX()*Config.SIZE_SEG , 
            p.getY()*Config.SIZE_SEG, 
            Config.SIZE_SEG , 
            Config.SIZE_SEG);
        }



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
       



    }

    @Override
    public void actionPerformed(ActionEvent e) {
        ArrayList<Point> body = new ArrayList<Point>();
        body = snake.getBody();
        for(int i = body.size()-1; i>0 ; i--){
            
            int x = body.get(i-1).getX();
            body.get(i).setX(x);
            int y = body.get(i-1).getY();
            body.get(i).setY(y);
        }
        int y = body.get(Config.HEAD).getY();
        y+=1;
        body.get(Config.HEAD).setY(y);
        repaint();
        
    }
}