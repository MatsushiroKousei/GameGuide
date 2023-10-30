package jp.gihyo.projava.gameguide;



import jp.gihyo.projava.gameguide.entity.Blog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Controller
public class HomeController {
    @Autowired
    BlogService service;

    /*
    ホームページの昇順表示処理（新着順と人気順（閲覧数））
     */
    @GetMapping("/index")
    String index(Model model) {
        List<Blog> blogs = service.getBlogTop3();
        List<Blog> blogs1 = service.BlogDate();
        model.addAttribute("blogs", blogs);
        model.addAttribute("blogsDate", blogs1);
        return "index";
    }

    /**
     *投稿画面表示
     */
    @GetMapping("/postblog")
    String postBlog(Model model, BlogRequest request) {

        return "postblog";
    }

    /*
    閲覧数処理（クリックを１回押されると閲覧数が＋１される）
     */
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

    /*
    投稿送信（送る処理）
     */
    @PostMapping("/blog/add")
    public String add(Model model, @ModelAttribute @Validated BlogRequest blogRequest,BindingResult result) {

        //---バリデーション追加sart---
        if (result.hasErrors()) {
            return postBlog(model,blogRequest);
        }
        //---バリデーション追加end---

        service.create(blogRequest);
        return "redirect:/index";
    }

    /*
    削除処理
     */
    @GetMapping("/blog/delete/{id}")
    public String deleteBlog(@PathVariable Integer id) {
        Blog blog = service.getByIdBlog(id);
        service.deleteByIdBlog(blog);
        return "redirect:/index";
    }

    /*
    good機能（閲覧数と同じ原理）
     */
    @GetMapping("/good/{id}")
    String goodBlog(Model model, @PathVariable("id") Integer id) {
        Blog blog = service.getByIdBlog(id);
        Integer Goodcount = 0;
        //trycatch文でNullPointerExceptionが確認される場合はcatchで処理。カウントを１追加
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

    /*
    編集・更新画面の表示（idからのデータをセーブしてedit.htmlで表示）
     */
    @GetMapping("/update/{id}")
    String GetUpdate(@PathVariable("id") Integer id, Model model,BlogRequest request) {
        Blog blog = service.getByIdBlog(id);
        BlogRequest br = new BlogRequest();
        br.setTitle(blog.getTitle());
        br.setContents(blog.getText());
        br.setId(id);
        model.addAttribute("blogRequest", br);
        return "edit";
    }

    /**
     * 編集画面の変更内容を送信する処理
     */
    @PostMapping("/blog/edit")
    public String editBlog(@ModelAttribute BlogRequest request) {
        Blog blog = service.getByIdBlog(request.getId());
        blog.setTitle(request.getTitle());
        blog.setText(request.getContents());
        service.save(blog);
        return "redirect:/index";
    }
}
