package jp.gihyo.projava.gameguide.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Blob;

@Entity //DBのテーブル構造を表したオブジェクト
@Table(name = "image")  //テーブル名の設定
@Getter //依存関係Lombok「取得する」を自動生成してくれる
@Setter //依存関係Lombok「セットする」を自動生成してくれる
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  //主キーが自動生成される
    private Integer id;

    @Lob
    @Column(name = "data")
    private Blob data;

    @OneToOne
    @JoinColumn(name = "blog_id")
    private Image image;

//    @OneToOne(mappedBy = "blog")
//    private Blog blog;
}
