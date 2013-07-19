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
package org.jboss.tools.examples.test;

import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.inject.Inject;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.persistence.ApplyScriptAfter;
import org.jboss.arquillian.persistence.ApplyScriptBefore;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.dcsoft.football.model.Fixture;
import com.dcsoft.football.model.League;
import com.dcsoft.football.model.Player;
import com.dcsoft.football.model.Team;
import com.dcsoft.football.service.LeagueService;

@RunWith(Arquillian.class)
public class LeagueServiceTest {
    @Deployment
    public static Archive<?> createTestArchive() {
        return ShrinkWrap.create(WebArchive.class, "test.war")        		
        		.addPackages(true, "com.dcsoft.football")                
                .addAsResource("META-INF/test-persistence.xml", "META-INF/persistence.xml")             
                .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml")
                // Deploy our test datasource
                .addAsWebInfResource("test-ds.xml");
    }

    @Inject
    LeagueService leagueService;

    @Inject
    Logger log;

    @Test
    public void testCreate() throws Exception {
    	League league = new League();    	
        league.setName("Division One");
        Team homeTeam = new Team();
        homeTeam.setName("Manchester City");
        league.addTeam(homeTeam);
        
        Team awayTeam = new Team();
        awayTeam.setName("Manchester United");
        league.addTeam(awayTeam);
        
        Player player = new Player();
        player.setName("Rooney");
        homeTeam.addPlayer(player);
        
    	
        List<Fixture> fixtures = new ArrayList<Fixture>();
        Fixture fixture = new Fixture();
        fixture.setAwayTeam(awayTeam);
        fixture.setHomeTeam(homeTeam);
        fixture.setSequence(1);
        fixtures.add(fixture);
        
        league.setFixtures(fixtures);
        
        leagueService.insert(league);
        
        assertNotNull(league.getId());
        assertNotNull(homeTeam.getId());
        assertNotNull(player.getId());
        
        log.info(league.getName() + " was persisted with id " + league.getId());
    }
    
    @Test  
    @ApplyScriptBefore("import_league.sql")
    @ApplyScriptAfter("delete_league.sql")
    public void testFind() throws Exception {
    	League league = leagueService.findById(new Long(1));    	
        
        assertNotNull(league);
        log.info(league.getName() + " was found with id " + league.getId());
    }


}
