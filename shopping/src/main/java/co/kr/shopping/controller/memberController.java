package co.kr.shopping.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import co.kr.shopping.service.MemberService;
import co.kr.shopping.vo.MemberVO;

@Controller
@RequestMapping("/member")
public class memberController {
	
	@Autowired
	MemberService memberService;
	
	@PostMapping("/memberRegProc")
	public String saveMember(@RequestParam Map<String, Object> param) {
		String msg = "";
		System.err.println(param);
		String id = (String)param.get("mem_id");
	
		MemberVO member = new MemberVO();
		member.setMemId(id);
		member.setMemPw((String)param.get("mem_pw"));
		member.setMemEmail((String)param.get("mem_email"));
		member.setMemName((String)param.get("mem_name"));
		member.setMemGen((String)param.get("mem_gen"));
		memberService.JoinorModifyByMember(member, 1);
			
		return "redirect:/login";
		
	}
	@PreAuthorize("isAuthenticated()")
	@GetMapping("/memberMod")
	public String modify(Model model, @RequestParam Map<String, Object> param) {
		String Id = (String)param.getOrDefault("id", "");
		System.err.println(Id);
		MemberVO memberVO = memberService.selectMember(Id);
		
		
		model.addAttribute("mem_id", memberVO.getMemId());
		model.addAttribute("mem_email", memberVO.getMemEmail());
		model.addAttribute("mem_name", memberVO.getMemName());
		model.addAttribute("mem_gen", memberVO.getMemGen());
		
		return "member/memberMod";
	}
	
	
	@PostMapping("/memberModProc")
	public String changeMember(@RequestParam Map<String, Object> param){
		MemberVO memberVO = new MemberVO();
		memberVO.setMemId((String)param.getOrDefault("mem_id", ""));
		memberVO.setMemPw((String)param.getOrDefault("mem_pw", ""));
		memberVO.setMemEmail((String)param.getOrDefault("mem_email", ""));
		memberVO.setMemName((String)param.getOrDefault("mem_name", ""));
		memberVO.setMemGen((String)param.getOrDefault("mem_gen", ""));
		System.out.println(memberVO);
		memberService.JoinorModifyByMember(memberVO, 2);
		
		return "login";
	}
}
