package jp.gihyo.projava.gameguide;

import jp.gihyo.projava.gameguide.entity.Blog;
import jp.gihyo.projava.gameguide.repository.BlogPagingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public class BlogPagingService {

    @Autowired
    BlogPagingRepository blogPagingRepository;

    public Page<Blog> findByTitleContaining(String title, Pageable pageable) {
        return blogPagingRepository.findByTitleContaining(title, pageable);
    }

}
