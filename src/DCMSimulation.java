import java.awt.Color;
import java.awt.Font;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.SystemColor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

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
import javax.swing.ButtonGroup;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.SwingConstants;
import java.awt.Component;
import java.awt.FlowLayout;

public class DCMSimulation extends JFrame{
	JPanel contentPane, initialPanel, secondPanel;
	Panel heartBeatPanel, normalPanel;
	ArrayList<Point> pacedList = new ArrayList<Point>();

    /**
	 * Create the frame.
	 */
    
    public DCMSimulation(JTabbedPane tabbedPane, HeartRate heartRate) {
		contentPane = new JPanel();
		contentPane.setBounds(100, 100, 832, 694);

		
		initialPanel = new JPanel();
		initialPanel.setBounds(5,5,820,190);		
		initialPanel.setBackground(Color.white);
		
		JLabel lblOnPanel = new JLabel("Patient's Heart Rate");
		lblOnPanel.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblOnPanel.setBounds(5,5,100,100);
		
		
		secondPanel = new JPanel();
		secondPanel.setBounds(5, 200, 820, 190);
		secondPanel.setBackground(Color.white);
			
		contentPane.add(initialPanel);
		GroupLayout gl_initialPanel = new GroupLayout(initialPanel);
		gl_initialPanel.setHorizontalGroup(
			gl_initialPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_initialPanel.createSequentialGroup()
					.addComponent(lblOnPanel)
					.addContainerGap(649, Short.MAX_VALUE))
		);
		gl_initialPanel.setVerticalGroup(
			gl_initialPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_initialPanel.createSequentialGroup()
					.addComponent(lblOnPanel)
					.addContainerGap(169, Short.MAX_VALUE))
		);
		initialPanel.setLayout(gl_initialPanel);
		
		contentPane.add(secondPanel);
		
		JLabel lblPacedHeartRate = new JLabel("Paced Heart Rate");
		lblPacedHeartRate.setFont(new Font("Tahoma", Font.BOLD, 17));
		GroupLayout gl_secondPanel = new GroupLayout(secondPanel);
		gl_secondPanel.setHorizontalGroup(
			gl_secondPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_secondPanel.createSequentialGroup()
					.addComponent(lblPacedHeartRate)
					.addContainerGap(673, Short.MAX_VALUE))
		);
		gl_secondPanel.setVerticalGroup(
			gl_secondPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_secondPanel.createSequentialGroup()
					.addComponent(lblPacedHeartRate)
					.addContainerGap(169, Short.MAX_VALUE))
		);
		secondPanel.setLayout(gl_secondPanel);

		HeartRate normalHeartRate = new HeartRate();
		
		JButton btnHeartRate = new JButton("Heart Rate");
		btnHeartRate.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if (heartRate.getRatesList() != null) {
					if (initialPanel != null) contentPane.remove(initialPanel);
					if (secondPanel != null) contentPane.remove(secondPanel);
					
					//Top panel to show sensing
					if (heartBeatPanel != null) contentPane.remove(heartBeatPanel);
					heartBeatPanel = new Panel(heartRate.getRatesList());
					heartBeatPanel.resetGraph();
					heartBeatPanel.setBounds(5,5,820,190);
					contentPane.add(heartBeatPanel);
					
					//Lower panel to show normal heart rate
					if (normalPanel != null) contentPane.remove(normalPanel);
					normalHeartRate.reset();
					normalPanel = new Panel();
					normalPanel.resetGraph();
					normalPanel.setBounds(5, 200, 820, 190);
					contentPane.add(normalPanel);
					
					btnHeartRate.setBorder(new LineBorder(Color.GREEN));
				} else {
					btnHeartRate.setBorder(new LineBorder(Color.RED));
				}
			}
		});
		btnHeartRate.setBackground(SystemColor.inactiveCaptionBorder);
		btnHeartRate.setFont(new Font("Tahoma", Font.BOLD, 15));
		
		JRadioButton rdbtnPermanentState = new JRadioButton("Permanent State");
		rdbtnPermanentState.setSelected(true);
		rdbtnPermanentState.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JRadioButton rdbtnTemporaryState = new JRadioButton("Temporary State");
		rdbtnTemporaryState.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JRadioButton rdbtnPacenowStae = new JRadioButton("Pace-Now State");
		rdbtnPacenowStae.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JRadioButton rdbtnPoweronresetState = new JRadioButton("Power-On-Reset State");
		rdbtnPoweronresetState.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		ButtonGroup states = new ButtonGroup();
		states.add(rdbtnPoweronresetState);
		states.add(rdbtnTemporaryState);
		states.add(rdbtnPermanentState);
		states.add(rdbtnPacenowStae);
		
		JLabel lblNewLabel = new JLabel("Operating Modes");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"O - None", "A - Atrium", "V - Ventricle", "D - Dual"}));
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setModel(new DefaultComboBoxModel(new String[] {"O - None", "A - Atrium", "V - Ventricle", "D - Dual"}));
		
		JComboBox comboBox_2 = new JComboBox();
		comboBox_2.setModel(new DefaultComboBoxModel(new String[] {"O - None", "A - Atrium", "V - Ventricle", "D -Dual"}));
		
		JLabel lblChambersPaced = new JLabel("Chambers Paced");
		lblChambersPaced.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		JLabel lblChambersSensed = new JLabel("Chambers Sensed");
		lblChambersSensed.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		JLabel lblResponseToSensing = new JLabel("Response to Sensing");
		lblResponseToSensing.setFont(new Font("Tahoma", Font.BOLD, 13));
		
		JLabel label = new JLabel("Patient's Heart Rate");
		label.setFont(new Font("Tahoma", Font.BOLD, 17));
				
		
		JLabel lblRecordingModes = new JLabel("Recording Modes");
		lblRecordingModes.setFont(new Font("Tahoma", Font.BOLD, 16));
		
		JRadioButton rdbtnFixed = new JRadioButton("Fixed");
		rdbtnFixed.setSelected(true);
		rdbtnFixed.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JRadioButton rdbtnContinuous = new JRadioButton("Continuous");
		rdbtnContinuous.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		ButtonGroup recording = new ButtonGroup();
		recording.add(rdbtnFixed);
		recording.add(rdbtnContinuous);
		
		JLabel label_1 = new JLabel("Paced Heart Rate");
		label_1.setFont(new Font("Tahoma", Font.BOLD, 17));
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(btnHeartRate, GroupLayout.PREFERRED_SIZE, 179, GroupLayout.PREFERRED_SIZE)
							.addGap(28)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(rdbtnTemporaryState)
								.addComponent(rdbtnPacenowStae, GroupLayout.PREFERRED_SIZE, 148, GroupLayout.PREFERRED_SIZE)
								.addComponent(rdbtnPoweronresetState)
								.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
									.addComponent(rdbtnPermanentState, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
									.addComponent(lblNewLabel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
							.addGap(38)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addGroup(gl_contentPane.createSequentialGroup()
											.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
												.addComponent(comboBox, 0, 115, Short.MAX_VALUE)
												.addComponent(lblChambersPaced, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
											.addGap(7)
											.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
												.addGroup(gl_contentPane.createSequentialGroup()
													.addComponent(comboBox_1, GroupLayout.PREFERRED_SIZE, 125, GroupLayout.PREFERRED_SIZE)
													.addPreferredGap(ComponentPlacement.UNRELATED)
													.addComponent(comboBox_2, 0, 134, Short.MAX_VALUE))
												.addGroup(gl_contentPane.createSequentialGroup()
													.addComponent(lblChambersSensed, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
													.addPreferredGap(ComponentPlacement.UNRELATED)
													.addComponent(lblResponseToSensing)))
											.addGap(20))
										.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
											.addComponent(rdbtnFixed, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
											.addComponent(lblRecordingModes, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
									.addGap(65))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(rdbtnContinuous, GroupLayout.PREFERRED_SIZE, 139, GroupLayout.PREFERRED_SIZE)
									.addContainerGap())))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(label, GroupLayout.PREFERRED_SIZE, 171, GroupLayout.PREFERRED_SIZE)
							.addContainerGap(649, Short.MAX_VALUE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(label_1)
							.addContainerGap(764, Short.MAX_VALUE))))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(label, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)
					.addGap(166)
					.addComponent(label_1)
					.addPreferredGap(ComponentPlacement.RELATED, 318, Short.MAX_VALUE)
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
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(2)
							.addComponent(rdbtnTemporaryState)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(rdbtnPacenowStae)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(rdbtnPoweronresetState))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(18)
							.addComponent(lblRecordingModes)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(rdbtnFixed)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(rdbtnContinuous)))
					.addGap(14))
		);

		new Thread(new Runnable() {
		     public void run() {
	    		int countHeartBeats = 0;
		        try {
					while (true) {
						if (heartBeatPanel != null) {
							heartBeatPanel.continousDraw(countHeartBeats, false, false, null);
						}
						countHeartBeats++;
						Thread.sleep(200);
					}
		        } catch (InterruptedException e) {}
		     }
		}).start();

		new Thread(new Runnable() {
		     public void run() {
	    		int countHeartBeats = 0;
	    		int countPacedBeats = 0;
		        try {
					while (true) {
						if (normalPanel != null) { 
							if(heartRate.ratesList.size() > countPacedBeats + 2) {
								pacedList.add(heartRate.ratesList.get(countPacedBeats));
								countPacedBeats++;
								pacedList.add(heartRate.ratesList.get(countPacedBeats));
								countPacedBeats++;
							}
							boolean skipPoint = normalPanel.continousDraw(countHeartBeats, true, true, pacedList);
							if (skipPoint) {
								countPacedBeats = countPacedBeats - 2;
								pacedList.remove(pacedList.size()-1);
								pacedList.remove(pacedList.size()-1);
							}
						}
						countHeartBeats++;
						Thread.sleep(200);
					}
		        } catch (InterruptedException e) {}
		     }
		}).start();
		
		contentPane.setLayout(gl_contentPane);
		tabbedPane.addTab("DCM Simulator", contentPane);
    }
}
