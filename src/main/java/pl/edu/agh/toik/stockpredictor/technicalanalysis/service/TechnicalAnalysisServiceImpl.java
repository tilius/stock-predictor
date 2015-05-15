package pl.edu.agh.toik.stockpredictor.technicalanalysis.service;

import pl.edu.agh.toik.stockpredictor.technicalanalysis.chart.CandlestickChart;
import pl.edu.agh.toik.stockpredictor.technicalanalysis.domain.Candle;
import pl.edu.agh.toik.stockpredictor.technicalanalysis.domain.ListedCompany;
import pl.edu.agh.toik.stockpredictor.technicalanalysis.serializer.StockQuote;
import pl.edu.agh.toik.stockpredictor.technicalanalysis.tools.CandleTools;
import pl.edu.agh.toik.stockpredictor.technicalanalysis.tools.DateTools;
import pl.edu.agh.toik.stockpredictor.technicalanalysis.tools.FormationTools;

import java.math.BigDecimal;
import java.util.*;

/**
 * Created by Krzysztof Kicinger on 2015-05-14.
 */
public class TechnicalAnalysisServiceImpl implements ITechnicalAnalysisService {

    private static Comparator<StockQuote> SHARE_DATA_COMPARATOR = new Comparator<StockQuote>() {
        @Override
        public int compare(StockQuote o1, StockQuote o2) {
            return o1.getDate().compareTo(o2.getDate());
        }
    };

    private static Comparator<Candle> CANDLE_COMPARATOR = new Comparator<Candle>() {
        @Override
        public int compare(Candle o1, Candle o2) {
            return o1.getDate().compareTo(o2.getDate());
        }
    };

    @Override
    public List<Candle> getCandles(List<StockQuote> shareData) {
        createHourAccurateDates(shareData);
        Collections.sort(shareData, SHARE_DATA_COMPARATOR);
        Map<Date, List<StockQuote>> dayToShareDataMap = createDayToListOfShareDataMap(shareData);
        return createCandles(dayToShareDataMap);
    }

    @Override
    public CandlestickChart createCandlestickChart(ListedCompany listedCompany, List<Candle> candles) {
        Collections.sort(candles, CANDLE_COMPARATOR);
        CandlestickChart candlestickChart = new CandlestickChart();
        candlestickChart.setListedCompany(listedCompany);
        candlestickChart.setCandles(candles);
        candlestickChart.setStartDate(candles.get(0).getDate());
        candlestickChart.setEndDate(candles.get(candles.size() - 1).getDate());
        candlestickChart.setFormations(FormationTools.getFormations(candles));
        return candlestickChart;
    }

    private void createHourAccurateDates(List<StockQuote> shareData) {
        for(StockQuote sd : shareData) {
            sd.setDate(DateTools.getHourAccurateDate(sd.getDate()));
        }
    }

    private Map<Date, List<StockQuote>> createDayToListOfShareDataMap(List<StockQuote> shareData) {
        Map<Date, List<StockQuote>> dayToShareDataMap = new HashMap<Date, List<StockQuote>>();
        for(StockQuote sd : shareData) {
            Date dayAccurateDay = DateTools.getDayAccurateDate(sd.getDate());
            if(dayToShareDataMap.containsKey(dayAccurateDay)) {
                dayToShareDataMap.get(dayAccurateDay).add(sd);
            } else {
                dayToShareDataMap.put(dayAccurateDay, Arrays.asList(sd));
            }
        }
        return dayToShareDataMap;
    }

    private List<Candle> createCandles(Map<Date, List<StockQuote>> dayToShareDataMap) {
        List<Candle> candles = new ArrayList<Candle>();
        for(Date date : dayToShareDataMap.keySet()) {
            List<StockQuote> shareDataList = dayToShareDataMap.get(date);
            Candle candle = new Candle();
            candle.setOpeningPrice(shareDataList.get(0).getValue());
            candle.setClosingPrice(shareDataList.get(shareDataList.size() - 1).getValue());
            candle.setMaxPrice(BigDecimal.ZERO);
            candle.setMinPrice(BigDecimal.ZERO);
            for(StockQuote sd : dayToShareDataMap.get(date)) {
                if(sd.getValue().compareTo(candle.getMaxPrice()) > 0) {
                    candle.setMaxPrice(sd.getValue());
                }
                if(sd.getValue().compareTo(candle.getMinPrice()) < 0) {
                    candle.setMinPrice(sd.getValue());
                }
            }
            candle.setDate(date);
            candle.setColor(CandleTools.getCandleColor(candle));
            candle.setType(CandleTools.getCandleType(candle));
            candles.add(candle);
        }
        return candles;
    }

}