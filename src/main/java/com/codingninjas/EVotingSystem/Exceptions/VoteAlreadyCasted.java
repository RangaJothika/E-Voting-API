package com.codingninjas.EVotingSystem.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class VoteAlreadyCasted extends RuntimeException {
	public VoteAlreadyCasted(String message) {
		super(message);
	}
}
