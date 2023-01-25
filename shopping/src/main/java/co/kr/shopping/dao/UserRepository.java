package co.kr.shopping.dao;

import java.util.Optional;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.data.jpa.repository.JpaRepository;
import co.kr.shopping.utils.User;

@Mapper
public interface UserRepository extends JpaRepository<User, Long>{
	User findByUsername(String username);
}
