package uk.jpmc.trade.report.weekend.service;

import java.util.Currency;

import uk.jpmc.trade.report.weekend.service.impl.DefaultWeekendHandler;
import uk.jpmc.trade.report.weekend.service.impl.MiddleEastWeekendHandler;

public class WeekendFactory {

	public static final String AED_ISO_CODE = "AED";
	public static final String SAR_ISO_CODE = "SAR";

	/*
	 * return IWeekendHandler implementation object by currency
	 * 
	 * @param currency
	 * 
	 * @return IWeekendHandler
	 */
	public static IWeekendHandler getWeekendHandlerByCurrency(Currency currency) {
		
		if (currency == null) {
			return DefaultWeekendHandler.getInstance();
		}
		
		switch (currency.getCurrencyCode()) {
			case AED_ISO_CODE:
			case SAR_ISO_CODE:
				return MiddleEastWeekendHandler.getInstance();
			default:
				return DefaultWeekendHandler.getInstance();
		}
	}
}
