package kr.or.ddit.prod.service;

import kr.or.ddit.commons.enumpkg.ServiceResult;
import kr.or.ddit.commons.exception.PKNotFoundException;
import kr.or.ddit.prod.dao.ProdMapper;
import kr.or.ddit.prod.dao.ProdMapperImpl;
import kr.or.ddit.vo.ProdVO;

import java.util.List;

public class ProdServiceImpl implements ProdService {

    private ProdMapper dao = new ProdMapperImpl();

    @Override
    public ServiceResult createProd(ProdVO prod) {
        return dao.insertProd(prod) > 0 ? ServiceResult.OK : ServiceResult.FAIL;
    }

    @Override
    public ProdVO readProd(String prodId) throws PKNotFoundException {
        ProdVO prod = dao.selectProd(prodId);
        if(prod==null)
            throw new PKNotFoundException(String.format("%s 상품 없음.", prodId));
        return prod;
    }

    @Override
    public List<ProdVO> readProdList() {
        return dao.selectProdList();
    }

    @Override
    public ServiceResult modifyProd(ProdVO prod) {
        return dao.updateProd(prod) > 0 ? ServiceResult.OK : ServiceResult.FAIL;
    }

}











