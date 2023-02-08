package co.kr.shopping.controller;
import java.security.Principal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import co.kr.shopping.service.ReviewService;
import co.kr.shopping.vo.PaginationVO;
import co.kr.shopping.vo.ReviewVO;

@Controller
@RequestMapping("/review")
public class ReviewController {

	@Autowired
	ReviewService reviewService;
	
	@GetMapping("/reviewList")
	public String reviewList(Model model, @RequestParam Map<String, Object> param) {
		PaginationVO paging = new PaginationVO();
		if(param.get("page") == null || param.get("page").equals("")) {
			param.put("page", "1");
		}
		int page = Integer.parseInt((String) param.get("page"));
		int firstRecord = (page-1) * 10;
		paging.setCurrentPageNo(page);
		paging.setFirstRecordOnPage(firstRecord);
		String keyword = (String)param.getOrDefault("searchWord", "");
		String option = (String)param.getOrDefault("option", "");
		paging.setTotalRecordCount(reviewService.selectReviewCount(keyword, option));
		List<ReviewVO> reviewList = new ArrayList<>();
		reviewList = reviewService.selectReviewList(keyword, option, paging);
	
		model.addAttribute("keyword", keyword);
		model.addAttribute("option", option);
		model.addAttribute("pageNo", page);
		model.addAttribute("size", reviewList.size());
		model.addAttribute("reviewList", reviewList);
		model.addAttribute("paging", paging);
		
		
		return "review/reviewList";
	}
	
	@GetMapping("/reviewWrite")
	public String reviewWrite() {
		return "review/reviewWrite";
	}
	
	@ResponseBody
	@PostMapping("/regRev")
	public Map<String, Object> registerReview(@RequestBody Map<String, Object> param){
		Map<String, Object> result = new HashMap<>();
		
		ReviewVO reviewVO = new ReviewVO();
		reviewVO.setReviewTitle(param.getOrDefault("title", "").toString());
		
		int check = reviewService.saveReview(reviewVO);
		
		if(check > 0) {
			result.put("msg", "리뷰가 저장되었습니다.");
			result.put("url", "/review/reviewList");
			result.put("result", "S");
		}else {
			result.put("result", "F");
			result.put("msg", "리뷰가 정상적으로 저장되지 않았습니다. 다시 시도해주시기 바랍니다.");
		}
		
		return result;
	}
	
	@ResponseBody
	@PostMapping("/modRev")
	public Map<String, Object> modifyReview(@RequestBody Map<String, Object> param){
		Map<String, Object> result = new HashMap<>();
		return result;
	}
	
	@GetMapping("/reviewDetail")
	public String reviewDetail(Model model, @RequestParam(required = false) Map<String, Object> param, Principal principal) throws ParseException {
		
		String reviewNo = param.getOrDefault("id", "").toString();
		Map<String, Object> reviewVo = reviewService.selectReviewDetail(reviewNo);
		String writer = reviewVo.getOrDefault("review_writer", "").toString();
		int user_check;
		if(principal.getName().equals(writer)) {
			user_check = 1;
		}else {
			user_check = 0;
		}

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		String regDate = reviewVo.getOrDefault("regDate", "").toString();
		Date parseDate = sdf.parse(regDate);
		
		reviewService.mergeView(reviewNo);
		
		model.addAttribute("check", user_check);
		model.addAttribute("title", reviewVo.getOrDefault("review_title", "").toString());
		model.addAttribute("writer", reviewVo.getOrDefault("review_writer", "").toString());
		model.addAttribute("regDate", parseDate);
		
		return "review/reviewDetail";
	}
	
}
