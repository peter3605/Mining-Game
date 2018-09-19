import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class Main {
	private JFrame mainFrame, buttonFrame, newFrame;
	private MainPanel mainPanel;
	private BarPanel barPanel;
	private JPanel container, buttonPanel;
	private JButton big, small;
	Main(int number){
		//initialize frames
		mainFrame = new JFrame("Main");
		buttonFrame = new JFrame("Size");
		
		//initialize panels
		barPanel = new BarPanel(this);
		mainPanel = new MainPanel(barPanel,this, mainFrame, number);
		container = new JPanel();
		buttonPanel = new JPanel();
		
		//initialize buttons and add actionlisteners
		big = new JButton(new ImageIcon("pics/big.png"));
		small = new JButton(new ImageIcon("pics/small.png"));
		big.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				mainPanel.switchButton(true);
			}
    	});
		small.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				mainPanel.switchButton(false);
			}
    	});
		
		//add buttons to button panels
		buttonPanel.setPreferredSize(new Dimension(90,100));
		buttonPanel.add(big);
		buttonPanel.add(small);
		
		//add panels to the container
		container.add(mainPanel);
		container.add(barPanel);
		
		//set the main frame
		mainFrame.setContentPane(container);
		mainFrame.pack();
		mainFrame.setVisible(true);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  	
		
		//set the button frame
		buttonFrame.add(buttonPanel);
		buttonFrame.setUndecorated(true);
		buttonFrame.setBackground(new Color(0,0,0,0));
		buttonFrame.pack();
		buttonFrame.setLocation(820,740);
		buttonFrame.setAlwaysOnTop(true);
		buttonFrame.setVisible(true);
		buttonFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  	
	}
	public void endGame( boolean b){
		if(!b){
			for(int i=0;i<100;i++){
				new ShakeFrame(mainFrame, true);
			}
			//close frames
			mainFrame.dispose();
			buttonFrame.dispose();
		}
		
		//initialize new frame
		newFrame = new JFrame("End");
		
		//create labels
		String string;
		if(mainPanel.numberFound()==1){
			string = " jewel";
		}else{
			string = " jewels";
		}
		JLabel label;
		if(b){
			label = new JLabel("You found all of the jewels. You win!",SwingConstants.CENTER);
		}else{
			label = new JLabel("The wall crumbled. You only found "+mainPanel.numberFound()+string,SwingConstants.CENTER);
		}
		label.setFont(new Font("Monospaced", Font.BOLD, 12));
		
		//create play again button
		JButton again = new JButton("Play Again");
		again.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				mainFrame.dispose();
				buttonFrame.dispose();
				newFrame.dispose();
				Start newGame= new Start();
			}
    	});
		
		//create the panel
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
		panel.add(label);
		panel.add(again);
		
		//set newFrame
		newFrame.setContentPane(panel);
		newFrame.pack();
		newFrame.setLocationRelativeTo(mainFrame);
		newFrame.setAlwaysOnTop(true);
		newFrame.setVisible(true);
		newFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  	
	}
}
