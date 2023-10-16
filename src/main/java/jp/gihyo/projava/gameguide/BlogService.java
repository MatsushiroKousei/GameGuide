package jp.gihyo.projava.gameguide;

import jakarta.persistence.criteria.CriteriaBuilder;
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

    List<Blog> blogGetAll(){
       return blogRepository.findAll();
    }
    public void create(BlogRequest blogRequest) {

        blogRepository.save(CreateBlog(blogRequest));//??
    }
    public List<Blog> getBlogTop3() {return blogRepository.getBlogList();}//BlogRepositoryのQuery:getBlogList()が使える

    public List<Blog> BlogDate() {return blogRepository.getDate();}//logRepositoryのQuery:getDate()が使える

    Blog getByIdBlog(Integer id) {return blogRepository.getByIdBlog(id);}//logRepositoryのQuery:getByIdBlog(@Param("id")Integer id)が使える

    public void save(Blog con) {blogRepository.save(con);}//??

    public void deleteByIdBlog(Blog id) {blogRepository.delete(id);}//??

    public void blogUpdate(String text, String title, Integer id) {blogRepository.upDateBlog(text,title,id);}

    public List<Blog> partsSearch(String title) {return blogRepository.partsSearch(title);}
    /**
     * Creaateはリクエストを受け取ってentityクラスをセットして返す
     *
     */
    private Blog CreateBlog(BlogRequest blogRequest){
        Blog blog = new Blog();
        blog.setText(blogRequest.getContents());
        blog.setTitle(blogRequest.getTitle());
        blog.setViewCount(0);
        blog.setGoodCount(0);
        blog.setCreatedDate(new Date());
        return blog;
    }
}
