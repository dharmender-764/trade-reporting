package uk.jpmc.trade.report.utils;

import java.net.URL;
import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import uk.jpmc.trade.report.DailyTradeReportApp;
import uk.jpmc.trade.report.model.TradeInstruction;

public class TradeReportTestUtils {

	public static List<TradeInstruction> loadTradeInstructionFromJsonFile(String jsonFile) {
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
