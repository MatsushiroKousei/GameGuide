package jp.gihyo.projava.gameguide;


import jp.gihyo.projava.gameguide.entity.Blog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class HomeController {
    @Autowired
    BlogService service;
    @GetMapping("/index")
    String index(Model model){
        List<Blog> blogs = service.blogGetAll();
        model.addAttribute("blogs" , blogs);
    return "index";
    }

}
