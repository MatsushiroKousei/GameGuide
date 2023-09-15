package jp.gihyo.projava.gameguide;

import jp.gihyo.projava.gameguide.repository.BlogRepository;
import jp.gihyo.projava.gameguide.entity.Blog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
public class HomeController {
    @Autowired
    BlogService service;

    @GetMapping("/index")
    String index(Model model){
        List<Blog> blogs = service.blogGetAll();
        Blog top1 = blogs.get(0);
        model.addAttribute("blogs" , blogs);
        model.addAttribute("top1",top1);
    return "index";
    }
    @GetMapping("/postblog")
    String postblog(Model model){
        model.addAttribute("blogRequest" , new BlogRequest());
        return "postblog";
    }

    @RequestMapping("/blog/add")
    String post(Model model, @ModelAttribute BlogRequest blogRequest){
        service.create(blogRequest);
        return "redirect:/index";
    }

        @RequestMapping("/search")
        public String blogList(Model model, @ModelAttribute BlogsRequest blogsRequest) {
        List<Blog> blogs = service.blogGetAll();
        model.addAttribute("blogs",blogs );
        service.search(blogsRequest);
        return "search";
    }

//    @RequestMapping(path = "/blog/{id}")
//    public String showEmployee(@PathVariable int id, Model model) {
//        List<Blog> blog = BlogRepository.getOne(id);
//        model.addAttribute("blog", blog);
//        return "blog/{id}";
//    }

//    @RequestMapping("/blog/{id}")
//    public String blog(Model model, int id, @ModelAttribute BlogsRequest blogsRequest) {
//        Blog blog = blog.get();
//        String id = Repository.getOne(id);
//        model.addAttribute("blog" , blog);
//        model.addAttribute("blog", blog);
//        return "/blog/{id}";
//    }
//
//    @RequestMapping(value="/find", method=RequestMethod.POST)
//    public String find(Model model, @RequestParam("find") int id) {
//        MyData data = repository.getOne(id);
//        model.addAttribute("datas", data);
//        return "helo";
//    }

}
