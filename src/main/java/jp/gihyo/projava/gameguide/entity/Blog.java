package jp.gihyo.projava.gameguide.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.util.Date;

@Entity //DBのテーブル構造を表したオブジェクト
@Table(name = "blog") //テーブル名の設定
@Getter //依存関係Lombok「取得する」を自動生成してくれる
@Setter //依存関係Lombok「セットする」を自動生成してくれる
public class Blog { //DBに操作してもらうた為の情報（Entityクラス）にセット
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  //主キーが自動生成される
    private Integer id;

    @NotEmpty
    @Column(name="title", length = 30) //@Column:テーブルのカラム情報を示す
    private String title;

    @NotEmpty
    @Column(name = "text", length = 1000)
    private String text;

    @Column(name = "view_count")
    private Integer viewCount;

    @Column(name = "created_date",updatable = false)
    @CreatedDate    //レコードの作成日時がフィールドに自動的に設定される
    private Date createdDate;

    @Column(name = "updated_date")
    @LastModifiedDate       //更新の際レコードが生成される
    private Date updatedDate;

    @Column(name = "good_count")
    private Integer goodCount;

//    @OneToOne(mappedBy = "image",orphanRemoval=true)
//    private Image image;
}
