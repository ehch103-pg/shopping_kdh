package co.kr.shopping.vo;

import lombok.Data;

@Data
public class ReviewVO {
	private int reviewNo;
	private String reviewTitle;
	private String reviewContents;
	private String reviewLock;
	private String reviewDate;
	private String DelTime;
	private String DelYn;
	
}
