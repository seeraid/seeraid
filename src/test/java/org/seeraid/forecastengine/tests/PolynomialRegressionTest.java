package org.seeraid.forecastengine.tests;

import org.seeraid.forecastengine.DataPoint;
import org.seeraid.forecastengine.DataSet;
import org.seeraid.forecastengine.ForecastingModel;
import org.seeraid.forecastengine.Observation;
import org.seeraid.forecastengine.models.PolynomialRegressionModel;


/**
 * Tests the methods used by the Polynomial Regression model. This test
 * case needs to be part of the models package to enable it to access
 * the package-level Utils.GaussElimination method for testing.
 */
public class PolynomialRegressionTest extends ForecastEngineBaseTestCase
{
    public void testPolynomialRegression()
    {
        DataSet observedData = new DataSet();
        
        for ( int x=0; x<10; x++ )
            {
                double y = 10.6 + 3.5*x + 0.5*Math.pow(x,3);
                
                DataPoint dp = new Observation( y );
                dp.setIndependentValue( "x1", x );
                
                // Fill x2 with random data - it should be ignored
                dp.setIndependentValue( "x2", (Math.random()-0.5)*100 );
                observedData.add( dp );
            }
        
        ForecastingModel model = new PolynomialRegressionModel( "x1", 5 );
        model.init( observedData );
        
        DataSet fcValues = new DataSet();
        double expectedResult[] = new double[10];
        for ( int x=10; x<20; x++ )
            {
                double y = 10.6 + 3.5*x + 0.5*Math.pow(x,3);
                
                DataPoint dp = new Observation( 0.0 );
                dp.setIndependentValue( "x1", x );
                
                // Fill x2 with random data - it should be ignored
                dp.setIndependentValue( "x2", (Math.random()-0.5)*100 );
                fcValues.add( dp );
                
                // Save expected value
                expectedResult[x-10] = y;
            }
        
        DataSet results = model.forecast( fcValues );
        
        checkResults( results, expectedResult );
    }
    
    public PolynomialRegressionTest( String name )
    {
        super(name);
    }
}
