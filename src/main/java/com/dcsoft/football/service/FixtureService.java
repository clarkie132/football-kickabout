/*
 * JBoss, Home of Professional Open Source
 * Copyright 2013, Red Hat, Inc. and/or its affiliates, and individual
 * contributors by the @authors tag. See the copyright.txt in the
 * distribution for a full listing of individual contributors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.dcsoft.football.service;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.inject.Inject;

import com.dcsoft.football.model.Fixture;
import com.dcsoft.football.model.League;
import com.dcsoft.football.model.Team;

@Stateless
public class FixtureService {

	@Inject
	private Logger log;

	public List<Fixture> createFixtures(League league) {
		log.info("Generating fixtures");
		List<Team> teamCopy = new ArrayList<Team>(league.getTeams());
		List<Fixture> fixtures = new ArrayList<Fixture>();
		List<Team> teams = teamCopy;
		for (Team homeTeam : teams) {
			for (Team awayTeam : teams) {
				if (!homeTeam.equals(awayTeam)) {
					Fixture fixture = new Fixture();
					fixture.setHomeTeam(homeTeam);
					fixture.setAwayTeam(awayTeam);
					fixtures.add(fixture);
				}

			}
		}
		sequenceFixtures(fixtures, teamCopy);
		return fixtures;
	}

	private void sequenceFixtures(List<Fixture> fixtures, List<Team> teams) {
		boolean done = false;
		int sequence = 1;
		while (!done) {
			done = true;
			List<Team> candidateTeams = new ArrayList<Team>();
			for (Fixture fixture : fixtures) {
				if (fixture.getSequence() == 0) {
					done = false;
					if (candidateTeams.isEmpty()) {
						candidateTeams.add(fixture.getAwayTeam());
						candidateTeams.add(fixture.getHomeTeam());
						fixture.setSequence(sequence);
					} else {
						if (!(candidateTeams.contains(fixture.getAwayTeam()) || candidateTeams
								.contains(fixture.getHomeTeam()))) {
							candidateTeams.add(fixture.getAwayTeam());
							candidateTeams.add(fixture.getHomeTeam());
							fixture.setSequence(sequence);
						}
					}
				}
			}
			sequence++;
		}

	}

}
