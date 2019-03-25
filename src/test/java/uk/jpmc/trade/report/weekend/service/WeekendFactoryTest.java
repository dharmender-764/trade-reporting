package uk.jpmc.trade.report.weekend.service;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Currency;

import org.junit.Test;

import uk.jpmc.trade.report.weekend.service.impl.DefaultWeekendHandler;
import uk.jpmc.trade.report.weekend.service.impl.MiddleEastWeekendHandler;

public class WeekendFactoryTest {

	@Test
	public void testGetWeekendHandlerByCurrencyShouldReturnDefaultWeekendHandlerForNullCurrency() {
		IWeekendHandler weekendHandler = WeekendFactory.getWeekendHandlerByCurrency(null);
		assertNotNull(weekendHandler);
		assertTrue(weekendHandler instanceof DefaultWeekendHandler);
	}
	
	@Test
	public void testGetWeekendHandlerByCurrencyShouldReturnDefaultWeekendHandlerForNonMiddleEastCurrency() {
		IWeekendHandler weekendHandler = WeekendFactory.getWeekendHandlerByCurrency(Currency.getInstance("GBP"));
		assertNotNull(weekendHandler);
		assertTrue(weekendHandler instanceof DefaultWeekendHandler);
		
		weekendHandler = WeekendFactory.getWeekendHandlerByCurrency(Currency.getInstance("USD"));
		assertNotNull(weekendHandler);
		assertTrue(weekendHandler instanceof DefaultWeekendHandler);
	}
	
	@Test
	public void testGetWeekendHandlerByCurrencyShouldReturnMiddleEastWeekendHandlerForMiddleEastCurrency() {
		IWeekendHandler weekendHandler = WeekendFactory.getWeekendHandlerByCurrency(Currency.getInstance("AED"));
		assertNotNull(weekendHandler);
		assertTrue(weekendHandler instanceof MiddleEastWeekendHandler);
	}

}
