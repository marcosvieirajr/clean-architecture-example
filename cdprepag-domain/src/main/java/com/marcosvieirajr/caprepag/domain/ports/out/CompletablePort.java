package com.marcosvieirajr.caprepag.domain.ports.out;

import java.util.Optional;

public interface CompletablePort<T> {
	
	 void complete(T value);
	 
	 public Optional<T> get();

}
