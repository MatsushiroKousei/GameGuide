package jp.gihyo.projava.gameguide;

import jp.gihyo.projava.gameguide.entity.Blog;
import jp.gihyo.projava.gameguide.repository.BlogRepository;
import jp.gihyo.projava.gameguide.repository.ImageRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.List;
import java.util.Map;

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
//    public <LIst> List<HomeController.SearchItem> search(Integer id,String title,String text,Integer viewCount){
//        String query = "SELECT * FROM blog WHERE search_data like '" + id + "'%' AND title '%"+ text + "%' AND '%"+viewCount+"%'";
//        if(done.equals("true")){
//            query += " AND done = true";
//        }
//        if(done.equals("false")){
//            query += " AND done = false";
//        }
//        List<Map<String, Object>> result = this.blogRepository.queryForList(query);
//        List<HomeController.SearchItem> list = result.stream().map(
//                (Map<String, Object> row) -> new HomeController.SearchItem(
//                        row.get("id").toString(),
//                        row.get("title").toString(),
//                        row.get("text").toString(),//,
//                        row.get("viewCount").toString()
////                        (Boolean)row.get("done")
//
//                )).toList();
//        return list;
//    }
}
