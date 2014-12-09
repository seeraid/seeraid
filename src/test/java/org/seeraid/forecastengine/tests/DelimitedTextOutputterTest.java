package org.seeraid.forecastengine.tests;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.seeraid.forecastengine.DataPoint;
import org.seeraid.forecastengine.DataSet;
import org.seeraid.forecastengine.Observation;
import org.seeraid.forecastengine.input.CSVBuilder;
import org.seeraid.forecastengine.output.DelimitedTextOutputter;


/**
 * Defines test cases that are designed to test the DelimitedTextOutputter
 * class. For many of these tests we make the assumption that the CSVBuilder
 * class is also correct.
 */
public class DelimitedTextOutputterTest extends ForecastEngineBaseTestCase
{
    private DataSet expectedDataSet;
    
    
    public DelimitedTextOutputterTest( String name )
    {
        super(name);
    }
    
    /**
     * Creates a dummy data setto be written by all test cases.
     */
    public void setUp()
    {
        // Constants used to determine size of test
        int MAX_X1 = 10;
        int MAX_X2 = 10;
        
        // Set up array for expected results
        expectedDataSet = new DataSet();
        
        // Create test DataSet
        int numberOfDataPoints = 0;
        for ( int x1=0; x1<MAX_X1; x1++ )
            for ( int x2=0; x2<MAX_X2; x2++ )
                {
                    double expectedValue = x1+2*x2+3.14;
                    DataPoint dp = new Observation( expectedValue );
                    dp.setIndependentValue( "x1", x1 );
                    dp.setIndependentValue( "x2", x2 );
                    expectedDataSet.add( dp );
                    numberOfDataPoints++;
                }
        
        assertEquals("Checking correct number of data points created",
                     numberOfDataPoints, expectedDataSet.size());
    }
    
    /**
     * Resets the data set created during setUp.
     */
    public void tearDown()
    {
        expectedDataSet.clear();
        expectedDataSet = null;
    }
    
    /**
     * Tests the correct output of a DataSet to a CSV file. Assumes that the
     * CSVBuilder input is correct and valid.
     */
    public void testCSVOutput()
        throws FileNotFoundException, IOException
    {
        // Create new File object to which output should be sent
        File testFile = File.createTempFile( "test", ".csv" );
        
        // Create new outputter and use it to write a CSV file
        DelimitedTextOutputter outputter
            = new DelimitedTextOutputter( testFile.getAbsolutePath() );
        outputter.output( expectedDataSet );
        
        // Use a CSVBuilder to read in the file
        CSVBuilder builder = new CSVBuilder( testFile.getAbsolutePath() );
        DataSet writtenDataSet = builder.build();
        
        // Compare the expectedDataSet with the writtenDataSet
        assertEquals("Comparing data set written with data set written then read back",
                     expectedDataSet, writtenDataSet);
        
        // Clean up - remove test file
        testFile.delete();
    }
    
    /**
     * Tests the correct output of a DataSet to a CSV file, using a modified
     * delimiter String - a comma surrounded by various whitespace. Assumes
     * that the CSVBuilder input is correct and valid.
     */
    public void testAltCSVOutput()
        throws FileNotFoundException, IOException
    {
        final String DELIMITER = ", ";
        
        // Create new File object to which output should be sent
        File testFile = File.createTempFile( "test", ".csv" );
        
        // Create new outputter and use it to write a CSV file
        DelimitedTextOutputter outputter
            = new DelimitedTextOutputter( testFile.getAbsolutePath() );
        outputter.setDelimiter( DELIMITER );
        outputter.setOutputHeaderRow( true );
        outputter.output( expectedDataSet );
        
        // Use a CSVBuilder to read in the file
        CSVBuilder builder
            = new CSVBuilder( testFile.getAbsolutePath(), true );
        DataSet writtenDataSet = builder.build();
        
        // Compare the expectedDataSet with the writtenDataSet
        assertEquals("Comparing data set written with data set written then read back",
                     expectedDataSet, writtenDataSet);
        
        // Clean up - remove test file
        testFile.delete();
    }
}
