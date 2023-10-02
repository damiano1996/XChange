package org.knowm.xchange.coinbasepro;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import java.io.IOException;
import java.util.Map;
import org.knowm.xchange.coinbasepro.dto.CoinbaseProException;
import org.knowm.xchange.coinbasepro.dto.CoinbaseProTrades;
import org.knowm.xchange.coinbasepro.dto.marketdata.CoinbaseProCandle;
import org.knowm.xchange.coinbasepro.dto.marketdata.CoinbaseProCurrency;
import org.knowm.xchange.coinbasepro.dto.marketdata.CoinbaseProProduct;
import org.knowm.xchange.coinbasepro.dto.marketdata.CoinbaseProProductBook;
import org.knowm.xchange.coinbasepro.dto.marketdata.CoinbaseProProductStats;
import org.knowm.xchange.coinbasepro.dto.marketdata.CoinbaseProProductTicker;
import org.knowm.xchange.coinbasepro.dto.marketdata.CoinbaseProStats;
import org.knowm.xchange.coinbasepro.dto.marketdata.CoinbaseProTrade;

@Path("/")
@Produces(MediaType.APPLICATION_JSON)
public interface CoinbasePro {

  @GET
  @Path("currencies")
  CoinbaseProCurrency[] getCurrencies() throws CoinbaseProException, IOException;

  @GET
  @Path("products")
  CoinbaseProProduct[] getProducts() throws CoinbaseProException, IOException;

  @GET
  @Path("products/{baseCurrency}-{targetCurrency}/ticker")
  CoinbaseProProductTicker getProductTicker(
      @PathParam("baseCurrency") String baseCurrency,
      @PathParam("targetCurrency") String targetCurrency)
      throws CoinbaseProException, IOException;

  @GET
  @Path("products/{baseCurrency}-{targetCurrency}/stats")
  CoinbaseProProductStats getProductStats(
      @PathParam("baseCurrency") String baseCurrency,
      @PathParam("targetCurrency") String targetCurrency)
      throws CoinbaseProException, IOException;

  @GET
  @Path("products/stats")
  Map<String, CoinbaseProStats> getStats() throws CoinbaseProException, IOException;

  @GET
  @Path("products/{baseCurrency}-{targetCurrency}/book?level={level}")
  CoinbaseProProductBook getProductOrderBook(
      @PathParam("baseCurrency") String baseCurrency,
      @PathParam("targetCurrency") String targetCurrency,
      @PathParam("level") String level)
      throws CoinbaseProException, IOException;

  @GET
  @Path("products/{baseCurrency}-{targetCurrency}/trades")
  CoinbaseProTrade[] getTrades(
      @PathParam("baseCurrency") String baseCurrency,
      @PathParam("targetCurrency") String targetCurrency)
      throws CoinbaseProException, IOException;

  @GET
  @Path("products/{baseCurrency}-{targetCurrency}/trades")
  CoinbaseProTrades getTradesPageable(
      @PathParam("baseCurrency") String baseCurrency,
      @PathParam("targetCurrency") String targetCurrency,
      @QueryParam("after") Long after,
      @QueryParam("limit") Integer limit)
      throws CoinbaseProException, IOException;

  @GET
  @Path("products/{baseCurrency}-{targetCurrency}/candles")
  CoinbaseProCandle[] getHistoricalCandles(
      @PathParam("baseCurrency") String baseCurrency,
      @PathParam("targetCurrency") String targetCurrency,
      @QueryParam("start") String start,
      @QueryParam("end") String end,
      @QueryParam("granularity") String granularity)
      throws CoinbaseProException, IOException;
}
