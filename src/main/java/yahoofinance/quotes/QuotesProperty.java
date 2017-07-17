package yahoofinance.quotes;

/**
 * @author Stijn Strickx
 */
public enum QuotesProperty {
    AfterHoursChangeRealtime("c8", false),
    AnnualizedGain("g3", false),
    Ask("a", true),
    AskRealtime("b2", false),
    AskSize("a5", true),
    AverageDailyVolume("a2", true),
    Bid("b", true),
    BidRealtime("b3", false),
    BidSize("b6", true),
    BookValuePerShare("b4", true),
    Change("c1", false),
    Change_ChangeInPercent("c", false),
    ChangeFromFiftydayMovingAverage("m7", false),
    ChangeFromTwoHundreddayMovingAverage("m5", false),
    ChangeFromYearHigh("k4", false),
    ChangeFromYearLow("j5", false),
    ChangeInPercent("p2", false),
    ChangeInPercentRealtime("k2", false),
    ChangeRealtime("c6", false),
    Commission("c3", false),
    Currency("c4", false),
    DaysHigh("h", true),
    DaysLow("g", true),
    DaysRange("m", false),
    DaysRangeRealtime("m2", false),
    DaysValueChange("w1", false),
    DaysValueChangeRealtime("w4", false),
    DividendPayDate("r1", true),
    TrailingAnnualDividendYield("d", true),
    TrailingAnnualDividendYieldInPercent("y", true),
    DilutedEPS("e", true),
    EBITDA("j4", true),
    EPSEstimateCurrentYear("e7", true),
    EPSEstimateNextQuarter("e9", true),
    EPSEstimateNextYear("e8", true),
    ExDividendDate("q", true),
    FiftydayMovingAverage("m3", true),
    SharesFloat("f6", true),
    HighLimit("l2", false),
    HoldingsGain("g4", false),
    HoldingsGainPercent("g1", false),
    HoldingsGainPercentRealtime("g5", false),
    HoldingsGainRealtime("g6", false),
    HoldingsValue("v1", false),
    HoldingsValueRealtime("v7", false),
    LastTradeDate("d1", false),
    LastTradePriceOnly("l1", false),
    LastTradeRealtimeWithTime("k1", false),
    LastTradeSize("k3", true),
    LastTradeTime("t1", false),
    LastTradeWithTime("l", false),
    LowLimit("l3", false),
    MarketCapitalization("j1", true),
    MarketCapRealtime("j3", false),
    MoreInfo("i", false),
    Name("n", false),
    Notes("n4", false),
    OneyrTargetPrice("t8", true),
    Open("o", true),
    OrderBookRealtime("i5", false),
    PEGRatio("r5", true),
    PERatio("r", true),
    PERatioRealtime("r2", false),
    PercentChangeFromFiftydayMovingAverage("m8", false),
    PercentChangeFromTwoHundreddayMovingAverage("m6", false),
    ChangeInPercentFromYearHigh("k5", false),
    PercentChangeFromYearLow("j6", false),
    PreviousClose("p", true),
    PriceBook("p6", true),
    PriceEPSEstimateCurrentYear("r6", false),
    PriceEPSEstimateNextYear("r7", false),
    PricePaid("p1", false),
    PriceSales("p5", true),
    Revenue("s6", true),
    SharesOwned("s1", true),
    SharesOutstanding("j2", true),
    ShortRatio("s7", true),
    StockExchange("x", false),
    Symbol("s", false),
    TickerTrend("t7", false),
    TradeDate("d2", false),
    TradeLinks("t6", false),
    TradeLinksAdditional("f", false),
    TwoHundreddayMovingAverage("m4", true),
    Volume("v", true),
    YearHigh("k", true),
    YearLow("j", true),
    YearRange("w", false);

    private final String tag;
    private final boolean padded;

    QuotesProperty(String tag, boolean padded) {
        this.tag = tag;
        this.padded = padded;
    }

    public String getTag() {
        return this.tag;
    }

    public boolean isPadded() {
        return padded;
    }
}

