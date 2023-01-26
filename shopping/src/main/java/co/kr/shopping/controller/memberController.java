package co.kr.shopping.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import co.kr.shopping.dao.UserRepository;
import co.kr.shopping.service.MemberService;

@Controller("/member")
public class memberController {
	
	@Autowired
	MemberService memberService;
	
	@GetMapping("/memberReg")
	public String registerMem(Model model) {
		return "member/memberReg";
	}
	
	@PostMapping("/memberRegProc")
	public Map<String, Object> memberInsert(@RequestParam Map<String, Object> param){
		SimpleDateFormat simpledate = new SimpleDateFormat("yyyy-MM-dd");
		
		String mem_id = (String) param.getOrDefault("mem_id", "");
		String mem_pw = (String) param.getOrDefault("mem_pw", "");
		String mem_email = (String)param.getOrDefault("mem_email", "");
		String mem_regDate = simpledate.format(new Date());
		String mem_name = (String) param.getOrDefault("mem_name", "");
		String mem_gen = (String) param.getOrDefault("mem_gen", "");
		
		String Chdate = simpledate.format(new Date());
		
		Map<String, Object> result = new HashMap<>();
		int count = user
		return result;
	}
}
