package com.marcosvieirajr.caprepag.domain.ports.in;

public interface UseCaseWithParameter<P> {

	void execute(P params);
}