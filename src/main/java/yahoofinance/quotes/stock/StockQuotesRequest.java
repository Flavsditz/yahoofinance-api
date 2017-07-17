package yahoofinance.quotes.stock;

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
    private static final String PADDING = "***";

    public StockQuotesRequest(String query) {
        super(query, StockProperties.getDefaultProperties());
    }

    private List<String> parsedLine = new ArrayList<>();

    @Override
    protected StockQuotesData parseCSVLine(String line) {
        line = parseNextToken(line);
        line = parseCompanyTicker(line);

        while (line.length() > 0) {
            line = parseNextToken(line);
        }
        return new StockQuotesData(parsedLine);
    }

    private String parseNextToken(String line) {
        if (isNextPadding(line)) {
            line = parsePaddedValueTokenAndReturnRest(line);
        } else if (isNextAString(line)) {
            line = parseStringTokenAndReturnRest(line);
        } else {
            line = parseNonStringTokenAndReturnRest(line);
        }
        return line;
    }

    private String parseCompanyTicker(String line) {
        String resultLine = parseStringTokenAndReturnRest(line);
        String ticker = parsedLine.get(parsedLine.size() - 1);

        resultLine = resultLine.replace("\"" + ticker + "\"", PADDING);

        return resultLine;
    }

    private String parsePaddedValueTokenAndReturnRest(String line) {
        int tokenStart = PADDING.length() + 1; // skip symbol
        int tokenEnd = line.indexOf(PADDING, tokenStart) - 1; // don't include last ,
        int skip = 2;

        parsedLine.add(line.substring(tokenStart, tokenEnd));

        tokenEnd += PADDING.length();

        return trimLineForNextToken(line, tokenEnd, skip);
    }

    private String parseStringTokenAndReturnRest(String line) {
        int tokenEnd = line.indexOf('\"', 1);
        int skip = 2;

        parsedLine.add(line.substring(1, tokenEnd));
        return trimLineForNextToken(line, tokenEnd, skip);
    }

    private String parseNonStringTokenAndReturnRest(String line) {
        int tokenEnd = line.indexOf(',', 0);
        int skip = 1;

        parsedLine.add(line.substring(0, tokenEnd));

        return trimLineForNextToken(line, tokenEnd, skip);
    }

    private String trimLineForNextToken(String line, int tokenEnd, int skip) {
        final int nextStartPoint = tokenEnd + skip;

        if (nextStartPoint >= line.length()) {
            return "";
        }

        return line.substring(nextStartPoint);
    }

    private boolean isNextPadding(String line) {
        return line.startsWith(PADDING);
    }

    private boolean isNextAString(String line) {
        return line.startsWith("\"");
    }

}
