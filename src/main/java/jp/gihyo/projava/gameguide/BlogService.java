package jp.gihyo.projava.gameguide;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.persistence.criteria.CriteriaBuilder;
import jp.gihyo.projava.gameguide.entity.Blog;
import jp.gihyo.projava.gameguide.repository.BlogJdbcRepository;
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
    @Autowired
    EntityManager en;


    List<Blog> blogGetAll(){
       return blogRepository.findAll();
    }
    public void create(BlogRequest blogRequest) {
        blogRepository.save(CreateBlog(blogRequest));
    }

    public List<Blog> getBlogTop3() {return blogRepository.getBlogList();}
    public List<Blog> BlogDate() {return blogRepository.getDate();}
    public List<Blog> find(String title) {
        List<Blog> blogs = en.createQuery("from Blog where title = :title",Blog.class)
                .setParameter("title", title)
                .getResultList();
        return blogs;
    }
    public List<Blog> pageBlog(int x,int y){
        return en.createQuery("from Blog",Blog.class)
                .setFirstResult(x).setMaxResults(y).getResultList();
    }


    Blog getByIdBlog(Integer id) {return blogRepository.getByIdBlog(id);}

    public void save(Blog con) {blogRepository.save(con);}

    public void deleteByIdBlog(Blog id) {blogRepository.delete(id);}

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
        blog.setCreatedDate(new Date());
        return blog;
    }

//    public void search(BlogsRequest blogsRequest) {
//        blogRepository.save(SearchBlog(blogsRequest));
//    }
    /**
     * Searchはリクエストを受け取ってentityクラスをセットして返す
     *substringはList型には使えない。
     */
//    private Blog SearchBlog(BlogsRequest blogsRequest){
//        Blog blogList = new Blog();
//        blogList.setText(blogsRequest.getContents());
//        String gtc = blogsRequest.getContents();
//        blogList.setText(blogsRequest.getContents());
//        blogList.setTitle(blogsRequest.getTitle());
//        blogList.setViewCount(0);
//        blogList.setCreatedDate(new Date());
//        return blogList;
//    }
}
