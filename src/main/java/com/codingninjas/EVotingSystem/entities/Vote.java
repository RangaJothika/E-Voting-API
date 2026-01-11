package com.codingninjas.EVotingSystem.entities;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

@Entity
public class Vote {

	// declare the attributes here
	public Vote() {
	}

	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@OneToOne(cascade= {CascadeType.REFRESH,CascadeType.MERGE,CascadeType.PERSIST})
//	@JoinColumn(name="user_Id")
	private User user;
	@ManyToOne(cascade= {CascadeType.REFRESH,CascadeType.MERGE,CascadeType.PERSIST})
	@JsonProperty(access=JsonProperty.Access.WRITE_ONLY)
//	@JoinColumn(name="election_id")
	private Election election;
	@ManyToOne(cascade= {CascadeType.REFRESH,CascadeType.MERGE,CascadeType.PERSIST})
	@JsonProperty(access=JsonProperty.Access.WRITE_ONLY)
	@JoinColumn(name="election_choice_id")
	private ElectionChoice electionChoice;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Election getElection() {
		return election;
	}

	public void setElection(Election election) {
		this.election = election;
	}

	public ElectionChoice getElectionChoice() {
		return electionChoice;
	}

	public void setElectionChoice(ElectionChoice electionChoice) {
		this.electionChoice = electionChoice;
	}

}
