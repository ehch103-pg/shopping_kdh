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
	
	public List<ProductVO> ProductList(String keyword, PaginationVO paging){
		return productMapper.selectProductList(keyword, paging);
	}
	
	
	public int ProductRegister(ProductVO product) {
		return productMapper.insertProduct(product);
	}

	public ProductVO selectProductInfo(String product_id) {
		// TODO Auto-generated method stub
		return productMapper.selectProductDetail(product_id);
	}
	
}
