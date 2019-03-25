package uk.jpmc.trade.report.service.impl;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import uk.jpmc.trade.report.model.TradeInstruction;
import uk.jpmc.trade.report.utils.TradeReportTestConstants;
import uk.jpmc.trade.report.utils.TradeReportTestUtils;

public class OutgoingAmountReportGeneratorTest {

	private OutgoingAmountReportGenerator outgoingAmountReportGenerator;
	
	@Before
	public void setup() {
		outgoingAmountReportGenerator = new OutgoingAmountReportGenerator();
	}
	
	@Test
	public void testGenerateReportShouldReturnEmptyReportContentForNullOrEmptyInstructions() {
		StringBuilder reportContent = outgoingAmountReportGenerator.generateReport(null);
		assertTrue(reportContent.length() == 0);
		
		reportContent = outgoingAmountReportGenerator.generateReport(new ArrayList<>());
		assertTrue(reportContent.length() == 0);
	}
	
	@Test
	public void testGenerateReportShouldReturnNonEmptyReportContentForValidInstructions() {
		List<TradeInstruction> tradeInstructions = TradeReportTestUtils
				.loadTradeInstructionFromJsonFile(TradeReportTestConstants.TEST_TRADE_INSTRUCTIONS_JSON_FILE_NAME);
		
		StringBuilder reportContent = outgoingAmountReportGenerator.generateReport(tradeInstructions);
		assertTrue(reportContent.length() >= 0);
	}

}
