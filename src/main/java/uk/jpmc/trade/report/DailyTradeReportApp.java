package uk.jpmc.trade.report;

import java.net.URL;
import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import uk.jpmc.trade.report.model.TradeInstruction;
import uk.jpmc.trade.report.service.TradeReportService;

public class DailyTradeReportApp {

	private static final String TRADE_INSTRUCTIONS_JSON_FILE_NAME = "trade-instructions.json";

	public static void main(String[] args) {
		DailyTradeReportApp dailyTradeReportApp = new DailyTradeReportApp();
		List<TradeInstruction> tradeInstructions = dailyTradeReportApp
				.loadTradeInstructionFromJsonFile(TRADE_INSTRUCTIONS_JSON_FILE_NAME);
		System.out.println("Successfully loaded trade instructions from json file, now starting trade reports job");
		dailyTradeReportApp.startTradingReportApp(tradeInstructions);
	}

	public boolean startTradingReportApp(List<TradeInstruction> tradeInstructions) {
		System.out.println("Daily trade reports job started");

		// Generates the daily reports for the loaded instructions
		if (tradeInstructions != null && tradeInstructions.size() >= 1) {
			TradeReportService tradeReportService = new TradeReportService();
			tradeReportService.generateReportForInstructions(tradeInstructions);
			System.out.println("Generating daily trade reports job executed successfully");
			return true;
		}
		return false;
	}

	public List<TradeInstruction> loadTradeInstructionFromJsonFile(String jsonFile) {
		List<TradeInstruction> tradeInstructions = null;
		System.out.println("Loading trade instructions from json file: " + jsonFile);
		try {
			// Loads all trade instructions samples from the json file
			URL fileUrl = DailyTradeReportApp.class.getClassLoader().getResource(jsonFile);
			if (fileUrl != null) {
				ObjectMapper mapper = new ObjectMapper();
				tradeInstructions = mapper.readValue(fileUrl, new TypeReference<List<TradeInstruction>>() {
				});
			} else {
				System.err.println("Unable to load trade-instructions.json, file does not exist");
			}
		} catch (Exception e) {
			System.err.println("Could not load trade instructions from json file: " + jsonFile);
			e.printStackTrace();
		}

		return tradeInstructions;
	}
}
