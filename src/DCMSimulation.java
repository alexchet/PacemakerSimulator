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
import javax.swing.border.LineBorder;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JRadioButton;
import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JList;
import javax.swing.AbstractListModel;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class DCMSimulation extends JFrame{
	JPanel contentPane, initialPanel;
	Panel heartBeatPanel;
	int countHeartBeats = 0;

    /**
	 * Create the frame.
	 */
    
    public DCMSimulation(JTabbedPane tabbedPane, HeartRate heartRate) {
		contentPane = new JPanel();
		contentPane.setBounds(100, 100, 832, 694);
		
		initialPanel = new JPanel();
		initialPanel.setBounds(5,5,820,300);		
		initialPanel.setBackground(Color.white);
				
		contentPane.add(initialPanel);
		
		JButton btnHeartRate = new JButton("Heart Rate");
		btnHeartRate.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if (heartRate.getRatesList() != null) {
					countHeartBeats = 0;
					if (initialPanel != null) contentPane.remove(initialPanel);
					if (heartBeatPanel != null) contentPane.remove(heartBeatPanel);
					heartBeatPanel = new Panel(heartRate.getRatesList());
					heartBeatPanel.resetGraph();
					contentPane.add(heartBeatPanel);
					btnHeartRate.setBorder(new LineBorder(Color.GREEN));
				} else {
					btnHeartRate.setBorder(new LineBorder(Color.RED));
				}
			}
		});
		btnHeartRate.setBackground(SystemColor.inactiveCaptionBorder);
		btnHeartRate.setFont(new Font("Tahoma", Font.BOLD, 15));
		
		JRadioButton rdbtnTemporaryState = new JRadioButton("Temporary State");
		
		JRadioButton rdbtnPacenowStae = new JRadioButton("Pace-Now State");
		
		JRadioButton rdbtnPoweronresetState = new JRadioButton("Power-On-Reset State");
		
		JLabel lblNewLabel = new JLabel("Operating Modes");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"O - None", "A - Atrium", "V - Ventricle", "D -Dual"}));
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setModel(new DefaultComboBoxModel(new String[] {"O - None", "A - Atrium", "V - Ventricle", "D -Dual"}));
		
		JComboBox comboBox_2 = new JComboBox();
		comboBox_2.setModel(new DefaultComboBoxModel(new String[] {"O - None", "A - Atrium", "V - Ventricle", "D -Dual"}));
		
		JLabel lblChambersPaced = new JLabel("Chambers Paced");
		lblChambersPaced.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		JLabel lblChambersSensed = new JLabel("Chambers Sensed");
		lblChambersSensed.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		JLabel lblResponseToSensing = new JLabel("Response to Sensing");
		lblResponseToSensing.setFont(new Font("Tahoma", Font.BOLD, 13));
		
		JRadioButton rdbtnPermanentState = new JRadioButton("Permanent State");

		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(btnHeartRate, GroupLayout.PREFERRED_SIZE, 179, GroupLayout.PREFERRED_SIZE)
					.addGap(28)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(rdbtnPoweronresetState)
						.addComponent(rdbtnPacenowStae, GroupLayout.PREFERRED_SIZE, 148, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(rdbtnTemporaryState)
								.addComponent(lblNewLabel)
								.addComponent(rdbtnPermanentState, GroupLayout.PREFERRED_SIZE, 125, GroupLayout.PREFERRED_SIZE))
							.addGap(38)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(comboBox, 0, 115, Short.MAX_VALUE)
								.addComponent(lblChambersPaced, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
							.addGap(7)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(comboBox_1, GroupLayout.PREFERRED_SIZE, 125, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(comboBox_2, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
								.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
									.addComponent(lblChambersSensed, GroupLayout.DEFAULT_SIZE, 131, Short.MAX_VALUE)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(lblResponseToSensing)))
							.addGap(20)))
					.addGap(65))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap(538, Short.MAX_VALUE)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(btnHeartRate, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblNewLabel)
								.addComponent(lblChambersPaced)
								.addComponent(lblResponseToSensing)
								.addComponent(lblChambersSensed, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(comboBox_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(comboBox_2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(rdbtnPermanentState))))
					.addGap(2)
					.addComponent(rdbtnTemporaryState)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(rdbtnPacenowStae)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(rdbtnPoweronresetState)
					.addGap(28))
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
