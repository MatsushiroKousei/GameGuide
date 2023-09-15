package jp.gihyo.projava.gameguide;

import jp.gihyo.projava.gameguide.entity.Blog;
import jp.gihyo.projava.gameguide.repository.BlogRepository;
import jp.gihyo.projava.gameguide.repository.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.List;

@Service
public class BlogService {
    @Autowired
    BlogRepository blogRepository;

    @Autowired
    ImageRepository imageRepository;

    public List<Blog> blogGetAll(){
       return blogRepository.findAll();
    }

//    public List<Blog> blogGetA112(){
//        return blogRepository.findAll(Sort.by(Sort.Direction.ASC, "createdDate"));
//    }


//        public List<Blog> findAll(){
//       return blogRepository.findAll();
//    }

    public void create(BlogRequest blogRequest) {
        blogRepository.save(CreateBlog(blogRequest));
    }

    /**
     * Creaateはリクエストを受け取ってentityクラスをセットして返す
     *
     */
    private Blog CreateBlog(BlogRequest blogRequest){
        Blog blog = new Blog();

        blog.setText(blogRequest.getContents());
        blog.setTitle(blogRequest.getTitle());
        blog.setViewCount(0);
        blog.setCreatedDate(new Date());
        return blog;
    }

    public void search(BlogsRequest blogsRequest) {
        blogRepository.save(SearchBlog(blogsRequest));
    }

    /**
     * Searchはリクエストを受け取ってentityクラスをセットして返す
     *substringはList型には使えない。
     */
    private Blog SearchBlog(BlogsRequest blogsRequest){
        Blog blogList = new Blog();
//        blogList.setText(blogsRequest.getContents());
//        String gtc = blogsRequest.getContents();
        blogList.setText(blogsRequest.getContents());
        blogList.setTitle(blogsRequest.getTitle());
        blogList.setViewCount(0);
        blogList.setCreatedDate(new Date());
        return blogList;
    }

}
