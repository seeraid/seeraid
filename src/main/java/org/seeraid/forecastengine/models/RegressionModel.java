package org.seeraid.forecastengine.models;


import java.util.Iterator;

import org.seeraid.forecastengine.DataPoint;
import org.seeraid.forecastengine.DataSet;

public class RegressionModel extends AbstractForecastingModel
{
    /**
     * The name of the independent variable used in this regression model.
     */
    private String independentVariable;
    
    /**
     * The intercept for the linear regression model. Initialized following a
     * call to init.
     */
    private double intercept = 0.0;
    
    /**
     * The slope for the linear regression model. Initialized following a call
     * to init.
     */
    private double slope = 0.0;
    
    /**
     * Constructs a new linear regression model, using the given name as the
     * independent variable. For a valid model to be constructed, you should
     * call init and pass in a data set containing a series of data points
     * involving the given independent variable.
     * @param independentVariable the name of the independent variable to use
     * in this model.
     */
    public RegressionModel( String independentVariable )
    {
        this.independentVariable = independentVariable;
    }
    
    /**
     * Initializes the coefficients to use for this regression model. The
     * intercept and slope are derived so as to give the best fit line for the
     * given data set.
     *
     * <p>Additionally, the accuracy indicators are calculated based on this
     * data set.
     * @param dataSet the set of observations to use to derive the regression
     * coefficients for this model.
     */
    public void init( DataSet dataSet )
    {
        int n = dataSet.size();
        double sumX = 0.0;
        double sumY = 0.0;
        double sumXX = 0.0;
        double sumXY = 0.0;
        
        Iterator<DataPoint> it = dataSet.iterator();
        while ( it.hasNext() )
            {
                DataPoint dp = it.next();
                
                double x = dp.getIndependentValue( independentVariable );
                double y = dp.getDependentValue();
                
                sumX += x;
                sumY += y;
                sumXX += x*x;
                sumXY += x*y;
            }
        
        double xMean = sumX / n;
        double yMean = sumY / n;
        
        slope = (n*sumXY - sumX*sumY) / (n*sumXX - sumX*sumX);
        intercept = yMean - slope*xMean;
        
        // Calculate the accuracy of this model
        calculateAccuracyIndicators( dataSet );
    }
    
    /**
     * Using the current model parameters (initialized in init), apply the
     * forecast model to the given data point. The data point must have valid
     * values for the independent variables. Upon return, the value of the
     * dependent variable will be updated with the forecast value computed for
     * that data point.
     * @param dataPoint the data point for which a forecast value (for the
     *        dependent variable) is required.
     * @return the same data point passed in but with the dependent value
     *         updated to contain the new forecast value.
     * @throws ModelNotInitializedException if forecast is called before the
     *         model has been initialized with a call to init.
     */
    public double forecast( DataPoint dataPoint )
        throws ModelNotInitializedException
    {
        if ( !initialized )
            throw new ModelNotInitializedException();
        
        double x = dataPoint.getIndependentValue( independentVariable );
        double forecastValue = intercept + slope*x;
        
        dataPoint.setDependentValue( forecastValue );
        
        return forecastValue;
    }
    
    /**
     * Returns a short name for this type of forecasting model. A more detailed
     * explanation is provided by the toString method.
     * @return a short string describing this type of forecasting model.
     */
    public String getForecastType()
    {
        return "Single variable regression";
    }
    
    /**
     * Returns the number of predictors used by the underlying model.
     * @return the number of predictors used by the underlying model.
     * @since 0.5
     */
    public int getNumberOfPredictors()
    {
        return 1;
    }
    
    /**
     * Returns a detailed description of this forcasting model, including the
     * intercept and slope. A shortened version of this is provided by the
     * getForecastType method.
     * @return a description of this forecasting model.
     */
    public String toString()
    {
        return "Single variable regression model with a slope of " + slope
            + " and an intercept of " + intercept
            + ". That is, y=" + intercept + (slope>0.0 ? "+" : "") + slope
            + "*" + independentVariable + ".";
    }
}
