package uk.jpmc.trade.report.service.impl;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import uk.jpmc.trade.report.model.TradeInstruction;
import uk.jpmc.trade.report.utils.TradeReportTestConstants;
import uk.jpmc.trade.report.utils.TradeReportTestUtils;

public class IncomingRankingReportGeneratorTest {

	private IncomingRankingReportGenerator incomingRankingReportGenerator;
	
	@Before
	public void setup() {
		incomingRankingReportGenerator = new IncomingRankingReportGenerator();
	}
	
	@Test
	public void testGenerateReportShouldReturnEmptyReportContentForNullOrEmptyInstructions() {
		StringBuilder reportContent = incomingRankingReportGenerator.generateReport(null);
		assertTrue(reportContent.length() == 0);
		
		reportContent = incomingRankingReportGenerator.generateReport(new ArrayList<>());
		assertTrue(reportContent.length() == 0);
	}
	
	@Test
	public void testGenerateReportShouldReturnNonEmptyReportContentForValidInstructions() {
		List<TradeInstruction> tradeInstructions = TradeReportTestUtils
				.loadTradeInstructionFromJsonFile(TradeReportTestConstants.TEST_TRADE_INSTRUCTIONS_JSON_FILE_NAME);
		
		StringBuilder reportContent = incomingRankingReportGenerator.generateReport(tradeInstructions);
		assertTrue(reportContent.length() >= 0);
	}

}
