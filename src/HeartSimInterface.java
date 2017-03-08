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

public class HeartSimInterface extends JFrame {
	Panel panelNormal = new Panel();
	BradyPanel bradyPanel = new BradyPanel();
	TachyPanel tachyPanel = new TachyPanel(); 
	ActivityPanel activityPanel = new ActivityPanel();
	
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
    
	ArrayList al = new ArrayList();
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
		getContentPane().remove(bradyPanel);
		getContentPane().remove(panelNormal);
		getContentPane().remove(tachyPanel);
		getContentPane().remove(activityPanel);
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
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		normal = new HeartRate(RatePoints.getNormalRates());
		normalRatesList = normal.getRatesList();
		
		brady = new HeartRate(RatePoints.getBrachycardicRates());
		bradyRatesList = brady.getRatesList();
		
		tachy = new HeartRate(RatePoints.getTachycardicRates());
		tachyRatesList = tachy.getRatesList();
		
		acti = new HeartRate(RatePoints.getRunningActivityRates());
		activityRatesList = tachy.getRatesList();
		
		JButton btnNormalRate = new JButton("Normal Heart Rate");
		btnNormalRate.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				resetPanels();
				resetFlags();
				
				normalFlag = true;
				getContentPane().add(panelNormal);
			}
		});
		btnNormalRate.setFont(new Font("Tahoma", Font.PLAIN, 17));
		
		JButton btnBradyRate = new JButton("Bradycardia Heart Rate");
		btnBradyRate.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				resetPanels();
				resetFlags();
				
				bradyFlag = true;
				getContentPane().add(bradyPanel);
			}
		});		
				
		JButton btnTachycardiaHeartRate = new JButton("Tachycardia Heart Rate");
		btnTachycardiaHeartRate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				resetPanels();
				resetFlags();
				
				tachyFlag = true;
				getContentPane().add(tachyPanel);
			}
		});
		btnTachycardiaHeartRate.setFont(new Font("Tahoma", Font.PLAIN, 17));
		
		JButton btnRunningactivity = new JButton("Running Activity");
		btnRunningactivity.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				resetPanels();
				resetFlags();
				
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
		                panelNormal.continousDraw();
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
	
	public class Panel extends JPanel{
        Graphics2D graph;
        BufferedImage bufferedImage;
        int spaceBetweenpoints = 20;

        public Panel(){
            setBounds(10,10,600,300);
            bufferedImage = new BufferedImage(getWidth(),getHeight(),BufferedImage.TYPE_INT_ARGB);
            graph = bufferedImage.createGraphics();
            graph.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            graph.setColor(Color.white);
            graph.fillRect(0,0,900,300);
           
        }

        /**
         * This method stops the graph from drawing the same point again when trying to print a new point
         * in other words it keeps the graph tidy
         * @param x
         * @param width
         */
        public void deletePoints(int x,int width){
            graph.setColor(Color.white);
            graph.fillRect(x,0,width,getHeight());
        }

        /**
         * Method which "loops" the graph"
         */
        public void continousDraw(){
            //Checks if i passed two points before doing anything, it draws the image
            //the image in this case is the rectangle
            if(normalRatesList.size()>=2){
                graph.drawImage(bufferedImage,0,0, getWidth()-spaceBetweenpoints,getHeight(),spaceBetweenpoints,0,getWidth(),getHeight(),null);
                deletePoints(getWidth()-spaceBetweenpoints,spaceBetweenpoints);
                graph.setColor(Color.BLACK);
                Point point1;
                Point point2;
                point1=normalRatesList.get(0);
                point2 =normalRatesList.get(1);
                graph.drawLine(getWidth()-spaceBetweenpoints-1,point1.y+getHeight()/2,getWidth()-1,point2.y+getHeight()/2);
                normalRatesList.remove(0);
            }
        }

        public void paint(Graphics g)
        {
            g.drawImage(bufferedImage,0,0,null);
        }

    }
	 
		public class BradyPanel extends JPanel{
	        Graphics2D graph;
	        BufferedImage bufferedImage;
	        int spaceBetweenpoints = 20;

	        public BradyPanel(){
	            setBounds(10,10,600,300);
	            bufferedImage = new BufferedImage(getWidth(),getHeight(),BufferedImage.TYPE_INT_ARGB);
	            graph = bufferedImage.createGraphics();
	            graph.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
	            graph.setColor(Color.white);
	            graph.fillRect(0,0,900,300);
	            
	        }

	        /**
	         * This method stops the graph from drawing the same point again when trying to print a new point
	         * in other words it keeps the graph tidy
	         * @param x
	         * @param width
	         */
	        public void deletePoints(int x,int width){
	            graph.setColor(Color.white);
	            graph.fillRect(x,0,width,getHeight());
	        }

	        /**
	         * Method which "loops" the graph"
	         */
	        public void continousDraw(){
	            //Checks if i passed two points before doing anything, it draws the image
	            //the image in this case is the rectangle
	            if(bradyRatesList.size()>=2){
	                graph.drawImage(bufferedImage,0,0, getWidth()-spaceBetweenpoints,getHeight(),spaceBetweenpoints,0,getWidth(),getHeight(),null);
	                deletePoints(getWidth()-spaceBetweenpoints,spaceBetweenpoints);
	                graph.setColor(Color.BLACK);
	                Point point1;
	                Point point2;
	                point1=bradyRatesList.get(0);
	                point2 =bradyRatesList.get(1);
	                graph.drawLine(getWidth()-spaceBetweenpoints-1,point1.y+getHeight()/2,getWidth()-1,point2.y+getHeight()/2);
	                bradyRatesList.remove(0);
	            }
	        }

	        public void paint(Graphics g)
	        {
	            g.drawImage(bufferedImage,0,0,null);
	        }

	    }
				
		/*Tachycardia */

		public class TachyPanel extends JPanel{
	        Graphics2D graph;
	        BufferedImage bufferedImage;
	        int spaceBetweenpoints = 20;

	        public TachyPanel(){
	            setBounds(10,10,600,300);
	            bufferedImage = new BufferedImage(getWidth(),getHeight(),BufferedImage.TYPE_INT_ARGB);
	            graph = bufferedImage.createGraphics();
	            graph.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
	            graph.setColor(Color.white);
	            graph.fillRect(0,0,900,300);
	        }

	        /**
	         * This method stops the graph from drawing the same point again when trying to print a new point
	         * in other words it keeps the graph tidy
	         * @param x
	         * @param width
	         */
	        public void deletePoints(int x,int width){
	            graph.setColor(Color.white);
	            graph.fillRect(x,0,width,getHeight());
	        }

	        /**
	         * Method which "loops" the graph"
	         */
	        public void continousDraw(){
	            //Checks if i passed two points before doing anything, it draws the image
	            //the image in this case is the rectangle
	            if(tachyRatesList.size()>=2){
	                graph.drawImage(bufferedImage,0,0, getWidth()-spaceBetweenpoints,getHeight(),spaceBetweenpoints,0,getWidth(),getHeight(),null);
	                deletePoints(getWidth()-spaceBetweenpoints,spaceBetweenpoints);
	                graph.setColor(Color.BLACK);
	                Point point1;
	                Point point2;
	                point1=tachyRatesList.get(0);
	                point2 =tachyRatesList.get(1);
	                graph.drawLine(getWidth()-spaceBetweenpoints-1,point1.y+getHeight()/2,getWidth()-1,point2.y+getHeight()/2);
	                tachyRatesList.remove(0);
	            }
	        }

	        public void paint(Graphics g)
	        {
	            g.drawImage(bufferedImage,0,0,null);
	        }

	    }		
		
		public class ActivityPanel extends JPanel{
	        Graphics2D graph;
	        BufferedImage bufferedImage;
	        int spaceBetweenpoints = 20;

	        public ActivityPanel(){
	            setBounds(10,10,600,300);
	            bufferedImage = new BufferedImage(getWidth(),getHeight(),BufferedImage.TYPE_INT_ARGB);
	            graph = bufferedImage.createGraphics();
	            graph.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
	            graph.setColor(Color.white);
	            graph.fillRect(0,0,900,300);
	           
	        }

	        /**
	         * This method stops the graph from drawing the same point again when trying to print a new point
	         * in other words it keeps the graph tidy
	         * @param x
	         * @param width
	         */
	        public void deletePoints(int x,int width){
	            graph.setColor(Color.white);
	            graph.fillRect(x,0,width,getHeight());
	        }

	        /**
	         * Method which "loops" the graph"
	         */
	        public void continousDraw(){
	            //Checks if i passed two points before doing anything, it draws the image
	            //the image in this case is the rectangle
	            if(activityRatesList.size()>=2){
	                graph.drawImage(bufferedImage,0,0, getWidth()-spaceBetweenpoints,getHeight(),spaceBetweenpoints,0,getWidth(),getHeight(),null);
	                deletePoints(getWidth()-spaceBetweenpoints,spaceBetweenpoints);
	                graph.setColor(Color.BLACK);
	                Point point1;
	                Point point2;
	                point1=activityRatesList.get(0);
	                point2 =activityRatesList.get(1);
	                graph.drawLine(getWidth()-spaceBetweenpoints-1,point1.y+getHeight()/2,getWidth()-1,point2.y+getHeight()/2);
	                activityRatesList.remove(0);
	            }
	        }

	        public void paint(Graphics g)
	        {
	            g.drawImage(bufferedImage,0,0,null);
	        }
	    }
}
	
	