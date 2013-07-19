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

import java.util.logging.Logger;

import javax.inject.Inject;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.dcsoft.football.model.Fixture;
import com.dcsoft.football.model.League;
import com.dcsoft.football.model.Player;
import com.dcsoft.football.model.Result;
import com.dcsoft.football.model.Skill;
import com.dcsoft.football.model.Team;
import com.dcsoft.football.service.ResultRegistration;
import com.dcsoft.football.util.Resources;

@RunWith(Arquillian.class)
public class ResultRegistrationTest {
    @Deployment
    public static Archive<?> createTestArchive() {
        return ShrinkWrap.create(WebArchive.class, "test.war")
                .addClasses(Result.class, ResultRegistration.class, Resources.class, Fixture.class, Team.class, League.class, Player.class, Skill.class)
                .addAsResource("META-INF/test-persistence.xml", "META-INF/persistence.xml")
                .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml")
                // Deploy our test datasource
                .addAsWebInfResource("test-ds.xml");
    }

    @Inject
    ResultRegistration resultRegistration;

    @Inject
    Logger log;

    @Test
    public void testRegister() throws Exception {
    	    	
        Result newResult = new Result();
        newResult.setAwayGoals(1);
        newResult.setHomeGoals(2);        
        resultRegistration.register(newResult);
        assertNotNull(newResult.getId());
        log.info(newResult.getAwayGoals() + " was persisted with id " + newResult.getId());
    }

}
