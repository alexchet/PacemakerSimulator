import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

public class DCMSimulation extends JFrame{
	JPanel contentPane;

    /**
	 * Create the frame.
	 */
    
    public DCMSimulation(JTabbedPane tabbedPane, HeartRate heartRate) {
		contentPane = new JPanel();
		tabbedPane.addTab("DCM Simulator", contentPane);
		
		JButton btnNormalRate = new JButton("Test HeartRate");
		btnNormalRate.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				System.out.println(heartRate.getRatesList().size());
			}
		});
		btnNormalRate.setFont(new Font("Tahoma", Font.PLAIN, 16));
		contentPane.add(btnNormalRate);
    }
}
