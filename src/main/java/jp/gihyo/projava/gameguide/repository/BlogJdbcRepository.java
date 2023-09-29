package jp.gihyo.projava.gameguide.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class BlogJdbcRepository {
    @Autowired
    JdbcTemplate jd;

    /**
     *閲覧回数が多い順に記事を三つ取得　多い順なのでDESC
     */
    public List<Map<String,Object>> findTop3(){
        String query = "SELECT id, title, text, created_date, view_count " +
                "FROM blog ORDER BY view_count DESC LIMIT 3";
        return jd.queryForList(query);
    }

    /**
     *投稿日時が新しい順に三つの記事を取得
     */
    public List<Map<String,Object>> findNew3(){
        String query ="SELECT id, title, text, created_date, view_count " +
                "FROM blog ORDER BY created_date ASC LIMIT 3";
        return jd.queryForList(query);
    }
    /**
     * タイトルで検索し、記事一覧を所得　部分検索
     */
    public List<Map<String,Object>> findSearch(String title){
        String query = "SELECT id, title, text, created_date, view_count " +
                "FROM blog WHERE title like '%" + title + "%'";
        return jd.queryForList(query);
    }
}
