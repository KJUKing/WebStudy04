package kr.or.ddit.buyer.service;

import kr.or.ddit.commons.enumpkg.ServiceResult;
import kr.or.ddit.vo.BuyerVO;

import java.util.List;

public interface BuyerService {

    public ServiceResult createBuyer(BuyerVO buyer);

    public List<BuyerVO> readBuyerList();

    public BuyerVO readBuyer(String buyerId);

    public ServiceResult modifyBuyer(BuyerVO buyer);
}
