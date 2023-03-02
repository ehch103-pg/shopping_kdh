package co.kr.shopping.controller;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import co.kr.shopping.service.CommonService;

@Controller
public class HomeController {
	
	@Autowired
	CommonService commService;
	
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
	
	@GetMapping("/retire/retirePage")
	public String retireInfo(Model model, Principal principal) {
		String id = principal.getName();
		
		model.addAttribute("Id", id);
		return "retire/retirePage";
	}

	
	@PostMapping("/imgShow")
	@ResponseBody
	public ResponseEntity<byte[]> showImg(@RequestBody Map<String, Object> param){
		String imgId = param.getOrDefault("imgId", "").toString();
		List<Map<String, Object>> imgList = commService.searchFile(imgId);
		return null;
	}
	
}
