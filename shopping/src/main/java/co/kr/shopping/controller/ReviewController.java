package co.kr.shopping.controller;
import java.security.Principal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

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

import co.kr.shopping.service.MemberService;
import co.kr.shopping.service.ReviewService;
import co.kr.shopping.vo.PaginationVO;
import co.kr.shopping.vo.ReviewVO;

@Controller
@RequestMapping("/review")
public class ReviewController {

	@Autowired
	ReviewService reviewService;
	
	@Autowired
	MemberService memberService;
	
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
		
		ReviewVO reviewVo = new ReviewVO();
		String title = param.get("title").toString();
		
		int check = reviewService.updateReview(reviewVo);
		
		if(check > 0) {
			
		}
		
		return result;
	}
	
	@ResponseBody
	@PostMapping("/LikeProc")
	public Map<String, Object> likeCheck(@RequestBody Map<String, Object> param){
		Map<String, Object> map = new HashMap<>();
		String reviewno = param.getOrDefault("reviewNo", "").toString();
		String like_User = param.getOrDefault("like_user", "").toString();
		
		int like_User_no = memberService.selectMember(like_User).getMemSeq();
		
		map.put("review_no", reviewno);
		map.put("mem_no", like_User_no);
		
		int findLike = reviewService.findLike(map);
		if(findLike == 1) {
			int likeCheck = reviewService.likeCheck(map);
			if(likeCheck == 1) {
				map.put("like_switch", "0");
			}else {
				map.put("like_switch", "1");
			}
			reviewService.updateLike(map);
		}else {
			map.put("like_switch", "1");
			reviewService.insertLike(map);
		}
		map.put("result", "S");
		return map;
	}
	
	@GetMapping("/reviewDetail")
	public String reviewDetail(Model model, @RequestParam(required = false) Map<String, Object> param, Principal principal, HttpServletRequest request) 
			throws ParseException {
		
		String reviewNo = param.getOrDefault("id", "").toString();
		Map<String, Object> reviewVo = reviewService.selectReviewDetail(reviewNo);
		String writer = reviewVo.getOrDefault("review_writer", "").toString();
		String content_user = principal.getName();
		int viewCount = Integer.parseInt(reviewVo.getOrDefault("view_count", "").toString());
		int likeCount = Integer.parseInt(reviewVo.getOrDefault("like_count", "").toString());
//		int likeCheck = reviewService.likeCheck(map);
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		String regDate = reviewVo.getOrDefault("regDate", "").toString();
		Date parseDate = sdf.parse(regDate);
				
//		System.err.println("like:"+ likeCheck);
		
		model.addAttribute("like_count", likeCount);
		model.addAttribute("view_Count", viewCount);
		model.addAttribute("reviewNo", reviewNo);
		model.addAttribute("check", content_user);
		model.addAttribute("title", reviewVo.getOrDefault("review_title", "").toString());
		model.addAttribute("writer", writer);
		model.addAttribute("regDate", parseDate);
		
		return "review/reviewDetail";
	}
	
}
