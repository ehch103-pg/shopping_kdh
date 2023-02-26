package co.kr.shopping.controller;

import java.security.Principal;
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
public class MemberController {
	
	@Autowired
	MemberService memberService;
	
	@PostMapping("/memberCheck")
	@ResponseBody
	public Map<String, Object> checkId(@RequestBody Map<String, Object> param){
		String userId = (String)param.getOrDefault("Id", "");
		Map<String, Object> result = new HashMap<>();
		int member_check = memberService.checkMember(userId);
		if(member_check == 1) {
			result.put("result", "Exist");
		}else {
			result.put("result", "None");
		}
		return result;
	}
	
	@PostMapping("/memberReg")
	public Map<String, Object> saveMember(@RequestParam Map<String, Object> param) {
		String id = (String)param.get("mem_id");
		Map<String, Object> result = new HashMap<>();
		
		MemberVO member = new MemberVO();
		member.setMemId(id);
		member.setMemPw((String)param.get("mem_pw"));
		member.setMemEmail((String)param.get("mem_email"));
		member.setMemName((String)param.get("mem_name"));
		member.setMemGen((String)param.get("mem_gen"));
		memberService.JoinorModifyByMember(member, 1, "U");
		result.put("msg", "회원가입을 성공하였습니다. 축하드립니다!");
		
		return result;
		
	}
	@PreAuthorize("isAuthenticated()")
	@GetMapping("/memberMod")
	public String modify(Model model, @RequestParam Map<String, Object> param) {
		String Id = (String)param.getOrDefault("id", "");
		MemberVO memberVO = memberService.selectMember(Id);
				
		model.addAttribute("mem_id", memberVO.getMemId());
		model.addAttribute("mem_email", memberVO.getMemEmail());
		model.addAttribute("mem_name", memberVO.getMemName());
		model.addAttribute("mem_gen", memberVO.getMemGen());
		
		return "member/memberMod";
	}
	
	
	@PostMapping("/memberModProc")
	public String changeMember(@RequestParam Map<String, Object> param){
		String member_Id = (String)param.getOrDefault("mem_id", "").toString();
		String userCheck = memberService.selectMember(member_Id).getRole();
		
		MemberVO memberVO = new MemberVO();
		memberVO.setMemId(member_Id);
		memberVO.setMemPw((String)param.getOrDefault("mem_pw", ""));
		memberVO.setMemEmail((String)param.getOrDefault("mem_email", ""));
		memberVO.setMemName((String)param.getOrDefault("mem_name", ""));
		memberVO.setMemGen((String)param.getOrDefault("mem_gen", ""));
		memberService.JoinorModifyByMember(memberVO, 2, userCheck);
		
		return "login";
	}
}
