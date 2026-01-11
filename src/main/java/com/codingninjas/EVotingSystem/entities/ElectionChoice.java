package com.codingninjas.EVotingSystem.entities;

import jakarta.persistence.*;
import org.springframework.data.repository.cdi.Eager;

@Entity
public class ElectionChoice {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column
	private long id;
	@Column
	private String name;

	@ManyToOne(cascade= {CascadeType.REFRESH,CascadeType.MERGE,CascadeType.PERSIST})
//	@JoinColumn(name="election_id")//optional - it is also auto created with fieldname_id as fk in table 
	private Election election;
//	
//	@OneToMany()
//	private User user;
//	@OneToMany()
//	private Vote vote;
	
	public ElectionChoice() {}
	public ElectionChoice(String name) {
		this.name=name;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Election getElection() {
		return election;
	}

	public void setElection(Election election) {
		this.election = election;
	}
}
