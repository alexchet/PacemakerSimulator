import java.awt.Point;
import java.util.ArrayList;

public class HeartRate extends Thread {
    ArrayList<Point> ratesList = new ArrayList<Point>();
    int[] rates;
	
    public HeartRate() { }

    public void startHeart(int[] _rates){
        rates = _rates;
        this.start();
    }
    
    public void run()
    {
        while (ratesList.size() < 1000)
        {
            for(int i=0; i<rates.length; i++){
                int p = rates[i];
                ratesList.add(new Point(0,50-p));
            }
        }
	}
    
    public ArrayList<Point> getRatesList(){
    	return this.ratesList;
    }
}