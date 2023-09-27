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
        Blog top1 = blogs.get(0);
        Blog top2 = blogs.get(1);
        Blog top3 = blogs.get(2);

        List<Blog> blogs1 = service.BlogDate();
        Blog Date1 = blogs1.get(0);
        Blog Date2 = blogs1.get(1);
        Blog Date3 = blogs1.get(2);

        model.addAttribute("blogs", blogs);
        model.addAttribute("top1", top1);
        model.addAttribute("top2", top2);
        model.addAttribute("top3", top3);
        model.addAttribute("Date1", Date1);
        model.addAttribute("Date2", Date2);
        model.addAttribute("Date3", Date3);

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
    public String searchBlog(@RequestParam("search") String title,Model model){
        List<Blog> blogs2 = service.partsSearch(title);
       model.addAttribute("blogs", blogs2);
        return "search";
    }
//    @RequestMapping("/search")
//    public String blogList(Model model) {
//        List<Blog> blogs = service.blogGetAll();
//        model.addAttribute("blogs",blogs );
//        service.search();
//        return "search";
//    }
}