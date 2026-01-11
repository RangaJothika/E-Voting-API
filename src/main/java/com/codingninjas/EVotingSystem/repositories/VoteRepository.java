package com.codingninjas.EVotingSystem.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.codingninjas.EVotingSystem.entities.Election;
import com.codingninjas.EVotingSystem.entities.User;
import com.codingninjas.EVotingSystem.entities.Vote;

public interface VoteRepository extends JpaRepository<Vote, Long> {

	@Query(value = "SELECT COUNT(*) FROM vote", nativeQuery = true)
	public long getCountOfTotalVotes();

	@Query(value = "SELECT COUNT(*) FROM vote JOIN election ON vote.election_id=election.id WHERE election.name=:electionName ", nativeQuery = true)
	// here instead of election.name ,vote.election.name is wrong as vote table
	// doesnot have election column,vote entity only have election field ,we are
	// using sql so tables here
	public long getCountOfTotalVotesByElectionName(String electionName);
	
	public boolean existsByUserIdAndElectionId(Long userId, Long electionId);


}
