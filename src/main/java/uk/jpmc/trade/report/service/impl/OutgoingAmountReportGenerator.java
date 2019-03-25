package uk.jpmc.trade.report.service.impl;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import uk.jpmc.trade.report.constants.TradeReportConstrants;
import uk.jpmc.trade.report.model.Predicates;
import uk.jpmc.trade.report.model.TradeInstruction;
import uk.jpmc.trade.report.service.IReportGenerator;
import uk.jpmc.trade.report.utils.ReportGeneratorUtil;

public class OutgoingAmountReportGenerator implements IReportGenerator {

	@Override
	public StringBuilder generateReport(List<TradeInstruction> tradeInstructions) {
		StringBuilder reportContent = new StringBuilder();
		if (tradeInstructions != null && tradeInstructions.size() >= 1) {
			reportContent.append(TradeReportConstrants.REPORT_SEPERATOR + TradeReportConstrants.NEWLINE);
			reportContent.append("    Daily Outgoing AMOUNT SETTLEMENT Report" + TradeReportConstrants.NEWLINE);
			reportContent.append(TradeReportConstrants.REPORT_SEPERATOR + TradeReportConstrants.NEWLINE);
			reportContent.append(TradeReportConstrants.REPORT_HEADER + TradeReportConstrants.NEWLINE);
	
			Map<LocalDate, BigDecimal> reportData = ReportGeneratorUtil.calculateDailyAmount(tradeInstructions, Predicates.OUTGOING_PREDICATE);
			reportData.forEach((k, v) -> reportContent.append(k).append("       |      ").append(v)
					.append(TradeReportConstrants.NEWLINE));
		}
		return reportContent;
	}
}
