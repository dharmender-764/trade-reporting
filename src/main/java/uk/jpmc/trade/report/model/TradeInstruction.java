package uk.jpmc.trade.report.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Currency;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import uk.jpmc.trade.report.utils.LocalDateDeserializer;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TradeInstruction {

	private String entity;
	private TradingType tradeType;
	private BigDecimal agreedFx;
	private Currency currency;

	@JsonDeserialize(using = LocalDateDeserializer.class)
	private LocalDate instructionDate;

	@JsonDeserialize(using = LocalDateDeserializer.class)
	private LocalDate settlementDate;
	private int units;
	private BigDecimal unitPrice;
	
	public TradeInstruction() {
		
	}
	
	public TradeInstruction(String entity, TradingType tradeType, BigDecimal agreedFx, Currency currency,
			LocalDate instructionDate, LocalDate settlementDate, int units, BigDecimal unitPrice) {
		this.entity = entity;
		this.tradeType = tradeType;
		this.agreedFx = agreedFx;
		this.currency = currency;
		this.instructionDate = instructionDate;
		this.settlementDate = settlementDate;
		this.units = units;
		this.unitPrice = unitPrice;
	}

	public String getEntity() {
		return entity;
	}

	public void setEntity(String entity) {
		this.entity = entity;
	}

	public TradingType getTradeType() {
		return tradeType;
	}

	public void setTradeType(TradingType tradeType) {
		this.tradeType = tradeType;
	}

	public BigDecimal getAgreedFx() {
		return agreedFx;
	}

	public void setAgreedFx(BigDecimal agreedFx) {
		this.agreedFx = agreedFx;
	}

	public Currency getCurrency() {
		return currency;
	}

	public void setCurrency(Currency currency) {
		this.currency = currency;
	}

	public LocalDate getInstructionDate() {
		return instructionDate;
	}

	public void setInstructionDate(LocalDate instructionDate) {
		this.instructionDate = instructionDate;
	}

	public LocalDate getSettlementDate() {
		return settlementDate;
	}

	public void setSettlementDate(LocalDate settlementDate) {
		this.settlementDate = settlementDate;
	}

	public int getUnits() {
		return units;
	}

	public void setUnits(int units) {
		this.units = units;
	}

	public BigDecimal getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(BigDecimal unitPrice) {
		this.unitPrice = unitPrice;
	}
	
	public BigDecimal getTradeAmountInUSD() {
		return unitPrice.multiply(BigDecimal.valueOf(units)).multiply(agreedFx);
	}

	public String toString() {
		return "TradeInstruction: { entity: " + entity + ", tradeType: " + tradeType + ", agreedFx: " + agreedFx
				+ ", currency: " + currency + ", instructionDate: " + instructionDate + ", settlementDate: "
				+ settlementDate + ", units: " + units + ", unitPrice: " + unitPrice + "}";
	}

}
