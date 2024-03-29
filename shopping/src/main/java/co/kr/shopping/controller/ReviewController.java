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
import org.springframework.security.core.Authentication;
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
import co.kr.shopping.service.ProductService;
import co.kr.shopping.service.ReviewService;
import co.kr.shopping.vo.MemberVO;
import co.kr.shopping.vo.PaginationVO;
import co.kr.shopping.vo.ReviewVO;

@Controller
@RequestMapping("/review")
public class ReviewController {

	@Autowired
	ReviewService reviewService;
	
	@Autowired
	MemberService memberService;
	
	@Autowired
	ProductService productService;
	
	@GetMapping("/reviewList")
	public String reviewList(Model model, @RequestParam Map<String, Object> param) {
		
		PaginationVO paging = new PaginationVO();
		if(param.get("page") == null || param.get("page").equals("")) {
			param.put("page", "1");
		}
		int page = Integer.parseInt((String) param.get("page"));
		String keyword = (String)param.getOrDefault("keyword", "");
		String option = (String)param.getOrDefault("option", "");
		
		int firstRecord = (page-1) * 10;
		paging.setCurrentPageNo(page);
		paging.setFirstRecordOnPage(firstRecord);
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
	
	@GetMapping("/reviewLockAlert")
	public String reviewLock(Model model) {
		return "/review/reviewLockAlert";
	}
	
	@GetMapping("/reviewWrite")
	public String reviewWrite(@RequestParam(required = false) Map<String, Object> param, Model model, Principal principal) throws Exception {
		String reviewNo = param.getOrDefault("id", "").toString();
		String productCd, productNm;
		if(reviewNo != null && reviewNo != "") {
				Map<String, Object> reviewVo = reviewService.selectReviewDetail(reviewNo);				
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
				String regDate = reviewVo.getOrDefault("regDate", "").toString();
				productCd = (String)reviewVo.get("review_product_Id");
				productNm = (String)reviewVo.get("product_name");
				Date parseDate = sdf.parse(regDate);
				model.addAttribute("check", "M");
				model.addAttribute("review", reviewVo);
				model.addAttribute("product_name", productNm);
				model.addAttribute("regDate", parseDate);
				model.addAttribute("productCd", productCd);
		}else {
			productCd = (String)param.getOrDefault("productCd", "");
			productNm = productService.selectProductInfo(productCd).getProductName();
			model.addAttribute("product_name", productNm);
			model.addAttribute("productCd", productCd);
			model.addAttribute("writer", principal.getName());
			model.addAttribute("check", "W");
		}
		model.addAttribute("id", reviewNo);
		return "review/reviewWrite";
	}
	
	@ResponseBody
	@PostMapping("/regRev")
	public Map<String, Object> registerReview(@RequestBody Map<String, Object> param){
		Map<String, Object> result = new HashMap<>();
		ReviewVO reviewVO = new ReviewVO();
		
		reviewVO.setReviewTitle(param.getOrDefault("title", "").toString());
		reviewVO.setReviewWriter(param.getOrDefault("writer", "").toString());
		reviewVO.setReviewContents(param.getOrDefault("content","").toString());
		reviewVO.setReviewProductId(param.getOrDefault("product","").toString());
				
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
		int reviewNo = Integer.parseInt((param.getOrDefault("reviewNo", "").toString()));
		String review_title = param.getOrDefault("title", "").toString();
		String review_writer = param.getOrDefault("writer", "").toString();
		String review_content = param.getOrDefault("content", "").toString();
		String review_product = param.getOrDefault("product", "").toString();
		
		ReviewVO reviewVo = new ReviewVO();
		reviewVo.setReviewNo(reviewNo);
		reviewVo.setReviewTitle(review_title);
		reviewVo.setReviewWriter(review_writer);
		reviewVo.setReviewContents(review_content);
		reviewVo.setReviewProductId(review_product);
	
		int check = reviewService.updateReview(reviewVo);
		
		
		if(check > 0) {
			result.put("msg", "리뷰가 수정되었습니다.");
			result.put("result", "S");
			result.put("url", "/review/reviewDetail?id="+reviewNo);
		}else {
			result.put("msg", "리뷰가 정상적으로 수정되지 않았습니다. 다시 시도해주시기 바랍니다.");
			result.put("result", "F");
		}
		
		return result;
	}
	
	@ResponseBody
	@PostMapping("/LikeProc")
	public Map<String, Object> likeCheck(@RequestBody Map<String, Object> param){
		Map<String, Object> map = new HashMap<>();
		String reviewno = param.getOrDefault("reviewNo", "").toString();
		String like_User = param.getOrDefault("like_user", "").toString();
		Map<String, Object> review = reviewService.selectReviewDetail(reviewno);
		int like_User_no = memberService.selectMember(like_User).getMemSeq();
		int result = 0;
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
			result = reviewService.updateLike(map);
			
		}else {
			map.put("like_switch", "1");
			result = reviewService.insertLike(map);
		}
		int like_count = reviewService.likeCount(reviewno);
		if(result == 1) {
			map.put("result", "S");
		}else {
			map.put("result", "F");
		}
		map.put("like_count", like_count);
		return map;
	}
	
	@GetMapping("/reviewDetail")
	public String reviewDetail(Model model, @RequestParam(required = false) Map<String, Object> param
			, Principal principal, HttpServletRequest request) throws ParseException {
		int likeCheck;
		String reviewNo = param.getOrDefault("id", "").toString();
		
		reviewService.updateViewCount(reviewNo);
		Map<String, Object> reviewVo = reviewService.selectReviewDetail(reviewNo);
		String writer = reviewVo.getOrDefault("review_writer", "").toString();
		int viewCount = Integer.parseInt(reviewVo.getOrDefault("view_count", "").toString());
			
		int likeCount = reviewService.likeCount(reviewNo);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		String regDate = reviewVo.getOrDefault("regDate", "").toString();
		Date parseDate = sdf.parse(regDate);
			
		Map<String, Object> map = new HashMap<>();
			
		if(principal != null) {
			MemberVO member =  memberService.selectMember(principal.getName());
			String content_user = member.getMemId();
			String user_role = member.getRole();
			int user_no = member.getMemSeq();
				
			map.put("review_no", reviewNo);
			map.put("mem_no", user_no);
			likeCheck = reviewService.likeCheck(map);
			model.addAttribute("check", content_user);
			model.addAttribute("role", user_role);
			model.addAttribute("like_check", likeCheck);
		}
		
		model.addAttribute("like_count", likeCount);
		model.addAttribute("view_Count", viewCount);
		model.addAttribute("reviewNo", reviewNo);
		model.addAttribute("title", reviewVo.getOrDefault("review_title", "").toString());
		model.addAttribute("content", reviewVo.getOrDefault("review_content", "").toString());
		model.addAttribute("writer", writer);
		model.addAttribute("regDate", parseDate);
		model.addAttribute("product_cd", reviewVo.getOrDefault("review_product_Id", "").toString());
		model.addAttribute("product_name", reviewVo.getOrDefault("product_name", "").toString());
		
		return "review/reviewDetail";
	}
	
}
