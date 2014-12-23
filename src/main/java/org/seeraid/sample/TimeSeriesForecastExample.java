package org.seeraid.sample;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

import org.joda.time.DateTime;

import weka.classifiers.evaluation.NumericPrediction;
import weka.classifiers.functions.GaussianProcesses;
import weka.classifiers.timeseries.WekaForecaster;
import weka.classifiers.timeseries.core.TSLagMaker;
import weka.core.Instances;

public class TimeSeriesForecastExample {

private TimeSeriesForecastExample() {
    try {
      // path to the Australian wine data included with the time series forecasting
      // package
      InputStream inputStream = this.getClass().getResourceAsStream("sample-data/w.txt");

      // load the wine data
      Instances wine = new Instances(new InputStreamReader(inputStream));

      // new forecaster
      WekaForecaster forecaster = new WekaForecaster();

      // set the targets we want to forecast. This method calls
      // setFieldsToLag() on the lag maker object for us
      forecaster.setFieldsToForecast("Fortified,Dry-white,Sweet-white,Red,Rose,Sparkling");

      // default underlying classifier is SMOreg (SVM) - we'll use
      // gaussian processes for regression instead
//      3179,4290,274,3923,62,2031
      forecaster.setBaseForecaster(new GaussianProcesses());
//      forecaster.setBaseForecaster(new LinearRegression());
//      forecaster.setBaseForecaster(new Logistic());
//      forecaster.setBaseForecaster(new MultilayerPerceptron());
//      forecaster.setBaseForecaster(new SimpleLinearRegression());
//      forecaster.setBaseForecaster(new SimpleLogistic());
//      forecaster.setBaseForecaster(new SMO());
//      forecaster.setBaseForecaster(new SMOreg());
//      forecaster.setBaseForecaster(new VotedPerceptron());
//      forecaster.setBaseForecaster(new LibSVM());

      forecaster.getTSLagMaker().setTimeStampField("Date"); // date time stamp
//      forecaster.getTSLagMaker().setMinLag(1);
//      forecaster.getTSLagMaker().setMaxLag(12); // monthly data

      // add a month of the year indicator field
      forecaster.getTSLagMaker().setAddMonthOfYear(true);

      // add a quarter of the year indicator field
      forecaster.getTSLagMaker().setAddQuarterOfYear(true);

      // build the model
      forecaster.buildForecaster(wine, System.out);

      // prime the forecaster with enough recent historical data
      // to cover up to the maximum lag. In our case, we could just supply
      // the 12 most recent historical instances, as this covers our maximum
      // lag period
      forecaster.primeForecaster(wine);

      // forecast for 12 units (months) beyond the end of the
      // training data
      List<List<NumericPrediction>> forecast = forecaster.forecast(5, System.out);

      DateTime currentDt = getCurrentDateTime(forecaster.getTSLagMaker());

      // output the predictions. Outer list is over the steps; inner list is over
      // the targets
      for (List<NumericPrediction> predsAtStep:forecast) {
        System.out.print(currentDt + ": ");
        for(NumericPrediction predForTarget:predsAtStep) {
        	Double predicted = predForTarget.predicted();
        	System.out.print("" + (predicted.intValue()) + ",");
        }
        System.out.println();

        // Advance the current date to the next prediction date
        currentDt = advanceTime(forecaster.getTSLagMaker(), currentDt);
      }

      // we can continue to use the trained forecaster for further forecasting
      // by priming with the most recent historical data (as it becomes available).
      // At some stage it becomes prudent to re-build the model using current
      // historical data.

    } catch (Exception ex) {
      ex.printStackTrace();
    }
  }

public static void main(String[] args) {
	new TimeSeriesForecastExample();
}

  private static DateTime getCurrentDateTime(TSLagMaker lm) throws Exception {
    return new DateTime((long)lm.getCurrentTimeStampValue());
  }

  private static DateTime advanceTime(TSLagMaker lm, DateTime dt) {
    return new DateTime((long)lm.advanceSuppliedTimeValue(dt.getMillis()));
  }
}
