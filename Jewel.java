import java.awt.Color;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.util.ArrayList;


public class Jewel extends Rectangle{
	private final int EMPTY = 0;
	private final int NOT_HIT = 1;
	private final int HIT = 2;
	
	private Color color;
	private boolean found;
	private int[][] points;
	Jewel(int a,int b, int c, int d, Color z){
		x=a;
		y=b;
		width = c;
		height = d;
		color = z;
		createPoints();
	}
	private void createPoints(){
		points = new int[800][800];
		for(int i=0;i<800-1;i++){
			for(int e=0;e<800-1;e++){
				points[i][e]=EMPTY;
			}
		}
		for(int i=0;i<width-1;i++){
			for(int e=0;e<height-1;e++){
				points[x+i][y+e]=NOT_HIT;
			}
		}
	}
	private void setPoints(int X, int Y, int W, int H){
		for(int i=0;i<W-1;i++){
			for(int e=0;e<H-1;e++){
				int nx = X+i;
				int ny = Y+e;
				points[nx][ny]=HIT;
			}
		}
		if(checkPoints()){
			setFound(true);
		}
	}
	public double getX(){return x;}
	public double getY(){return y;}
	public void setX(int a){x = a;}
	public void setY(int a){y = a;}
	
	private boolean checkPoints(){
		for(int i=0;i<799;i++){
			for(int e=0;e<799;e++){
				if(points[i][e]==NOT_HIT){
					return false;
				}
			}
		}
		return true;
	}
	public void setFound(boolean b){found = b;}
	public boolean getFound(){return found;}
	public Color getColor(){return color;}
	public void setColor(Color c){color=c;}
	
	//point 0 is top left corner, point 1 is top right corner, point 2 is bottom left corner, point 3 is bottom right corner
	//this method returns the rectangle that is the intersection of the jewel and the clicked square
	public Rectangle check(Rectangle rect){
		int rx = (int) rect.getX();
		int ry = (int) rect.getY();
		int rx1 = rx+(int)rect.getWidth();
		int ry1 = ry;
		int rx2 = rx;
		int ry2 = ry+(int)rect.getHeight();
		int rx3 = rx1;
		int ry3 = ry2;
	
		
		//if the rectangle covers the jewel
		if(rx<=x){
			if(rx1>=(x+height)){
				if(rx2<=x){
					if(rx3>=(x+height)){
						if(ry<=y){
							if(ry1<=y){
								if(ry2>=(y+height)){
									if(ry3>=(y+height)){
										setFound(true);
										return new Rectangle(x,y,width,height);
									}
								}
							}
						}
					}
				}
			}
		}
		
		//if rectangle is long and to the right of the jewel
		if(rx>x){
			if((ry<y)&&(ry2>(y+height))){
				int nx = rx;
				int ny = y;
				int nWidth = (x+width)-rx;
				int nHeight = height;
				setPoints(nx,ny,nWidth, nHeight);
				return new Rectangle(nx,ny,nWidth, nHeight);
			}
		}
		//if the rectangle is long and to the left of the jewel
		else if(rx<x){
			if((ry1<y)&&(ry3>(y+height))){
				int nx = x;
				int ny = y;
				int nWidth = rx1-x;
				int nHeight = height;
				setPoints(nx,ny,nWidth, nHeight);
				return new Rectangle(nx,ny,nWidth, nHeight);
			}
		}
		//if the rectangle is long and below the jewel
		if(ry>y){
			if(rx<x){
				if(rx1>(x+height)){
					int nx = x;
					int ny= ry;
					int nWidth = width;
					int nHeight = (y+height)-ry;
					setPoints(nx,ny,nWidth, nHeight);
					return new Rectangle(nx,ny,nWidth, nHeight);
				}
			}
		}
		//if the rectangle is long and above the jewel
		if(ry<y){
			if(rx<x){
				if(rx1>(x+width)){
					int nx = x;
					int ny = y;
					int nWidth = width;
					int nHeight = ry2 - y;
					setPoints(nx,ny,nWidth, nHeight);
					return new Rectangle(nx,ny,nWidth, nHeight);
				}
			}
		}
		
		//if the rectangle is lower than the jewel
		if(ry>y){
			//if the rectangle is to the right of the jewel
			if(rx>x){
				int nx = rx;
				int nWidth = (x+width)-rx;
				int ny = ry;
				int nHeight = (y+height)-ry;
				setPoints(nx,ny,nWidth, nHeight);
				return new Rectangle(nx,ny,nWidth, nHeight);
			}
			//if the rectangle is to the left of the jewel
			else if(rx<=x){
				int nx = x;
				int nWidth = rx1-x;
				int ny = ry;
				int nHeight = (y+height)-ry;
				setPoints(nx,ny,nWidth, nHeight);
				return new Rectangle(nx,ny,nWidth, nHeight);
			}
		//if the rectangle is above the jewel
		}else if(ry<y){
			//if the rectangle is to the right of the jewel
			if(rx>x){
				int nx = rx;
				int nWidth = (x+width)-rx;
				int ny = y;
				int nHeight = ry2-y;
				setPoints(nx,ny,nWidth, nHeight);
				return new Rectangle(nx,ny,nWidth, nHeight);
			}
			//if the rectangle is to the left of the jewel
			else if(rx<=x){
				int nx = x;
				int ny=y;
				int nWidth = rx3-x;
				int nHeight = ry2-y;
				setPoints(nx,ny,nWidth, nHeight);
				return new Rectangle(nx,ny,nWidth, nHeight);
			}
		}
		
		return null;
		
	}
}
