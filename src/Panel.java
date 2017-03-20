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
public class Panel extends JPanel{
	Graphics2D graph;
    BufferedImage bufferedImage;
    int spaceBetweenpoints = 10;
	ArrayList<Point> panelList;
	int width = 820;
	int x1 = 5;
	int y1 = 5;
	Border blackline = BorderFactory.createLineBorder(Color.black);

	long atriumSensed, ventricalSensed;
	int atriumPaced = 0, ventricalPaced = 0, singleChambersPaced = 0;
	boolean batriumPaced, bventricalPaced;
	boolean skipPoint;
	
	PacingModes paceMode;
	SensingModes senseMode; 
	ResponseModes respondMode;
	int iSensedDelay = 1200;
	
	boolean correctStateMode=false;
	
	
	
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
    
    public void setModes(PacingModes pm, SensingModes sm, ResponseModes rm, String sensedDelay) {
    	paceMode = pm;
    	senseMode = sm;
    	respondMode = rm;  
    	iSensedDelay = Integer.parseInt(sensedDelay);
    }
       
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

            if(correctStateMode){
            if (showSensing) {
            	//Sensing Switch
            	switch (senseMode) {
            	case NONE:
        			// do nothing;
            		break;
            	case ATRIUM:
			        if (point1.y == 40 && point2.y == 10)
			        {
			        	senseAtrial();
			        }
            		break;
            	case VENTRICAL:
			        if (point1.y == 40 && point2.y == -40)
			        {
			        	senseVentrical();
			        }
            		break;
            	case DUAL:
			        if (point1.y == 40 && point2.y == 10)
			        {
			        	senseAtrial();
			        }
			        
			        if (point1.y == 40 && point2.y == -40)
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
            				paceSingleChambers(true, false);
            			}
        			}
        			break;
        		case VENTRICAL:
        			if (respondMode == ResponseModes.INHIBITED) {
            			if (ventricalSensed != 0 && System.currentTimeMillis() - ventricalSensed > iSensedDelay)
            			{
            				paceSingleChambers(false, true);
            			}
        			}
        			break;
        		case DUAL:
        			if (respondMode == ResponseModes.INHIBITED) {
            			if (atriumSensed != 0 && System.currentTimeMillis() - atriumSensed > iSensedDelay)
            			{
            				paceAtrial(Color.BLUE, true, false);
            			}
            			if (ventricalSensed != 0 && System.currentTimeMillis() - ventricalSensed > iSensedDelay)
            			{
            				paceVentrical(Color.BLUE, true, false);
            			}
        			}
        			break;
        		case NONE:
        			break;
        		}
        	}
            }
            if (!skipPoint) {
	            graph.setColor(Color.BLACK);
	            graph.drawLine(getWidth()-spaceBetweenpoints-1,point1.y+getHeight()/2,getWidth()-1,point2.y+getHeight()/2);
            }
        }
        
        return skipPoint;
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
    
    public void paceSingleChambers(boolean atrium, boolean ventrical)
    {
		if (singleChambersPaced < 2) {
			if (atrium) {
				paceAtrial(Color.BLUE, true, true);
			}
			if (ventrical) {
				paceAtrial(Color.BLACK, false, true);
			}
			singleChambersPaced++;
		} else if (singleChambersPaced >= 2) {
			if (atrium) {
				paceVentrical(Color.BLACK, false, true);
			}
			if (ventrical) {
				paceVentrical(Color.BLUE, true, true);
			}
			singleChambersPaced++;
			if (singleChambersPaced == 4) {
				singleChambersPaced = 0;
			}
		}
    }
    
    public void paceVentrical(Color lineColor, boolean showPacingLine, boolean singleChamber)
    {
    	if	(ventricalPaced == 0) {
        	graph.setColor(lineColor);
        	if (showPacingLine) {
        		graph.drawLine(getWidth()-15,getHeight()+1,getWidth()-15,0);
        	}
			graph.drawLine(getWidth()-spaceBetweenpoints-1,135,getWidth()-1,55);
			ventricalPaced++;
			skipPoint = true;
		} else if (ventricalPaced == 1) {
        	graph.setColor(lineColor);
			graph.drawLine(getWidth()-spaceBetweenpoints-1,55,getWidth()-1,135);
			ventricalPaced = 0;
			ventricalSensed = 0;
			if (singleChamber) {
				atriumSensed = 0;
			}
			skipPoint = true;
		}
    }
    
    public void paceAtrial(Color lineColor, boolean showPacingLine, boolean singleChamber)
    {
    	if	(atriumPaced == 0) {
        	graph.setColor(lineColor);
        	if (showPacingLine) {
        		graph.drawLine(getWidth()-15,getHeight()+1,getWidth()-15,0);
        	}
			graph.drawLine(getWidth()-spaceBetweenpoints-1,40+getHeight()/2,getWidth()-1,10+getHeight()/2);
            atriumPaced++;
			skipPoint = true;
		} else if (atriumPaced == 1) {
        	graph.setColor(lineColor);
			graph.drawLine(getWidth()-spaceBetweenpoints-1,10+getHeight()/2,getWidth()-1,40+getHeight()/2);
			atriumPaced = 0;
			if (!singleChamber) {
				atriumSensed = 0;
			}
			skipPoint = true;
		}	
    }

    public void paint(Graphics g) {
        g.drawImage(bufferedImage,0,0,null);
    }

}



