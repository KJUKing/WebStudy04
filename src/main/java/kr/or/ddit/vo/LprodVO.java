package kr.or.ddit.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class LprodVO implements Serializable{
    private Long lprodId;
    private String lprodGu;
    private String lprodNm;

    private List<ProdVO> prodList; // Has Many (1:N)
    private List<BuyerVO> buyerList; // Has Many (1:N)
}
