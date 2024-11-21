package kr.or.ddit.prod.service;

import kr.or.ddit.commons.enumpkg.ServiceResult;
import kr.or.ddit.commons.exception.PKNotFoundException;
import kr.or.ddit.paging.PaginationInfo;
import kr.or.ddit.prod.dao.ProdMapper;
import kr.or.ddit.vo.ProdVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdServiceImpl implements ProdService {

    @Autowired
    private ProdMapper dao;

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
    public List<ProdVO> readProdList(PaginationInfo paging) {
        if (paging != null) {
            int totalRecord = dao.selectTotalRecord(paging);
            paging.setTotalRecord(totalRecord); // totalRecord 값을 설정합니다.
        }

        return dao.selectProdList(paging);
    }


    @Override
    public ServiceResult modifyProd(ProdVO prod) {
        return dao.updateProd(prod) > 0 ? ServiceResult.OK : ServiceResult.FAIL;
    }

}











