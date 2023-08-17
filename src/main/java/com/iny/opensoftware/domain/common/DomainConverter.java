package com.iny.opensoftware.domain.common;

public interface DomainConverter<A, B> {
	B convert(A a);
}
