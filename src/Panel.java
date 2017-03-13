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

public class Panel extends JPanel{
	Graphics2D graph;
    BufferedImage bufferedImage;
    int spaceBetweenpoints = 20;
	ArrayList<Point> panelList;
	int width = 820;
	int x1 = 5;
	int y1 = 5;
	Border blackline = BorderFactory.createLineBorder(Color.black);

	long atriumSensed, ventricalSensed, atriumPaced = 0, ventricalPaced = 0;
	boolean batriumPaced, bventricalPaced;
	boolean skipPoint;
	
	
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
	
	public void deletePoints(int x,int width) {
        graph.setColor(Color.white);
        graph.fillRect(x,0,width,getHeight());
    }
    
    public void resetGraph(){
        graph.setColor(Color.white);
        graph.fillRect(0,0,width,190);
    }
    
    public boolean continousDraw(int c, boolean showSensing, boolean showPacing, ArrayList<Point> p) {
        //Checks if i passed two points before doing anything, it draws the image
        //the image in this case is the rectangle
        skipPoint = false;
    	if (p != null) panelList = p;
        if(panelList.size() > c + 1) {
            graph.drawImage(bufferedImage,0,0, getWidth()-spaceBetweenpoints,getHeight(),spaceBetweenpoints,0,getWidth(),getHeight(),null);
            deletePoints(getWidth()-spaceBetweenpoints,spaceBetweenpoints);
            
            Point point1;
            Point point2;
            point1 = panelList.get(c);
            point2 = panelList.get(c + 1);
        	PacingModes pm = PacingModes.ATRIUM;
        	SensingModes sm = SensingModes.ATRIUM;
        	ResponseModes rm = ResponseModes.INHIBITED;
        	
        	if (showPacing) {
        		switch(pm) {
        		case ATRIUM:
        			if (rm == ResponseModes.INHIBITED) {
            			if (System.currentTimeMillis() - atriumSensed > 1000)
    			        	graph.setColor(Color.BLUE);
            				if	(atriumPaced == 0 && !batriumPaced) {
        			            graph.drawLine(getWidth()-15,point1.y+getHeight()+1,getWidth()-15,0);
            					graph.drawLine(getWidth()-spaceBetweenpoints-1,40+getHeight()/2,getWidth()-1,10+getHeight()/2);
        			            atriumPaced = System.currentTimeMillis();
        			            skipPoint = true;
            				} else if (atriumPaced != 0 && !batriumPaced) {
            					graph.drawLine(getWidth()-spaceBetweenpoints-1,10+getHeight()/2,getWidth()-1,40+getHeight()/2);
            					atriumPaced = 0;
            					batriumPaced = true;
        			            skipPoint = true;
            				} else {
            					batriumPaced = false;
            				}
            					
            			}
        			break;
        		}
        	}
            
            if (showSensing) {
            	//Sensing Switch
            	switch (sm) {
            	case NONE:
        			// do nothing;
            		break;
            	case ATRIUM:
			        if (point1.y == 40 && point2.y == 10)
			        {
			        	graph.setColor(Color.RED);
			            graph.drawLine(getWidth()-15,point1.y+getHeight()+1,getWidth()-15,0);
				        atriumSensed = System.currentTimeMillis();
				        atriumPaced = 0;
			        }
            		break;
            	case VENTRICAL:
			        if (point1.y == 40 && point2.y == -40)
			        {
			        	graph.setColor(Color.RED);
			            graph.drawLine(getWidth()-15,point1.y+getHeight()+1,getWidth()-15,0);
				        ventricalSensed = System.currentTimeMillis();
				        ventricalPaced = 0;
			            
			        }
            		break;
            	case DUAL:
			        if (point1.y == 40 && point2.y == 10)
			        {
			        	graph.setColor(Color.RED);
			            graph.drawLine(getWidth()-15,point1.y+getHeight()+1,getWidth()-15,0);
				        atriumSensed = System.currentTimeMillis();
				        atriumPaced = 0;
			        }
			        
			        if (point1.y == 40 && point2.y == -40)
			        {
			        	graph.setColor(Color.RED);
			            graph.drawLine(getWidth()-15,point1.y+getHeight()+1,getWidth()-15,0);
				        ventricalSensed = System.currentTimeMillis();
				        ventricalPaced = 0;
			            
			        }
            		break;
            	}
            }
            
            if ((!batriumPaced && atriumPaced == 0) && !bventricalPaced) {
	            graph.setColor(Color.BLACK);
	            graph.drawLine(getWidth()-spaceBetweenpoints-1,point1.y+getHeight()/2,getWidth()-1,point2.y+getHeight()/2);
            }
        }
        
        return skipPoint;
    }

    public void paint(Graphics g) {
        g.drawImage(bufferedImage,0,0,null);
    }

}



