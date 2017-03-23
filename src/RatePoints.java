
public class RatePoints {
	/**
	 * In this class we are passing into arrays, the values which are used by the continuousDraw method 
	 * in the Panel class to draw the ECG graph for each defined Heart Rate
	 */
	private static int normalRates[] = {10,10,24,29,24,10,10,-10,90,-20,10,10,34,39,34,10,10,10,10};
	private static int bradyRates [] = {10,10,24,29,24,10,10,-10,90,-20,10,10,34,39,34,10,10,10,10,
			10,10,10,10,10,10,10,10,10,10,10,10};
	private static int bradySecAVRates [] = {10,10,24,29,24,10,10,-10,90,-20,10,10,34,39,34,10,10,10,10,
			10,24,29,24,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,24,29,24,10,10,10,10,10,10,10,10,
			10,10,10,10,10};
	private static int bradySinusRates [] = {10,10,24,29,24,10,10,-10,90,-20,10,10,34,39,34,10,10,10,
			10,10,24,29,24,10,10,-10,90,-20,10,10,34,39,34,10,10,10,10,10,24,29,24,10,10,-10,90,-20,10,
			10,34,39,34,10,10,10,10,10,24,29,24,10,10,-10,90,-20,10,10,34,39,34,10,10,10,10,10,10,10,10,
			10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10};	
	private static int tachycardicRates[] =  {10,10,24,29,24,10,10,-10,90,-20,10,10,34,39,34,10,10};
	private static int runningActivityRates[] = {10,10,24,29,24,10,10,-10,90,-20,10,10,34,39,34,10,10};
	/**
	 * Method that returns all the Normal Heart Rate points
	 * @return the normalRates
	 */
	public static int[] getNormalRates() {
		return normalRates;
	}
	/**
	 * Method that returns all the Bradycardia Heart Rate points
	 * @return the brachycardicRates
	 */
	public static int[] getBrachycardicRates() {
		return bradyRates;
	}
	/**
	 * Method that returns all the Tachycardia Heart Rate points
	 * @return the tachycardicRates
	 */
	public static int[] getTachycardicRates() {
		return tachycardicRates;
	}
	/**
	 * Method that returns all the Activity Heart Rate points
	 * @return the runningActivityRates
	 */
	public static int[] getRunningActivityRates() {
		return runningActivityRates;
	}
	
	/**
	 * Method that returns all the Bradycardia Secondary AV Block Heart Rate points
	 * @return the bradySecAVRates
	 */
	public static int[] getBradySecAVRates() {
		return bradySecAVRates;
	}

	/**
	 * Method that returns all the Bradycardia Sinus Heart Rate points
	 * @return the bradySecAVRates
	 */
	public static int[] getBradyArrestRates() {
		return bradySinusRates;
	}
	
}

