package org.seeraid.forecastengine.tests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
	org.seeraid.forecastengine.tests.BasicTest.class,
	org.seeraid.forecastengine.tests.DataSetTest.class,
	org.seeraid.forecastengine.tests.SimpleExponentialSmoothingTest.class,
	org.seeraid.forecastengine.tests.DoubleExponentialSmoothingTest.class,
	org.seeraid.forecastengine.tests.TripleExponentialSmoothingTest.class,
	org.seeraid.forecastengine.tests.MovingAverageTest.class,
	org.seeraid.forecastengine.tests.MultipleLinearRegressionTest.class,
	org.seeraid.forecastengine.tests.PolynomialRegressionTest.class,
	org.seeraid.forecastengine.tests.WeightedMovingAverageTest.class,
	org.seeraid.forecastengine.tests.CSVBuilderTest.class,
	org.seeraid.forecastengine.tests.TimeSeriesBuilderTest.class,
	org.seeraid.forecastengine.tests.DelimitedTextOutputterTest.class,
	org.seeraid.forecastengine.tests.TimeSeriesOutputterTest.class
	})
public class ForecastEngineTestSuite
{
    private ForecastEngineTestSuite()
    {
    }
    
}
