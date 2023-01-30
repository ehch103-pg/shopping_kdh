package co.kr.shopping.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import co.kr.shopping.service.MemberService;
import co.kr.shopping.vo.MemberVO;

@Controller
public class memberController {
	
	private final MemberService memberService;
	
	public memberController(MemberService memberService) {
		this.memberService = memberService;
	}
	
	@PostMapping("regProc")
	@ResponseBody
	public void save(@RequestBody MemberVO.SaveRequest member) {
		memberService.save(member);
		
	}
}
