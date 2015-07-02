package info.rlira.bm.services.to;

import java.io.Serializable;
import java.util.List;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class GeneralRankingTO implements Serializable{

	private static final long serialVersionUID = -5923333493997449861L;
	
	private UserTO lastUserVotes;
	private List<BandTO> bands;
	
	
	public GeneralRankingTO() {
		super();
	}

	public GeneralRankingTO(UserTO lastUserVotes, List<BandTO> bands) {
		super();
		this.lastUserVotes = lastUserVotes;
		this.bands = bands;
	}

	public UserTO getLastUserVotes() {
		return lastUserVotes;
	}

	public List<BandTO> getBands() {
		return bands;
	}

	public void setLastUserVotes(UserTO lastUserVotes) {
		this.lastUserVotes = lastUserVotes;
	}

	public void setBands(List<BandTO> bands) {
		this.bands = bands;
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
