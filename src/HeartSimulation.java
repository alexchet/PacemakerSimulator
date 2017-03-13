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
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;

public class HeartSimulation extends JFrame{
	JPanel contentPane, initialPanel;
	Panel normalPanel, bradyPanel, tachyPanel, activityPanel;         
    boolean normalFlag, bradyFlag, tachyFlag, activityFlag;
	int countHeartBeats = 0;

    /**
	 * Create the frame.
	 */

	public HeartSimulation(JTabbedPane tabbedPane, HeartRate heartRate) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 850, 643);

		contentPane = new JPanel();
		contentPane.setBounds(100, 100, 751, 620);
		
		initialPanel = new JPanel();
		initialPanel.setBounds(5,5,820,190);	
		initialPanel.setBackground(Color.white);
				
		contentPane.add(initialPanel);
		
		JLabel lblPatientsHeartRate = new JLabel("Patient's Heart Rate");
		lblPatientsHeartRate.setFont(new Font("Tahoma", Font.BOLD, 17));
		GroupLayout gl_initialPanel = new GroupLayout(initialPanel);
		gl_initialPanel.setHorizontalGroup(
			gl_initialPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_initialPanel.createSequentialGroup()
					.addComponent(lblPatientsHeartRate)
					.addContainerGap(681, Short.MAX_VALUE))
		);
		gl_initialPanel.setVerticalGroup(
			gl_initialPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_initialPanel.createSequentialGroup()
					.addComponent(lblPatientsHeartRate)
					.addContainerGap(173, Short.MAX_VALUE))
		);
		initialPanel.setLayout(gl_initialPanel);
		
		
		tabbedPane.addTab("Heart Simulator", contentPane);
		
		JButton btnNormalRate = new JButton("Normal Heart Rate");
		btnNormalRate.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {	
				if (!normalFlag) {
					countHeartBeats = 0;
					heartRate.reset();
					heartRate.startHeart(RatePoints.getNormalRates());
					normalPanel = new Panel(heartRate.getRatesList());
					
					resetPanels();
					resetFlags();
					normalPanel.resetGraph();
					
					normalFlag = true;
					contentPane.add(normalPanel);
				}
			}
		});
		btnNormalRate.setBackground(SystemColor.inactiveCaptionBorder);
		btnNormalRate.setFont(new Font("Tahoma", Font.BOLD, 15));
		
		JButton btnBradyRate = new JButton("Bradycardia Heart Rate");
		btnBradyRate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnBradyRate.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (!bradyFlag) {
					countHeartBeats = 0;
					heartRate.reset();
					heartRate.startHeart(RatePoints.getBrachycardicRates());
					bradyPanel = new Panel(heartRate.getRatesList());
					
					resetPanels();
					resetFlags();
					bradyPanel.resetGraph();
					
					bradyFlag = true;
					contentPane.add(bradyPanel);
				}
			}
		});
		btnBradyRate.setBackground(SystemColor.inactiveCaptionBorder);
		btnBradyRate.setFont(new Font("Tahoma", Font.BOLD, 15));
		
		JButton btnRunningactivity = new JButton("Running Activity");
		btnRunningactivity.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {				
				if (!activityFlag) {
					countHeartBeats = 0;
					heartRate.reset();
					heartRate.startHeart(RatePoints.getRunningActivityRates());
					activityPanel = new Panel(heartRate.getRatesList());
					
					resetPanels();
					resetFlags();
					activityPanel.resetGraph();
					
					activityFlag = true;
					contentPane.add(activityPanel);
				}
			}
		});
		btnRunningactivity.setBackground(SystemColor.inactiveCaptionBorder);
		btnRunningactivity.setFont(new Font("Tahoma", Font.BOLD, 15));
		
		JButton btnTachycardiaHeartRate = new JButton("Tachycardia Heart Rate");
		btnTachycardiaHeartRate.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {					
				if (!tachyFlag) {
					countHeartBeats = 0;
					heartRate.reset();
					heartRate.startHeart(RatePoints.getTachycardicRates());
					tachyPanel = new Panel(heartRate.getRatesList());
					
					resetPanels();
					resetFlags();
					tachyPanel.resetGraph();
					
					tachyFlag = true;
					contentPane.add(tachyPanel);
				}
			}
		});
		btnTachycardiaHeartRate.setBackground(SystemColor.inactiveCaptionBorder);
		btnTachycardiaHeartRate.setFont(new Font("Tahoma", Font.BOLD, 15));
		
		JButton btnStopSimulation = new JButton("Stop Simulation");
		btnStopSimulation.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				countHeartBeats = 0;
				heartRate.reset();
				resetPanels();
				resetFlags();
				contentPane.add(initialPanel);
			}
		});
		btnStopSimulation.setBackground(SystemColor.inactiveCaptionBorder);
		btnStopSimulation.setFont(new Font("Tahoma", Font.BOLD, 15));
		
		JLabel label = new JLabel("Patient's Heart Rate");
		label.setFont(new Font("Tahoma", Font.BOLD, 17));
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
						.addComponent(label, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(btnStopSimulation, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(btnNormalRate, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 175, Short.MAX_VALUE))
					.addGap(6)
					.addComponent(btnBradyRate, GroupLayout.PREFERRED_SIZE, 214, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnTachycardiaHeartRate)
					.addGap(7)
					.addComponent(btnRunningactivity, GroupLayout.PREFERRED_SIZE, 172, GroupLayout.PREFERRED_SIZE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(label, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
					.addGap(247)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnTachycardiaHeartRate, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnBradyRate, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnNormalRate, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnRunningactivity, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnStopSimulation, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(248, Short.MAX_VALUE))
		);

		contentPane.setLayout(gl_contentPane);
		getContentPane().add(tabbedPane);
		setVisible(true);

		new Thread(new Runnable() {
		     public void run() {
		        try {
					while (true) {
						if (normalFlag) {
				                normalPanel.continousDraw(countHeartBeats, false, false, null);
						}
		
						if (bradyFlag) {
				                bradyPanel.continousDraw(countHeartBeats, false, false, null);
						}
		
						if (tachyFlag) {
				                tachyPanel.continousDraw(countHeartBeats, false, false, null);
						}
		
						if (activityFlag) {
				                activityPanel.continousDraw(countHeartBeats, false, false, null);
						}
						countHeartBeats++;
			            repaint();
			            Thread.sleep(200);
					}
		        } catch (InterruptedException e) {}
		     }
		}).start();
	}
    
    public void resetPanels() {
		if (bradyPanel != null) contentPane.remove(bradyPanel);
		if (normalPanel != null) contentPane.remove(normalPanel);
		if (tachyPanel != null) contentPane.remove(tachyPanel);
		if (activityPanel != null) contentPane.remove(activityPanel);
		if (initialPanel != null) contentPane.remove(initialPanel);
    }
    
    public void resetFlags() {
    	normalFlag = false;
		bradyFlag = false;
		tachyFlag = false;
		activityFlag = false;
    }
}
