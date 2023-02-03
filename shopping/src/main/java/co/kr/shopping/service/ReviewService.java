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
	
	public int selectReviewCount(String keyword) {
		return reviewMapper.totalReviewCount(keyword);
	}
	
	public List<ReviewVO> selectReviewList(String keyword, PaginationVO paging){
		return reviewMapper.selectReviewList(keyword, paging);
	}
	
	public Map<String, Object> selectReviewDetail(Map<String, Object> param){
		return reviewMapper.selectReviewDetail(param);
	}
	
	public int saveReview(ReviewVO review) {
		return reviewMapper.insertReview(review);
	}
}
