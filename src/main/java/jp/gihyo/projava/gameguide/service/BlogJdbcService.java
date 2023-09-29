package jp.gihyo.projava.gameguide.service;

import jp.gihyo.projava.gameguide.domain.BlogDto;
import jp.gihyo.projava.gameguide.repository.BlogJdbcRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class BlogJdbcService {
    @Autowired
    BlogJdbcRepository repository;

    public List<BlogDto> findTop(){
        List<Map<String,Object>> getList = repository.findTop3();
        return createBlog(getList);
    }
    public List<BlogDto> findNew(){
        List<Map<String,Object>> getList = repository.findNew3();
        return createBlog(getList);
    }
    public List<BlogDto> searchBlogs(String title){
        List<Map<String,Object>> getList = repository.findSearch(title);
        return createBlog(getList);
    }

    private List<BlogDto> createBlog(List<Map<String,Object>> getList){
        List<BlogDto> blogs = new ArrayList<>();
        for (Map<String,Object>map : getList ){
            BlogDto blog = new BlogDto();
            blog.setId((Integer) map.get("id"));
            blog.setTitle((String)map.get("title"));
            blog.setText((String)map.get("text"));
            blog.setViewCount((Integer)map.get("view_count"));
            LocalDateTime time = (LocalDateTime) map.get("created_date");
            //localDateTimeをLocalDate型に変換する
            LocalDate date = LocalDate.of(time.getYear(),time.getMonth(),time.getDayOfMonth());
            blog.setCreateDate(date);
            blogs.add(blog);
        }
        return blogs;
    }
}
