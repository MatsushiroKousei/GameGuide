package jp.gihyo.projava.gameguide;



import jp.gihyo.projava.gameguide.entity.Blog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Controller
public class HomeController implements WebMvcConfigurer{
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/results").setViewName("results");
    }
    @Autowired
    BlogService service;



        //ホームページ新着と人気投稿の表示start
    @GetMapping("/index")
    String index(Model model) {
        List<Blog> blogs = service.getBlogTop3();
        List<Blog> blogs1 = service.BlogDate();
        model.addAttribute("blogs", blogs);
        model.addAttribute("blogsDate", blogs1);
        return "index";
    }
//ホームページ新着と人気投稿の表示end

    //
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
        List<Blog> blogs = service.getBlogTop3();
        model.addAttribute("blog", blog);
        model.addAttribute("BlogId", id);
        model.addAttribute("blogs", blogs);
        service.save(blog);
        return "blog";
    }

    //
    @PostMapping("/blog/add")
    public String checkPersonInfo(BlogRequest blogRequest, Model model) {
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
