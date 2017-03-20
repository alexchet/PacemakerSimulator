
public class RatePoints {
	private static int normalRates[] = {10,10,24,29,24,10,10,-10,90,-20,10,10,34,39,34,10,10,10,10};
	private static int bradySinusRates [] = {10,10,24,29,24,10,10,-10,90,-20,10,10,34,39,34,10,10,10,10,10,10,10};
	private static int bradySecAVRates [] = {10,10,24,29,24,10,10,-10,90,-20,10,10,34,39,34,10,10,10,10,10,24,29,24,10,10,10,10,10,24,29,24,10,10,10,10};
	private static int bradyArrestRates [] = {10,10,24,29,24,10,10,-10,90,-20,10,10,34,39,34,10,10,10,10,10,24,29,24,10,10,-10,90,-20,10,10,34,39,34,10,10,10,10,10,24,29,24,10,10,-10,90,-20,10,10,34,39,34,10,10,10,10,10,24,29,24,10,10,-10,90,-20,10,10,34,39,34,10,10,10,10,10,10,10,10,10,10};
	
	private static int tachycardicRates[] =  {10,10,24,29,24,10,10,-10,90,-20,10,10,34,39,34,10,10};
	private static int runningActivityRates[] = {10,10,24,29,24,10,10,-10,90,-20,10,10,34,39,34,10,10};
	/**
	 * @return the normalRates
	 */
	public static int[] getNormalRates() {
		return normalRates;
	}
	/**
	 * @return the brachycardicRates
	 */
	public static int[] getBrachycardicRates() {
		return bradySinusRates;
	}
	/**
	 * @return the tachycardicRates
	 */
	public static int[] getTachycardicRates() {
		return tachycardicRates;
	}
	/**
	 * @return the runningActivityRates
	 */
	public static int[] getRunningActivityRates() {
		return runningActivityRates;
	}
	
	public static int[] getBradySecAVRates() {
		return bradySecAVRates;
	}
	public static int[] getBradyArrestRates() {
		return bradyArrestRates;
	}
	
}

