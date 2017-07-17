package yahoofinance.quotes.stock;

import yahoofinance.Stock;
import yahoofinance.Utils;
import yahoofinance.exchanges.ExchangeTimeZone;
import yahoofinance.quotes.QuotesProperty;

import java.util.List;

/**
 *
 * @author Stijn Strickx
 */
public class StockQuotesData {

    private final List<String> data;

    public StockQuotesData(List<String> data) {
        this.data = data;
    }
    
    public String getValue(QuotesProperty property) {
        int idx = StockProperties.getPropertyIndex(property);

        if (idx >= 0 && idx < data.size()) {
            return data.get(idx);
        }
        return null;
    }
    
    public StockQuote getQuote() {
        String symbol = this.getValue(QuotesProperty.SYMBOL);
        StockQuote quote = new StockQuote(symbol);

        quote.setPrice(Utils.getBigDecimal(this.getValue(QuotesProperty.LAST_TRADE_PRICE_ONLY)));
        quote.setLastTradeSize(Utils.getLong(this.getValue(QuotesProperty.LAST_TRADE_SIZE)));
        quote.setAsk(Utils.getBigDecimal(this.getValue(QuotesProperty.ASK_REALTIME), this.getValue(QuotesProperty.ASK)));
        quote.setAskSize(Utils.getLong(this.getValue(QuotesProperty.ASK_SIZE)));
        quote.setBid(Utils.getBigDecimal(this.getValue(QuotesProperty.BID_REALTIME), this.getValue(QuotesProperty.BID)));
        quote.setBidSize(Utils.getLong(this.getValue(QuotesProperty.BID_SIZE)));
        quote.setOpen(Utils.getBigDecimal(this.getValue(QuotesProperty.OPEN)));
        quote.setPreviousClose(Utils.getBigDecimal(this.getValue(QuotesProperty.PREVIOUS_CLOSE)));
        quote.setDayHigh(Utils.getBigDecimal(this.getValue(QuotesProperty.DAYS_HIGH)));
        quote.setDayLow(Utils.getBigDecimal(this.getValue(QuotesProperty.DAYS_LOW)));
        
        quote.setTimeZone(ExchangeTimeZone.getStockTimeZone(symbol));
        quote.setLastTradeDateStr(this.getValue(QuotesProperty.LAST_TRADE_DATE));
        quote.setLastTradeTimeStr(this.getValue(QuotesProperty.LAST_TRADE_TIME));
        quote.setLastTradeTime(Utils.parseDateTime(this.getValue(QuotesProperty.LAST_TRADE_DATE), this.getValue(QuotesProperty.LAST_TRADE_TIME), quote.getTimeZone()));

        quote.setYearHigh(Utils.getBigDecimal(this.getValue(QuotesProperty.YEAR_HIGH)));
        quote.setYearLow(Utils.getBigDecimal(this.getValue(QuotesProperty.YEAR_LOW)));
        quote.setPriceAvg50(Utils.getBigDecimal(this.getValue(QuotesProperty.FIFTY_DAY_MOVING_AVERAGE)));
        quote.setPriceAvg200(Utils.getBigDecimal(this.getValue(QuotesProperty.TWO_HUNDRED_DAY_MOVING_AVERAGE)));

        quote.setVolume(Utils.getLong(this.getValue(QuotesProperty.VOLUME)));
        quote.setAvgVolume(Utils.getLong(this.getValue(QuotesProperty.AVERAGE_DAILY_VOLUME)));
        
        return quote;
    }
    
    public StockStats getStats() {
        String symbol = this.getValue(QuotesProperty.SYMBOL);
        StockStats stats = new StockStats(symbol);

        stats.setMarketCap(Utils.getBigDecimal(this.getValue(QuotesProperty.MARKET_CAPITALIZATION)));
        stats.setSharesFloat(Utils.getLong(this.getValue(QuotesProperty.SHARES_FLOAT)));
        stats.setSharesOutstanding(Utils.getLong(this.getValue(QuotesProperty.SHARES_OUTSTANDING)));
        stats.setSharesOwned(Utils.getLong(this.getValue(QuotesProperty.SHARES_OWNED)));

        stats.setEps(Utils.getBigDecimal(this.getValue(QuotesProperty.DILUTED_EPS)));
        stats.setPe(Utils.getBigDecimal(this.getValue(QuotesProperty.PE_RATIO)));
        stats.setPeg(Utils.getBigDecimal(this.getValue(QuotesProperty.PEG_RATIO)));

        stats.setEpsEstimateCurrentYear(Utils.getBigDecimal(this.getValue(QuotesProperty.EPS_ESTIMATE_CURRENT_YEAR)));
        stats.setEpsEstimateNextQuarter(Utils.getBigDecimal(this.getValue(QuotesProperty.EPS_ESTIMATE_NEXT_QUARTER)));
        stats.setEpsEstimateNextYear(Utils.getBigDecimal(this.getValue(QuotesProperty.EPS_ESTIMATE_NEXT_YEAR)));

        stats.setPriceBook(Utils.getBigDecimal(this.getValue(QuotesProperty.PRICE_BOOK)));
        stats.setPriceSales(Utils.getBigDecimal(this.getValue(QuotesProperty.PRICE_SALES)));
        stats.setBookValuePerShare(Utils.getBigDecimal(this.getValue(QuotesProperty.BOOK_VALUE_PER_SHARE)));

        stats.setOneYearTargetPrice(Utils.getBigDecimal(this.getValue(QuotesProperty.ONE_YEAR_TARGET_PRICE)));
        stats.setEBITDA(Utils.getBigDecimal(this.getValue(QuotesProperty.EBITDA)));
        stats.setRevenue(Utils.getBigDecimal(this.getValue(QuotesProperty.REVENUE)));

        stats.setShortRatio(Utils.getBigDecimal(this.getValue(QuotesProperty.SHORT_RATIO)));
        
        return stats;
    }
    
    public StockDividend getDividend() {
        String symbol = this.getValue(QuotesProperty.SYMBOL);
        StockDividend dividend = new StockDividend(symbol);

        dividend.setPayDate(Utils.parseDividendDate(this.getValue(QuotesProperty.DIVIDEND_PAY_DATE)));
        dividend.setExDate(Utils.parseDividendDate(this.getValue(QuotesProperty.EX_DIVIDEND_DATE)));
        dividend.setAnnualYield(Utils.getBigDecimal(this.getValue(QuotesProperty.TRAILING_ANNUAL_DIVIDEND_YIELD)));
        dividend.setAnnualYieldPercent(Utils.getBigDecimal(this.getValue(QuotesProperty.TRAILING_ANNUAL_DIVIDEND_YIELD_IN_PERCENT)));
        
        return dividend;
    }
    
    public Stock getStock() {
        String symbol = this.getValue(QuotesProperty.SYMBOL);
        Stock stock = new Stock(symbol);

        stock.setName(Utils.getString(this.getValue(QuotesProperty.NAME)));
        stock.setCurrency(Utils.getString(this.getValue(QuotesProperty.CURRENCY)));
        stock.setStockExchange(Utils.getString(this.getValue(QuotesProperty.STOCK_EXCHANGE)));
        
        stock.setQuote(this.getQuote());
        stock.setStats(this.getStats());
        stock.setDividend(this.getDividend());
        
        return stock;
    }
    
}
