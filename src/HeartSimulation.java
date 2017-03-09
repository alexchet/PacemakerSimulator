import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

public class HeartSimulation extends JFrame{
	JPanel contentPane;
	
	Panel normalPanel;
	Panel bradyPanel;
	Panel tachyPanel;
	Panel activityPanel;
         
    boolean normalFlag=false;
    boolean bradyFlag = false;
    boolean tachyFlag = false;
    boolean activityFlag = false;
   
	int countHeartBeats = 0;

    /**
	 * Create the frame.
	 */

	public HeartSimulation(JTabbedPane tabbedPane, HeartRate heartRate) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 744, 597);

		contentPane = new JPanel();
		tabbedPane.addTab("Heart Simulator", contentPane);
		
		JButton btnNormalRate = new JButton("Normal Heart Rate");
		btnNormalRate.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {				
				countHeartBeats = 0;
				heartRate.startHeart(RatePoints.getNormalRates());
				normalPanel = new Panel(heartRate.getRatesList());
				
				resetPanels();
				resetFlags();
				normalPanel.resetGraph();
				
				normalFlag = true;
				contentPane.add(normalPanel);
			}
		});
		btnNormalRate.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		JButton btnBradyRate = new JButton("Bradycardia Heart Rate");
		btnBradyRate.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnBradyRate.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {				
				countHeartBeats = 0;
				heartRate.startHeart(RatePoints.getBrachycardicRates());
				bradyPanel = new Panel(heartRate.getRatesList());
				
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
		btnRunningactivity.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {				
				countHeartBeats = 0;
				heartRate.startHeart(RatePoints.getRunningActivityRates());
				activityPanel = new Panel(heartRate.getRatesList());
				
				resetPanels();
				resetFlags();
				activityPanel.resetGraph();
				
				activityFlag = true;
				contentPane.add(activityPanel);
			}
		});
		btnRunningactivity.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		JButton btnTachycardiaHeartRate = new JButton("Tachycardia Heart Rate");
		btnTachycardiaHeartRate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {				
				countHeartBeats = 0;
				heartRate.startHeart(RatePoints.getTachycardicRates());
				tachyPanel = new Panel(heartRate.getRatesList());
				
				resetPanels();
				resetFlags();
				tachyPanel.resetGraph();
				
				tachyFlag = true;
				contentPane.add(tachyPanel);
			}
		});
		getContentPane().setLayout(new FlowLayout(FlowLayout.CENTER, 1, 1));
		btnTachycardiaHeartRate.setFont(new Font("Tahoma", Font.PLAIN, 16));
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
		getContentPane().add(tabbedPane);
		setVisible(true);

		new Thread(new Runnable() {
		     public void run() {
		        try {
					while (true) {
						if (normalFlag) {
				                normalPanel.continousDraw(countHeartBeats);
						}
		
						if (bradyFlag) {
				                bradyPanel.continousDraw(countHeartBeats);
						}
		
						if (tachyFlag) {
				                tachyPanel.continousDraw(countHeartBeats);
						}
		
						if (activityFlag) {
				                activityPanel.continousDraw(countHeartBeats);
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
    }
    
    public void resetFlags() {
    	normalFlag = false;
		bradyFlag= false;
		tachyFlag=false;
		activityFlag=false;
    }
}
