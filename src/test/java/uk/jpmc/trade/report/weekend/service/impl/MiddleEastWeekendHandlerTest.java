package uk.jpmc.trade.report.weekend.service.impl;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;

import org.junit.Before;
import org.junit.Test;

public class MiddleEastWeekendHandlerTest {

	private MiddleEastWeekendHandler middleEastWeekendHandler;

	@Before
	public void setup() {
		middleEastWeekendHandler = MiddleEastWeekendHandler.getInstance();
	}

	@Test
	public void testGetInstanceShouldReturnSameInstanceForMultipleCalls() {
		MiddleEastWeekendHandler instance1 = MiddleEastWeekendHandler.getInstance();
		assertNotNull(instance1);

		MiddleEastWeekendHandler instance2 = MiddleEastWeekendHandler.getInstance();
		assertNotNull(instance2);

		MiddleEastWeekendHandler instance3 = MiddleEastWeekendHandler.getInstance();
		assertNotNull(instance3);

		assertTrue(instance1 == instance2);
		assertTrue(instance1 == instance3);
		assertTrue(instance2 == instance3);
		assertTrue(middleEastWeekendHandler == instance1);
	}

	@Test
	public void testGetUpdatedSettlementDateShouldReturnNextWorkingDaySettlementDate() {
		LocalDate oldSettlementDate = LocalDate.of(2019, 3, 22);
		LocalDate updatedSettlementDate = middleEastWeekendHandler.getUpdatedSettlementDate(oldSettlementDate);
		assertNotNull(updatedSettlementDate);
		assertTrue(oldSettlementDate.getDayOfMonth() != updatedSettlementDate.getDayOfMonth());
		assertTrue(updatedSettlementDate.getDayOfMonth() > oldSettlementDate.getDayOfMonth());
	}

	@Test
	public void testGetUpdatedSettlementDateShouldReturnSameSettlementDate() {
		LocalDate oldSettlementDate = LocalDate.of(2019, 3, 24);
		LocalDate updatedSettlementDate = middleEastWeekendHandler.getUpdatedSettlementDate(oldSettlementDate);
		assertNotNull(updatedSettlementDate);
		assertTrue(oldSettlementDate.getDayOfMonth() == updatedSettlementDate.getDayOfMonth());
	}

}
