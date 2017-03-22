
public class RatePoints {
	private static int normalRates[] = {10,10,20,23,20,10,10,-10,90,-20,10,10,26,30,26,10,10,10,10};
	private static int bradyRates [] = {10,10,20,23,20,10,10,-10,90,-20,10,10,26,30,26,10,10,10,10,
			10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10};
	private static int bradySecAVRates [] = {10,10,20,23,20,10,10,-10,90,-20,10,10,26,30,26,10,10,10,10,
			10,20,23,20,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,
			20,23,20,10,10,10,10,10,10,10,10,10,10,10,10,10};
	private static int bradySinusRates [] = {10,10,20,23,20,10,10,-10,90,-20,10,10,26,30,26,10,10,10,
			10,10,20,23,20,10,10,-10,90,-20,10,10,26,30,26,10,10,10,10,10,20,23,20,10,10,-10,90,-20,10,
			10,26,30,26,10,10,10,10,10,20,23,20,10,10,-10,90,-20,10,10,26,30,26,10,10,10,10,10,10,10,10,
			10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10};	
	private static int tachycardicRates[] =  {10,10,20,23,20,10,10,-10,90,-20,10,10,26,30,26,10,10};
	private static int runningActivityRates[] = {10,10,20,23,20,10,10,-10,90,-20,10,10,26,30,26,10,10};
	

	private static int atrialRates[] = {10,10,20,23,20,10,10,-10,90,-20,10,10,26,30,26,10};
	private static int ventricalRates[] = {10,10,20,23,20,10,10,-10,90,-20,10,10,26,30,26,10};
	private static int dualRates[] = {10,10,20,23,20,10,10,-10,90,-20,10,10,26,30,26,10};
	
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
		return bradyRates;
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
		return bradySinusRates;
	}
	/**
	 * @return the atrialRates
	 */
	public static int[] getAtrialRates() {
		return atrialRates;
	}
	/**
	 * @return the ventricalRates
	 */
	public static int[] getVentricalRates() {
		return ventricalRates;
	}

	/**
	 * @return the dualRates
	 */
	public static int[] getDualRates() {
		return dualRates;
	}
}

