import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import java.awt.BorderLayout;
import javax.swing.JTabbedPane;
import java.awt.FlowLayout;
import java.awt.SystemColor;
import javax.swing.JLabel;

public class HeartSimInterface extends JFrame {
	JPanel contentPane;
	
	Panel normalPanel;
	Panel bradyPanel;
	Panel tachyPanel;
	Panel activityPanel;
	JPanel initialPanel;
	
    ArrayList<Point> normalRatesList;
	ArrayList<Point> tachyRatesList;
    ArrayList<Point> bradyRatesList;
    ArrayList<Point> activityRatesList;  
         
    boolean normalFlag=false;
    boolean bradyFlag = false;
    boolean tachyFlag = false;
    boolean activityFlag = false;

    HeartRate normal;
    HeartRate brady;
    HeartRate tachy;
    HeartRate acti;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		new HeartSimInterface();
	}

	/**
	 * Create the frame.
	 */

    
    public void resetPanels() {
		if (bradyPanel != null) contentPane.remove(bradyPanel);
		if (normalPanel != null) contentPane.remove(normalPanel);
		if (tachyPanel != null) contentPane.remove(tachyPanel);
		if (activityPanel != null) contentPane.remove(activityPanel);
		if (initialPanel != null) contentPane.remove(initialPanel);
    }
    
    public void resetFlags() {
    	normalFlag = false;
		bradyFlag= false;
		tachyFlag=false;
		activityFlag=false;
    }
    
	public HeartSimInterface() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 850, 643);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBackground(SystemColor.text);
		tabbedPane.setFont(new Font("Tahoma", Font.BOLD, 15));
		tabbedPane.setSize(getContentPane().getWidth(), getContentPane().getHeight());
		tabbedPane.setBounds(100, 100, 751, 633);
		
		
		contentPane = new JPanel();
		contentPane.setSize(getContentPane().getWidth(), getContentPane().getHeight());
		contentPane.setBounds(100, 100, 751, 620);
		
		initialPanel = new JPanel();
		initialPanel.setBounds(5,5,800,300);		
		initialPanel.setBorder(BorderFactory.createLineBorder(Color.white, 2));
		initialPanel.setBackground(Color.white);
		
		contentPane.add(initialPanel);
		
		tabbedPane.addTab("Heart Simulator",contentPane);
		tabbedPane.setForegroundAt(0, new Color(0, 0, 0));
		tabbedPane.setBackgroundAt(0, SystemColor.activeCaption);
		
		JButton btnNormalRate = new JButton("Normal Heart Rate");
		btnNormalRate.setBackground(SystemColor.inactiveCaptionBorder);
		btnNormalRate.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				normal = new HeartRate(RatePoints.getNormalRates());
				normalRatesList = normal.getRatesList();
				normalPanel = new Panel(normalRatesList);
				
				resetPanels();
				resetFlags();
				normalPanel.resetGraph();
				
				normalFlag = true;
				contentPane.add(normalPanel);
			}
		});
		btnNormalRate.setFont(new Font("Tahoma", Font.BOLD, 15));
		
		JButton btnBradyRate = new JButton("Bradycardia Heart Rate");
		btnBradyRate.setBackground(SystemColor.inactiveCaptionBorder);
		btnBradyRate.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnBradyRate.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {				

				brady = new HeartRate(RatePoints.getBrachycardicRates());
				bradyRatesList = brady.getRatesList();
				bradyPanel = new Panel(bradyRatesList);
				
				resetPanels();
				resetFlags();
				bradyPanel.resetGraph();
				
				bradyFlag = true;
				contentPane.add(bradyPanel);
				//mnHeartSimulation.add(contentPane);
				//contentPane.add(bradyPanel);
				//mnHeartSimulation.add(bradyPanel);
			}
		});
		
		JButton btnRunningactivity = new JButton("Running Activity");
		btnRunningactivity.setBackground(SystemColor.inactiveCaptionBorder);
		btnRunningactivity.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {				
				
				acti = new HeartRate(RatePoints.getRunningActivityRates());
				activityRatesList = tachy.getRatesList();
				activityPanel = new Panel(activityRatesList);
				
				resetPanels();
				resetFlags();
				activityPanel.resetGraph();
				
				activityFlag = true;
				contentPane.add(activityPanel);
			}
		});
		btnRunningactivity.setFont(new Font("Tahoma", Font.BOLD, 15));
		
		JButton btnTachycardiaHeartRate = new JButton("Tachycardia Heart Rate");
		btnTachycardiaHeartRate.setBackground(SystemColor.inactiveCaptionBorder);
		btnTachycardiaHeartRate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {	
				
				tachy = new HeartRate(RatePoints.getTachycardicRates());
				tachyRatesList = tachy.getRatesList();
				tachyPanel = new Panel(tachyRatesList);
				
				resetPanels();
				resetFlags();
				tachyPanel.resetGraph();
				
				tachyFlag = true;
				contentPane.add(tachyPanel);
			}
		});
		getContentPane().setLayout(new FlowLayout(FlowLayout.CENTER, 1, 1));
		btnTachycardiaHeartRate.setFont(new Font("Tahoma", Font.BOLD, 15));
		//lblG.se
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(5)
					.addComponent(btnNormalRate, GroupLayout.PREFERRED_SIZE, 179, GroupLayout.PREFERRED_SIZE)
					.addGap(6)
					.addComponent(btnBradyRate, GroupLayout.DEFAULT_SIZE, 215, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnTachycardiaHeartRate, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnRunningactivity, GroupLayout.PREFERRED_SIZE, 172, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap(314, Short.MAX_VALUE)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnNormalRate, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnBradyRate, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnTachycardiaHeartRate, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnRunningactivity, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE))
					.addGap(180))
		);
		contentPane.setLayout(gl_contentPane);
		getContentPane().add(tabbedPane);
		
		setVisible(true);

        try {
			while (true) {
				if (normalFlag) {
		                normalPanel.continousDraw();
				}

				if (bradyFlag) {
		                bradyPanel.continousDraw();
				}

				if (tachyFlag) {
		                tachyPanel.continousDraw();
				}

				if (activityFlag) {
		                activityPanel.continousDraw();
				}
	
	            repaint();
	            Thread.sleep(200);
			}
        } catch (InterruptedException e) {}
	}
}
	
	