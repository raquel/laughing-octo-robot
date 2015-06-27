package info.rlira.bm.services.entity;

import java.io.Serializable;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

/**
 * {@link }
 * 
 * @author Raquel Lira (raquel.lira@gmail.com) - 27.06.2015
 * 
 */
@Entity
@Table(name="portfolios_types", schema = "painelv3")
public class PortfolioType extends Model implements Serializable {

	private static final long serialVersionUID = -2832894835351243862L;

	@Id
	@Column(name = "portfolio_type_id")
	@Type(type = "pg-uuid")
	private UUID portfolioTypeId;
	
	@Column(name = "portfolio_type_name")
	private String portfolioTypeName;

	public UUID getPortfolioTypeId() {
		return portfolioTypeId;
	}

	public void setPortfolioTypeId(UUID portfolioTypeId) {
		this.portfolioTypeId = portfolioTypeId;
	}

	public String getPortfolioTypeName() {
		return portfolioTypeName;
	}

	public void setPortfolioTypeName(String portfolioTypeName) {
		this.portfolioTypeName = portfolioTypeName;
	}
	
	public String toString() {
		return "Tyep Id - " + this.portfolioTypeId + " ## Type Name - " + this.portfolioTypeName;
	}
}
