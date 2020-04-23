package com.marcosvieirajr.caprepag.domain._base;

import java.util.Optional;

import com.marcosvieirajr.caprepag.domain.ports.out.CompletablePort;

public class Completable<T> implements CompletablePort<T> {
	
	private T value;

	@Override
	public void complete(T value) {
		this.value = value;
	}

	@Override
	public Optional<T> get() {
		var toReturn = value;
		value = null;
		return Optional.ofNullable(toReturn);
	}
}
