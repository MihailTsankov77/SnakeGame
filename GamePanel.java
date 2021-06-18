package GameOfSnake;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.Timer;

public class GamePanel extends JPanel implements ActionListener{

	static final int SCREEN_WIDTH = 1000;
	static final int SCREEN_HEIGHT = 1000;
	
	static final int CELL_SIZE = 50;
	static final int CELLS = (SCREEN_WIDTH/CELL_SIZE)*(SCREEN_HEIGHT/CELL_SIZE);
	final int[] [] XY = new int [CELLS] [2];
	char direction = 'R';
	final int DELAY = 100;
	
	Timer timer;
	int snakeLenght;
	int score;
	int appleEaten;
	int appleX;
	int appleY;
	
	
	public GamePanel(){

		this.setPreferredSize(new Dimension(SCREEN_WIDTH,SCREEN_HEIGHT));

		this.setBackground(Color.black);

		this.setFocusable(true);

//		this.addKeyListener(new KeyListener());
//
		startGame();
	}
	
	private void startGame() {
		snakeLenght = 3;
		score = 0;
		appleEaten = 0;
		timer = new Timer(DELAY,this);
		timer.start();

	
		createNewApple();
	}
	
	public void createNewApple() {
		appleX = ((int)(Math.random()*(SCREEN_WIDTH/CELL_SIZE)))*CELL_SIZE;
		appleY = ((int)(Math.random()*(SCREEN_HEIGHT/CELL_SIZE)))*CELL_SIZE;
		appleEaten++;
	}
	
	
	
	public void move(){
System.out.println("gdf");
		for(int i = snakeLenght; i>0; i--) {
			XY[i][0] = XY[i-1][0];
			XY[i][1] = XY[i-1][1];
		}
			


		switch(direction) {

		case 'U':

			XY[0][1] = XY[0][1] - CELL_SIZE;

			break;

		case 'D':

			XY[0][1] = XY[0][1] + CELL_SIZE;

			break;

		case 'L':

			XY[0][0] = XY[0][0] - CELL_SIZE;

			break;

		case 'R':

			XY[0][0] = XY[0][0] + CELL_SIZE;
			
			break;

		}

		

	}


	@Override
	public void actionPerformed(ActionEvent e) {
		move();
		repaint();
	}
	
	
	
	
	
	
	
	
	
	
	public void paintComponent(Graphics g) {

		super.paintComponent(g);

		draw(g);

	}

	public void draw(Graphics g) {
	
		
		drawApple(g);

		drawSnake(g);
	}
	
	public void drawApple(Graphics g) {
		
		g.setColor(Color.red);

		g.fillOval(appleX, appleY, CELL_SIZE, CELL_SIZE);
		
		if(appleEaten%5==0) {
			g.setColor(Color.WHITE);
			g.fillOval(appleX + CELL_SIZE/3/2, appleY + CELL_SIZE/3/2, CELL_SIZE*2/3, CELL_SIZE*2/3);
			
			g.setColor(Color.red);
			g.fillOval(appleX + CELL_SIZE/3, appleY + CELL_SIZE/3, CELL_SIZE/4+2, CELL_SIZE/4+2);
		}
		
	}
	
	public void drawSnake(Graphics g) {
		
		Graphics2D g2 = (Graphics2D) g;

		for(int i = 0; i< snakeLenght;i++) {

			if(i == 0)
				g.setColor(Color.YELLOW);
			else 
				g.setColor(Color.green);
			
		
			g.fillRect(XY[i][0], XY[i][1], CELL_SIZE, CELL_SIZE);
			g.setColor(Color.white);
			g.drawRect(XY[i][0], XY[i][1], CELL_SIZE, CELL_SIZE);
		}

	}



}
