package co.kr.shopping.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import co.kr.shopping.vo.PaginationVO;
import co.kr.shopping.vo.ReviewVO;

@Mapper
public interface ReviewMapper {
	
	public List<ReviewVO> selectReviewList(@Param("keyword") String keyword, @Param ("option") String option, @Param("paging") PaginationVO paging);
	
	public Map<String, Object> selectReviewDetail(@Param("param") Map<String, Object> param);
	
	public int insertReview(ReviewVO review);
	
	public int updateReview(ReviewVO review);
	
	public int delete(ReviewVO review); 
	
	public int totalReviewCount(@Param("keyword") String keyword, @Param("option") String option);
}
