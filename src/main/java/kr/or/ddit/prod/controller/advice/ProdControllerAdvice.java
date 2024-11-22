package kr.or.ddit.prod.controller.advice;

import kr.or.ddit.buyer.dao.BuyerMapper;
import kr.or.ddit.lprod.dao.LprodMapper;
import kr.or.ddit.vo.BuyerVO;
import kr.or.ddit.vo.LprodVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;

@ControllerAdvice
public class ProdControllerAdvice {

    @Autowired
    private LprodMapper lprodMapper;
    @Autowired
    private BuyerMapper buyerMapper;

    @ModelAttribute("lprodList")
    public List<LprodVO> lprodList() {
        return lprodMapper.selectLprodList();
    }

    @ModelAttribute("buyerList")
    public List<BuyerVO> buyerList() {
        return buyerMapper.selectBuyerList();
    }

}
