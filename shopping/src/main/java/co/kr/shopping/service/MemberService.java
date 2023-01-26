package co.kr.shopping.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.kr.shopping.dao.MemberMapper;

@Service
public class MemberService {
	
	@Autowired
	MemberMapper memberMapper;
	
	public int insertMember(Map<String, Object> param) {
		return memberMapper.insertMember(param);
	}
}
