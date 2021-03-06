/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.edu.agh.toik.stockpredictor.core;

import java.util.Date;
import java.util.List;
import org.springframework.transaction.annotation.Transactional;
import pl.edu.agh.toik.stockpredictor.technicalanalysis.domain.ListedCompany;
import pl.edu.agh.toik.stockpredictor.technicalanalysis.serializer.StockQuote;

/**
 *
 * @author Dariusz Hudziak
 */
@Transactional
public interface IStockQuoteService {
    
   List<ListedCompany> getCompanies();
   List<StockQuote> getStockQuotes(ListedCompany listedCompany, Date from, Date to);
   List<StockQuote> getStockQuotes(ListedCompany listedCompany, int n); 
   void storeStockQuotes(List<StockQuote> list);
}
