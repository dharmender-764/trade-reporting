package uk.jpmc.trade.report;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import uk.jpmc.trade.report.model.TradeInstruction;
import uk.jpmc.trade.report.utils.TradeReportTestConstants;
import uk.jpmc.trade.report.utils.TradeReportTestUtils;

public class DailyTradeReportAppTest {

	private DailyTradeReportApp dailyTradeReportApp;

	@Before
	public void setUp() {
		dailyTradeReportApp = new DailyTradeReportApp();
	}

	@Test
	public void testStartTradingReportAppShouldReturnFalseWhenInstructionsNullOrEmpty() {
		boolean tradingReportAppStarted = dailyTradeReportApp.startTradingReportApp(null);
		assertEquals(false, tradingReportAppStarted);

		tradingReportAppStarted = dailyTradeReportApp.startTradingReportApp(new ArrayList<>());
		assertEquals(false, tradingReportAppStarted);
	}

	@Test
	public void testStartTradingReportAppShouldReturnTrueWhenInstructionsNotNullOrEmpty() {
		List<TradeInstruction> tradeInstructions = TradeReportTestUtils
				.loadTradeInstructionFromJsonFile(TradeReportTestConstants.TEST_TRADE_INSTRUCTIONS_JSON_FILE_NAME);

		boolean tradingReportAppStarted = dailyTradeReportApp.startTradingReportApp(tradeInstructions);
		assertEquals(true, tradingReportAppStarted);
	}

	@Test
	public void testLoadTradeInstructionFromJsonFileShouldReturnNullWhenJsonFileNotExist() {
		List<TradeInstruction> tradeInstructions = dailyTradeReportApp.loadTradeInstructionFromJsonFile(null);
		assertNull(tradeInstructions);
	}

	@Test
	public void testLoadTradeInstructionFromJsonFileShouldReturnEmptyInstructionsForInvalidJsonContent() {
		List<TradeInstruction> tradeInstructions = dailyTradeReportApp
				.loadTradeInstructionFromJsonFile(TradeReportTestConstants.TEST_TRADE_INSTRUCTIONS_JSON_2_FILE_NAME);
		assertTrue(tradeInstructions.size() <= 0);
	}

	@Test
	public void testLoadTradeInstructionFromJsonFileShouldReturnInstructionForCorrectJsonFile() {
		List<TradeInstruction> tradeInstructions = dailyTradeReportApp
				.loadTradeInstructionFromJsonFile(TradeReportTestConstants.TEST_TRADE_INSTRUCTIONS_JSON_FILE_NAME);
		assertNotNull(tradeInstructions);
		assertTrue(tradeInstructions.size() >= 1);
	}

}
