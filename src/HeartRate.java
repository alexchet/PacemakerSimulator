import java.awt.Point;
import java.util.ArrayList;

public class HeartRate extends Thread {
	ArrayList points;
    ArrayList<Point> ratesList = new ArrayList<Point>();
	
    public HeartRate(ArrayList p){
        points=p;
        this.start();
    }
    
    public void run()
    {
	    while (true)
	    {
	        while (ratesList.size() < 500)
	        {
	            for(int i=0; i<rates.length; i++){
	                int p = rates[i];
	                points.add(new Point(0,50-p));
	            }
	        }
	    }
	}
    
    public ArrayList getRatesList(){
    	return this.ratesList;
    }
}