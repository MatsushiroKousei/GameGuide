package jp.gihyo.projava.gameguide;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
@Data
public class BlogRequest {

    /*
    @NotBlank ->空白も×
    @NotNull　->nullのみ×
    @NotEmpty ->空白のみ〇
    Validationで制限する文字、これらをtitleとcontentsに付与することにより、投稿に制限を掛ける
    @Size(min = "", message=" ") ->Validationに文字数制限を掛ける。
     */

    @NotBlank(message = "")
    @Size(min = 5,message = "タイトルは5文字以上記入してください。")
    private String title;

    @NotBlank(message = "")
    @Size(min = 30,message = "内容は30文字以上記入してください。")
    private String contents;

    private Integer id;

}
