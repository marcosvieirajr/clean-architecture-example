package br.com.mvj.prepag.domain._base.exception;

import static org.apache.commons.lang3.StringUtils.leftPad;

public class BaseBusinesException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	private static final String BASE_ERROR_MSG = "[%s]%s";
	
	public BaseBusinesException(final String errorCode, final String message) {
		super(String.format(BASE_ERROR_MSG, leftPad(errorCode, 2, "0"), message));
	}
}
///caprepag-domain/src/main/java/com/marcosvieirajr/caprepag/domain/_base/exception/BaseException.java