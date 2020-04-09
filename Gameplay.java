import java.awt.Color;
import java.awt.Graphics;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Gameplay extends JPanel implements KeyListener, ActionListener {
	private ImageIcon titleImage;
	private ImageIcon snakeimage;
	
	private ImageIcon leftmouth;
	private ImageIcon rightmouth;
	private ImageIcon upmouth;
	private ImageIcon downmouth;
	
	private ImageIcon enemy;
	
	private int snakeXLength[]= new int[750];
	private int snakeYLength[]= new int[750];
	
	private int enemyXPos[]= {25, 50, 75, 100, 125, 150, 175, 200, 225, 250, 275, 300, 325, 350, 375, 400, 425, 450, 475, 500, 525, 550, 575, 600, 625, 650, 675, 700, 725, 750, 775, 800, 825, 850};
	private int enemyYPos[]= {75, 100, 125, 150, 175, 200, 225, 250, 275, 300, 325, 350, 375, 400, 425, 450, 475, 500, 525, 550, 575, 600, 625};
	
	private Random random= new Random();
	private int xpos= random.nextInt(34);
	private int ypos= random.nextInt(23);
	
	private int bodylength= 3;  // default
	private int moves= 0;
	
	private boolean left= false;
	private boolean right= false;
	private boolean up= false;
	private boolean down= false;
	
	private boolean collision= false;
	
	private Timer timer;
	private int delay= 100;
	
	private int score= 0;
	
	public Gameplay() {
		addKeyListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
		timer= new Timer(delay, this);
		timer.start();
		
	}
	
	public void paint(Graphics g) {
		if(moves == 0)
		{
			snakeXLength[2]= 50;
			snakeXLength[1]= 75;
			snakeXLength[0]= 100;
			
			snakeYLength[2]= 100;
			snakeYLength[1]= 100;
			snakeYLength[0]= 100;
		}
		//title image border
		g.setColor(Color.white);
		g.drawRect(24, 10, 851, 55);
		
		//title image
		titleImage= new ImageIcon("snaketitle.jpg");
		titleImage.paintIcon(this, g, 25, 11);
		
		//gameplay border
		g.setColor(Color.WHITE);
		g.drawRect(24, 74, 851, 577);
		
		//gameplay background
		g.setColor(Color.black);
		g.fillRect(25, 75, 850, 575);
		
		rightmouth= new ImageIcon("rightmouth.png");
		rightmouth.paintIcon(this, g, snakeXLength[0], snakeYLength[0]);
		
		for(int i= 0; i < bodylength; i++) {
			if(i == 0)
			{
				if(left)
				{
					leftmouth= new ImageIcon("leftmouth.png");
					leftmouth.paintIcon(this, g, snakeXLength[0], snakeYLength[0]);
				}
				if(right)
				{
					rightmouth= new ImageIcon("rightmouth.png");
					rightmouth.paintIcon(this, g, snakeXLength[0], snakeYLength[0]);
				}
				if(up)
				{
					upmouth= new ImageIcon("upmouth.png");
					upmouth.paintIcon(this, g, snakeXLength[0], snakeYLength[0]);
				}
				if(down)
				{
					downmouth= new ImageIcon("downmouth.png");
					downmouth.paintIcon(this, g, snakeXLength[0], snakeYLength[0]);
				}
			}
			else if(i > 0)
			{
				snakeimage= new ImageIcon("snakeimage.png");
				snakeimage.paintIcon(this, g, snakeXLength[i], snakeYLength[i]);
			}
		}

		//length
		g.setColor(Color.white);
		g.setFont(new Font("arial", Font.PLAIN, 15));
		g.drawString("Length: " +bodylength, 780, 30);
		
		//score
		g.setColor(Color.white);
		g.setFont(new Font("arial", Font.PLAIN, 15));
		g.drawString("Score: " +score, 780, 50);
		
		//snake food
		enemy= new ImageIcon("enemy.png");
		if(enemyXPos[xpos] == snakeXLength[0] && enemyYPos[ypos] == snakeYLength[0])
		{
			score++;
			bodylength++;
			xpos= random.nextInt(34);
			ypos= random.nextInt(23);
		}
		enemy.paintIcon(this, g, enemyXPos[xpos], enemyYPos[ypos]);
		
		for(int i= 1; i<bodylength; i++)
		{
			if(snakeXLength[i] == snakeXLength[0] && snakeYLength[i] == snakeYLength[0] && moves > 1)
			{
				collision= true;
				left= right= up= down= false;
				
				//game over msg
				g.setColor(Color.white);
				g.setFont(new Font("arial", Font.PLAIN, 50));
				g.drawString("GAME OVER!", 300, 300);
				
				//continue msg
				g.setColor(Color.white);
				g.setFont(new Font("arial", Font.PLAIN, 20));
				g.drawString("Press the space bar to restart", 325, 340);
			}
		}
		//clear resources at each frame/UI component
		g.dispose();
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
		
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_SPACE)
		{
			left= right= up= down= collision= false;
			bodylength= 3;
			moves= 0;
			score= 0;
			xpos= random.nextInt(34);
			ypos= random.nextInt(23);
			repaint();
		}
		if(e.getKeyCode() == KeyEvent.VK_LEFT && !collision)
		{
			if(!right)
			{
				left= true;
				moves++;
			}
			up= false;
			down= false;
		}
		if(e.getKeyCode() == KeyEvent.VK_RIGHT && !collision)
		{
			if(!left)
			{
				right= true;
				moves++;
			}
			up= false;
			down= false;
		}
		if(e.getKeyCode() == KeyEvent.VK_UP && !collision)
		{
			if(!down)
			{
				up= true;
				moves++;
			}
			right= false;
			left= false;
		}
		if(e.getKeyCode() == KeyEvent.VK_DOWN && !collision)
		{
			if(!up)
			{
				down= true;
				moves++;
			}
			right= false;
			left= false;
		}
	}
	
	@Override
	public void keyReleased(KeyEvent e) {
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		timer.start();
		if(right)
		{
			for(int i=bodylength-1; i>=0; i--)
				snakeYLength[i+1]= snakeYLength[i];
			
			for(int i=bodylength; i>=0; i--)
			{
				if(i == 0)
					snakeXLength[i]= snakeXLength[i] + 25;
				else
					snakeXLength[i]= snakeXLength[i-1];
				
				if(snakeXLength[i] > 850)
					snakeXLength[i]= 25;
			}
			
			repaint();
		}
		if(left)
		{
			for(int i=bodylength-1; i>=0; i--)
				snakeYLength[i+1]= snakeYLength[i];
			for(int i=bodylength; i>=0; i--)
			{
				if(i == 0)
					snakeXLength[i]= snakeXLength[i] - 25;
				else
					snakeXLength[i]= snakeXLength[i-1];
				
				if(snakeXLength[i] < 25)
					snakeXLength[i]= 850;
			}
			
			repaint();
		}
		if(up)
		{
			for(int i=bodylength-1; i>=0; i--)
				snakeXLength[i+1]= snakeXLength[i];
			for(int i=bodylength; i>=0; i--)
			{
				if(i == 0)
					snakeYLength[i]= snakeYLength[i] - 25;
				else
					snakeYLength[i]= snakeYLength[i-1];
				
				if(snakeYLength[i] < 75)
					snakeYLength[i]= 625;
			}
			
			repaint();
		}
		if(down)
		{
			for(int i=bodylength-1; i>=0; i--)
				snakeXLength[i+1]= snakeXLength[i];
			for(int i=bodylength; i>=0; i--)
			{
				if(i == 0)
					snakeYLength[i]= snakeYLength[i] + 25;
				else
					snakeYLength[i]= snakeYLength[i-1];
				
				if(snakeYLength[i] > 625)
					snakeYLength[i]= 75;
			}
			
			repaint();
		}
		
	}
}
