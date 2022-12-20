package Charactor;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Wave {

    public int speed;
    public int x;
    public int y;
    public int num = 0;
    public int point;
    public String vord;
    Timer timeMove;
    public int upSpeed = 2;
    public int upPoint;

    public Wave(int x, int y, int speed, String vord, int point, int c_point, JPanel page) {
        this.x = x;
        this.y = y;
        this.speed = speed;
        this.vord = vord;
        this.point = point;
        this.move(page, c_point);
    }

    public void move(JPanel page, int c_point) {
        this.timeMove = new Timer(0, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (c_point >= 100 + upPoint) {
                    upPoint += 100;
                    upSpeed += 2;
                    x -= (10 + upSpeed);
                } else {
                    x -= (10 + upSpeed);
                }
                page.repaint();
            }
        });
        this.timeMove.start();

    }

}
