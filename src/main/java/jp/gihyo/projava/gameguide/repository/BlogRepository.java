package jp.gihyo.projava.gameguide.repository;

import jp.gihyo.projava.gameguide.entity.Blog;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface

BlogRepository extends JpaRepository<Blog,Integer> {
    @Query("SELECT m FROM Blog m order by m.viewCount desc")
    List<Blog> getBlogList();

    @Query("SELECT m FROM Blog m order by m.createdDate desc")
    List<Blog> getDate();
}