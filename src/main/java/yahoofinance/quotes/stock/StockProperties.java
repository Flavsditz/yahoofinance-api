package yahoofinance.quotes.stock;

import yahoofinance.quotes.QuotesProperty;

import java.util.Arrays;
import java.util.List;

public class StockProperties {

    private static final List<QuotesProperty> quotesProperties = Arrays.asList(
            QuotesProperty.Name,
            QuotesProperty.Symbol,
            QuotesProperty.Currency,
            QuotesProperty.StockExchange,
            QuotesProperty.Ask,
            QuotesProperty.AskRealtime,
            QuotesProperty.AskSize,
            QuotesProperty.Bid,
            QuotesProperty.BidRealtime,
            QuotesProperty.BidSize,
            QuotesProperty.LastTradePriceOnly,
            QuotesProperty.LastTradeSize,
            QuotesProperty.LastTradeDate,
            QuotesProperty.LastTradeTime,
            QuotesProperty.Open,
            QuotesProperty.PreviousClose,
            QuotesProperty.DaysLow,
            QuotesProperty.DaysHigh,
            QuotesProperty.Volume,
            QuotesProperty.AverageDailyVolume,
            QuotesProperty.YearHigh,
            QuotesProperty.YearLow,
            QuotesProperty.FiftydayMovingAverage,
            QuotesProperty.TwoHundreddayMovingAverage,
            QuotesProperty.SharesOutstanding,
            QuotesProperty.SharesOwned,
            QuotesProperty.MarketCapitalization,
            QuotesProperty.SharesFloat,
            QuotesProperty.DividendPayDate,
            QuotesProperty.ExDividendDate,
            QuotesProperty.TrailingAnnualDividendYield,
            QuotesProperty.TrailingAnnualDividendYieldInPercent,
            QuotesProperty.DilutedEPS,
            QuotesProperty.EPSEstimateCurrentYear,
            QuotesProperty.EPSEstimateNextQuarter,
            QuotesProperty.EPSEstimateNextYear,
            QuotesProperty.PERatio,
            QuotesProperty.PEGRatio,
            QuotesProperty.PriceBook,
            QuotesProperty.PriceSales,
            QuotesProperty.BookValuePerShare,
            QuotesProperty.Revenue,
            QuotesProperty.EBITDA,
            QuotesProperty.OneyrTargetPrice,
            QuotesProperty.ShortRatio);

    public static List<QuotesProperty> getDefaultProperties() {
        return quotesProperties;
    }

    public static int getPropertyIndex(QuotesProperty property) {
        return quotesProperties.indexOf(property);
    }

}
