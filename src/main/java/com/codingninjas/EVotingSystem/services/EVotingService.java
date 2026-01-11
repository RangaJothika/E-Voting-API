package com.codingninjas.EVotingSystem.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codingninjas.EVotingSystem.Exceptions.ElectionChoiceNotFoundInElection;
import com.codingninjas.EVotingSystem.Exceptions.VoteAlreadyCasted;
import com.codingninjas.EVotingSystem.entities.Election;
import com.codingninjas.EVotingSystem.entities.ElectionChoice;
import com.codingninjas.EVotingSystem.entities.User;
import com.codingninjas.EVotingSystem.entities.Vote;
import com.codingninjas.EVotingSystem.repositories.ElectionChoiceRepository;
import com.codingninjas.EVotingSystem.repositories.ElectionRepository;
import com.codingninjas.EVotingSystem.repositories.UserRepository;
import com.codingninjas.EVotingSystem.repositories.VoteRepository;

@Service
public class EVotingService {

	@Autowired
	VoteRepository voteRepository;

	@Autowired
	UserRepository userRepository;

	@Autowired
	ElectionRepository electionRepository;

	@Autowired
	ElectionChoiceRepository electionChoiceRepository;

	public List<Vote> getAllVotes() {
		return voteRepository.findAll();
	}

	public void addUser(User user) {
		userRepository.save(user);

	}

	public List<User> getAllUsers() {
		return userRepository.findAll();
	}

	public void addVote(Long userId, Long electionId, Long electionChoiceId) {
		if (AlreadyGivenVote(userId, electionId))
			throw new VoteAlreadyCasted("You have already given your vote");
		if (!IsElectionChoicePresentInElection(electionId, electionChoiceId)) {
			throw new ElectionChoiceNotFoundInElection("Election Choice is not of the specified election");
		}
		User user = userRepository.findById(userId).get();
		Election election = electionRepository.findById(electionId).get();
		ElectionChoice electionChoice = electionChoiceRepository.findById(electionChoiceId).get();
		Vote vote = new Vote();
		vote.setElection(election);
		vote.setElectionChoice(electionChoice);
		vote.setUser(user);
		voteRepository.save(vote);
	}

	public boolean IsElectionChoicePresentInElection(Long electionId, Long electionChoiceId) {
		ElectionChoice electionChoice = electionChoiceRepository.findById(electionChoiceId).get();
	return electionId.equals( electionChoice.getElection().getId());
	}

	public boolean AlreadyGivenVote(Long userId, Long electionId) {
		return voteRepository.existsByUserIdAndElectionId(userId, electionId);

	}

	public void addElection(Election election) {
		electionRepository.save(election);
	}

	public List<Election> getAllElections() {
		return electionRepository.findAll();
	}

	public void addElectionChoice(ElectionChoice electionChoice) {
		long electionId = electionChoice.getElection().getId();
		Election election = electionRepository.findById(electionId).get();
		electionChoice.setElection(election);
//		electionChoice.setName(electionChoice.getName());
		electionChoiceRepository.save(electionChoice);
	}

	public List<ElectionChoice> getAllElectionChoices() {
		return electionChoiceRepository.findAll();
	}

	public long countTotalVotes() {
		return voteRepository.getCountOfTotalVotes();
	}

	public long countVotesByElectionName(String electionName) {
		return voteRepository.getCountOfTotalVotesByElectionName(electionName);
	}

	public long choicesByElection(Long electionId) {
		return electionChoiceRepository.choicesByElection(electionId);
	}

//  public Election findElectionByName(String electionName) {
//  return electionRepository.findByName(electionName);
//}
	public ElectionChoice findElectionWinner(String electionName) {
		Election election = electionRepository.findByName(electionName);
		return electionChoiceRepository.findElectionChoiceWithMaxVotes(election.getId());
	}

}
