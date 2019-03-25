package uk.jpmc.trade.report.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum TradingType {

	@JsonProperty("B")
	BUY,

	@JsonProperty("S")
	SELL;

}