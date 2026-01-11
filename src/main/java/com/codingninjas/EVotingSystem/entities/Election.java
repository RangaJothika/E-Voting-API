package com.codingninjas.EVotingSystem.entities;

import jakarta.persistence.*;

@Entity
public class Election {
//	public Vote getVote() {
//		return vote;
//	}
//
//	public void setVote(Vote vote) {
//		this.vote = vote;
//	}
//
//	public ElectionChoice getElectionChoice() {
//		return electionChoice;
//	}
//
//	public void setElectionChoice(ElectionChoice electionChoice) {
//		this.electionChoice = electionChoice;
//	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column
	private long id;

	@Column(unique = true)
	private String name;
//	@OneToMany(mappedBy="election",cascade=CascadeType.ALL)
//	private Vote vote;
//	@OneToMany(cascade=CascadeType.ALL,mappedBy="election")
//	private ElectionChoice electionChoice;
	public Election() {
	}

	public Election(String name) {
		this.name = name;
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
}
