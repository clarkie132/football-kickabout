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

import java.util.List;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import com.dcsoft.football.model.Fixture;
import com.dcsoft.football.model.League;
import com.dcsoft.football.model.Result;

// The @Stateless annotation eliminates the need for manual transaction demarcation
@Stateless
public class LeagueService {

    @Inject
    private Logger log;
    
    @Inject
    FixtureService fixtureService;

    @Inject
    private EntityManager em;

    @Inject
    private Event<League> leagueEventSrc;

    public void insert(League league) throws Exception {
        log.info("Registering " + league.getName());
        em.persist(league);
        leagueEventSrc.fire(league);
    }
    
    public League findById(Long id) {
    	log.info("Finding league" + id);
    	return em.find(League.class, id);
    }
    
	public void progressLeague(Long id) {
		League league = em.find(League.class, id);
		List<Fixture> fixtures = league.getFixtures();
		int currentSequence = fixtureService.getCurrentSequence(fixtures);
		for(Fixture fixture : fixtures) {
			if(fixture.getSequence()==currentSequence) {
				calculateResult(fixture);
			}			
		}
		em.merge(league);
	}
	
	private void calculateResult(Fixture fixture) {
		Result result = new Result();
		result.setAwayGoals(2);
		result.setAwayGoals(1);
		fixture.setResult(result);
	}

}
