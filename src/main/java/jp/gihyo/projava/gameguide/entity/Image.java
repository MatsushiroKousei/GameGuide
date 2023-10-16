package jp.gihyo.projava.gameguide.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;

import java.sql.Blob;

@Entity //DBのテーブル構造を表したオブジェクト
@Table(name = "image")  //テーブル名の設定
@Getter //依存関係Lombok「取得する」を自動生成してくれる
@Setter //依存関係Lombok「セットする」を自動生成してくれる
@NoArgsConstructor
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String type;
//    @OneToOne(mappedBy = "blog")
//    private Blog blog;

    @Lob // ポイント2: @Lobと@Typeを以下のようにつける(@Lobはサイズが大きいデータのカラムにつけるみたい。@Typeがないと「bigintのデータが出力されてますよ」的なエラーが出る
//    @Type(type = "org.hibernate.type.BinaryType")
    @Column(name = "data")
    private byte[] data;

    public Image(String name, String type, byte[] data) {
        this.name = name;
        this.type = type;
        this.data = data;
    }
}

//    @Entity //DBのテーブル構造を表したオブジェクト
//    @Table(name = "image")  //テーブル名の設定
//    @Getter //依存関係Lombok「取得する」を自動生成してくれる
//    @Setter //依存関係Lombok「セットする」を自動生成してくれる
//    public class Image {
//        @Id
//        @GeneratedValue(strategy = GenerationType.IDENTITY)  //主キーが自動生成される
//        private Integer id;
//
//        @Lob
//        @Column(name = "data")
//        private Blob data;
//
//        @OneToOne
//        @JoinColumn(name = "blog_id")
//        private Image image;

//    @OneToOne(mappedBy = "blog")
//    private Blog blog;
//    }
