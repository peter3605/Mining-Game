import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

public class ShakeFrame{
	private JFrame frame;
	private Point currLocation;
	private int displaceXBy;
	private int displaceYBy;

	public ShakeFrame(JFrame frame, boolean b) {
		if(b){
			 displaceXBy = 5;
			 displaceYBy= -10;
		}else{
			 displaceXBy = 1;
			 displaceYBy= -2;
		}
	    this.frame = frame;
	    actionPerformed();
	  }
	
	public void actionPerformed() {
	    currLocation = frame.getLocationOnScreen();
	    Point position1 = new Point(currLocation.x + displaceXBy, currLocation.y + displaceYBy);
	    Point position2 = new Point(currLocation.x - displaceXBy, currLocation.y - displaceYBy);
	    for (int i = 0; i < 20; i++) {
	      frame.setLocation(position1);
	      frame.setLocation(position2);
	    }
	    frame.setLocation(currLocation);
	}
}