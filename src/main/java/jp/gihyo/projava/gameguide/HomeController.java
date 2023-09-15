package jp.gihyo.projava.gameguide;


import jp.gihyo.projava.gameguide.entity.Blog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.parser.Entity;
import java.util.ArrayList;
import java.util.List;

@Controller
public class HomeController {
    @Autowired
    BlogService service;
    record SearchItem(Integer id,String title,String text,Integer viewCount){}
    private List<SearchItem> searchItems = new ArrayList<>();
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

    @GetMapping("/search")
    String listItems(Model model){
        model.addAttribute("SearchList",searchItems);
        return "search";
    }
//    <form action="/search">
//<!--            <label>新着順</label><input type="radio" name="order" value="新着順" checked>-->
//<!--            <label>人気順</label><input type="radio" name="order" value="人気順">-->
//            <input type="text" id="search" name="search" size="20" /><button>検索</button>
//<!--            <label>AND検索</label><input type="radio" name="keyword" value="AND" checked>-->
//<!--            <label>OR検索</label><input type="radio" name="keyword" value="OR">-->
//<!--            <label>NOT検索</label><input type="radio" name="keyword" value="NOT">-->
//        </form>

}
