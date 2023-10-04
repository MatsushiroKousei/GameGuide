package jp.gihyo.projava.gameguide.repository;

import jp.gihyo.projava.gameguide.entity.Blog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface

BlogRepository extends JpaRepository<Blog,Integer> { //entityを元にDBを操作
    @Query(value = "SELECT m FROM Blog m order by m.viewCount desc")
    List<Blog> getBlogList();

    @Query(value = "SELECT m FROM Blog m order by m.createdDate desc")
    List<Blog> getDate();

    @Query(value = "SELECT m FROM Blog m WHERE m.id = :id")
    Blog getByIdBlog(@Param("id")Integer id);

    @Query("SELECT m FROM Blog m WHERE m.title LIKE %:title" + "%")
    List<Blog> partsSearch(@Param("title")String name);

}