package co.kr.shopping.controller;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
		int page = Integer.parseInt((String) param.get("page"));
		paging.setCurrentPageNo(page);
		String keyword = (String)param.getOrDefault("keyword", "");
//		paging.setTotalRecordCount(reviewService.selectReviewCount(keyword));
//		List<ReviewVO> reviewList = new ArrayList<>();
//		reviewList = reviewService.selectReviewList(keyword, paging);
//		
//		model.addAttribute("reviewList", reviewList);
//		model.addAttribute("paging", paging);
		
		
		return "review/reviewList";
	}
	
	@GetMapping("/reviewDetail")
	public String reviewDetail(Model model) {
		
		return "review/reviewDetail";
	}
}
