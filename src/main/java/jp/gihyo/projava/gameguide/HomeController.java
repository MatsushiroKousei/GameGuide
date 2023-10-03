package jp.gihyo.projava.gameguide;

import jp.gihyo.projava.gameguide.entity.Blog;
import jp.gihyo.projava.gameguide.repository.BlogPagingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

        model.addAttribute("blogs", blogs);
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

//    @GetMapping("/search")
//    public String searchBlog(@RequestParam("search") String title, Model model) {
//        List<Blog> blogs2 = service.partsSearch(title);
//
//
//        if (blogs2.size() == 0) {
//            model.addAttribute("empty", "記事がありません。");
//        }
//        model.addAttribute("blogs", blogs2);
//        return "search";
//    }
//    @Autowired
//    BlogPagingRepository blogPagingRepository;
//
//    @RequestMapping("/search")
//    public String showBlogPagingList(Model model, Pageable pageable, @RequestParam(name = "search", required = true) String title) {
//        Page<Blog> pageList = blogPagingRepository.findByTitleContaining(title, pageable);
//        //検索結果を保存するための JavaBeans(リスト）を用意
//        List<Blog> itemList = pageList.getContent();
//        //商品情報をリクエストスコープに保存
//        model.addAttribute("title", title);
//        model.addAttribute("pages", pageList);
//        model.addAttribute("items", itemList);
//        return "search";
//    }

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
}
