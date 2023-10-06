package jp.gihyo.projava.gameguide;


import jakarta.persistence.criteria.CriteriaBuilder;
import jp.gihyo.projava.gameguide.entity.Blog;
import jp.gihyo.projava.gameguide.repository.BlogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.parser.Entity;
import java.util.List;

@Controller
public class HomeController {
    @Autowired
    BlogService service;

    @GetMapping("/index")
    String index(Model model) {
        List<Blog> blogs = service.getBlogTop3();
        List<Blog> blogs1 = service.BlogDate();
        model.addAttribute("blogs", blogs);
        model.addAttribute("blogsDate", blogs1);
        return "index";
    }

    @GetMapping("/postblog")
    String postBlog(Model model) {
        model.addAttribute("blogRequest", new BlogRequest());
        return "postblog";
    }

    @GetMapping("/blog/{id}")
    String viewBlog(Model model, @PathVariable("id") Integer id) {
        Blog blog = service.getByIdBlog(id);
        Integer count = blog.getViewCount();
        count++;
        blog.setViewCount(count);
        model.addAttribute("blog", blog);
        model.addAttribute("BlogId",id);
        service.save(blog);
        return "blog";
    }

    @RequestMapping("/blog/add")
    String post(Model model, @ModelAttribute BlogRequest blogRequest) {
        service.create(blogRequest);
        return "redirect:/index";
    }

    @GetMapping("/blog/delete/{id}")
    public String deleteBlog(@PathVariable Integer id, Model model) {
        Blog blog = service.getByIdBlog(id);
        service.deleteByIdBlog(blog);
        return "redirect:/index";
    }

    @GetMapping("/search")
    public String searchBlog(@RequestParam("search") String title, Model model) {
        List<Blog> blogs2 = service.partsSearch(title);


        if (blogs2.size() == 0) {
            model.addAttribute("empty", "記事がありません。");
        }
        model.addAttribute("blogs", blogs2);
        return "search";
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
        System.out.println(Goodcount);
        return "redirect:/blog/" + id;
    }
    @GetMapping("/update/{id}")
    String blogUpdate(@PathVariable("id")Integer id, Model model){
        Blog blog = service.getByIdBlog(id);
        BlogRequest br = new BlogRequest();
        br.setTitle(blog.getTitle());
        br.setContents(blog.getText());
        model.addAttribute("blogRequest",br);
        return "postblog";
    }
}
