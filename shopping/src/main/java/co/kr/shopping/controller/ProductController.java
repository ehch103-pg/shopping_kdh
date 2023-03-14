package co.kr.shopping.controller;

import java.io.File;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FilenameUtils;
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

import co.kr.shopping.service.CommonService;
import co.kr.shopping.service.ProductService;
import co.kr.shopping.vo.PaginationVO;
import co.kr.shopping.vo.ProductVO;

@Controller
@RequestMapping("/product")
public class ProductController {
	
	@Autowired
	ProductService productService;
	
	@Autowired
	CommonService commonService;
	
	@GetMapping("/productList")
	public String productList(Model model, @RequestParam(required = false) Map<String, Object> param){
		String keyword = param.getOrDefault("keyword", "").toString();
		List<ProductVO> product = new ArrayList<>();
		PaginationVO paging = new PaginationVO();

		product = productService.ProductList(keyword, paging);
		model.addAttribute("paging", paging);
		model.addAttribute("products", product);
		return "product/productList";
	}
	
	@GetMapping("/productDetail")
	public String productDetail(Model model, @RequestParam Map<String, Object> param) {
		String productId = param.getOrDefault("id", "").toString();
		
		ProductVO product = productService.selectProductInfo(productId);
		
		model.addAttribute("product", product);
		model.addAttribute("Id", productId);
		
		return "product/productDetail";
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/productReg")
	public String productReg(Model model, @RequestParam(required = false) String productId) {
		ProductVO product =  productService.selectProductInfo(productId);
		String button;
		if(product == null) {
			button = "<button class='btn btn-success' id='regBtn'> 등록 </button>";
		}else {
			button = "<button class='btn btn-warning' id='modBtn'> 수정 </button>";
		}
		
		model.addAttribute("Btn", button);
		return "product/productReg";
	}
	
	@PostMapping("/productUpload")
	@ResponseBody
	public Map<String, Object> productUpload(@RequestParam Map<String, Object> param, Model model) {
		Map<String, Object> result = new HashMap<String, Object>();
		
		ProductVO product = new ProductVO();
		product.setProductName(param.get("product_Nm").toString());
		product.setProductPrice(param.get("product_price").toString());
		product.setProductIntro(param.get("product_intro").toString());
		product.setProductId(param.get("product_kind").toString());
		int check = productService.ProductRegister(product);
		if(check == 0) {
			result.put("result", "F");
		}else {
			result.put("result", "S");
		}
		
		return result;
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/productDel")
	public String productDel(Model model) {
		return "product/productRmv";
	}
}
