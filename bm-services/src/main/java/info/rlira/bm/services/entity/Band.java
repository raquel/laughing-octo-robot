package info.rlira.bm.services.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * {@link }
 * 
 * @author Raquel Lira (raquel.lira@gmail.com) - 01.07.2015
 * 
 */
@Entity
@Table(name="bands", schema = "lumiere")
public class Band {

	@Id
	@Column(name = "id")
	private Long id;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "photo")
	private String photo;

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getPhoto() {
		return photo;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}
	
}
