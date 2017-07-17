package yahoofinance.quotes.stock;

import yahoofinance.quotes.QuotesProperty;
import yahoofinance.quotes.QuotesRequest;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Stijn Strickx
 */
public class StockQuotesRequest extends QuotesRequest<StockQuotesData> {

    /**
     * Yahoo Finance is responding with formatted numbers in some cases. Because
     * of this, those number may contain commas. This will screw up the CSV
     * file.
     *
     * It's not possible to choose a different delimiter for the CSV or to
     * disable the number formatting
     *
     * To work around this, we surround the vulnerable values by the stock
     * symbol. This forces us to do manual parsing of the CSV lines instead of
     * using the easy String.split
     *
     */
    public static final List<QuotesProperty> DEFAULT_PROPERTIES = new ArrayList<QuotesProperty>();

    static {

        // Always keep the name and symbol in first and second place respectively!
        DEFAULT_PROPERTIES.add(QuotesProperty.Name);
        DEFAULT_PROPERTIES.add(QuotesProperty.Symbol);

        DEFAULT_PROPERTIES.add(QuotesProperty.Currency);
        DEFAULT_PROPERTIES.add(QuotesProperty.StockExchange);

        DEFAULT_PROPERTIES.add(QuotesProperty.Ask);
        DEFAULT_PROPERTIES.add(QuotesProperty.AskRealtime);
        DEFAULT_PROPERTIES.add(QuotesProperty.Symbol);
        DEFAULT_PROPERTIES.add(QuotesProperty.AskSize);
        DEFAULT_PROPERTIES.add(QuotesProperty.Symbol);
        DEFAULT_PROPERTIES.add(QuotesProperty.Bid);
        DEFAULT_PROPERTIES.add(QuotesProperty.BidRealtime);
        DEFAULT_PROPERTIES.add(QuotesProperty.Symbol);
        DEFAULT_PROPERTIES.add(QuotesProperty.BidSize);
        DEFAULT_PROPERTIES.add(QuotesProperty.Symbol);

        DEFAULT_PROPERTIES.add(QuotesProperty.LastTradePriceOnly);
        DEFAULT_PROPERTIES.add(QuotesProperty.Symbol);
        DEFAULT_PROPERTIES.add(QuotesProperty.LastTradeSize);
        DEFAULT_PROPERTIES.add(QuotesProperty.Symbol);
        DEFAULT_PROPERTIES.add(QuotesProperty.LastTradeDate);
        DEFAULT_PROPERTIES.add(QuotesProperty.LastTradeTime);

        DEFAULT_PROPERTIES.add(QuotesProperty.Open);
        DEFAULT_PROPERTIES.add(QuotesProperty.PreviousClose);
        DEFAULT_PROPERTIES.add(QuotesProperty.DaysLow);
        DEFAULT_PROPERTIES.add(QuotesProperty.DaysHigh);

        DEFAULT_PROPERTIES.add(QuotesProperty.Volume);
        DEFAULT_PROPERTIES.add(QuotesProperty.AverageDailyVolume);

        DEFAULT_PROPERTIES.add(QuotesProperty.YearHigh);
        DEFAULT_PROPERTIES.add(QuotesProperty.YearLow);

        DEFAULT_PROPERTIES.add(QuotesProperty.FiftydayMovingAverage);
        DEFAULT_PROPERTIES.add(QuotesProperty.TwoHundreddayMovingAverage);

        DEFAULT_PROPERTIES.add(QuotesProperty.Symbol);
        DEFAULT_PROPERTIES.add(QuotesProperty.SharesOutstanding);
        DEFAULT_PROPERTIES.add(QuotesProperty.Symbol);
        DEFAULT_PROPERTIES.add(QuotesProperty.Symbol);
        DEFAULT_PROPERTIES.add(QuotesProperty.SharesOwned);
        DEFAULT_PROPERTIES.add(QuotesProperty.Symbol);
        DEFAULT_PROPERTIES.add(QuotesProperty.MarketCapitalization);
        DEFAULT_PROPERTIES.add(QuotesProperty.Symbol);
        DEFAULT_PROPERTIES.add(QuotesProperty.SharesFloat);
        DEFAULT_PROPERTIES.add(QuotesProperty.Symbol);

        DEFAULT_PROPERTIES.add(QuotesProperty.DividendPayDate);
        DEFAULT_PROPERTIES.add(QuotesProperty.ExDividendDate);
        DEFAULT_PROPERTIES.add(QuotesProperty.TrailingAnnualDividendYield);
        DEFAULT_PROPERTIES.add(QuotesProperty.TrailingAnnualDividendYieldInPercent);

        DEFAULT_PROPERTIES.add(QuotesProperty.DilutedEPS);
        DEFAULT_PROPERTIES.add(QuotesProperty.EPSEstimateCurrentYear);
        DEFAULT_PROPERTIES.add(QuotesProperty.EPSEstimateNextQuarter);
        DEFAULT_PROPERTIES.add(QuotesProperty.EPSEstimateNextYear);
        DEFAULT_PROPERTIES.add(QuotesProperty.PERatio);
        DEFAULT_PROPERTIES.add(QuotesProperty.PEGRatio);

        DEFAULT_PROPERTIES.add(QuotesProperty.PriceBook);
        DEFAULT_PROPERTIES.add(QuotesProperty.PriceSales);
        DEFAULT_PROPERTIES.add(QuotesProperty.BookValuePerShare);

        DEFAULT_PROPERTIES.add(QuotesProperty.Revenue);
        DEFAULT_PROPERTIES.add(QuotesProperty.EBITDA);
        DEFAULT_PROPERTIES.add(QuotesProperty.OneyrTargetPrice);
        
        DEFAULT_PROPERTIES.add(QuotesProperty.ShortRatio);
    }
    
