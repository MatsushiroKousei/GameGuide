package jp.gihyo.projava.gameguide.repository;

import jp.gihyo.projava.gameguide.entity.Blog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface
BlogRepository extends JpaRepository<Blog,Integer> {

//    List<Blog> findAllOrderByAllId(String createdDate);

}
