package jp.gihyo.projava.gameguide;


import jp.gihyo.projava.gameguide.domain.BlogDto;
import jp.gihyo.projava.gameguide.entity.Blog;
import jp.gihyo.projava.gameguide.service.BlogJdbcService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
    @GetMapping("/search/{page}")
    public String searchBlog(@RequestParam("search") String title,Model model,
                             @PathVariable("page")Integer page){
        List<BlogDto> blogs = blogService.searchBlogs(title);
        List<BlogDto> blogPage = blogService.searchPage(title, pageCount(page));
        page++;
       model.addAttribute("blogs", blogPage);
       model.addAttribute("title",title);
       model.addAttribute("page",page);
        return "search";
    }
    private String pageCount(Integer page){
        if (page == 0){
            return String.valueOf(page);
        }
        int y = page * 5;
        return String.valueOf(y);
    }
}