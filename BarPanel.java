import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;

public class BarPanel extends JPanel{
	private Block bar;
	private int height = 700;
	private Main main;
	private boolean size = false;
	
	BarPanel(Main m){
		setPreferredSize(new Dimension(100,800));
		main = m;
		bar  = new Block(10,10,80,height);
	}
	public void switchSize(boolean b){
		size = b;
	}
	public void reduce(){
		if(height<=0){
			main.endGame(false);
		}else{
			if(size){
				height-=25;
			}else{
				height-=10;
			}
			repaint();
		}
	}
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		g.setColor(new Color(139,69,19));
		g.fillRect((int)bar.getX(),(int)bar.getY(),(int)bar.getWidth(),(int)bar.getHeight());
		g.setColor(new Color(105,105,105));
		g.fillRect((int)bar.getX(),(int)bar.getY(),(int)bar.getWidth(),height);
	}
	
}
