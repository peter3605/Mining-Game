import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.TexturePaint;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class MainPanel extends JPanel{
	private final static int BIG_WIDTH = 100;
	private final static int SMALL_WIDTH = 50;
	private final static int JWIDTH = 40;
	private final static int JHEIGHT = 40;
	private int count;
	private int num = 0;
	private ArrayList<Block> list = new <Block>ArrayList();
	private ArrayList<Rectangle>parts = new <Rectangle>ArrayList();
	private Jewel[] jewels;
	private Block current = new Block(0,0,0,0);
	private BarPanel barPanel;
	private boolean size = false;   
	private Main main;
	private JFrame frame;
	
	MainPanel(BarPanel b, Main m, JFrame j, int number){
		count = number;
		//set size
		setPreferredSize(new Dimension(800,800));
		
		//set initial cursor to small hammer
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Image image = toolkit.getImage("pics/small.png");
		Cursor c = toolkit.createCustomCursor(image , new Point(getX(), getY()), "img");
		setCursor(c);
		
		//initialize the frames
		frame = j;
		main = m;
		barPanel = b;
		
		//create the jewels
		createJewels();
		
		//add the action in response to clicking the mouse
		addMouseListener(new MouseAdapter(){
	        public void mouseClicked(MouseEvent e){
	        	if(!checkAll()){
		        	//shake the frame once
		        	new ShakeFrame(frame, size);
		        	num++;
		        	//create the new block
		        	Block block = new Block(0,0,0,0);
		        	if(!size){
		        		block = new Block(e.getX()-10,e.getY()-10,SMALL_WIDTH, SMALL_WIDTH);
		        	}else{
		        		block = new Block(e.getX()-10,e.getY()-10,BIG_WIDTH, BIG_WIDTH);
		        	}
		        	//set the new block to be the current one
		        	current = block;
		        	//add the new block to the list of blocks
		        	list.add(block);
		        	//make the barPanel smaller
		        	barPanel.reduce();
		        	repaint();
	        	}
	        }
		});
	}
	
	//used to switch the size of the block, the cursor image, and change the size in barPanel
	public void switchButton(boolean b){
		size = b;
		barPanel.switchSize(b);
		if(size){
			Toolkit toolkit = Toolkit.getDefaultToolkit();
			Image image = toolkit.getImage("pics/big.png");
			Cursor c = toolkit.createCustomCursor(image , new Point(getX(),getY()), "img");
			setCursor(c);
		}else{
			Toolkit toolkit = Toolkit.getDefaultToolkit();
			Image image = toolkit.getImage("pics/small.png");
			Cursor c = toolkit.createCustomCursor(image , new Point(getX(),getY()), "img");
			setCursor(c);
		}
	}
	
	//Initializes the jewels and creates random locations for them
	public void createJewels(){
		jewels = new Jewel[count];
		for(int i=0;i<count;i++){
			boolean intersect = true;
			while(intersect){
				int randomX = (int)(Math.random()*750);
				int randomY = (int)(Math.random()*750);
				jewels[i] = new Jewel(randomX,randomY,JWIDTH,JHEIGHT,Color.BLUE);
				if(i>0){
					if(!(jewels[i].intersects(jewels[i-1]))){
						intersect = false;
					}
				}else{
					intersect = false;
				}
			}
		}
	}
	
	
	//used to check if all of the jewels have been found
	public boolean checkAll(){
		for(int i=0;i<count;i++){
			if(!jewels[i].getFound()){
				return false;
			}
		}
		return true;
	}
	public int numberFound(){
		int c = 0;
		for(int i= 0;i<count;i++){
			if(jewels[i].getFound()){
				c++;
			}
		}
		return c;
	}
	
	public void paintComponent(Graphics a){
		Graphics2D g = (Graphics2D)a;
		super.paintComponent(g);
		
		//drawing the background
		Graphics2D g2d = (Graphics2D) g;
		BufferedImage bufferedImage = new BufferedImage(8, 8, BufferedImage.TYPE_USHORT_565_RGB);
		Graphics2D g2 = bufferedImage.createGraphics();
		g2.setColor(new Color(105,105,105));
		g2.fillRect(0, 0, 8, 8);
		g2.setColor(new Color(139,69,19));
		g2.fillOval(0, 0, 8, 8);
		Rectangle2D rect = new Rectangle2D.Double(0, 0,16,16);
		g2d.setPaint(new TexturePaint(bufferedImage, rect));
		g2d.fillRect(0,0,800,800);
		
		//drawing the squares
		g.setColor(getBackground());
		for(int i=0;i<list.size();i++){
			g.fillRect((int)list.get(i).getX(),(int)list.get(i).getY(),(int)list.get(i).getWidth(),(int)list.get(i).getHeight());
		}
		
		//drawing the jewels if they have been found
		for(int i=0;i<count;i++){
			if(jewels[i].getFound()){
				g.setColor(jewels[i].getColor());
				g.fillRect((int)jewels[i].getX(),(int) jewels[i].getY(),JWIDTH,JHEIGHT);
			}
		}
		//get the rectangle that is the intersection
		for(int i=0;i<count;i++){
			if(current.intersects(jewels[i])){
				Rectangle r = jewels[i].check(current);
				parts.add(r);
				//g.fillRect((int)r.getX(),(int)r.getY(),(int)r.getWidth(),(int)r.getHeight());
			}
		}
		//draw the intersections
		for(int i=0;i<parts.size();i++){
			Rectangle z = parts.get(i);
			g.setColor(Color.BLUE);
			g.fillRect((int)z.getX(),(int)z.getY(),(int)z.getWidth(),(int)z.getHeight());
			
		}
		
		//check if all of the jewels have been found
		if(checkAll()){
			for(int i=0;i<count;i++){
				g.setColor(jewels[i].getColor());
				g.fillRect((int)jewels[i].getX(),(int) jewels[i].getY(),JWIDTH,JHEIGHT);
			}
			main.endGame(true);
		}
	}
}
