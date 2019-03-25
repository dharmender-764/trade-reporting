package uk.jpmc.trade.report.service;

import java.util.List;

import uk.jpmc.trade.report.model.TradeInstruction;

@FunctionalInterface
public interface IReportGenerator {

	/*
	 * Generates the daily Trade report
	 */
	public StringBuilder generateReport(List<TradeInstruction> tradeInstructions);
	
}
