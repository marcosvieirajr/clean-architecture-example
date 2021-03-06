package br.com.mvj.prepag.domain.card.usecase;

import br.com.mvj.prepag.domain._base.exception.BaseBusinesException;

public class InvalidCardRequestException extends BaseBusinesException {

	private static final long serialVersionUID = 1L;
	
	private static final String ERROR_CODE = "1";

	public InvalidCardRequestException(String message) {
		super(ERROR_CODE, message);
	}

}
