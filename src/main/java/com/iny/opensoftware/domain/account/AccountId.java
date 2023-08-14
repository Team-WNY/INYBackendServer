package com.iny.opensoftware.domain.account;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;

@Data
@EqualsAndHashCode
@RequiredArgsConstructor(staticName = "of")
public class AccountId {
	private final long value;
	
	static AccountId of(int value) { return AccountId.of((long) value); }
	
	@Override
	public String toString() { return Long.toString(this.value); }
}
