import java.awt.Color;
import java.awt.Font;
import java.awt.Point;
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
import javax.swing.ButtonGroup;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextField;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/*
 * The DCM Simulation class incorporates all the main functionality of the pacemaker simulation. This includes
 * displaying the chosen heart rate from the heart rate simulation, producing an ECG graph with the the 
 * updated pacing and sensing pulses that have been generated by the pacemaker. The class also includes all the
 * required modes and states that can be updated by the physician, including the varying states, low battery simulation,
 * sensing modes, pacing modes and response to sensing modes. The class also includes a delay in sensing to response.
 * The physician also has the option to chose between different recording modes such as continuous or fixed.
 * */

public class DCMSimulation extends JFrame{
	JPanel contentPane, initialPanel, secondPanel;
	Panel heartBeatPanel, normalPanel;
	ArrayList<Point> pacedList = new ArrayList<Point>();
	private JTextField sensedDelay;
	PacingModes pm = PacingModes.NONE;
	SensingModes sm = SensingModes.NONE;
	ResponseModes rm = ResponseModes.NONE;
	String state = "Permanent State";
	boolean fixedMemory = true;

    /**
	 * Two initial panels have been created to give the required space for the graphs to be drawn.
	 * The top graph which is the the actual patients heart will be have no modifications done to it, 
	 * this is required so that the effect of the pacemaker on the heart can be easily identified. The
	 * second graph will display the pace heart rate so that this graph is a representation of what the
	 * patients heart rate will look like after the pacemaker has started working.
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
		/**
		 * The Heart rate button will get the heart rates list points according to the heart rate
		 * selected in the heart rate simulation screen. In this way the application can loop the 
		 * selected points and generate new graphs to be shown in the panels available in this screen.
		 **/
		
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
		
		
		
