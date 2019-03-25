package uk.jpmc.trade.report.service;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import uk.jpmc.trade.report.model.TradeInstruction;
import uk.jpmc.trade.report.utils.TradeReportTestConstants;
import uk.jpmc.trade.report.utils.TradeReportTestUtils;

public class TradeReportServiceTest {

	private TradeReportService tradeReportService;

	@Before
	public void setup() {
		tradeReportService = new TradeReportService();
	}

	@Test
	public void testGenerateReportForInstructionsShouldReturnFalseForEmptyOrNullTradInstructions() {
		boolean reportsGenerated = tradeReportService.generateReportForInstructions(null);
		assertFalse(reportsGenerated);

		reportsGenerated = tradeReportService.generateReportForInstructions(new ArrayList<>());
		assertFalse(reportsGenerated);
	}

	@Test
	public void testGenerateReportForInstructionsShouldReturnTrueForValidTradInstructions() {
		List<TradeInstruction> tradeInstructions = TradeReportTestUtils
				.loadTradeInstructionFromJsonFile(TradeReportTestConstants.TEST_TRADE_INSTRUCTIONS_JSON_FILE_NAME);
		boolean reportsGenerated = tradeReportService.generateReportForInstructions(tradeInstructions);
		assertTrue(reportsGenerated);
	}
	
	@Test
	public void testUpdateSettlementDateForMiddleEastWeekend() {
		List<TradeInstruction> tradeInstructions = TradeReportTestUtils
				.loadTradeInstructionFromJsonFile(TradeReportTestConstants.TEST_TRADE_INSTRUCTIONS_JSON_FILE_NAME);
		
		TradeInstruction tradeInstruction = tradeInstructions.get(1);
		LocalDate oldSettlementDate = tradeInstruction.getSettlementDate();
		
		tradeReportService.updateSettlementDate(tradeInstruction);
		LocalDate newSettlementDate = tradeInstruction.getSettlementDate();
		assertTrue(oldSettlementDate.getDayOfMonth() != newSettlementDate.getDayOfMonth());
	}
	
	@Test
	public void testUpdateSettlementDateForOtherThanMiddleEastWeekend() {
		List<TradeInstruction> tradeInstructions = TradeReportTestUtils
				.loadTradeInstructionFromJsonFile(TradeReportTestConstants.TEST_TRADE_INSTRUCTIONS_JSON_FILE_NAME);
		
		TradeInstruction tradeInstruction = tradeInstructions.get(0);
		LocalDate oldSettlementDate = tradeInstruction.getSettlementDate();
		
		tradeReportService.updateSettlementDate(tradeInstruction);
		LocalDate newSettlementDate = tradeInstruction.getSettlementDate();
		assertTrue(oldSettlementDate.getDayOfMonth() != newSettlementDate.getDayOfMonth());
	}
	
	@Test
	public void testUpdateSettlementDateShouldNotUpdateSettlementDateForWeekdays() {
		List<TradeInstruction> tradeInstructions = TradeReportTestUtils
				.loadTradeInstructionFromJsonFile(TradeReportTestConstants.TEST_TRADE_INSTRUCTIONS_JSON_FILE_NAME);
		
		TradeInstruction tradeInstruction = tradeInstructions.get(2);
		LocalDate oldSettlementDate = tradeInstruction.getSettlementDate();
		
		tradeReportService.updateSettlementDate(tradeInstruction);
		LocalDate newSettlementDate = tradeInstruction.getSettlementDate();
		assertTrue(oldSettlementDate.getDayOfMonth() == newSettlementDate.getDayOfMonth());
	}
	
	@Test
	public void testGetReportGeneratorsShouldReturnNonEmptyList() {
		List<IReportGenerator> reportGenerators = tradeReportService.getReportGenerators();
		assertTrue(reportGenerators != null);
		assertTrue(reportGenerators.size() >= 1);
	}

}
