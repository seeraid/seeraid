package org.seeraid.forecastengine.models;

public class NaiveForecastingModel extends MovingAverageModel
{
    /**
     * Constructs a new naive forecasting model. For a valid model to be
     * constructed, you should call init and pass in a data set containing a
     * series of data points with the time variable initialized to identify
     * the independent variable.
     */
    public NaiveForecastingModel()
    {
        super( 1 );
    }
    
    /**
     * Constructs a new naive forecasting model, using the given name as the
     * independent variable.
     * @param independentVariable the name of the independent variable to use
     * in this model.
     * @deprecated As of 0.4, replaced by {@link #NaiveForecastingModel}.
     */
    public NaiveForecastingModel( String independentVariable )
    {
        super( independentVariable, 1 );
    }

    /**
     * Returns a one or two word name of this type of forecasting model. Keep
     * this short. A longer description should be implemented in the toString
     * method.
     * @return a string representation of the type of forecasting model
     *         implemented.
     */
    public String getForecastType()
    {
        return "Naive forecast";
    }
    
    /**
     * This should be overridden to provide a textual description of the
     * current forecasting model including, where possible, any derived
     * parameters used.
     * @return a string representation of the current forecast model, and its
     *         parameters.
     */
    public String toString()
    {
        return
            "Naive forecasting model (i.e. moving average with a period of 1)"
            + ", using an independent variable of " + getIndependentVariable()
            + ".";
    }
}
