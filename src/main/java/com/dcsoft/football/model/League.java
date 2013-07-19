package com.dcsoft.football.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@XmlRootElement
@Table(name = "League", uniqueConstraints = @UniqueConstraint(columnNames = "name"))
public class League {

	@Id
	@GeneratedValue
	private Long id;

	@OneToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, mappedBy = "league")
	private List<Team> teams = new ArrayList<Team>();

	@OneToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	private List<Fixture> fixtures = new ArrayList<Fixture>();
	
	@NotEmpty
	private String name;
	private int division;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<Fixture> getFixtures() {
		return fixtures;
	}

	public void setFixtures(List<Fixture> fixtures) {
		this.fixtures = fixtures;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setTeams(List<Team> teams) {
		this.teams = teams;
	}
	
	public List<Team> getTeams() {
		return teams;
	}

	public void addTeam(Team team) {
		teams.add(team);
		team.setLeague(this);
	}

	public int getDivision() {
		return division;
	}

	public void setDivision(int division) {
		this.division = division;
	}
	

}
