import java.awt.Color;
import java.awt.Font;
import java.awt.SystemColor;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;

public class Interface extends JFrame{

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		new Interface();
	}
	
	public Interface(){
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBackground(SystemColor.text);
		tabbedPane.setFont(new Font("Tahoma", Font.BOLD, 15));
		tabbedPane.setBounds(100, 100, 751, 633);
		
		HeartRate heartRate = new HeartRate();

		new HeartSimulation(tabbedPane, heartRate);
		new DCMSimulation(tabbedPane, heartRate);
	}
}
	
	