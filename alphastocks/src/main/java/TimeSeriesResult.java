import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;
import java.util.Map;

/**
 * Created by ormeidan on 11/4/18.
 */
public class TimeSeriesResult
{
    @JsonProperty("Meta Data")
    private MetaData metaData;
    @JsonProperty("Time Series (1min)")
    private Map<String, TimeSeries> timeSeries;

    public MetaData getMetaData() {
        return metaData;
    }

    public void setMetaData(MetaData metaData) {
        this.metaData = metaData;
    }

    public Map<String, TimeSeries> getTimeSeries() {
        return timeSeries;
    }

    public void setTimeSeries(Map<String, TimeSeries> timeSeries) {
        this.timeSeries = timeSeries;
    }
}