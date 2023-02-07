package co.kr.shopping.controller;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
		System.err.println(param);
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
	
	@GetMapping("/reviewDetail")
	public String reviewDetail(Model model, @RequestParam(required = false) Map<String, Object> param) {
		System.err.println(param);
		
		
		return "review/reviewDetail";
	}
}
