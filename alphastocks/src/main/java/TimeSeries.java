import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Optional;

public class TimeSeries {

    /*
     Override the getters to return optionals.
    */
    public Optional<Double> getAdjustedClose() {
        return Optional.ofNullable(adjustedClose);
    }

    public Optional<Double> getDividendAmount() {
        return Optional.ofNullable(dividendAmount);
    }

    public Optional<Double> getSplitCoefficient() {
        return Optional.ofNullable(splitCoefficient);
    }

    public double getClose(){return this.close;}

    @JsonProperty("1. open")
    private double open;
    @JsonProperty("2. high")
    private double high;
    @JsonProperty("3. low")
    private double low;
    @JsonProperty("4. close")
    private double close;
    @JsonProperty("5. volume")
    private double volume;
    @JsonProperty("adjusted close")
    private Double adjustedClose = null;
    @JsonProperty("dividend amount")
    private Double dividendAmount = null;
    @JsonProperty("split coefficient")
    private Double splitCoefficient = null;
}