		JLabel lblNewLabel = new JLabel("Operating States");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		
		/* Changing a pacing mode is done through the use of a drop down menu. The selected choice is then,
		 * mapped to an enum class where it can be used by the panel class to provide adequate visuals. The
		 * sensing mode and response to sensing modes all operate in a similar fashion to this thus allowing,
		 * ease of use to integrate these classes with the class models that display the graphs.
		 */
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"O - None", "A - Atrium", "V - Ventricle", "D - Dual"}));
		comboBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				Object paced = arg0;			
				if(comboBox.getSelectedIndex()==0) {
					pm = pm.NONE;
				}else if(comboBox.getSelectedIndex()==1) {
					pm= pm.ATRIUM;
				}else if(comboBox.getSelectedIndex()==2) {
					pm = pm.VENTRICAL;
				}else if(comboBox.getSelectedIndex()==3) {
					pm = pm.DUAL;
				}
				
			}
		});
		
		
	
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setModel(new DefaultComboBoxModel(new String[] {"O - None", "A - Atrium", "V - Ventricle", "D - Dual"}));
		comboBox_1.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
		if(comboBox_1.getSelectedIndex()==0) {
			sm = sm.NONE;
		}else if(comboBox_1.getSelectedIndex()==1) {
			sensedDelay.setText("2000");
			sm= sm.ATRIUM;
		}else if(comboBox_1.getSelectedIndex()==2) {
			sensedDelay.setText("1350");
			sm = sm.VENTRICAL;
		}else if(comboBox_1.getSelectedIndex()==3) {
			sm = sm.DUAL;
		}
	}
		});
			
		JComboBox comboBox_2 = new JComboBox();
		comboBox_2.setModel(new DefaultComboBoxModel(new String[] {"O - None", "T - Triggered", "I - Inhibited", "D - Tracked"}));
		comboBox_2.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
		if(comboBox_2.getSelectedIndex()==0) {
			rm = rm.NONE;
		}else if(comboBox_2.getSelectedIndex()==1) {
			rm= rm.TRIGGERED;
		}else if(comboBox_2.getSelectedIndex()==2) {
			rm = rm.INHIBITED;
		}else if(comboBox_2.getSelectedIndex()==3) {
			rm = rm.TRACKED;
		}
			}
		});
		
		/*
		 * The operating states of the that can be chose have different effect of the operating modes such 
		 * as sensing and pacing. Permanent state set modes available to DDI, DOO, VOO, AOO, VVI, AAI while 
		 * temporary state set modes available to OOO, OVO, OAO, ODO. This is done to minimize the effect the 
		 * pacemaker has on the current patient, which also deters the physician from trying something harmful. 
		 * The pace-now state and the power-on-reset state locks the operating modes to VVI, until the operating 
		 * state is changed.
		 * 
		 * */
		
		JRadioButton rdbtnPermanentState = new JRadioButton("Permanent State");		
		rdbtnPermanentState.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				if(rdbtnPermanentState.isSelected()) { 
					state = rdbtnPermanentState.getText();
					comboBox.setEnabled(true);
					comboBox_1.setEnabled(true);
					comboBox_2.setEnabled(true);
					comboBox.setSelectedIndex(0);
					comboBox_1.setSelectedIndex(0);
					comboBox_2.setSelectedIndex(0);
			
		}		
			}
		});
		rdbtnPermanentState.setSelected(true);
		rdbtnPermanentState.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		
		JRadioButton rdbtnTemporaryState = new JRadioButton("Temporary State");
		rdbtnTemporaryState.addItemListener(new ItemListener() {
		public void itemStateChanged(ItemEvent e) {
			if(rdbtnTemporaryState.isSelected()) { 
				state = (rdbtnTemporaryState.getText());
				comboBox.setEnabled(true);
				comboBox_1.setEnabled(true);
				comboBox_2.setEnabled(true);
				comboBox.setSelectedIndex(0);
				comboBox_1.setSelectedIndex(0);
				comboBox_2.setSelectedIndex(0);
				
		}
			}
		});
		rdbtnTemporaryState.setFont(new Font("Tahoma", Font.PLAIN, 14));		
		
		
		JRadioButton rdbtnPacenowStae = new JRadioButton("Pace-Now State");
		rdbtnPacenowStae.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if(rdbtnPacenowStae.isSelected()) { 
					state = (rdbtnPacenowStae.getText());
					comboBox.setSelectedIndex(2);
					comboBox_1.setSelectedIndex(2);
					comboBox_2.setSelectedIndex(2);
					comboBox.setEnabled(false);
					comboBox_1.setEnabled(false);
					comboBox_2.setEnabled(false);
				}
			}
		});
		rdbtnPacenowStae.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JRadioButton rdbtnPoweronresetState = new JRadioButton("Power-On-Reset State");
		rdbtnPoweronresetState.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if(rdbtnPoweronresetState.isSelected()) { 
					state = (rdbtnPoweronresetState.getText());
					comboBox.setSelectedIndex(2);
					comboBox_1.setSelectedIndex(2);
					comboBox_2.setSelectedIndex(2);
					comboBox.setEnabled(false);
					comboBox_1.setEnabled(false);
					comboBox_2.setEnabled(false);
				}
			}
		});
		rdbtnPoweronresetState.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		ButtonGroup states = new ButtonGroup();
		states.add(rdbtnPoweronresetState);
		states.add(rdbtnTemporaryState);
		states.add(rdbtnPermanentState);
		states.add(rdbtnPacenowStae);
		
		JLabel lblChambersPaced = new JLabel("Chambers Paced");
		lblChambersPaced.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		JLabel lblChambersSensed = new JLabel("Chambers Sensed");
		lblChambersSensed.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		JLabel lblResponseToSensing = new JLabel("Response to Sensing");
		lblResponseToSensing.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		JLabel label = new JLabel("Patient's Heart Rate");
		label.setFont(new Font("Tahoma", Font.BOLD, 17));
				
		
		JLabel lblRecordingModes = new JLabel("Recording Modes");
		lblRecordingModes.setFont(new Font("Tahoma", Font.BOLD, 16));
		
		JRadioButton rdbtnFixed = new JRadioButton("Fixed");
		rdbtnFixed.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				fixedMemory = true;
			}
		});
		rdbtnFixed.setSelected(true);
		rdbtnFixed.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JRadioButton rdbtnContinuous = new JRadioButton("Continuous");
		rdbtnContinuous.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				fixedMemory = false;
			}
		});
		rdbtnContinuous.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		ButtonGroup recording = new ButtonGroup();
		recording.add(rdbtnFixed);
		recording.add(rdbtnContinuous);
		
		JLabel label_1 = new JLabel("Paced Heart Rate");
		label_1.setFont(new Font("Tahoma", Font.BOLD, 17));
		
		JLabel lblSensedDelay = new JLabel("Sensed Delay");
		lblSensedDelay.setFont(new Font("Tahoma", Font.BOLD, 16));
		
		/**
		 * The sensed delay text box sets the delay in milliseconds before a pacing event
		 * happens after a sensing event took place.
		 */
		sensedDelay = new JTextField();
		sensedDelay.setText("2000");
		sensedDelay.setFont(new Font("Tahoma", Font.PLAIN, 15));
		sensedDelay.setColumns(10);
		
		JButton btnLowBattreySim = new JButton("Low Battery Sim");
		btnLowBattreySim.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				comboBox.setSelectedIndex(2);
				comboBox_1.setSelectedIndex(2);
				comboBox_2.setSelectedIndex(2);
				comboBox.setEnabled(false);
				comboBox_1.setEnabled(false);
				comboBox_2.setEnabled(false);
			}
		});
		btnLowBattreySim.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnLowBattreySim.setBackground(SystemColor.inactiveCaptionBorder);
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
							.addComponent(label, GroupLayout.PREFERRED_SIZE, 171, GroupLayout.PREFERRED_SIZE)
							.addContainerGap(651, Short.MAX_VALUE))
						.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
							.addComponent(label_1)
							.addContainerGap(675, Short.MAX_VALUE))
						.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(btnHeartRate, GroupLayout.PREFERRED_SIZE, 179, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnLowBattreySim, GroupLayout.PREFERRED_SIZE, 179, GroupLayout.PREFERRED_SIZE))
							.addGap(12)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(lblNewLabel, GroupLayout.DEFAULT_SIZE, 140, Short.MAX_VALUE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(lblChambersPaced, GroupLayout.DEFAULT_SIZE, 119, Short.MAX_VALUE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(lblChambersSensed, GroupLayout.DEFAULT_SIZE, 127, Short.MAX_VALUE))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addComponent(rdbtnPacenowStae, GroupLayout.PREFERRED_SIZE, 148, GroupLayout.PREFERRED_SIZE)
										.addComponent(rdbtnPoweronresetState)
										.addGroup(gl_contentPane.createSequentialGroup()
											.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
												.addGroup(gl_contentPane.createSequentialGroup()
													.addComponent(rdbtnTemporaryState)
													.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
													.addComponent(lblSensedDelay))
												.addGroup(gl_contentPane.createSequentialGroup()
													.addComponent(rdbtnPermanentState)
													.addGap(18)
													.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, 109, GroupLayout.PREFERRED_SIZE)))
											.addPreferredGap(ComponentPlacement.RELATED)
											.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
												.addComponent(sensedDelay, 0, 0, Short.MAX_VALUE)
												.addComponent(comboBox_1, 0, 128, Short.MAX_VALUE))))
									.addGap(8)))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
									.addGroup(gl_contentPane.createSequentialGroup()
										.addComponent(rdbtnContinuous, GroupLayout.PREFERRED_SIZE, 139, GroupLayout.PREFERRED_SIZE)
										.addContainerGap())
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addGroup(gl_contentPane.createSequentialGroup()
											.addComponent(rdbtnFixed)
											.addContainerGap())
										.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
											.addGroup(gl_contentPane.createSequentialGroup()
												.addComponent(lblResponseToSensing, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
												.addGap(111))
											.addGroup(gl_contentPane.createSequentialGroup()
												.addComponent(lblRecordingModes, GroupLayout.DEFAULT_SIZE, 245, Short.MAX_VALUE)
												.addContainerGap()))))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(comboBox_2, GroupLayout.PREFERRED_SIZE, 144, GroupLayout.PREFERRED_SIZE)
									.addContainerGap())))))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(label, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)
					.addGap(166)
					.addComponent(label_1)
					.addGap(196)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblNewLabel)
								.addComponent(lblChambersPaced)
								.addComponent(lblChambersSensed, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblResponseToSensing))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(comboBox_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(rdbtnPermanentState, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(comboBox_2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(rdbtnTemporaryState)
								.addComponent(lblSensedDelay)
								.addComponent(sensedDelay, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblRecordingModes))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(rdbtnPacenowStae)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(rdbtnPoweronresetState))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(rdbtnFixed)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(rdbtnContinuous)))
							.addGap(149))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(btnHeartRate, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnLowBattreySim, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE)
							.addGap(184))))
		);
		
		/**
		 * The following thread will take care of the drawing and calling the continuous draw
		 * method. The first method just draws the selected heart rate of the patient, while the 
		 * second thread draws the drawn points of the first thread, whilst applying the
		 * necessary sensing and pacing points. The return statement in the second part, will
		 * get the points to be written in the memory.
		 */
		new Thread(new Runnable() {
		     public void run() {
	    		int countHeartBeats = 0;
		        try {
					while (true) {
						if (heartBeatPanel != null) {
							heartBeatPanel.continousDraw(countHeartBeats, false, false, null);
						}
						countHeartBeats++;
						Thread.sleep(100);
					}
		        } catch (InterruptedException e) {}
		     }
		}).start();
		
		new Thread(new Runnable() {
		     public void run() {
	    		int countHeartBeats = 0;
	    		int countPacedBeats = 0;
	    		if (normalPanel != null) {}
	    		boolean clearFile = true;
		        try {
					while (true) {
						if (normalPanel != null) { 
							
			    			normalPanel.setModes(pm, sm, rm, sensedDelay.getText());
							normalPanel.checkStates(state);
							
							if(heartRate.ratesList.size() > countPacedBeats + 2) {
								pacedList.add(heartRate.ratesList.get(countPacedBeats));
								countPacedBeats++;
								pacedList.add(heartRate.ratesList.get(countPacedBeats));
								countPacedBeats++;
							}
							ArrayList<Point> points = normalPanel.continousDraw(countHeartBeats, true, true, pacedList);
							
							if (fixedMemory) {
								MemoryWrite.writeFixed(points, clearFile);
							} else {
								MemoryWrite.continuousWrite(points, clearFile);
							}
							
							clearFile = false;
							countHeartBeats++;
						}
						Thread.sleep(100);
					}
		        } catch (InterruptedException e) {}
		     }
		}).start();
		
		contentPane.setLayout(gl_contentPane);
		tabbedPane.addTab("DCM Simulator", contentPane);
    }
}
