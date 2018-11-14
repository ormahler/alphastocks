import com.fasterxml.jackson.databind.ObjectMapper;
import org.nd4j.linalg.util.ArrayUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.*;
import dro.stat.*;

public class converter1
{
    public static void main(String[] args) throws IOException, NoSuchAlgorithmException, KeyManagementException
    {
        double pValue = getCausality("MSFT", "GOOGL", "1min");
    }

    public static double getCausality(String i_StockNameX, String i_StockNameY, String i_Interval) throws IOException, NoSuchAlgorithmException, KeyManagementException
    {
        double [] stockX= getStockCloseTimeArray(i_StockNameX,i_Interval);
        double [] stockY= getStockCloseTimeArray(i_StockNameY, i_Interval);
        if(stockX.length > stockY.length)
        {
            double [] temp = new double[stockY.length];
            for(int i = 0 ; i<stockY.length; i++)
            {
                temp[i] = stockX[i];
            }
            stockX = temp;
        }
        else
        {
            double [] temp = new double[stockX.length];
            for(int i = 0 ; i<stockY.length; i++)
            {
                temp[i] = stockY[i];
            }
            stockY = temp;
        }
        GrangerCausalityStrategy_Bivariate big = new  GrangerCausalityStrategy_Bivariate(2);
        GrangerCausalIndicator T = big.apply(stockX,stockY);
        return T.getPValue();
    }

    public static double[] getStockCloseTimeArray(String i_StockName, String i_Interval) throws IOException
    {
        String apiKey = "5X2B3I2ROZ7Z7NNB";
        String function = "TIME_SERIES_INTRADAY";
        String symbol = i_StockName;
        String interval = i_Interval;
        String outputSize = "full";
        String reqFormat = "https://www.alphavantage.co/query?function=%s&symbol=%s&interval=%s&outputsize=%s&apikey=%s";

        String reqStr = String.format(reqFormat, function, symbol, interval, outputSize, apiKey);

        TimeSeriesResult response = getRequest(reqStr);
        Map<String, TimeSeries> x = response.getTimeSeries();
        double [] stock = new double[x.size()];
        int i =0;
        for (TimeSeries p: x.values())
        {
            stock[i] = p.getClose() ;
            i++;
        }
        return stock;

    }

    public static TimeSeriesResult getRequest(String req) throws IOException
    {
        ResponseEntity<String> responseEntity = new RestTemplate().getForEntity(req, String.class);
        ObjectMapper objectMapper = new ObjectMapper();
        TimeSeriesResult timeSeriesResult = objectMapper.readValue(responseEntity.getBody(), TimeSeriesResult.class);
        System.out.println(timeSeriesResult.getTimeSeries().size());
        return timeSeriesResult;
    }


    private static String getResponse(HttpURLConnection connection) throws IOException
    {
        BufferedReader in = new BufferedReader(
                new InputStreamReader(connection.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null)
        {
            response.append(inputLine);
        }

        in.close();

        return response.toString();
    }
}