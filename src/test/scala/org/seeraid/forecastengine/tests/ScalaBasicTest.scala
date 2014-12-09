package org.seeraid.forecastengine.tests

import org.seeraid.forecastengine.DataSet
import org.seeraid.forecastengine.Forecaster
import org.seeraid.forecastengine.Observation

class ScalaBasicTest extends ScalaForecastEngineTestSuite{

  /**
   * Tests that two DataPoint objects initialized differently but with the
   * same data are equal.
   */
  test("DataPoint") {
    val dp1 = new Observation(1.0)
    dp1.setIndependentValue("x", 2.0)
    dp1.setIndependentValue("t", 3.0)
    val dp2 = new Observation(1.0)
    dp2.setIndependentValue("t", 3.0)
    dp2.setIndependentValue("x", 2.0)
    assert(dp1.equals(dp2))
    println("Passed!!")
  }
}

