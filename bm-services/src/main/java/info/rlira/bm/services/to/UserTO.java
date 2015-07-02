package info.rlira.bm.services.to;

import java.io.Serializable;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class UserTO implements Serializable {

	private static final long serialVersionUID = -6890580708025770082L;

	private String name;
	private String email;
	private String bands;
	
	public String getName() {
		return name;
	}
	public String getEmail() {
		return email;
	}
	public String getBands() {
		return bands;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public void setBands(String bands) {
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