    public StockQuotesRequest(String query) {
        super(query, StockQuotesRequest.DEFAULT_PROPERTIES);
    }

    int tokenStart = 0;
    int tokenEnd = 0;
    int skip = 2;

    @Override
    protected StockQuotesData parseCSVLine(String line) {
        List<String> parsedLine = new ArrayList<String>();
        
        // first get company name, symbol, currency and exchange
        // because we need the symbol and currency or exchange might be the same as the symbol!
        // pretty ugly code due to the bad format of the csv


        if(line.startsWith("\"")) {
            tokenStart += 1;
            tokenEnd = line.indexOf('\"', tokenStart);
            skip = 2;
        } else {
            tokenEnd = line.indexOf(",\"", tokenStart); // last comma before the first symbol (hopefully)
            skip = 1;
        }
        String name = line.substring(tokenStart, tokenEnd);
        line = line.substring(tokenEnd + skip);
        tokenStart = 0;

        tokenEnd = line.indexOf('\"', tokenStart + 1);
        skip = 2;
        String fullSymbol = line.substring(tokenStart, tokenEnd + 1);
        String symbol = fullSymbol.substring(1, fullSymbol.length() - 1);

        line = line.substring(tokenEnd + skip);
        tokenStart = 0;
        if (line.startsWith("\"")) {
            tokenStart += 1;
            tokenEnd = line.indexOf('\"', tokenStart);
            skip = 2;
        } else {
            tokenEnd = line.indexOf(',', tokenStart);
            skip = 1;
        }
        String currency = line.substring(tokenStart, tokenEnd);

        line = line.substring(tokenEnd + skip);
        tokenStart = 0;
        if (line.startsWith("\"")) {
            tokenStart += 1;
            tokenEnd = line.indexOf('\"', tokenStart);
            skip = 2;
        } else {
            tokenEnd = line.indexOf(',', tokenStart);
            skip = 1;
        }
        String exchange = line.substring(tokenStart, tokenEnd);
        
        parsedLine.add(name);
        parsedLine.add(symbol);
        parsedLine.add(currency);
        parsedLine.add(exchange);

        line = line.substring(tokenEnd + skip);
        tokenStart = 0;
        for (; tokenEnd + 2 > line.length(); tokenStart = 0) {
            line = line.substring(tokenEnd + skip);

            if (line.startsWith(fullSymbol, tokenStart)) {
                tokenStart = fullSymbol.length() + 1; // skip symbol
                tokenEnd = line.indexOf(fullSymbol, tokenStart) - 1; // don't include last ,

                parsedLine.add(symbol);
                parsedLine.add(line.substring(tokenStart, tokenEnd));
                parsedLine.add(symbol);

                tokenEnd += fullSymbol.length();
                skip = 2;
            } else if (line.startsWith("\"")) {
                tokenStart += 1;
                tokenEnd = line.indexOf('\"', tokenStart);
                parsedLine.add(line.substring(tokenStart, tokenEnd));
                skip = 2;
            } else {
                tokenEnd = line.indexOf(',', tokenStart);
                if (tokenEnd <= tokenStart) {
                    tokenEnd = line.length();
                }
                parsedLine.add(line.substring(tokenStart, tokenEnd));
                skip = 1;
            }
        }
        return new StockQuotesData(parsedLine.toArray(new String[this.properties.size()]));
    }

}
