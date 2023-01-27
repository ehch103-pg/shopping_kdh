package co.kr.shopping.controller;


import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import co.kr.shopping.service.MemberService;
import co.kr.shopping.vo.MemberVO;

@Controller
public class memberController {
	
	@Autowired
	MemberService memberService;
	
	@GetMapping("/member/memberReg")
	public String registerMem(Model model) {
		return "member/memberReg";
	}
	
	@PostMapping("/member/memberRegProc")
	@ResponseBody
	public int memberInsert(@RequestBody Map<String, Object> param){
		System.out.println("1111");
		String mem_id = (String)param.getOrDefault("mem_id", "");
		MemberVO member = new MemberVO();
		int count = 1; 
//				memberService.insertMember(member);
		
		return count;
	}
}
