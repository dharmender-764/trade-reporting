package uk.jpmc.trade.report.service.impl;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import uk.jpmc.trade.report.constants.TradeReportConstrants;
import uk.jpmc.trade.report.model.Predicates;
import uk.jpmc.trade.report.model.RankedTradeInstruction;
import uk.jpmc.trade.report.model.TradeInstruction;
import uk.jpmc.trade.report.service.IReportGenerator;
import uk.jpmc.trade.report.utils.ReportGeneratorUtil;

public class IncomingRankingReportGenerator implements IReportGenerator {

	public StringBuilder generateReport(List<TradeInstruction> tradeInstructions) {
		StringBuilder reportContent = new StringBuilder();
		if (tradeInstructions != null && tradeInstructions.size() >= 1) {
			reportContent.append(TradeReportConstrants.REPORT_SEPERATOR + TradeReportConstrants.NEWLINE);
			reportContent.append("    Daily Incoming Ranking Report" + TradeReportConstrants.NEWLINE);
			reportContent.append(TradeReportConstrants.REPORT_SEPERATOR + TradeReportConstrants.NEWLINE);
			reportContent.append(TradeReportConstrants.DATE_RANK_ENTITY_REPORT_HEADER + TradeReportConstrants.NEWLINE);
	
			Map<LocalDate, LinkedList<RankedTradeInstruction>> rankedEntities = ReportGeneratorUtil
					.rankTradeInstructionEntitiesByAmount(tradeInstructions, Predicates.INCOMING_PREDICATE);
	
			for (LocalDate date : ((Set<LocalDate>) rankedEntities.keySet())) {
				for (RankedTradeInstruction rank : ((LinkedList<RankedTradeInstruction>) rankedEntities.get(date))) {
					reportContent.append(date).append("   |      ");
					reportContent.append(rank.getRank()).append("     |    ");
					reportContent.append(rank.getEntity()).append(TradeReportConstrants.NEWLINE);
				}
			}
		}
		return reportContent;
	}

}
