
public class RatePoints {
	private static int normalRates[] = {10,40,10,90,10,10,10,10};
	private static int brachycardicRates [] = {10,40,10,90,10,10,10,10,10,10};
	private static int tachycardicRates[] = {10,40,10,90,10,10};
	private static int runningActivityRates[] = {10,40,10,90,10,10};
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
		return brachycardicRates;
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
}
