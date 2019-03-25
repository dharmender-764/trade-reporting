package uk.jpmc.trade.report.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import uk.jpmc.trade.report.model.TradeInstruction;
import uk.jpmc.trade.report.service.impl.IncomingAmountReportGenerator;
import uk.jpmc.trade.report.service.impl.IncomingRankingReportGenerator;
import uk.jpmc.trade.report.service.impl.OutgoingAmountReportGenerator;
import uk.jpmc.trade.report.service.impl.OutgoingRankingReportGenerator;
import uk.jpmc.trade.report.weekend.service.IWeekendHandler;
import uk.jpmc.trade.report.weekend.service.WeekendFactory;

public class TradeReportService {

	/*
	 * Generates and print all reports
	 * 
	 * @Param tradeInstructions
	 * 
	 * @Return void
	 */
	public boolean generateReportForInstructions(List<TradeInstruction> tradeInstructions) {
		if (tradeInstructions != null && tradeInstructions.size() >= 1) {
			tradeInstructions.forEach(this::updateSettlementDate);
	
			StringBuilder reportContent = new StringBuilder();
	
			List<IReportGenerator> reportGenerators = getReportGenerators();
			for (IReportGenerator reportGenerator : reportGenerators) {
				reportContent.append(reportGenerator.generateReport(tradeInstructions));
			}
	
			System.out.println(reportContent);
			return true;
		}
		return false;
	}

	/*
	 * Update settlement day to next working day if falls on weekend based on
	 * currency
	 * 
	 * @Param tradeTradeInstruction
	 * 
	 * @Return void
	 */
	public void updateSettlementDate(TradeInstruction tradeInstruction) {

		IWeekendHandler weekendHandler = WeekendFactory.getWeekendHandlerByCurrency(tradeInstruction.getCurrency());
		LocalDate updateSettlementDate = weekendHandler.getUpdatedSettlementDate(tradeInstruction.getSettlementDate());
		tradeInstruction.setSettlementDate(updateSettlementDate);
	}

	/*
	 * Return the list of all daily report generators
	 * 
	 * @Return List<IReportGenerator>
	 * 
	 */
	public List<IReportGenerator> getReportGenerators() {
		List<IReportGenerator> reportGenerators = new ArrayList<>();

		reportGenerators.add(new IncomingAmountReportGenerator());
		reportGenerators.add(new OutgoingAmountReportGenerator());
		reportGenerators.add(new IncomingRankingReportGenerator());
		reportGenerators.add(new OutgoingRankingReportGenerator());

		return reportGenerators;
	}

}
