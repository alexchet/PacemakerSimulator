import java.awt.Color;
	import java.awt.Graphics;
	import java.awt.Graphics2D;
	import java.awt.Point;
	import java.awt.RenderingHints;
import java.awt.geom.CubicCurve2D;
import java.awt.geom.Path2D;
import java.awt.geom.QuadCurve2D;
import java.awt.image.BufferedImage;
	import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
	
public class CurvePanel extends JFrame {
	
		Graphics2D graph;
		
	    BufferedImage bufferedImage;
	    int spaceBetweenpoints = 20;
		ArrayList<Point> panelList;
		 int d = 4;
		
		public CurvePanel(/*ArrayList<Point> list*/) {
			//panelList = list;
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

			setBounds(10,10,1000,300);
	        bufferedImage = new BufferedImage(getWidth(),getHeight(),BufferedImage.TYPE_INT_ARGB);
	        graph = bufferedImage.createGraphics();
	        graph.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
	        graph.setColor(Color.white);
	        graph.fillRect(0,0,1000,300);  
	        setVisible(true);
		}
		
		public void deletePoints(int x,int width) {
	        graph.setColor(Color.white);
	        graph.fillRect(x,0,width,getHeight());
	    }
	    
	    public void resetGraph(){
	        graph.setColor(Color.white);
	        graph.fillRect(0,0,1000,300);
	    }
	    
	    public void continousDraw() {
	    
	          graph.setColor(Color.BLUE);
	            int x0 = getWidth()/8;
	            int y0 = 250;
	            graph.drawString("P0", x0, y0 + 4*d);
	            graph.fillRect(x0, y0, d, d);

	            // P1
	            int x1 = (getWidth()/7)*2;
	            int y1 = 235;
	            graph.drawString("P1", x1, y1 + 4*d);
	            graph.fillRect(x1, y1, d, d);

	            // P2
	            int x2 = (getWidth()/2);
	            int y2 = 200;
	            graph.drawString("P2", x2, y2 - 2*d);
	            graph.fillRect(x2, y2, d, d);

	            // P3
	            int x3 = (getWidth()/7)*5;
	            int y3 = 235;
	            graph.drawString("P3", x3, y3 + 4*d);
	            graph.fillRect(x3, y3, d, d);

	                    // P4
	            int x4 = (getWidth()/8)*7;
	            int y4 = 250;
	            graph.drawString("P4", x4, y4 + 4*d);
	            graph.fillRect(x4, y4, d, d);

	            graph.setColor(Color.cyan);
	            QuadCurve2D quadCurve = new QuadCurve2D.Double(x0, y0, x2, y2, x4, y4);
	            graph.draw(quadCurve);


	            graph.setColor(Color.YELLOW);
	            CubicCurve2D.Double cubicCurve = new CubicCurve2D.Double((double)x0, (double)y0, 
	                                                                     (double)x1, (double)y1, 
	                                                                     (double)x2, (double)y2, 
	                                                                     (double)x4, (double)y4);
	            graph.draw(cubicCurve);


	            graph.setColor(Color.red);    
	            
	            int cx1a = x1 + (x2 - x1) / 3;
	           int cy1a = y1 + (y2 - y1) / 3;
	           int cx1b = x2 - (x3 - x1) / 3;
	           int cy1b = y2 - (y3 - y1) / 3;
	           int cx2a = x2 + (x3 - x1) / 3;
	            int cy2a = y2 + (y3 - y1) / 3;
	            int cx2b = x3 - (x3 - x2) / 3;
	            int cy2b = y3 - (y3 - y2) / 3; 
	            
	            Path2D.Double path1 = new Path2D.Double();
	            path1.moveTo(x1, y1);
	            path1.curveTo(cx1a, cy1a, cx1b, cy1b, x2, y2);
	            path1.curveTo(cx2a, cy2a, cx2b, cy2b, x3, y3);
	            graph.draw(path1);
	            //graph.draw(path1);
	        }
	    

	    public void paint(Graphics g) {
	    	// Graphics2D g2 = (Graphics2D) g;
	        g.drawImage(bufferedImage,0,0,null);
	    }
	    
	    public static void main(String[] args) {
	    	CurvePanel c = new CurvePanel();
	    	c.continousDraw();
		}


	}
