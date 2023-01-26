package co.kr.shopping.dao;


import org.springframework.data.jpa.repository.JpaRepository;
import co.kr.shopping.vo.MemberVO;

public interface UserRepository extends JpaRepository<MemberVO, Long>{
	MemberVO findIdByUsername(String username);

}
