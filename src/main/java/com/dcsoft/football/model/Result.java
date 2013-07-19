package com.dcsoft.football.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@XmlRootElement
public class Result
{
   @Id
   @GeneratedValue
   private Long id;
   private int homeGoals;
   private int awayGoals;

   public Long getId()
   {
      return id;
   }

   public void setId(Long id)
   {
      this.id = id;
   }

   public int getHomeGoals()
   {
      return homeGoals;
   }

   public void setHomeGoals(int homeGoals)
   {
      this.homeGoals = homeGoals;
   }

   public int getAwayGoals()
   {
      return awayGoals;
   }

   public void setAwayGoals(int awayGoals)
   {
      this.awayGoals = awayGoals;
   }

}
