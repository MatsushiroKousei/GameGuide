package jp.gihyo.projava.gameguide.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.util.Date;

@Entity
@Table(name = "blog")
@Getter
@Setter
public class Blog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  //主キーが自動生成される
    private Integer id;

    @Column(name="title", length = 30)
    private String title;

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

    @OneToOne
    @JoinColumn(name = "image_id")
    private Image image;
}
