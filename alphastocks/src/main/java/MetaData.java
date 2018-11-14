import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by ormeidan on 11/4/18.
 */
public class MetaData
{
    @JsonProperty("1. Information")
    private String information;
    @JsonProperty("2. Symbol")
    private String symbol;
    @JsonProperty("5. Output Size")
    private String outputSize;
    @JsonProperty("6. Time Zone")
    private String timezone;
    @JsonProperty("3. Last Refreshed")
    private String lastRefreshed;
    @JsonProperty("4. Interval")
    private String interval;
    @JsonProperty("Notes")
    private String notes;
    @JsonProperty("From Symbol")
    private String fromCurrency;
    @JsonProperty("To Symbol")
    private String toCurrency;

    public static final String META_DATA_RESPONSE_KEY = "Meta Data";
}