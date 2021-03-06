import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

/*
 * Reference for the Graph: 
 * http://forums.hardwarezone.com.sg/programmers-den-296/i-dont-really-understand-java-ecg-graph-code-could-someone-help-me-2574017.html
 * We used the code from this website to build our ECG graph
 * 
 * */

/**
 * This class uses the Graphics2D class to draw an ECG graph
 *
 */
public class Panel extends JPanel{
	Graphics2D graph;
    BufferedImage bufferedImage;
    int spaceBetweenpoints = 5;
	ArrayList<Point> panelList;
	int width = 820;
	int x1 = 5;
	int y1 = 5;
	Border blackline = BorderFactory.createLineBorder(Color.black);

	long atriumSensed, internalAtriumSensed, ventricalSensed, internalVentricalSensed;
	int atriumPaced = 0, ventricalPaced = 0;
	boolean batriumPaced, bventricalPaced;
	boolean skipPoint;
	
	PacingModes paceMode;
	SensingModes senseMode; 
	ResponseModes respondMode;
	int iSensedDelay = 1200;
	
	boolean correctStateMode=false;
	
	
	/**
	 * In the constructor we are passing the arraylist and store them in the
	 * panelList. Then we are drawing a white rectangle on the screen.
	 * @param list
	 */
	public Panel(ArrayList<Point> list) {
		panelList = list;
		setBounds(x1,y1,width,190);
        bufferedImage = new BufferedImage(getWidth(),getHeight(),BufferedImage.TYPE_INT_ARGB);
        graph = bufferedImage.createGraphics();
        graph.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        graph.setColor(Color.white);
        graph.fillRect(0,0,width,190);       
	}
	
	public Panel() {
		setBounds(x1,y1,width,190);
        bufferedImage = new BufferedImage(getWidth(),getHeight(),BufferedImage.TYPE_INT_ARGB);
        graph = bufferedImage.createGraphics();
        graph.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        graph.setColor(Color.white);
        graph.fillRect(0,0,width,190);       
	}
	
	/**
	 * This method deletes the passed point from the rectangle
	 * @param x
	 * @param width
	 */
	public void deletePoints(int x,int width) {
        graph.setColor(Color.white);
        graph.fillRect(x,0,width,getHeight());
    }
    
	/**
	 * Thsi method just re-draws a white rectangle.
	 */
    public void resetGraph(){
        graph.setColor(Color.white);
        graph.fillRect(0,0,width,190);
    }
    
    /**
     * Method which sets the chosen mode.
     * @param pm
     * @param sm
     * @param rm
     * @param sensedDelay
     */
    public void setModes(PacingModes pm, SensingModes sm, ResponseModes rm, String sensedDelay) {
    	paceMode = pm;
    	senseMode = sm;
    	respondMode = rm;  
    	iSensedDelay = Integer.parseInt(sensedDelay);
    }
       
    /**
     * Method that checks that the chosen operating mode
     * is a valid for the chosen State. Before allowing the pacemaker
     * to pace the Heart.
     * @param state
     */
    public void checkStates(String state){
    	
    	String mode = paceMode.getMode()+""+senseMode.getMode()+""+respondMode.getMode();
    	if(state.equals("Permanent State")) {
    		if(mode.equals("333") || mode.equals("223") || mode.equals("311") || mode.equals("221") || mode.equals("411") || mode.equals("443")) { 
    			correctStateMode=true;
    		}
    	}else if(state.equals("Temporary State")) {
    		if( mode.equals("141") || mode.equals("121") || mode.equals("131") || mode.equals("111") ) {
    			correctStateMode=true;
    		}
    	}else if(state.equals("Pace-Now State")) {
    		correctStateMode =true;
    	}else if(state.equals("Power-On-Reset State")) {
    		correctStateMode = true;
    	}else{
    		correctStateMode=false;
    	}
    	
    }
    
