package jp.gihyo.projava.gameguide;

import jp.gihyo.projava.gameguide.entity.Blog;
import jp.gihyo.projava.gameguide.repository.BlogRepository;
import jp.gihyo.projava.gameguide.repository.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BlogService {
    @Autowired
    BlogRepository blogRepository;

    @Autowired
    ImageRepository imageRepository;

    List<Blog> blogGetAll(){
       return blogRepository.findAll();
    }
}
