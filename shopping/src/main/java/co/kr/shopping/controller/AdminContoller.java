package co.kr.shopping.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/admin")
@Controller
public class AdminContoller {

	@GetMapping("/adminReg")
	public String AdminReg(Model model) {
		return "admin/adminReg";
	}
}
