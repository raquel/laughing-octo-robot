package info.rlira.bm.services.to;

import java.io.Serializable;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class Band implements Serializable {

	private static final long serialVersionUID = -4055445236675624185L;

	private String name;
	private String photo;
	
	public Band() {
	}
	
	public Band(String name, String photo) {
		super();
		this.name = name;
		this.photo = photo;
	}
	
	public String getName() {
		return name;
	}

	public String getPhoto() {
		return photo;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
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
