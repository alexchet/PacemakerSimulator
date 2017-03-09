import javax.swing.JFrame;
import javax.swing.JTabbedPane;

public class Interface extends JFrame {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		
		new HeartSimulation(tabbedPane);
	}

	
}
	
	