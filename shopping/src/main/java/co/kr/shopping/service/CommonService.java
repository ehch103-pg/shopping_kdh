package co.kr.shopping.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.kr.shopping.mapper.CommonMapper;

@Service
public class CommonService {
	
	@Autowired
	CommonMapper commonMapper;
	
	public Map<String, Object> searchFile(String id) {
		return commonMapper.selectFile(id);
	}
	
}
