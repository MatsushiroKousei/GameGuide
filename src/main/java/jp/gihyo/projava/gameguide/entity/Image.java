package jp.gihyo.projava.gameguide.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "image")  //テーブル名の設定
@Getter
@Setter
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  //主キーが自動生成される
    private Integer id;

    @Lob
    @Column(name = "data")
    private byte[] data;

    @OneToOne(mappedBy = "blog")
    private Blog blog;
}
