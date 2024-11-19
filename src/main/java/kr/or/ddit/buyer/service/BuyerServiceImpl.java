package kr.or.ddit.buyer.service;

import kr.or.ddit.buyer.dao.BuyerMapper;
import kr.or.ddit.buyer.dao.BuyerMapperImpl;
import kr.or.ddit.commons.enumpkg.ServiceResult;
import kr.or.ddit.commons.exception.PKNotFoundException;
import kr.or.ddit.vo.BuyerVO;

import java.util.List;
import java.util.Optional;

public class BuyerServiceImpl implements BuyerService {

    private BuyerMapper dao = new BuyerMapperImpl();

    @Override
    public ServiceResult createBuyer(BuyerVO buyer) {
        return dao.insertBuyer(buyer) > 0 ? ServiceResult.OK : ServiceResult.FAIL;
    }

    @Override
    public List<BuyerVO> readBuyerList() {
        return dao.selectBuyerList();
    }

    @Override
    public BuyerVO readBuyer(String buyerId) {
        return Optional.ofNullable(dao.selectBuyer(buyerId))
                .orElseThrow(() -> new PKNotFoundException(String.format("%s라는 제조사는 없는듯?", buyerId)));
    }

    @Override
    public ServiceResult modifyBuyer(BuyerVO buyer) {
        return dao.updateBuyer(buyer) > 0 ? ServiceResult.OK : ServiceResult.FAIL;
    }
}
