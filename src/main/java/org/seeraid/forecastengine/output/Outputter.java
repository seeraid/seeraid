package org.seeraid.forecastengine.output;

import org.seeraid.forecastengine.DataSet;

public interface Outputter
{
    /**
     * Outputs the DataSet - a collection of DataPoints - to the current
     * output destination. The DataSet should contain all DataPoints to be
     * output.
     * @param dataSet the DataSet to be output to the current output
     * destination.
     * @throws Exception if an error occurred writing/outputting the DataSet
     * to the output destination.
     */
    public void output( DataSet dataSet )
        throws Exception;
}
