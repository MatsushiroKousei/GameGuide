package jp.gihyo.projava.gameguide;


import jakarta.persistence.criteria.CriteriaBuilder;
import jp.gihyo.projava.gameguide.domain.BlogDto;
import jp.gihyo.projava.gameguide.entity.Blog;
import jp.gihyo.projava.gameguide.repository.BlogRepository;
import jp.gihyo.projava.gameguide.service.BlogJdbcService;
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
    @Autowired
    BlogJdbcService blogService;

    @GetMapping("/index")
    String index(Model model) {
        List<BlogDto> topBlogs = blogService.findTop();
        List<BlogDto> newBlogs = blogService.findNew();
        model.addAttribute("topBlogs",topBlogs);
        model.addAttribute("newBlogs",newBlogs);

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
        List<BlogDto> blogs2 = blogService.searchBlogs(title);
       model.addAttribute("blogs", blogs2);
        return "search";
    }
}