package co.kr.shopping.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class HomeController {
	
	@GetMapping("/login")
	public String login(Model model) {
		return "login";
	}
	
	@PostMapping("/loginProc")
	public Map<String, Object> loginProc(Model model) {
		Map<String, Object> loginInfo = new HashMap<>();
		return loginInfo;
	}
	
	@GetMapping("/home")
	public String home(Model model) {
		return "home";
	}
}
