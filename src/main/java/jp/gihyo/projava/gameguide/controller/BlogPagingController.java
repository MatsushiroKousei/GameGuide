package jp.gihyo.projava.gameguide.controller;

import jp.gihyo.projava.gameguide.entity.Blog;
import jp.gihyo.projava.gameguide.repository.BlogPagingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class BlogPagingController {

//    @Autowired
//    BlogService service;

    @Autowired
    BlogPagingRepository blogPagingRepository;

    //    @RequestMapping("/blog/findAllPaging")
//    @RequestMapping("/search")
////    public String showBlogPagingList(Model model, Pageable pageable) {
//    public String showBlogPagingList(@RequestParam("search") String title, Model model, Pageable pageable) {
//        //ブログ情報を検策
////    Page<Blog> pageList = blogPagingRepository.findAll(pageable);
//
//
//        Page<Blog> pageList = blogPagingRepository.findByTitleContaining(title, pageable);
//
////    Page<Blog> pageList = service.partsSearch(title);
//        //検索結果を保存するための JavaBeans(リスト）を用意
//        List<Blog> itemList = pageList.getContent();
//
//        //商品情報をリクエストスコープに保存
//        model.addAttribute("pages", pageList);
//        model.addAttribute("items", itemList);
//
////    return "items/item_paging_list";
//        return "search";
//    }

    @RequestMapping("/search")
    public String showBlogPagingList(Model model, Pageable pageable, @RequestParam(name = "search", required = true) String title) {
//    public String showBlogPagingList(Model model, Pageable pageable) {
//    public String showBlogPagingList(@RequestParam("search") String title, Model model, Pageable pageable) {
//        public String showBlogPagingList(String title, Model model, Pageable pageable) {
        //ブログ情報を検策
//    Page<Blog> pageList = blogPagingRepository.findAll(pageable);


        Page<Blog> pageList = blogPagingRepository.findByTitleContaining(title, pageable);

        //検索結果を保存するための JavaBeans(リスト）を用意
        List<Blog> itemList = pageList.getContent();

        if (itemList.size()==0) {
            model.addAttribute("empty", "記事がありません。");
        }

        //商品情報をリクエストスコープに保存
        model.addAttribute("title", title);
        model.addAttribute("pages", pageList);
        model.addAttribute("items", itemList);

        return "search";
    }
}
