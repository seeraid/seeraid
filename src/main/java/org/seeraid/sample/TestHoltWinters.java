package org.seeraid.sample;

import org.seeraid.forecastengine.models.HoltWintersModel;
import org.seeraid.forecastengine.models.TripleExponentialSmoothingModel;

public class TestHoltWinters {
	public static void main(String[] args) {
		forecastNISTData();
	}
	public static void forecastNISTData() {
		/**
		 * NIST data available at:
		 * http://www.itl.nist.gov/div898/handbook/pmc/section4/pmc436.htm
		 */
		long[] y = { 2585,3368,3210,3111,3756,4216,5225,4426,3932,3816,3661,3795,
				2285,2934,2985,3646,4198,4935,5618,5454,3624,2898,3802,2369,2369,2511};
//		long[] y = { 362, 385, 432, 341, 382, 409, 498, 387, 473, 513, 582,
//				474, 544, 582, 681, 557, 628, 707, 773, 592, 627, 725, 854, 661 };
		int period = 4;
		int m = 4;
		double alpha = 0.5;
		double beta = 0.4;
		double gamma = 0.6;
//		System.out.print(alpha+" "+beta+" "+gamma + " == ");
//		double[] prediction = HoltWinters.forecast(y, alpha, beta, gamma,
//				period, m,false);

//		for(double d:prediction) System.out.println(d+", ");
//		System.out.println(prediction.length);
		for(double a=0;a<10;a++) {
			for(double b=0;b<10;b++) {
				for(double g=0;g<10;g++) {
					alpha=a/10;
					beta=b/10;
					gamma=g/10;
					System.out.print(alpha+" "+beta+" "+gamma + " == ");
					double[] prediction = HoltWinters.forecast(y, alpha, beta, gamma,
							period, m);
					for(double d:prediction) System.out.print(d+", ");
					System.out.println(prediction.length);
				}
			}
		}
		// These are the expected results
//		double[] expected = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,
//				594.8043646513713, 357.12171044215734, 410.9203094983815,
//				444.67743912921156, 550.9296957593741, 421.1681718160631,
//				565.905732450577, 639.2910221068818, 688.8541669002238,
//				532.7122406111591, 620.5492369959037, 668.5662327429854,
//				773.5946568453546, 629.0602103529998, 717.0290609530134,
//				836.4643466657625, 884.1797655866865, 617.6686414831381,
//				599.1184450128665, 733.227872348479, 949.0708357438998,
//				748.6618488792186 };
//		Assert.assertArrayEquals("Forecast does not match", expected,
//				prediction, 0.0000000000001);
	}
}