package com.marcosvieirajr.caprepag.domain.card.usecase.exeptions;

import com.marcosvieirajr.caprepag.domain._base.exception.BaseBusinesException;

public class InvalidAddNewCardParamsException extends BaseBusinesException {

	private static final long serialVersionUID = 1L;
	
	private static final String ERROR_CODE = "1";

	public InvalidAddNewCardParamsException(String message) {
		super(ERROR_CODE, message);
	}

}
