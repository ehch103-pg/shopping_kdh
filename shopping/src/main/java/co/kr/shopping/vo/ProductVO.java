package co.kr.shopping.vo;

import lombok.Data;

@Data
public class ProductVO {
	
	private int productSeq;
	private String productId;
	private String productName;
	private String productIntro;
	private String productPrice;
	private String productRegDate;
	private String fileSeq;
}
