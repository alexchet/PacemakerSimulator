import java.awt.Point;
import java.util.ArrayList;

public class HeartRate {
    ArrayList<Point> ratesList = null;
    int[] rates = null;
	
    public HeartRate() { }

    public void reset() {
        this.ratesList = new ArrayList<Point>();
        this.rates = null;
    }
    
    public void startHeart(int[] _rates){
        rates = _rates;
        
        while (ratesList.size() < 100)
        {
            for(int i=0; i<rates.length; i++){
                int p = rates[i];
                ratesList.add(new Point(0,50-p));
            }
        }
    }
    
    public void addHeartBeat(int p){
        ratesList.add(new Point(0,50-p));
    }
    
    public ArrayList<Point> getRatesList(){
    	return this.ratesList;
    }
}