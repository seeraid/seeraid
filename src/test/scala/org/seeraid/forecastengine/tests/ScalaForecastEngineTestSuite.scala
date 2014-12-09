package org.seeraid.forecastengine.tests

import java.util.Iterator
import org.seeraid.forecastengine.DataPoint
import org.seeraid.forecastengine.DataSet
import ScalaForecastEngineTestSuite._
import org.scalatest.FunSuite

object ScalaForecastEngineTestSuite {

  /**
   * The amount of error in the forecast values where the forecast is
   * considered "correct" by the test.
   */
  private var TOLERANCE: Double = 0.00001
}

/**
 * Defines a base test case class that all Open Forecast test cases can
 * extend (1) for consistency; and (2) in order to inherit some common
 * functionality used in validating test results.
 * @author Steven R. Gould
 */
class ScalaForecastEngineTestSuite extends FunSuite{

  /**
   * A helper function that validates the actual results obtaining for
   * a DataSet match the expected results.
   * @param actualResults the DataSet returned from the forecast method
   *        that contains the data points for which forecasts were done.
   * @param expectedResults an array of expected values for the forecast
   *        data points. The order should match the order of the results
   *        as defined by the DataSet iterator.
   */
  protected def checkResults(actualResults: DataSet, expectedResults: Array[Double]) {
    checkResults(actualResults, expectedResults, TOLERANCE)
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
  protected def checkResults(actualResults: DataSet, expectedResults: Array[Double], tolerance: Double) {
    assert(expectedResults!=null,"Checking expected results is not null")
    assert(expectedResults.length > 0,"Checking there are some expected results")
    assert(expectedResults.length==actualResults.size,"Checking the correct number of results returned")
    val it = actualResults.iterator()
    var i = 0
    while (it.hasNext) {
      val fc = it.next().asInstanceOf[DataPoint]
      val fcValue = fc.getDependentValue
      assert(expectedResults(i)==fcValue,"Checking result")
      i += 1
    }
  }
  
  protected def assertEquals(msg:String,first:Double,second:Double) {
    assert(first==second,msg);
  }
  protected def assertEquals(msg:String,first:Int,second:Int) {
	  assert(first==second,msg);
  }
  protected def assertTrue(msg:String,condition:Boolean) {
	  assert(condition==true,msg);
  }
  protected def assertTrue(condition:Boolean) {
	  assert(condition==true);
  }
}
