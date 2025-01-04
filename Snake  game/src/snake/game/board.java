package snake.game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class board extends JPanel implements ActionListener {

    private  Image apple;
    private Image dot;
    private  Image head1;

    private final int all_dots=900;
    private final int dot_Size=10;
    private  final int Random_position=29;

    private int apple_x;
    private int apple_y;

    private  final int x[]=new int[all_dots];
    private final int y[]=new int[all_dots];

    private boolean leftDirection=false;
    private  boolean rightDirection=true;
    private  boolean updirection=false;
    private boolean downdirection=false;

    private boolean ingame=true;

    private Timer timer;

    private  int dots;
    board(){
        addKeyListener(new TAdapter());
      setBackground(Color.BLACK);
      setPreferredSize(new Dimension(300,300));
      setFocusable(true);

      loadImages();
      initGame();
    }
    public void loadImages(){
     ImageIcon i1=new ImageIcon(ClassLoader.getSystemResource("icon/apple.jpeg"));
     apple=i1.getImage();
     ImageIcon i2=new ImageIcon(ClassLoader.getSystemResource("icon/dot.jpeg")) ;
     dot=i2.getImage();
     ImageIcon i3=new ImageIcon(ClassLoader.getSystemResource("icon/head1.jpeg"));
     head1=i3.getImage();
    }
    public  void initGame(){
        dots=3;
        for(int i=0;i<dots;i++){

         y[i]=50;
         x[i]=50-dot_Size*i;
        }
        locateapple();
        timer=new Timer(140,this);
        timer.start();
    }
    public void locateapple(){
        int r=(int)(Math.random() *Random_position);
        apple_x=r*dot_Size;
         r=(int)(Math.random()*Random_position);
         apple_y=r*dot_Size;
    }
    public void paintComponent(Graphics g){
        super.paintComponent(g);

        draw(g);
    }
    public void draw(Graphics g) {
        if (ingame) {
            g.drawImage(apple, apple_x, apple_y, this);

            for (int i = 0; i < dots; i++) {
                if (i == 0) {
                    g.drawImage(head1, x[i], y[i], this);
                } else {
                    g.drawImage(dot, x[i], y[i], this);
                }
            }
            Toolkit.getDefaultToolkit().sync();
        }else {
            gameover(g);
        }
    }
    public void gameover(Graphics g){
        String msg="GAME OVER!";
        Font font=new Font("SAN_SARIF",Font.BOLD,14);
        FontMetrics matrices=getFontMetrics(font);
        g.setColor(Color.WHITE);
        g.setFont(font);
        g.drawString(msg,(300-matrices.stringWidth(msg))/2,300/2);
    }
  public void move(){
        for(int i=dots;i>0;i--){
            x[i]=x[i-1];
            y[i]=y[i-1];
        }
        if(leftDirection){
            x[0]=x[0]-dot_Size;
        }
      if(rightDirection){
          x[0]=x[0]+dot_Size;
      }
      if(updirection){
          y[0]=y[0]-dot_Size;
      }
      if(downdirection){
          y[0]=y[0]+dot_Size;
      }
//        x[0]+=dot_Size;
//        y[0]+=dot_Size;
  }
  public void checkapple(){
        if((x[0]==apple_x)&&(y[0]==apple_y)){
            dots++;
            locateapple();
        }
  }
  public  void checkcolision(){
        for(int z=dots;z>0;z--){
            if((z>4)&&(x[0]==x[z])&&(y[0]==y[z])){
                ingame=false;
            }
        }
        if(y[0]>=300){
            ingame=false;
        }
      if(x[0]>=300){
          ingame=false;
      }
      if(y[0]<0){
          ingame=false;
      }
      if(x[0]<0){
          ingame=false;
      }
      if(!ingame){
          timer.stop();
      }
  }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (ingame) {
            checkapple();
            checkcolision();
            move();

        }
        repaint();
    }
 public class TAdapter extends KeyAdapter{
     @Override
     public void keyPressed(KeyEvent e) {
         int key=e.getKeyCode();
         if(key==KeyEvent.VK_LEFT && (!rightDirection)){
             leftDirection=true;
             updirection=false;
             downdirection=false;
         }
         if(key==KeyEvent.VK_RIGHT && (!leftDirection)){
             rightDirection=true;
             updirection=false;
             downdirection=false;
         }
         if(key==KeyEvent.VK_UP && (!downdirection)){
             updirection=true;
             leftDirection=false;
             rightDirection=false;
         }
         if(key==KeyEvent.VK_DOWN && (!updirection)){
             downdirection=true;
             leftDirection=false;
             rightDirection=false;
         }
     }
 }
}
