package co.kr.shopping.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import co.kr.shopping.vo.PaginationVO;
import co.kr.shopping.vo.ReviewVO;

@Mapper
public interface ReviewMapper {
	
	public List<ReviewVO> selectReviewList(@Param("keyword") Map<String, Object> keyword, @Param("paging") PaginationVO page);
	
	public ReviewVO selectReviewDetail(@Param("param") Map<String, Object> param);
	
	public int insertReview(@Param("param") Map<String, Object> param);
	
	public int updateReview(@Param("param") Map<String, Object> param);
	
	public int delete(@Param("param") Map<String, Object> param); 
	
	public int totalReviewCount(@Param("keyword") Map<String, Object> keyword);
}
