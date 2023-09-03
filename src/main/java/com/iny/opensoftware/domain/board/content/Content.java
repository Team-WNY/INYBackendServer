package com.iny.opensoftware.domain.board.content;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Content {
	private String title;
	private String contents;
	private String location;
}