    /**
     * The method continuosDraw accepts the the current count of the points, 2 booleans to set if sensing
     * or pacing is displayed and the points to be drawn on the graph. The last draw points
     * deleted and the next points to be drawn are selected from the main array list of points.
     * The first switch checks which enum is selected for the sensing mode. An if statement
     * in every case of the switch checks the current drawn point to detect which part of the grpah is 
     * being draw. With this information the applications, will sense that the atrium is being drawn, so
     * it will call the senseAtrial method. Same goes for the ventrical sensing or dual sensing.
     * A similar switch takes care of the pacing but instead of sensing the current drawn point, a boolean 
     * is set to skip the current drawn point and the next pacing points are drawn instead. This is
     * only done if the required criteria are met, such as sensing delay or correct operating
     * mode selected. The method then returns the list of current drawn points to be saved by the
     * recording mode selected.
     * 
     * @param c Point number
     * @param showSensing boolean to set if sensing is displayed
     * @param showPacing boolean to set if pacing is displayed
     * @param p Array points to draw the graph
     * @return ArrayPoint for writing to memory
     */
    public ArrayList<Point> continousDraw(int c, boolean showSensing, boolean showPacing, ArrayList<Point> p) {
        //Checks if i passed two points before doing anything, it draws the image
        //the image in this case is the rectangle
    	ArrayList<Point> points = new ArrayList<Point>();
        skipPoint = false;
    	if (p != null) panelList = p;
        if(panelList.size() > c + 1) {
            graph.drawImage(bufferedImage,0,0, getWidth()-spaceBetweenpoints,getHeight(),spaceBetweenpoints,0,getWidth(),getHeight(),null);
            deletePoints(getWidth()-spaceBetweenpoints,spaceBetweenpoints);
            
            Point point1 = panelList.get(c);
            Point point2 = panelList.get(c + 1);
            if(correctStateMode){
            if (showSensing) {
            	//Sensing Switch
            	switch (senseMode) {
            	case NONE:
        			// do nothing;
            		break;
            	case ATRIUM:
            		System.out.println(point1.y);
			        if (point1.y == 40 && point2.y == 30)
			        {
			        	senseAtrial();
			        }
            		break;
            	case VENTRICAL:
			        if (point1.y == 60 && point2.y == -40)
			        {
			        	senseVentrical();
			        }
            		break;
            	case DUAL:
			        if (point1.y == 40 && point2.y == 30)
			        {
			        	senseAtrial();
			        }
			        
			        if (point1.y == 60 && point2.y == -40)
			        {
			        	senseVentrical();
			        }
            		break;
            	}
            }

        	if (showPacing) {
        		switch(paceMode) {
        		case ATRIUM:
        			if (respondMode == ResponseModes.INHIBITED) {
            			if (atriumSensed != 0 && System.currentTimeMillis() - atriumSensed > iSensedDelay)
            			{
            				if (atriumPaced + 1 < RatePoints.getAtrialRates().length) {
            					points = paceAtrial();
            					skipPoint = true;
            				} else {
            					atriumSensed = System.currentTimeMillis();
            					atriumPaced = 0;
            				}
            			}
        			}
        			break;
        		case VENTRICAL:
        			if (respondMode == ResponseModes.INHIBITED) {
            			if (ventricalSensed != 0 && System.currentTimeMillis() - ventricalSensed > iSensedDelay)
            			{
            				if (ventricalPaced + 1 < RatePoints.getVentricalRates().length) {
            					points = paceVentrical();
            					skipPoint = true;
            				} else {
            					ventricalSensed = System.currentTimeMillis();
            					ventricalPaced = 0;
            				}
            			}
        			}
        			break;
        		case DUAL:
        			if (respondMode == ResponseModes.INHIBITED) {
        				if (atriumSensed != 0 && System.currentTimeMillis() - atriumSensed > iSensedDelay &&
        						ventricalSensed != 0 && System.currentTimeMillis() - ventricalSensed > iSensedDelay)
            			{
            				if (atriumPaced + 1 < RatePoints.getAtrialRates().length) {
            					points = paceAtrial();
            					skipPoint = true;
            				} else {
            					atriumSensed = System.currentTimeMillis();
            					atriumPaced = 0;
            				}
            			}
        			}
        			break;
        		case NONE:
        			break;
        		}
        	}
            }
            if (!skipPoint) {
            	points.add(point1);
            	points.add(point2);
            	
	            graph.setColor(Color.BLACK);
	            graph.drawLine(getWidth()-spaceBetweenpoints-1,point1.y+getHeight()/2,getWidth()-1,point2.y+getHeight()/2);
            }
        }
        
        return points;
    }
    
    public void senseVentrical()
    {
    	graph.setColor(Color.RED);
        graph.drawLine(getWidth()-15,getHeight()+1,getWidth()-15,0);
        ventricalSensed = System.currentTimeMillis();
    }
    
    public void senseAtrial()
    {
    	graph.setColor(Color.RED);
        graph.drawLine(getWidth()-15,getHeight()+1,getWidth()-15,0);
        atriumSensed = System.currentTimeMillis();
    }
    
    public ArrayList<Point> paceAtrial() {
    	ArrayList<Point> points = new ArrayList<Point>();
    	
    	graph.setColor(Color.BLUE);
        //graph.drawLine(getWidth()-15,getHeight()+1,getWidth()-15,0);
        int[] rates = RatePoints.getAtrialRates();
        
        int p = rates[atriumPaced];
        Point point1 = new Point(0,50-p);
        points.add(point1);

        int p2 = rates[atriumPaced + 1];
        Point point2 = new Point(0,50-p2);
        points.add(point2);
        
        atriumPaced++;
        
        graph.drawLine(getWidth()-spaceBetweenpoints-1,point1.y+getHeight()/2,getWidth()-1,point2.y+getHeight()/2);
        skipPoint = true;
        
        return points;
    }
    
    public ArrayList<Point> paceVentrical() {
    	ArrayList<Point> points = new ArrayList<Point>();
    	
    	graph.setColor(Color.BLUE);
        //graph.drawLine(getWidth()-15,getHeight()+1,getWidth()-15,0);
        int[] rates = RatePoints.getVentricalRates();
        
        int p = rates[ventricalPaced];
        Point point1 = new Point(0,50-p);
        points.add(point1);

        int p2 = rates[ventricalPaced + 1];
        Point point2 = new Point(0,50-p2);
        points.add(point2);
        
        ventricalPaced++;
        
        graph.drawLine(getWidth()-spaceBetweenpoints-1,point1.y+getHeight()/2,getWidth()-1,point2.y+getHeight()/2);
        skipPoint = true;
        
        return points;
    }

    /**
     * Method to just draw
     */
    public void paint(Graphics g) {
        g.drawImage(bufferedImage,0,0,null);
    }

}



