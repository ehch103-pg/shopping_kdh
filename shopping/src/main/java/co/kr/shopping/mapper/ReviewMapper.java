package co.kr.shopping.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import co.kr.shopping.vo.PaginationVO;
import co.kr.shopping.vo.ReviewVO;

@Mapper
public interface ReviewMapper {
	
	List<ReviewVO> selectReviewList(@Param("keyword") String keyword, @Param ("option") String option, @Param("paging") PaginationVO paging);
	
	Map<String, Object> selectReviewDetail(@Param("reviewNo") String reviewNo);
	
	int insertReview(ReviewVO review);
	
	int updateReview(ReviewVO review);

	boolean selectCheck(String reviewNo);
	
	int delete(ReviewVO review); 
	
	int totalReviewCount(@Param("keyword") String keyword, @Param("option") String option);

	void updateReviewLike(Map<String, Object> param);

	void insertReviewLike(Map<String, Object> param);
	
	int likeCheck(Map<String, Object> map);

	int selectViewCount(@Param("reviewNo") String reviewNo);

	int findlikeEx(Map<String, Object> map);
}
