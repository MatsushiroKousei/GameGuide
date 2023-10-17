package jp.gihyo.projava.gameguide;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
@Data
public class BlogRequest {
    private String title;

    private String contents;

    private Integer id;

}
