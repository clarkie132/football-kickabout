package com.dcsoft.football.model.dto;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class LeagueDTO {

	private Long id;

	private String name;
	private int division;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getDivision() {
		return division;
	}

	public void setDivision(int division) {
		this.division = division;
	}

}
