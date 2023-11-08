package jp.gihyo.projava.gameguide;


import jakarta.persistence.criteria.CriteriaBuilder;
import jp.gihyo.projava.gameguide.entity.Blog;
import jp.gihyo.projava.gameguide.repository.BlogRepository;
import org.apache.coyote.Request;
import org.hibernate.sql.Update;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.parser.Entity;
import java.util.List;

import static java.awt.SystemColor.info;
import static java.awt.SystemColor.text;

@Controller
public class HomeController {
    @Autowired
    BlogService service;

    @GetMapping("/")
    String toppage(Model model) {
        List<Blog> blogs = service.getBlogTop3();
        List<Blog> blogs1 = service.BlogDate();
        model.addAttribute("blogs", blogs);
        model.addAttribute("blogsDate", blogs1);
        return "index"; //ホームページ
    }

    @GetMapping("/index")
    String index(Model model) {
        List<Blog> blogs = service.getBlogTop3();
        List<Blog> blogs1 = service.BlogDate();
        model.addAttribute("blogs", blogs);
        model.addAttribute("blogsDate", blogs1);
        return "index";
    }

    /*
    投稿画面表示
     */
    @GetMapping("/postblog")
    String postBlog(Model model,BlogRequest blogRequest) {
        return "postblog";
    }

    /*
    各閲覧数を表示
     */
    @GetMapping("/blog/{id}")
    String viewBlog(Model model, @PathVariable("id") Integer id) {
        Blog blog = service.getByIdBlog(id);
        Integer count = blog.getViewCount();
        count++;
        blog.setViewCount(count);
        model.addAttribute("blog", blog);
        model.addAttribute("BlogId", id);
        service.save(blog);
        return "blog";
    }

    /*
    投稿処理
     */
    @PostMapping("/blog/add")
    String post(Model model, @ModelAttribute @Validated BlogRequest blogRequest,BindingResult result) {
        if (result.hasErrors()) {
            return postBlog(model,blogRequest);
        }
        service.create(blogRequest);
        return "redirect:/index";
    }

    /*
    削除処理
     */
    @GetMapping("/blog/delete/{id}")
    public String deleteBlog(@PathVariable Integer id) {
        Blog blog = service.getByIdBlog(id);
        service.deleteByIdBlog(blog);
        return "redirect:/index";
    }

    /*
    いいね数表示（閲覧数とほとんど同じ仕様）
     */
    @GetMapping("/good/{id}")
    String goodBlog(Model model, @PathVariable("id") Integer id) {
        Blog blog = service.getByIdBlog(id);
        Integer Goodcount = 0;
        try {

            Goodcount = blog.getGoodCount();
            Goodcount++;//Goodcount+=Goodcount+1;
        } catch (NullPointerException e) {
            Goodcount = 1;
        }
        blog.setGoodCount(Goodcount);
        model.addAttribute("blog", blog);
        service.save(blog);
        System.out.println(Goodcount);//確認の為の記述
        return "redirect:/blog/" + id;
    }

    /*
    更新画面表示
     */
    @GetMapping("/update/{id}")
    String GetUpdate(@PathVariable("id") Integer id, Model model,BlogRequest br) {
        Blog blog = service.getByIdBlog(id);
        br.setTitle(blog.getTitle()); //タイトルを更新画面にセット
        br.setContents(blog.getText()); //内容を更新画面にセット
        br.setId(id);
        return "edit";
    }

    /*
    更新処理
     */
        @PostMapping("/blog/edit")
    public String editBlog(Model model,@ModelAttribute @Validated BlogRequest request,BindingResult result,Integer id) {
        if (result.hasErrors()){
            return GetUpdate(id,model,request);
        }
        Blog blog = service.getByIdBlog(request.getId());
        blog.setTitle(request.getTitle());
        blog.setText(request.getContents());
        service.save(blog);
                return "redirect:/index";
    }
}
