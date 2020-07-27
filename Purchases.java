import java.util.List;
import java.util.ArrayList;

public class Purchases {
  private List<Integer> pills;
  private Double size, mean, stdDev;

  public Purchases() {
    pills = new ArrayList<Integer>();
    size = 0.0;
    mean = 0.0;
  }

  public void add(Integer dosage_unit) {
    pills.add(dosage_unit);
    size++;
  }

  public void calcMean() {
    int total = 0;
    for (int i = 0; i < size; i++) {
      total = total + pills.get(i);
    }
    mean = total / size;
  }

  private Double calcMean(List<Double> values) {
    int length = values.size();
    Double sum = 0.0;
    for (int i = 0; i < length; i++) {
      sum = sum + values.get(i);
    }
    Double stdMean = sum / length;
    return stdMean;
  }

  /**
   * this method calculates our standard deviation for the set of pills this
   * method is calle under the assumption you have already calculated the mean for
   * this set of pills. I used the standard deviation formula from the following
   * website: https://www.mathsisfun.com/data/standard-deviation-formulas.html
   */
  public void calcStdDev() {
    // subtract the mean from each value and then square
    int size = pills.size();
    List<Double> stdList = new ArrayList<Double>();
    for (int i = 0; i < size; i++) {
      // subtract mean from value in pills list
      Double x = pills.get(i) - mean;
      // square that value
      x = x * x;
      // add to new list
      stdList.add(x);
    }
    // take the mean of all these values
    Double stdMean = calcMean(stdList);
    // square that mean value to be the standard deviation
    stdDev = Math.sqrt(stdMean);
  }

  public Double getMean() {
    return mean;
  }

  public Double getStdDev() {
    return stdDev;
  }

  public List<Integer> getPills() {
    return pills;
  }
}

