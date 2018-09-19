import java.awt.Point;
import java.awt.Rectangle;
import javax.swing.JComponent;

public class Block extends Rectangle{
	Block(int a,int b, int c, int d){
		x=a;
		y=b;
		width = c;
		height = d;
	}
	public void setX(int i){x=i;}
	public void setY(int i){y=i;}
}
