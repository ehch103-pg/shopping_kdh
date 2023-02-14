package co.kr.shopping.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.kr.shopping.mapper.ReviewMapper;
import co.kr.shopping.vo.PaginationVO;
import co.kr.shopping.vo.ReviewVO;

@Service
public class ReviewService {

	@Autowired
	ReviewMapper reviewMapper;
	
	public int selectReviewCount(String keyword, String option) {
		return reviewMapper.totalReviewCount(keyword, option);
	}
	
	public List<ReviewVO> selectReviewList(String keyword, String option, PaginationVO paging){
		return reviewMapper.selectReviewList(keyword, option,paging);
	}
	
	public boolean selectCheck(String reviewNo) {
		return reviewMapper.selectCheck(reviewNo);
	}
	
	public Map<String, Object> selectReviewDetail(String reviewNo){
		return reviewMapper.selectReviewDetail(reviewNo);
	}
	
	public int saveReview(ReviewVO review) {
		return reviewMapper.insertReview(review);
	}

	public int updateLike(Map<String, Object> param) {
		// TODO Auto-generated method stub
		return reviewMapper.updateReviewLike(param);
	}

	public int likeCheck(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return reviewMapper.likeCheck(map);
	}

	public int updateReview(ReviewVO reviewVo) {
		// TODO Auto-generated method stub
		return reviewMapper.updateReview(reviewVo);
	}

	public int findLike(Map<String, Object> map) {
		// TODO Auto-generated method stub
		int result = reviewMapper.findlikeEx(map);
		
		return result;
	}

	public int insertLike(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return  reviewMapper.insertReviewLike(map);
	}

	public void updateViewCount(String reviewNo) {
		// TODO Auto-generated method stub
		reviewMapper.updateViewCount(reviewNo);
	}
	
	public int likeCount(String reviewNo) {
		return reviewMapper.likeCount(reviewNo);
	}
	
}
