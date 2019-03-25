package uk.jpmc.trade.report.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

public class RankedTradeInstruction {

	private final String entity;
	private final int rank;
	private final LocalDate date;
	private final BigDecimal amountInUSD;

	public RankedTradeInstruction(String entity, int rank, LocalDate date, BigDecimal amountInUSD) {
		this.entity = entity;
		this.rank = rank;
		this.date = date;
		this.amountInUSD = amountInUSD;
	}

	public String getEntity() {

		return entity;
	}

	public int getRank() {

		return rank;
	}

	public LocalDate getDate() {

		return date;
	}

	public BigDecimal getAmountInUSD() {
		return amountInUSD;
	}

	@Override
	public boolean equals(Object o) {

		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		
		RankedTradeInstruction that = (RankedTradeInstruction) o;
		return getRank() == that.getRank() && Objects.equals(getEntity(), that.getEntity())
				&& Objects.equals(getDate(), that.getDate());
	}

	@Override
	public int hashCode() {
		return Objects.hash(getEntity(), getRank(), getDate());
	}
}
