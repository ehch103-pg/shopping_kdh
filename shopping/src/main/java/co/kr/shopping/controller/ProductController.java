package co.kr.shopping.controller;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import co.kr.shopping.service.ProductService;
import co.kr.shopping.vo.PaginationVO;
import co.kr.shopping.vo.ProductVO;

@Controller
@RequestMapping("/product")
public class ProductController {
	
	@Autowired
	ProductService productService;
	
	private String rootUrl = "product";
	
	@GetMapping("/productList")
	public String productList(Model model, @RequestParam(required = false) Map<String, Object> param){
		List<ProductVO> product = new ArrayList<>();
		PaginationVO paging = new PaginationVO();
		product = productService.ProductList(paging, param);
		
		
		return rootUrl+"/productList";
	}
	
	@GetMapping("/productDetail")
	public String productDetail(Model model, @RequestParam Map<String, Object> param) {
		String productId = param.getOrDefault("productCd", "").toString();
		
		model.addAttribute("Id", productId);
		
		return rootUrl + "productDetail";
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/productReg")
	public String productReg(Model model) {
		
		return rootUrl + "/productReg";
	}
	
	@PostMapping
	@ResponseBody
	public Map<String, Object> productUpload(@RequestParam(required = false) MultipartFile files, @RequestParam Map<String, Object> param, Model model) {
		Map<String, Object> result = new HashMap<String, Object>();
	
		return result;
	}
}
