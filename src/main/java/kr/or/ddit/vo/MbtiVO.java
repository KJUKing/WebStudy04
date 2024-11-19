package kr.or.ddit.vo;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class MbtiVO {

//    @NotNull
    private Long mtSort;
    @NotBlank
    private String mtType;

    @NotBlank
    private String mtTitle;
    private String mtContent;


}
