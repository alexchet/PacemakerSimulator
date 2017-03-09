import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.JPanel;

public class Panel extends JPanel{
	Graphics2D graph;
    BufferedImage bufferedImage;
    int spaceBetweenpoints = 20;
	ArrayList<Point> panelList;
	
	public Panel(ArrayList<Point> list) {
		panelList = list;
		setBounds(10,10,600,300);
        bufferedImage = new BufferedImage(getWidth(),getHeight(),BufferedImage.TYPE_INT_ARGB);
        graph = bufferedImage.createGraphics();
        graph.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        graph.setColor(Color.white);
        graph.fillRect(0,0,900,300);       	
	}
	
	public void deletePoints(int x,int width) {
        graph.setColor(Color.white);
        graph.fillRect(x,0,width,getHeight());
    }
    
    public void resetGraph(){
        graph.setColor(Color.white);
        graph.fillRect(0,0,900,300);
    }
    
    public void continousDraw(int c) {
        //Checks if i passed two points before doing anything, it draws the image
        //the image in this case is the rectangle
        if(panelList.size()>=2) {
            graph.drawImage(bufferedImage,0,0, getWidth()-spaceBetweenpoints,getHeight(),spaceBetweenpoints,0,getWidth(),getHeight(),null);
            deletePoints(getWidth()-spaceBetweenpoints,spaceBetweenpoints);
            graph.setColor(Color.BLACK);
            Point point1;
            Point point2;
            point1 = panelList.get(c);
            point2 = panelList.get(c + 1);
            graph.drawLine(getWidth()-spaceBetweenpoints-1,point1.y+getHeight()/2,getWidth()-1,point2.y+getHeight()/2);
        }
    }

    public void paint(Graphics g) {
        g.drawImage(bufferedImage,0,0,null);
    }

}



