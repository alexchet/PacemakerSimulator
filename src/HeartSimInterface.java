import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.GridBagLayout;
import java.awt.Point;
import java.awt.RenderingHints;

import javax.swing.JButton;
import java.awt.GridBagConstraints;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.Panel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class HeartSimInterface extends JFrame {
	Panel panelNormal = new Panel();
	BradyPanel bradyPanel = new BradyPanel();
    ArrayList<Point> normalRatesList = new ArrayList<Point>();
    ArrayList<Point> bradyRatesList = new ArrayList<Point>();
    ArrayList<Point> tachyRatesList = new ArrayList<Point>();
    ArrayList<Point> activityRatesList = new ArrayList<Point>();
    boolean normalFlag=false;
    boolean bradyFlag = false;

    NormalData normal;
    BradyData brady;
    
	ArrayList al = new ArrayList();
	private JPanel contentPane;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		new HeartSimInterface();
		/*EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HeartSimInterface frame = new HeartSimInterface();
					frame.setVisible(true);
					//frame.add(new myView());
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});*/
	}

	/**
	 * Create the frame.
	 */
	public HeartSimInterface() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 744, 597);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		normal = new NormalData(normalRatesList);
		brady = new BradyData(bradyRatesList);
		
		//getContentPane().add(panelNormal);
		//getContentPane().add(bradyPanel);
		
		 
		JButton btnNormalRate = new JButton("Normal Heart Rate");
		btnNormalRate.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				getContentPane().remove(bradyPanel);
				getContentPane().add(panelNormal);
				normalFlag = true;
				System.out.println(normalFlag);
				bradyFlag= false;
				//return;
			}
		});
		btnNormalRate.setFont(new Font("Tahoma", Font.PLAIN, 17));
		
		JButton btnBradyRate = new JButton("Bradycardia Heart Rate");
		btnBradyRate.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				getContentPane().remove(panelNormal);
				getContentPane().add(bradyPanel);
				
				bradyFlag= true;
				System.out.println("brad");
				normalFlag=false;
			}
		});
		btnBradyRate.setFont(new Font("Tahoma", Font.PLAIN, 17));
		
		JButton btnTachycardiaHeartRate = new JButton("Tachycardia Heart Rate");
		btnTachycardiaHeartRate.setFont(new Font("Tahoma", Font.PLAIN, 17));
		
		JButton btnRunningactivity = new JButton("Running Activity");
		btnRunningactivity.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
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
		
		while(normalFlag==false && bradyFlag==false){
			System.out.println("bg");
		}
		int i=1;
		while(i>0){
		if(normalFlag ){

			 while (normalFlag && !bradyFlag) {
		            try {
		            	System.out.println("in");
		                panelNormal.continousDraw();
		                repaint();
		                Thread.sleep(200);
		            } catch (InterruptedException e) {}
		        }
			/* if(bradyFlag){
			System.out.println("ji");
		}*/

		}
		 if(bradyFlag){
				System.out.println("ji");
				 while (!normalFlag && bradyFlag) {
			            try {
			            	//System.out.println("in");
			                bradyPanel.continousDraw();
			                repaint();
			                Thread.sleep(200);
			            } catch (InterruptedException e) {}
			        }
			}
		}
	//}
		 /*while (true) {
	            try {
	                panelNormal.continousDraw();
	                repaint();
	                Thread.sleep(200);
	            } catch (InterruptedException e) {}
	        }
*/
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
            JButton btnJoinGame = new JButton("Join game"); 
			add(btnJoinGame);
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
	
	 private  class NormalData extends Thread{
	        ArrayList points;
	        int normalRates[] = {10,40,10,90,10};
	        public NormalData(ArrayList p){
	            points=p;
	            this.start();
	        }
	    public void run()
	    {

	        while (true)
	        {
	            while (normalRatesList.size() < 500)
	            {


	                for(int i=0; i<normalRates.length; i++){
	                    int p = normalRates[i];
	                    points.add(new Point(0,50-p));
	                }
	            }
	        }

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
	            JButton btnJoinGame = new JButton("Brady"); 
				add(btnJoinGame);
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
		
		private  class BradyData extends Thread{
	        ArrayList points;
	        int bradyRates[] = {10,40,10,90,10,10,10};
	        public BradyData(ArrayList p){
	            points=p;
	            this.start();
	        }
	    public void run()
	    {

	        while (true)
	        {
	            while (bradyRatesList.size() < 500)
	            {


	                for(int i=0; i<bradyRates.length; i++){
	                    int p = bradyRates[i];
	                    points.add(new Point(0,50-p));
	                }
	            }
	        }

	    }
	    }
}
	
	