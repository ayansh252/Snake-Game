package snake.game;

import javax.swing.*;

public class snake extends JFrame {

    snake(){

        super("Snake Game");
        add(new board());
        pack();
//        setSize(300,300);
        setLocationRelativeTo(null);
        setResizable(false);
//        setVisible(true);


    }
    public static void main(String[] args) {

        new snake().setVisible(true);
    }
}
