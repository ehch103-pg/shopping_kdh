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
	
	@GetMapping("/memberReg")
	public String register() {
		return "member/memberReg";
	}
	
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
	
	@GetMapping("/mypage")
	public String mypage(Model model, Principal principal) {
		
		String mem_id = principal.getName();
		
		model.addAttribute("id", mem_id);
		
		return "member/mypage";
	}
	
	@PostMapping("/memberReg")
	@ResponseBody
	public Map<String, Object> saveMember(@RequestParam Map<String, Object> param) {
		String id = (String)param.get("mem_id");
		Map<String, Object> result = new HashMap<>();
		
		MemberVO member = new MemberVO();
		member.setMemId(id);
		member.setMemPw((String)param.get("mem_pw"));
		member.setMemEmail((String)param.get("mem_email"));
		member.setMemName((String)param.get("mem_name"));
		member.setMemGen((String)param.get("mem_gen"));
		if(memberService.JoinorModifyByMember(member, 1, "U") != 0) {
			result.put("result", "S");
			result.put("msg", "회원가입에 성공하였습니다.");
			result.put("url", "/login");
		}else {
			result.put("result", "F");
			result.put("msg", "회원가입에 실패하였습니다.");
		}
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
	@ResponseBody
	public Map<String, Object> changeMember(@RequestBody Map<String, Object> param){
		Map<String, Object> result = new HashMap<String, Object>();
		String member_Id = param.getOrDefault("mem_id", "").toString();
		String userCheck = memberService.selectMember(member_Id).getRole();
		MemberVO memberVO = new MemberVO();
		memberVO.setMemId(member_Id);
		memberVO.setMemPw((String)param.getOrDefault("mem_pw", ""));
		memberVO.setMemEmail((String)param.getOrDefault("mem_email", ""));
		memberVO.setMemName((String)param.getOrDefault("mem_name", ""));
		memberVO.setMemGen((String)param.getOrDefault("mem_gen", ""));
		if(memberService.JoinorModifyByMember(memberVO, 2, userCheck) != 0) {
			result.put("result", "S");
			result.put("msg", "회원 정보가 수정되었습니다");
			result.put("url", "/logout");
		}else {
			result.put("msg", "회원 정보 수정에 실패하셨습니다. 다시 입력하시기 바랍니다");
			result.put("result", "F");
		}
	
		return result;
	}
	
	@PreAuthorize("isAuthenticated()")
	@GetMapping("/memberDel")
	public String delete(Model model, @RequestParam Map<String, Object> param) {
		String id = param.getOrDefault("id", "").toString();
		
		model.addAttribute("id", id);
		return "member/memberDel";
	}
	
	@PostMapping("/retireMember")
	@ResponseBody
	public Map<String, Object> deleteMember(@RequestBody Map<String, Object> param){
		Map<String, Object> result = new HashMap<String, Object>();
		
		String member_Id = (String)param.getOrDefault("Id", "");
		String member_Pw = (String)param.getOrDefault("Pw", "");
		
		MemberVO member = memberService.selectMember(member_Id);
		
		if(memberService.deleteMember(member_Id, member_Pw, member) != 0) {
			result.put("msg", "탈퇴에 성공하셨습니다.");
			result.put("result", "S");
			result.put("url", "/logout");
		}else {
			result.put("result", "F");
			result.put("msg", "회원 정보를 잘못 입력하셨습니다");
		}
		
		
		return result;
	}
	
	
}
