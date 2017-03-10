import java.awt.Color;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

public class DCMSimulation extends JFrame{
	JPanel contentPane, initialPanel;
	Panel heartBeatPanel;
	int countHeartBeats = 0;

    /**
	 * Create the frame.
	 */
    
    public DCMSimulation(JTabbedPane tabbedPane, HeartRate heartRate) {
		contentPane = new JPanel();
		contentPane.setBounds(100, 100, 751, 620);
		
		initialPanel = new JPanel();
		initialPanel.setBounds(5,5,820,300);		
		initialPanel.setBackground(Color.white);
				
		contentPane.add(initialPanel);
		
		JButton btnHeartRate = new JButton("Heart Rate");
		btnHeartRate.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				countHeartBeats = 0;
				if (initialPanel != null) contentPane.remove(initialPanel);
				if (heartBeatPanel != null) contentPane.remove(heartBeatPanel);
				heartBeatPanel = new Panel(heartRate.getRatesList());
				heartBeatPanel.resetGraph();
				
				contentPane.add(heartBeatPanel);
			}
		});
		btnHeartRate.setBackground(SystemColor.inactiveCaptionBorder);
		btnHeartRate.setFont(new Font("Tahoma", Font.BOLD, 15));

		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(5)
					.addComponent(btnHeartRate, GroupLayout.PREFERRED_SIZE, 179, GroupLayout.PREFERRED_SIZE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap(314, Short.MAX_VALUE)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnHeartRate, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE))
					.addGap(180))
		);

		new Thread(new Runnable() {
		     public void run() {
		        try {
					while (true) {
						if (heartBeatPanel != null) { 
							heartBeatPanel.continousDraw(countHeartBeats, true);
							countHeartBeats++;
							repaint();
						}
						Thread.sleep(200);
					}
		        } catch (InterruptedException e) {}
		     }
		}).start();
		
		contentPane.setLayout(gl_contentPane);
		tabbedPane.addTab("DCM Simulator", contentPane);
    }
}
