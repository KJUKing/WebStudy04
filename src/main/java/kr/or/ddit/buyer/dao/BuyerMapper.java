package kr.or.ddit.buyer.dao;

import kr.or.ddit.vo.BuyerVO;

import java.util.List;

public interface BuyerMapper {


    /**
     *
     * @param buyerVO
     * @return
     */
    public int insertBuyer(BuyerVO buyerVO);

    /**
     *
     * @param buyerId
     * @return
     */
    public BuyerVO selectBuyer(String buyerId);

    /**
     *
     * @return
     */
    public List<BuyerVO> selectBuyerList();

    /**
     *
     * @param buyer
     * @return
     */
    public int updateBuyer(BuyerVO buyer);
}
