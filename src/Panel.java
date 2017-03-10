import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.BorderFactory;
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
	
	public Panel(ArrayList<Point> list) {
		panelList = list;
		setBounds(x1,y1,width,300);
        bufferedImage = new BufferedImage(getWidth(),getHeight(),BufferedImage.TYPE_INT_ARGB);
        graph = bufferedImage.createGraphics();
        graph.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        graph.setColor(Color.white);
        graph.fillRect(0,0,width,300);       
	}
	
	public void deletePoints(int x,int width) {
        graph.setColor(Color.white);
        graph.fillRect(x,0,width,getHeight());
    }
    
    public void resetGraph(){
        graph.setColor(Color.white);
        graph.fillRect(0,0,width,300);
    }
    
    public void continousDraw(int c, boolean showPointer) {
        //Checks if i passed two points before doing anything, it draws the image
        //the image in this case is the rectangle
        if(panelList.size() > c + 1) {
            graph.drawImage(bufferedImage,0,0, getWidth()-spaceBetweenpoints,getHeight(),spaceBetweenpoints,0,getWidth(),getHeight(),null);
            deletePoints(getWidth()-spaceBetweenpoints,spaceBetweenpoints);
            
            Point point1;
            Point point2;
            point1 = panelList.get(c);
            point2 = panelList.get(c + 1);
            
            graph.setColor(Color.BLACK);
            graph.drawLine(getWidth()-spaceBetweenpoints-1,point1.y+getHeight()/2,getWidth()-1,point2.y+getHeight()/2);

            if (showPointer) {
		        if (point1.y == 40 && point2.y == -40)
		        {
		        	graph.setColor(Color.BLUE);
		            graph.drawLine(getWidth()-spaceBetweenpoints+18,point1.y+getHeight()+1,getWidth()-1,0);
		        }	
		       
		        if (point1.y == 40 && point2.y == 10)
		        {
		        	graph.setColor(Color.RED);
		            graph.drawLine(getWidth()-spaceBetweenpoints+18,point1.y+getHeight()+1,getWidth()-1,0);
		        }	
        	}
        }
    }

    public void paint(Graphics g) {
        g.drawImage(bufferedImage,0,0,null);
    }

}



