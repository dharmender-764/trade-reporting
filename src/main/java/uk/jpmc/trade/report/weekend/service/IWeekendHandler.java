package uk.jpmc.trade.report.weekend.service;

import java.time.LocalDate;

@FunctionalInterface
public interface IWeekendHandler {

	public LocalDate getUpdatedSettlementDate(LocalDate settlementDate);
	
}
