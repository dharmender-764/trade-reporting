package uk.jpmc.trade.report.utils;

import java.util.Comparator;

import uk.jpmc.trade.report.model.TradeInstruction;

public class SettlementAmountComparator implements Comparator<TradeInstruction> {

	@Override
	public int compare(TradeInstruction ti1, TradeInstruction ti2) {
		return ti1.getTradeAmountInUSD().subtract(ti2.getTradeAmountInUSD()).intValue();
	}

}