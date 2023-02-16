package co.kr.shopping.controller;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {
	
	@GetMapping("/")
	public String main(Model model, Principal principal, HttpServletRequest request) {
		String id = "";
		if(principal != null) {
			id = principal.getName();
			model.addAttribute("id", id);
		}
		
		return "index";
	}
	
	@GetMapping("/login")
	public String login(Model model) {
		return "login";
	}
	
	
	@GetMapping("/member/memberReg")
	public String register() {
		return "member/memberReg";
	}

}
