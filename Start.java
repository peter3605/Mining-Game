import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class Start {
	private String[] nums = new String[]{"1","2","3","4","5","6"};
	private JFrame frame;
	private JPanel panel;
	private JLabel label;
	private JComboBox options;
	private JButton button; 
	private int count=1;
	
	Start(){
		frame = new JFrame("Start");
		panel = new JPanel();
		label = new JLabel("Choose the number of jewels that you want to find:");
	
		options = new JComboBox(nums);
		options.setForeground(Color.BLUE);
		options.setFont(new Font("Arial", Font.BOLD, 14));
		options.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent event) {
		        JComboBox combo = (JComboBox) event.getSource();
		        count = Integer.parseInt((String) combo.getSelectedItem());
		    }
		});
		button = new JButton("Start");
		button.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event){
				Main main = new Main(count);
				frame.dispose();
			}
		});
		JLabel label1 = new JLabel();
		label1.setText("<html>Try to find jewels hidden in the cavern wall!<br> Click on the wall to uncover the dirt.The bar<br> on the right shows the strength of the wall -  <br>everytime you click, the bar gets smaller.<br> When the bar reaches zero, the wall collapses<br> and you lose! You can use either a big or small<br> hammer.The big hammer uncovers more dirt<br> but also weakens the wall more. </html>");
		JLabel empty1 = new JLabel("--------------------------------------------------------------------------------------------------------------");
		JLabel empty2 = new JLabel("--------------------------------------------------------------------------------------------------------------");
		
		panel.add(empty1);
		panel.add(label1);
		panel.add(empty2);
		panel.add(label);
		panel.add(options);
		panel.add(button);
		panel.setPreferredSize(new Dimension(500,300));
		
		
		frame.setContentPane(panel);
		frame.pack();
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}
	public static void main(String args[]){
		Start start = new Start();
	}
}
