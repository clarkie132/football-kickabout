package com.dcsoft.football.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Fixture {
	@Id
	@GeneratedValue
	private Long id;
	@Temporal(TemporalType.TIMESTAMP)
	private Date date;
	private int sequence;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "home_team_id")
	private Team homeTeam;
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "away_team_id")
	private Team awayTeam;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "result_id")
	private Result result;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Team getHomeTeam() {
		return homeTeam;
	}

	public void setHomeTeam(Team homeTeam) {
		this.homeTeam = homeTeam;
	}

	public Team getAwayTeam() {
		return awayTeam;
	}

	public void setAwayTeam(Team awayTeam) {
		this.awayTeam = awayTeam;
	}

	public Result getResult() {
		return result;
	}

	public void setResult(Result result) {
		this.result = result;
	}

	public void newHomeTeam() {
		this.homeTeam = new Team();
	}

	public void newAwayTeam() {
		this.awayTeam = new Team();
	}

	public void newResult() {
		this.result = new Result();
	}

	public int getSequence() {
		return sequence;
	}

	public void setSequence(int sequence) {
		this.sequence = sequence;
	}

	@Override
	public String toString() {
		return "Fixture [sequence=" + sequence + ", homeTeam=" + homeTeam
				+ ", awayTeam=" + awayTeam + "]";
	}

	
	
}
