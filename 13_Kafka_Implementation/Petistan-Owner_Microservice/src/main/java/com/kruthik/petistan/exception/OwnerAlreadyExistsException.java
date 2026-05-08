package com.kruthik.petistan.exception;

public class OwnerAlreadyExistsException extends RuntimeException {

	private static final long serialVersionUID = 4381995779879134344L;

	public OwnerAlreadyExistsException(String msg) {
		super(msg);
	}
}
