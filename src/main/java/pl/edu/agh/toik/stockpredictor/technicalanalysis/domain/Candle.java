package pl.edu.agh.toik.stockpredictor.technicalanalysis.domain;

import pl.edu.agh.toik.stockpredictor.technicalanalysis.tools.CandleTools;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Class represents single candle stick, based on Japanese Candlestick Charting Technique. Set of this candles form
 * financial chart used to describe price movements.
 *
 * Created by Krzysztof Kicinger on 2015-05-10.
 */
public class Candle {

    /**
     * Company that candle belongs to
     */
    private ListedCompany listedCompany;
    /**
     * Maximal stock price
     */
    private BigDecimal maxPrice;
    /**
     * Minimal stock price
     */
    private BigDecimal minPrice;
    /**
     * Opening stock price
     */
    private BigDecimal openingPrice;
    /**
     * Closing stock price
     */
    private BigDecimal closingPrice;
    /**
     * Date (accurate to day) that candle represents data for
     */
    private Date day;
    /**
     * Candle color (BLACK/WHITE/NOT_DEFINED)
     */
    private CandleColor color;
    /**
     * Candle type
     */
    private CandleType type;

    public Candle() {
    }

    public Candle(ListedCompany listedCompany, BigDecimal maxPrice, BigDecimal minPrice, BigDecimal openingPrice, BigDecimal closingPrice, Date day) {
        this.listedCompany = listedCompany;
        this.maxPrice = maxPrice;
        this.minPrice = minPrice;
        this.openingPrice = openingPrice;
        this.closingPrice = closingPrice;
        this.day = day;
        this.color = CandleTools.getCandleColor(this);
        this.type = CandleTools.getCandleType(this);
    }

    public ListedCompany getListedCompany() {
        return listedCompany;
    }

    public void setListedCompany(ListedCompany listedCompany) {
        this.listedCompany = listedCompany;
    }

    public BigDecimal getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(BigDecimal maxPrice) {
        this.maxPrice = maxPrice;
    }

    public BigDecimal getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(BigDecimal minPrice) {
        this.minPrice = minPrice;
    }

    public BigDecimal getOpeningPrice() {
        return openingPrice;
    }

    public void setOpeningPrice(BigDecimal openingPrice) {
        this.openingPrice = openingPrice;
    }

    public BigDecimal getClosingPrice() {
        return closingPrice;
    }

    public void setClosingPrice(BigDecimal closingPrice) {
        this.closingPrice = closingPrice;
    }

    public Date getDay() {
        return day;
    }

    public void setDay(Date day) {
        this.day = day;
    }

    public CandleColor getColor() {
        return color;
    }

    public void setColor(CandleColor color) {
        this.color = color;
    }

    public CandleType getType() {
        return type;
    }
    
    public Date getDate(){
        return day;
    }

    public void setType(CandleType type) {
        this.type = type;
    }
}
