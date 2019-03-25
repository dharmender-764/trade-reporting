package uk.jpmc.trade.report.model;

import java.util.function.Predicate;

public class Predicates {

	public final static Predicate<TradeInstruction> OUTGOING_PREDICATE = tradeInstruction -> tradeInstruction
			.getTradeType().equals(TradingType.BUY);

	public final static Predicate<TradeInstruction> INCOMING_PREDICATE = tradeInstruction -> tradeInstruction
			.getTradeType().equals(TradingType.SELL);

}
