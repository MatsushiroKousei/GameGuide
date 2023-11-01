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
        List<Blog> blogs = service.getBlogTop3();            //serviceクラスのgetBlogTop3()が呼び出される
        List<Blog> blogs1 = service.BlogDate();             //serviceクラスのBlogDate()が呼び出される
        model.addAttribute("blogs", blogs);     //List<Blog> blogs が"blogs"としてhtmlで扱えるようになる
        model.addAttribute("blogsDate", blogs1);//List<Blog> blogs1が"blogs1"としてhtmlで扱えるようになる
        return "index"; //ホームページ
    }

    @GetMapping("/index")
    String index(Model model) {
        List<Blog> blogs = service.getBlogTop3();            //serviceクラスのgetBlogTop3()が呼び出される
        List<Blog> blogs1 = service.BlogDate();             //serviceクラスのBlogDate()が呼び出される
        model.addAttribute("blogs", blogs);     //List<Blog> blogs が"blogs"としてhtmlで扱えるようになる
        model.addAttribute("blogsDate", blogs1);//List<Blog> blogs1が"blogs1"としてhtmlで扱えるようになる
        return "index"; //ホームページ
    }

    @GetMapping("/postblog")//
    String postBlog(Model model,BlogRequest blogRequest) {

        return "postblog";
    }

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

    @PostMapping("/blog/add")
    String post(Model model, @Validated @ModelAttribute BlogRequest blogRequest,BindingResult result) {
        if (result.hasErrors()){
            return postBlog(model,blogRequest);
        }
        service.create(blogRequest);
        return "redirect:/index";
    }
    @GetMapping("/blog/delete/{id}")
    public String deleteBlog(@PathVariable Integer id) {
        Blog blog = service.getByIdBlog(id);
        service.deleteByIdBlog(blog);
        return "redirect:/index";
    }


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

    @GetMapping("/update/{id}")
    String GetUpdate(@PathVariable("id") Integer id, Model model) {
        Blog blog = service.getByIdBlog(id);
        BlogRequest br = new BlogRequest();
        br.setTitle(blog.getTitle());
        br.setContents(blog.getText());
        br.setId(id);
        model.addAttribute("blogRequest", br);
        return "edit";
    }
        @PostMapping("/blog/edit")
    public String editBlog(@ModelAttribute BlogRequest request) {
        Blog blog = service.getByIdBlog(request.getId());
        blog.setTitle(request.getTitle());
        blog.setText(request.getContents());
        service.save(blog);
                return "redirect:/index";
    }
}
