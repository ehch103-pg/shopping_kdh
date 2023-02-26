package co.kr.shopping.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.kr.shopping.mapper.ProductMapper;
import co.kr.shopping.vo.PaginationVO;
import co.kr.shopping.vo.ProductVO;

@Service
public class ProductService {

	@Autowired
	ProductMapper productMapper;
	
	public List<ProductVO> ProductList(PaginationVO paging, Map<String, Object> param){
		return productMapper.selectProductList(param, paging);
	}
	
	public ProductVO productDetail(String reviewNo) {
		return productMapper.selectProductDetail(reviewNo);
	}
	
	public void ProductRegister(ProductVO product) {
		productMapper.insertProduct(product);
	}
	
}
