//package jp.gihyo.projava.gameguide;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
//import org.springframework.jdbc.core.namedparam.SqlParameterSource;
//import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
//import org.springframework.stereotype.Service;
//import org.springframework.jdbc.core.JdbcTemplate;
//
//import java.util.List;
//import java.util.Map;
//@Service
//public class SearchListDao {
//
//    private final static String TABLE_NAME = "blog";
//    private final JdbcTemplate jdbcTemplate;
//
//    @Autowired
//    SearchListDao(JdbcTemplate jdbcTemplate) {
//        this.jdbcTemplate = jdbcTemplate;
//    }
//
//
//    public <LIst> List<HomeController.SearchItem> findAll(){
//        String query = "SELECT * FROM " + TABLE_NAME;
//        List<Map<String, Object>> result = this.jdbcTemplate.queryForList(query);
//        List<HomeController.SearchItem> list = getResult(result);
//        return list;
//    }
//
//    private List<HomeController.SearchItem> getResult(List<Map<String, Object>> result){
//        List<HomeController.SearchItem> list = result.stream().map(
//                (Map<String, Object> row) -> new HomeController.SearchItem(
//                        (int)row.get("id"),
//                        row.get("title").toString(),
//                        row.get("text").toString(),
//                        (int)row.get("view_count")
////                        (Boolean)row.get("done")
//
//                )).toList();
//        return list;
//    }
//}
