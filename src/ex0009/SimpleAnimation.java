
package ex0009;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;


public class SimpleAnimation implements KeyListener
{

  private Canvas canvas;
  private BufferedImage trumpImage;
  private int trumpX,trumpWidth,trumpY=350;
  private boolean gameOver=false;
  
  public static void main(String[] args) throws IOException
  {
    SimpleAnimation ani = new SimpleAnimation();
    ani.go();
  }
  
   public void go() throws IOException
  {
    JFrame frame = new JFrame();
    frame.addKeyListener(this);
    frame.setDefaultCloseOperation(
            JFrame.EXIT_ON_CLOSE); 
    canvas = new Canvas();
    frame.getContentPane().add(canvas, BorderLayout.CENTER);
    frame.setTitle("Trump'n'Run");
    frame.pack();
    frame.setVisible(true);
    LocalDateTime now=LocalDateTime.now();
    int currentHour=now.getHour();
    // TODO: Change background image depending on hour.1
    gameLoop();
  }
  public void gameLoop()
  {
  while(!gameOver){
      try {
          canvas.repaint();
          Thread.sleep(4);
      } catch (InterruptedException ex) {
          
      }
  }    
  }
  @Override
  public void keyTyped(KeyEvent e){}

  @Override
  public void keyPressed(KeyEvent e)
  {
    if(e.getKeyCode()==KeyEvent.VK_D){
      trumpX = trumpX + 10;
      if((trumpX + trumpWidth) > 
              canvas.getWidth())
      {
        trumpX = 
          canvas.getWidth() - trumpWidth;
      }
      
    }
    if(e.getKeyCode()==KeyEvent.VK_A){
      trumpX = trumpX - 10;
      if(trumpX < 0 )
      {
        trumpX = 0;
      }
    }
    if(e.getKeyCode()==KeyEvent.VK_SPACE){
      
    } 
  }

  @Override
  public void keyReleased(KeyEvent e){}
  
  
  
  public class Canvas extends JPanel
  {
      private static final int HEIGHT = 600;
      private static final int WIDTH = 800;
    
    
    public Canvas() throws IOException{
      
        super();
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        URL trumpUrl = getClass().getResource("../images/TrumpRechts1.0.png");
        trumpImage = ImageIO.read(trumpUrl);
        trumpWidth=trumpImage.getWidth();
        trumpX = (WIDTH - trumpWidth) / 2;
    }
    
    @Override
    protected void paintComponent(Graphics g)
    {
      Graphics2D g2d = (Graphics2D) g;
      g2d.fillRect(0, 0, WIDTH, HEIGHT);
      g2d.drawImage(trumpImage, trumpX, trumpY, null);
    }  
  }
  
  
}



