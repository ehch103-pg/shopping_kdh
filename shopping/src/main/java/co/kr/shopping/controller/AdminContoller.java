package co.kr.shopping.controller;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import co.kr.shopping.service.MemberService;
import co.kr.shopping.vo.MemberVO;

@RequestMapping("/admin")
@Controller
public class AdminContoller {

	@Autowired
	MemberService memberService;
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping("/adminPage")
	public String AdminReg(Model model) {
		
		return "admin/adminPage";
	}
	
}
