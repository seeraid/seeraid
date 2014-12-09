package org.seeraid.forecastengine.tests;


import java.util.Iterator;

import org.seeraid.forecastengine.DataPoint;
import org.seeraid.forecastengine.DataSet;

import junit.framework.TestCase;

public class ForecastEngineBaseTestCase extends TestCase
{
    /**
     * The amount of error in the forecast values where the forecast is
     * considered "correct" by the test.
     */
    private static double TOLERANCE = 0.00001;
    
    /**
     * Constructs a new test case using the given name. Required by the
     * JUnit framework.
     * @param name the name of this test case. 
     */
    public ForecastEngineBaseTestCase( String name )
    {
        super(name);
    }
    
    /**
     * A helper function that validates the actual results obtaining for
     * a DataSet match the expected results.
     * @param actualResults the DataSet returned from the forecast method
     *        that contains the data points for which forecasts were done.
     * @param expectedResults an array of expected values for the forecast
     *        data points. The order should match the order of the results
     *        as defined by the DataSet iterator.
     */
    protected void checkResults( DataSet actualResults,
                                 double[] expectedResults )
    {
        checkResults( actualResults, expectedResults, TOLERANCE );
    }
    
    /**
     * A helper function that validates the actual results obtaining for
     * a DataSet match the expected results. This is the same as the other
     * checkResults method except that with this method, the caller can
     * specify an acceptance tolerance when comparing actual with expected
     * results.
     * @param actualResults the DataSet returned from the forecast method
     *        that contains the data points for which forecasts were done.
     * @param expectedResults an array of expected values for the forecast
     *        data points. The order should match the order of the results
     *        as defined by the DataSet iterator.
     * @param tolerance the tolerance to accept when comparing the actual
     *        results (obtained from a forecasting model) with the expected
     *        results.
     */
    protected void checkResults( DataSet actualResults,
                                 double[] expectedResults,
                                 double tolerance )
    {
        // This is just to safeguard against a bug in the test case!  :-)
        assertNotNull( "Checking expected results is not null",
                       expectedResults );
        assertTrue( "Checking there are some expected results",
                    expectedResults.length > 0 );
        
        assertEquals( "Checking the correct number of results returned",
                      expectedResults.length, actualResults.size() );
        
        // Iterate through the results, checking each one in turn
        Iterator<DataPoint> it = actualResults.iterator();
        int i=0;
        while ( it.hasNext() )
            {
                // Check that the results are within specified tolerance
                //  of the expected values
                DataPoint fc = (DataPoint)it.next();
                double fcValue = fc.getDependentValue();
                
                assertEquals( "Checking result",
                              expectedResults[i], fcValue, tolerance );
                i++;
            }
    }
}
