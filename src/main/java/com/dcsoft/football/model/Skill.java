package com.dcsoft.football.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlRootElement;


@Entity
@XmlRootElement
public class Skill
{
   
	@Id
	@GeneratedValue
	private Long id;
	
   public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

private double level;


   public double getLevel()
   {
      return level;
   }

   public void setLevel(double level)
   {
      this.level = level;
   }

}
