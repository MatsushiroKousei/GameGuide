package jp.gihyo.projava.gameguide.repository;

import jp.gihyo.projava.gameguide.entity.Blog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface
BlogRepository extends JpaRepository<Blog,Integer> {
}
