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
		HeartRate heartRate = new HeartRate();

		new HeartSimulation(tabbedPane, heartRate);
		new DCMSimulation(tabbedPane, heartRate);
	}
}
	
	