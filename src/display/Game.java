package display;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;

import Charactor.*;
import Element.Element;
import event.Event;
import java.awt.geom.RoundRectangle2D;
import java.util.ArrayList;
import java.util.Random;

public class Game extends JPanel implements KeyListener{

	private static final long serialVersionUID = 1L;
	
	private static int speed = 30, dogSize = 60 ,waveHeight = 50;
	private static int base=400,xStart = 1000;
	private long point = 0,lastPress=0;
	private boolean check;
        
	private Dog dog = new Dog(0,base-50);
	static Display display;
//	------------------Wave SIze ----------------------------
	private ArrayList<Wave> waveSet1 = makeWave1(10);
        private ArrayList<Wave> waveSet2 = makeWave2(10);
        private ArrayList<Wave> waveSet3 = makeWave3(10);
//--------------------Cloud--------------------------------
	private Environment[] envSet = makeEnv(2,Environment.CLOUD);
	private Environment building = new Environment(xStart-100,base-150,this,Environment.BUILDING,4);
	
		public Game(){
		this.setBounds(0,0,1000,600);
		this.addKeyListener(this);
		this.setLayout(null);
		this.setFocusable(true);
	}
	
	@Override
	public void paint(Graphics g) {
			try {
				super.paint(g);
				Graphics2D g2 = (Graphics2D) g;
				this.drawBackground(g2);
				//---POINT----
				g2.setFont(Element.getFont(30));
				g2.setColor(Color.white);
				g2.drawString("Point : "+point,750,40);
				//--- dog --
				g2.setColor(Color.RED);
				drawDogHealth(g2);
				g2.drawImage(dog.getImage(),dog.x,dog.y,dogSize,dogSize, null);
				//----Wave----
				for(Wave item : waveSet1) {
                                    drawWave(item,g2);    
				}
                                for(Wave item : waveSet2) {
                                    drawWave(item,g2);    
				}
                                for(Wave item : waveSet3) {
                                    drawWave(item,g2);    
				}
				this.point+=1;
			} catch (Exception e) {
				e.printStackTrace();
			}
	}
	private void drawBackground(Graphics2D g2) throws IOException {
			g2.drawImage(ImageIO.read(new File("img\\sky.png")),0,0,2000,1000, null);
			g2.drawImage(building.getImage(),building.x,building.y,500,200,null);
			g2.drawImage(ImageIO.read(new File("img\\dir.png")),0,base+10,2000,220, null);
			for(Environment item:envSet) {
				g2.drawImage(item.getImage(),item.x,item.y,250,160, null);
			}
	}
        
	private void drawDogHealth(Graphics2D g2) {
		try {
			g2.drawImage(ImageIO.read(new File("img\\heart.png")),10,20, 20,20,null);
			g2.setStroke(new BasicStroke(18.0f));
			g2.setColor(new Color(241, 98, 69));
			g2.drawLine(60, 30,60+dog.health,30);	
			g2.setColor(Color.white);
			g2.setStroke(new BasicStroke(6.0f));
			g2.drawRect(50,20, 200,20);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private ArrayList makeWave1(int size) {
                Random rand = new Random();
		ArrayList<Wave> waveSet1 = new ArrayList<Wave>();
                int far = 500;
		for(int i=0;i<size;i++) {
                        waveSet1.add(new Wave(xStart+far,base,speed,this));
			far+= 100+ rand.nextInt(0,500);
		}
		return waveSet1;
	}
	private ArrayList makeWave2(int size) {
		ArrayList<Wave> waveSet2 = new ArrayList<Wave>();
                Random rand = new Random();
		int far = 0;
		for(int i=0;i<size;i++) {
			waveSet2.add(new Wave(xStart+far,base-150,speed,this));
			far+= 200+ rand.nextInt(0,500);
		}
		return waveSet2;
	}
	private ArrayList makeWave3(int size) {
		ArrayList<Wave> waveSet3 = new ArrayList<Wave>();
                Random rand = new Random();
		int far = 250;
		for(int i=0;i<size;i++) {
			waveSet3.add(new Wave(xStart+far,base-300,speed,this));
			far+= 300+ rand.nextInt(0,500);
		}
		return waveSet3;
	}
	
	private Environment[] makeEnv(int size,int eType){
		Environment[] envSet = new Environment[size];
		int far = 0;
		for(int i=0;i<size;i++) {
			envSet[i] = new Environment(xStart+far,20,this,eType,10);
			far+=600;
		}
		return envSet;
	}
        
        public void checkEnd(){
            
        }
	
	private void drawWave(Wave wave,Graphics2D g2) {
                        
			g2.drawImage(wave.getImage(),wave.x ,(wave.y-waveHeight),50,waveHeight+10,null);
			if(Event.checkHit(dog,wave)){
					g2.setColor(new Color(240, 98, 69));
                                        System.out.println("hit");
					g2.setStroke(new BasicStroke(10.0f));
                                        g2.draw(new RoundRectangle2D.Double(5, 5, 977, 555, 0, 10));
                                        dog.health -= 20;
                                        wave.x += 10000;
					if(dog.health<=0){
						display.endGame(this.point);
						dog.health = new Dog().health;
						this.point = 0;	
					}
			}
	}
        
	
	@Override
	public void keyPressed(KeyEvent e) {
		if(System.currentTimeMillis() - lastPress > 600) {
			if(e.getKeyCode()==32||e.getKeyCode()==38) {
					dog.jump(this);
					lastPress = System.currentTimeMillis();
			}
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		//---
	}

	@Override
	public void keyReleased(KeyEvent e) {
		//---
	}
	
	public static void main(String[] arg) {
		 display = new Display();
	}

}
