package co.kr.shopping.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller("/member")
public class memberController {
	
	@GetMapping("/memberReg")
	public String registerMem(Model model) {
		return "member/memberReg";
	}
}
