package com.dcsoft.football.model;

import java.util.List;

public class DataTableWrapper {
	private List<League> aaData;
	private String sEcho;
	

	public List<League> getAaData() {
		return aaData;
	}

	public void setAaData(List<League> aaData) {
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
