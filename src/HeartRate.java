import java.awt.Point;
import java.util.ArrayList;

/**
 * In this class we are passing all the values, for the chosen 
 * heart rate array in the RatePoints class, for each passed value
 * we are constructing a 2-dimensional point from and storing it into the ratesList which is an
 * ArrayList of Points.
 * For each value we set the X axis value to 0 and the
 * Y axis to 50 minus the value we are passing.
 * @author Stella
 *
 */
public class HeartRate {
    ArrayList<Point> ratesList = null;
    int[] rates = null;
	
    public HeartRate() { }

    /**
     * Method to rest the ArrayList and rates arrays
     */
    public void reset() {
        this.ratesList = new ArrayList<Point>();
        this.rates = null;
    }
    
    /**
     * Method which we pass each value for the chosen array of the RatesPoints class
     * and constructs a 2dimension point for each value.
     * it accepts a total of 100000 values for points
     * @param _rates
     */
    public void startHeart(int[] _rates){
        rates = _rates;
        
        while (ratesList.size() < 10000)
        {
            for(int i=0; i<rates.length; i++){
                int p = rates[i];
                ratesList.add(new Point(0,50-p));
            }
        }
    }
    
    /**
     * Method which adds a new point into the ratesList array list
     * @param p
     */
    public void addHeartBeat(int p){
        ratesList.add(new Point(0,50-p));
    }
    
    /**
     * Returns the ratesList arraylist
     * @return ratesList
     */
    public ArrayList<Point> getRatesList(){
    	return this.ratesList;
    }
}