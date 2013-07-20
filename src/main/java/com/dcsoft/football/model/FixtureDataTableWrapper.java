package com.dcsoft.football.model;

import java.util.List;

public class FixtureDataTableWrapper {
	private List<Fixture> aaData;
	private String sEcho;
	

	public List<Fixture> getAaData() {
		return aaData;
	}

	public void setAaData(List<Fixture> aaData) {
		this.aaData = aaData;
	}

	public int getiTotalRecords() {
		return aaData == null ? 0 : aaData.size();
	}

	public int getiTotalDisplayRecords() {
		return aaData == null ? 0 : aaData.size();
	}

	public String getsEcho() {
		return sEcho;
	}

	public void setsEcho(String sEcho) {
		this.sEcho = sEcho;
	}

}
