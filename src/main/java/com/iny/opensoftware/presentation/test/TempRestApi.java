package com.iny.opensoftware.presentation.test;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class TempRestApi {

	@GetMapping("/test")
	public String apiTest(@RequestParam("id") int id, @RequestParam("name") String name) {
		return "Spring boot 3.1.1 Test id : " + id + ", name : " + name;
	}
	
	/**
	 * 임시 Root 페이지를 위해서 만든 컨트롤러 (스웨거 접속용)
	 * @return
	 */
	@GetMapping("/")
	public String rootTest() {
		return "Hello! world! I'm root <br/>"
				+ "<h1><a href='/swagger-ui/index.html'>swagger</a></h1></br>"
				+ "<h1><a href='/test'>test</a></h1></br>";
	}
}
