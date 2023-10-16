package jp.gihyo.projava.gameguide.repository;

import jp.gihyo.projava.gameguide.entity.Blog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.RequestParam;

public interface

BlogPagingRepository extends JpaRepository<Blog,Integer> { //entityを元にDBを操作
    //    @Query("SELECT m FROM Blog m WHERE m.title LIKE %:title" + "%")
//    Page<Blog> findByTitleContaining(@Param("title") String title, Pageable pageable);
//    @Query("SELECT m FROM Blog m WHERE m.title LIKE %:title" + "%")
//    Page<Blog> findByTitleContaining(String title, Pageable pageable);
    @Query("SELECT m FROM Blog m WHERE m.title LIKE %:title" + "%")
    Page<Blog> findByTitleContaining(String title, Pageable pageable);
}
