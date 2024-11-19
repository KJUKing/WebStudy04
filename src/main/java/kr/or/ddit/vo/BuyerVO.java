package kr.or.ddit.vo;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

@Data
public class BuyerVO implements Serializable {
    private String buyerId;
    private String buyerName;
    private String buyerLgu;
    private String buyerBank;
    private String buyerBankno;
    private String buyerBankname;
    private String buyerZip;
    private String buyerAdd1;
    private String buyerAdd2;
    private String buyerComtel;
    private String buyerFax;
    private String buyerMail;
    private String buyerCharger;
    private String buyerTelext;

    @ToString.Exclude
    private List<ProdVO> prodList; // BuyerVO Has Many ProdVO (1:N) 하나의 부모는 여러 자식을 갖는다.
    @ToString.Exclude
    private LprodVO lprod; // BuyerVO Has A LprodVO (1:1) 자식이 하나의 부모를 갖는다.
}








