package uk.jpmc.trade.report.weekend.service.impl;

import java.time.DayOfWeek;
import java.time.LocalDate;

import uk.jpmc.trade.report.weekend.service.IWeekendHandler;

public class DefaultWeekendHandler implements IWeekendHandler {

	private static DefaultWeekendHandler instance = null;

	private DefaultWeekendHandler() {
		super();
	}

	public static DefaultWeekendHandler getInstance() {
		if (instance == null) {
			instance = new DefaultWeekendHandler();
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
		if (dayOfWeek == DayOfWeek.SATURDAY || dayOfWeek == DayOfWeek.SUNDAY) {
			System.out.println("DefaultWeekendHandler: Day of week falls on weekend, updating to next working day");
			settlementDate = settlementDate.plusDays(1);
			getUpdatedSettlementDate(settlementDate);
		}
		return settlementDate;
	}
}
