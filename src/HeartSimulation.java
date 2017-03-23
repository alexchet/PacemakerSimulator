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

/*
 * This class simulates different Heart Rates. Such as Normal Heart Rate, Bradycardia Heart Rate,
 * Bradycardia Sinus Arrest,Rate-Adaptive Heart Rate and Tachycardia. It also gives the option to stop the simulation.
 */

public class HeartSimulation extends JFrame{
	JPanel contentPane, initialPanel;
	/**
	 * Panels for each Heart Rate we are simulating. 
	 */
	Panel normalPanel, bradyPanel, tachyPanel, activityPanel, bradySinusPanel, bradySecAVPanel;  
	/**
	 * Flags to control which Heart Rate is going to be simulated.
	 */
    boolean normalFlag, bradyFlag, tachyFlag, activityFlag, bradySinusFlag, bradySecAVFlag;
	int countHeartBeats = 0;

	
/*
 * The HeartSimulation Constructor. Here we have the content panel which is the main panel and on top of it we
 * build the interface. Initially we only have the initial panell and all the buttons 
 * to change between heart rates.
 */
	public HeartSimulation(JTabbedPane tabbedPane, HeartRate heartRate) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 850, 643);

		contentPane = new JPanel();
		contentPane.setBounds(100, 100, 751, 620);
		/*
		 * The initial panel is just a white rectangle that indicates where the graph for the chosen Heart Rate simulation 
		 * is going to be drawn on. 
		 */
		initialPanel = new JPanel();
		initialPanel.setBounds(5,5,820,190);	
		initialPanel.setBackground(Color.white);
				
		contentPane.add(initialPanel);
		GroupLayout gl_initialPanel = new GroupLayout(initialPanel);
		gl_initialPanel.setHorizontalGroup(
			gl_initialPanel.createParallelGroup(Alignment.LEADING)
				.addGap(0, 820, Short.MAX_VALUE)
		);
		gl_initialPanel.setVerticalGroup(
			gl_initialPanel.createParallelGroup(Alignment.LEADING)
				.addGap(0, 190, Short.MAX_VALUE)
		);
		initialPanel.setLayout(gl_initialPanel);
		
		
		tabbedPane.addTab("Heart Simulator", contentPane);
		
		/**
		 * This Button has an event listener, which listens on click events.
		 * When the button is clicked the startHeart method in the HeartRate class,
		 * it is called.
		 * It sets the flags to indicate which heart rate is going to be simulated.
		 * It also creates a new Panel that replaces the existing panels on the ContentPanel.
		 * The graph for the Normal Heart Rate is drawn on this new panel.
		 */
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
		

		/**
		 * This Button has an event listener, which listens on click events.
		 * When the button is clicked the startHeart method in the HeartRate class,
		 * it is called.
		 * It sets the flags to indicate which heart rate is going to be simulated.
		 * It also creates a new Panel that replaces the existing panels on the ContentPanel.
		 * The graph for the Bradycardia Heart Rate is drawn on this panel.
		 */
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
		
		/**
		 * This Button has an event listener, which listens on click events.
		 * When the button is clicked the startHeart method in the HeartRate class,
		 * it is called.
		 * It sets the flags to indicate which heart rate is going to be simulated.
		 * It also creates a new Panel that replaces the existing panels on the ContentPanel.
		 * The graph for the Adaptive-Heart Rate is drawn on this panel.
		 */
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
		
		/**
		 * This Button has an event listener, which listens on click events.
		 * When the button is clicked the startHeart method in the HeartRate class,
		 * it is called.
		 * It sets the flags to indicate which heart rate is going to be simulated.
		 * It also creates a new Panel that replaces the existing panels on the ContentPanel.
		 * The graph for the Tachycardia Heart Rate is drawn on this panel.
		 */
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
		
		JLabel lblPatientsHeartRate = new JLabel("Patient's Heart Rate");
		lblPatientsHeartRate.setBackground(Color.WHITE);
		lblPatientsHeartRate.setFont(new Font("Tahoma", Font.BOLD, 17));
		
		/**
		 * This Button has an event listener, which listens on click events.
		 * When the button is clicked the startHeart method in the HeartRate class,
		 * it is called.
		 * It sets the flags to indicate which heart rate is going to be simulated.
		 * It also creates a new Panel that replaces the existing panels on the ContentPanel.
		 * The graph for the Bradycardia Sinus Arrest Heart Rate is drawn on this panel.
		 */
		JButton btnBradycardiaSinusArrest = new JButton("Bradycardia Sinus Arrest");
		btnBradycardiaSinusArrest.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
				if (!bradySinusFlag) {
					countHeartBeats = 0;
					heartRate.reset();
					heartRate.startHeart(RatePoints.getBradyArrestRates());
					bradySinusPanel = new Panel(heartRate.getRatesList());
					
					resetPanels();
					resetFlags();
					bradySinusPanel.resetGraph();
					
					bradySinusFlag = true;
					contentPane.add(bradySinusPanel);
				}
			}
		});
		btnBradycardiaSinusArrest.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnBradycardiaSinusArrest.setBackground(SystemColor.inactiveCaptionBorder);
		
		/**
		 * This Button has an event listener, which listens on click events.
		 * When the button is clicked the startHeart method in the HeartRate class,
		 * it is called.
		 * It sets the flags to indicate which heart rate is going to be simulated.
		 * It also creates a new Panel that replaces the existing panels on the ContentPanel.
		 * The graph for the Secondary AV Block Heart Rate is drawn on this panel.
		 */
		JButton btnSecondaryAvBlock = new JButton("Secondary AV Block");
		btnSecondaryAvBlock.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (!bradySecAVFlag) {
					countHeartBeats = 0;
					heartRate.reset();
					heartRate.startHeart(RatePoints.getBradySecAVRates());
					bradySecAVPanel = new Panel(heartRate.getRatesList());
					
					resetPanels();
					resetFlags();
					bradySecAVPanel.resetGraph();
					
					bradySecAVFlag = true;
					contentPane.add(bradySecAVPanel);
				}
			}
		});
		btnSecondaryAvBlock.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnSecondaryAvBlock.setBackground(SystemColor.inactiveCaptionBorder);
		
		
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblPatientsHeartRate)
							.addGap(619))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
								.addComponent(btnRunningactivity, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 231, Short.MAX_VALUE)
								.addComponent(btnTachycardiaHeartRate, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 231, Short.MAX_VALUE)
								.addComponent(btnBradyRate, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 231, Short.MAX_VALUE))
							.addGap(12)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(btnStopSimulation, GroupLayout.DEFAULT_SIZE, 221, Short.MAX_VALUE)
								.addComponent(btnBradycardiaSinusArrest, GroupLayout.DEFAULT_SIZE, 221, Short.MAX_VALUE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnSecondaryAvBlock, GroupLayout.PREFERRED_SIZE, 241, GroupLayout.PREFERRED_SIZE)
							.addGap(78))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(btnNormalRate, GroupLayout.PREFERRED_SIZE, 244, GroupLayout.PREFERRED_SIZE)
							.addContainerGap())))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(195)
					.addComponent(lblPatientsHeartRate)
					.addPreferredGap(ComponentPlacement.RELATED, 61, Short.MAX_VALUE)
					.addComponent(btnNormalRate, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnBradyRate, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnBradycardiaSinusArrest, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnSecondaryAvBlock, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnTachycardiaHeartRate, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnRunningactivity, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnStopSimulation, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE))
					.addGap(146))
		);

		contentPane.setLayout(gl_contentPane);
		getContentPane().add(tabbedPane);
		setVisible(true);

		/**
		 * We start a new Java Thread. Here we check which Flag is set to true based
		 * on the button which was clicked and we call the continuousDraw method in the Panel
		 * class, to draw the selected Heart Rate on the selected Panel.
		 */
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
		
						if (bradySinusFlag) {
				                bradySinusPanel.continousDraw(countHeartBeats, false, false, null);
						}
		
						if (bradySecAVFlag) {
				                bradySecAVPanel.continousDraw(countHeartBeats, false, false, null);
						}
						countHeartBeats++;
			            repaint();
			            Thread.sleep(100);
					}
		        } catch (InterruptedException e) {}
		     }
		}).start();
	}
    
	/**
	 * Method which check if the panel is on the contentPanel and
	 * if it is then it removes it.
	 */
    public void resetPanels() {
		if (bradyPanel != null) contentPane.remove(bradyPanel);
		if (normalPanel != null) contentPane.remove(normalPanel);
		if (tachyPanel != null) contentPane.remove(tachyPanel);
		if (activityPanel != null) contentPane.remove(activityPanel);
		if (initialPanel != null) contentPane.remove(initialPanel);
		if (bradySinusPanel != null) contentPane.remove(bradySinusPanel);
		if (bradySecAVPanel != null) contentPane.remove(bradySecAVPanel);
    }
    /**
	 * Method which sets all the flags to false when a button is clicked
	 */
    public void resetFlags() {
    	normalFlag = false;
		bradyFlag = false;
		bradySinusFlag = false;
		bradySecAVFlag = false;
		tachyFlag = false;
		activityFlag = false;
    }
}
