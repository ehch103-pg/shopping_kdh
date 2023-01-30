package co.kr.shopping.mapper;


import org.springframework.data.repository.CrudRepository;
import co.kr.shopping.vo.MemberVO;

public interface MemberMapper extends CrudRepository<MemberVO, Long>  {
		
}
