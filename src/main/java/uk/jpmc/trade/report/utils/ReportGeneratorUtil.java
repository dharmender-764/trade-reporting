package uk.jpmc.trade.report.utils;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.mapping;
import static java.util.stream.Collectors.reducing;
import static java.util.stream.Collectors.toSet;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import uk.jpmc.trade.report.model.RankedTradeInstruction;
import uk.jpmc.trade.report.model.TradeInstruction;

public class ReportGeneratorUtil {

	/*
	 * Calculates daily trade amount
	 * 
	 * @Return Map<LocalDate, BigDecimal>
	 * 
	 * @Param tradeInstructions, predicate
	 */
	public static Map<LocalDate, BigDecimal> calculateDailyAmount(List<TradeInstruction> tradeInstructions,
			Predicate<TradeInstruction> predicate) {
		return tradeInstructions.stream().filter(predicate).collect(groupingBy(TradeInstruction::getSettlementDate,
				mapping(TradeInstruction::getTradeAmountInUSD, reducing(BigDecimal.ZERO, BigDecimal::add))));
	}

	/*
	 * Group the entities by date and rank them by Trade amount descending
	 * 
	 * @Return rtis
	 * 
	 * @Param tradeInstructions, predicate
	 */
	public static Map<LocalDate, LinkedList<RankedTradeInstruction>> rankTradeInstructionEntitiesByAmount(
			List<TradeInstruction> tradeInstructions, Predicate<TradeInstruction> predicate) {

		final Map<LocalDate, LinkedList<RankedTradeInstruction>> rtis = new HashMap<>();
		tradeInstructions.stream().filter(predicate).collect(groupingBy(TradeInstruction::getSettlementDate, toSet()))
				.forEach((date, dailyInstructionSet) -> {
					final AtomicInteger rank = new AtomicInteger(1);
					final LinkedList<RankedTradeInstruction> ranks = dailyInstructionSet.stream()
							.sorted((a, b) -> b.getTradeAmountInUSD().compareTo(a.getTradeAmountInUSD()))
							.map(instruction -> new RankedTradeInstruction(instruction.getEntity(),
									rank.getAndIncrement(), date, instruction.getTradeAmountInUSD()))
							.collect(Collectors.toCollection(LinkedList::new));
					rtis.put(date, ranks);
				});
		return rtis;
	}
}
