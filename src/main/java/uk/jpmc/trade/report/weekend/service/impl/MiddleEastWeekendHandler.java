package uk.jpmc.trade.report.weekend.service.impl;

import java.time.DayOfWeek;
import java.time.LocalDate;

import uk.jpmc.trade.report.weekend.service.IWeekendHandler;

public class MiddleEastWeekendHandler implements IWeekendHandler {

	private static MiddleEastWeekendHandler instance = null;

	private MiddleEastWeekendHandler() {
		super();
	}

	public static MiddleEastWeekendHandler getInstance() {
		if (instance == null) {
			instance = new MiddleEastWeekendHandler();
		}
		return instance;
	}

	/*
	 * Update settlement day to next working day if falls on weekend
	 * 
	 * @param settlementDate
	 * 
	 * @return LocalDate
	 */
	@Override
	public LocalDate getUpdatedSettlementDate(LocalDate settlementDate) {
		DayOfWeek dayOfWeek = settlementDate.getDayOfWeek();
		if (dayOfWeek == DayOfWeek.FRIDAY || dayOfWeek == DayOfWeek.SATURDAY) {
			System.out.println("MiddleEastWeekendHandler: Day of week falls on weekend, updating to next working day");
			settlementDate = settlementDate.plusDays(1);
			getUpdatedSettlementDate(settlementDate);
		}
		return settlementDate;
	}

}
