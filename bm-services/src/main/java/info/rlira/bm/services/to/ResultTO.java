package info.rlira.bm.services.to;

import java.io.Serializable;
import java.math.BigDecimal;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * {@link }
 * 
 * @author Raquel Lira (raquel.lira@gmail.com) - 27.06.2015
 * 
 */
public class ResultTO implements Serializable {

	private static final long serialVersionUID = -3497165337707016783L;

	private BigDecimal unitValue;
	private BigDecimal calculatedValue;

	public ResultTO() {
	}

	public ResultTO(BigDecimal unitValue, BigDecimal calculatedValue) {
		this.unitValue = unitValue;
		this.calculatedValue = calculatedValue;
	}

	public BigDecimal getUnitValue() {
		return unitValue;
	}

	public void setUnitValue(BigDecimal unitValue) {
		this.unitValue = unitValue;
	}

	public BigDecimal getCalculatedValue() {
		return calculatedValue;
	}

	public void setCalculatedValue(BigDecimal calculatedValue) {
		this.calculatedValue = calculatedValue;
	}

	@Override
	public int hashCode() {

		return HashCodeBuilder.reflectionHashCode(this);

	}

	@Override
	public boolean equals(Object obj) {

		return EqualsBuilder.reflectionEquals(this, obj);

	}

	@Override
	public String toString() {

		return ToStringBuilder.reflectionToString(this);

	}

}
