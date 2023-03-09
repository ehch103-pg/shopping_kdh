package co.kr.shopping.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import co.kr.shopping.vo.PaginationVO;
import co.kr.shopping.vo.ProductVO;

@Mapper
public interface ProductMapper {

	public List<ProductVO> selectProductList(String keyword, PaginationVO paging);
	
	public ProductVO selectProductDetail(@Param("product_id") String product_id);

	public int insertProduct(ProductVO product);
}
