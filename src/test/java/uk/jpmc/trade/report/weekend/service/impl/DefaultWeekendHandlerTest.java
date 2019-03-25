package uk.jpmc.trade.report.weekend.service.impl;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;

import org.junit.Before;
import org.junit.Test;

public class DefaultWeekendHandlerTest {

	private DefaultWeekendHandler defaultWeekendHandler;
	
	@Before
	public void setup() {
		defaultWeekendHandler = DefaultWeekendHandler.getInstance();
	}
	
	@Test
	public void testGetInstanceShouldReturnSameInstanceForMultipleCalls() {
		DefaultWeekendHandler instance1 = DefaultWeekendHandler.getInstance();
		assertNotNull(instance1);
		
		DefaultWeekendHandler instance2 = DefaultWeekendHandler.getInstance();
		assertNotNull(instance2);
		
		DefaultWeekendHandler instance3 = DefaultWeekendHandler.getInstance();
		assertNotNull(instance3);
		
		assertTrue(instance1 == instance2);
		assertTrue(instance1 == instance3);
		assertTrue(instance2 == instance3);
		assertTrue(defaultWeekendHandler == instance1);
	}
	
	@Test
	public void testGetUpdatedSettlementDateShouldReturnNextWorkingDaySettlementDate() {
		LocalDate oldSettlementDate = LocalDate.of(2019, 3, 24);
		LocalDate updatedSettlementDate = defaultWeekendHandler.getUpdatedSettlementDate(oldSettlementDate);
		assertNotNull(updatedSettlementDate);
		assertTrue(oldSettlementDate.getDayOfMonth() != updatedSettlementDate.getDayOfMonth());
		assertTrue(updatedSettlementDate.getDayOfMonth() > oldSettlementDate.getDayOfMonth());
	}
	
	@Test
	public void testGetUpdatedSettlementDateShouldReturnSameSettlementDate() {
		LocalDate oldSettlementDate = LocalDate.of(2019, 3, 25);
		LocalDate updatedSettlementDate = defaultWeekendHandler.getUpdatedSettlementDate(oldSettlementDate);
		assertNotNull(updatedSettlementDate);
		assertTrue(oldSettlementDate.getDayOfMonth() == updatedSettlementDate.getDayOfMonth());
	}

}
