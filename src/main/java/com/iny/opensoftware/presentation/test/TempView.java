package com.iny.opensoftware.presentation.test;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TempView {

	@GetMapping("/tmp")
	@ResponseBody
	public String testView() {
		return "<a href='/swagger-ui/index.html'>swagger</a>";
	}
}
