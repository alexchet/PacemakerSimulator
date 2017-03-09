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

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenu;

public class HeartSimInterface extends JFrame {
	
	Panel normalPanel;
	Panel bradyPanel;
	Panel tachyPanel;
	Panel activityPanel;
	
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
    
	private JPanel contentPane;
	

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
		if (bradyPanel != null) getContentPane().remove(bradyPanel);
		if (normalPanel != null) getContentPane().remove(normalPanel);
		if (tachyPanel != null) getContentPane().remove(tachyPanel);
		if (activityPanel != null) getContentPane().remove(activityPanel);
    }
    
    public void resetFlags() {
    	normalFlag = false;
		bradyFlag= false;
		tachyFlag=false;
		activityFlag=false;
    }
    
	public HeartSimInterface() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 744, 597);
		
		JMenuBar menuBar = new JMenuBar();
		
		setJMenuBar(menuBar);
		
		JMenu mnHeartSimulation = new JMenu("Heart Simulation");
		menuBar.add(mnHeartSimulation);
		
		contentPane = new JPanel();
		//contentPane.add(bradyPanel);
		mnHeartSimulation.add(contentPane);
		
		
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		JButton btnNormalRate = new JButton("Normal Heart Rate");
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
				getContentPane().add(normalPanel);
			}
		});
		btnNormalRate.setFont(new Font("Tahoma", Font.PLAIN, 17));
		
		JButton btnBradyRate = new JButton("Bradycardia Heart Rate");
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
				mnHeartSimulation.add(contentPane);
				//getContentPane().add(bradyPanel);
				//mnHeartSimulation.add(bradyPanel);
			}
		});		
				
		JButton btnTachycardiaHeartRate = new JButton("Tachycardia Heart Rate");
		btnTachycardiaHeartRate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {	
				
				tachy = new HeartRate(RatePoints.getTachycardicRates());
				tachyRatesList = tachy.getRatesList();
				tachyPanel = new Panel(tachyRatesList);
				
				resetPanels();
				resetFlags();
				tachyPanel.resetGraph();
				
				tachyFlag = true;
				getContentPane().add(tachyPanel);
			}
		});
		btnTachycardiaHeartRate.setFont(new Font("Tahoma", Font.PLAIN, 17));
		
		JButton btnRunningactivity = new JButton("Running Activity");
		btnRunningactivity.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {				
				
				acti = new HeartRate(RatePoints.getRunningActivityRates());
				activityRatesList = tachy.getRatesList();
				activityPanel = new Panel(activityRatesList);
				
				resetPanels();
				resetFlags();
				activityPanel.resetGraph();
				
				activityFlag = true;
				getContentPane().add(activityPanel);
			}
		});
		btnRunningactivity.setFont(new Font("Tahoma", Font.PLAIN, 17));
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(btnBradyRate, GroupLayout.DEFAULT_SIZE, 692, Short.MAX_VALUE)
						.addComponent(btnNormalRate, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 692, Short.MAX_VALUE)
						.addComponent(btnTachycardiaHeartRate, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 692, Short.MAX_VALUE)
						.addComponent(btnRunningactivity, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 692, Short.MAX_VALUE))
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
					.addContainerGap(330, Short.MAX_VALUE)
					.addComponent(btnNormalRate, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnBradyRate, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnTachycardiaHeartRate, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnRunningactivity, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		contentPane.setLayout(gl_contentPane);
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
	
	