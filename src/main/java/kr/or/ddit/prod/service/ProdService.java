package kr.or.ddit.prod.service;

import kr.or.ddit.commons.enumpkg.ServiceResult;
import kr.or.ddit.commons.exception.PKNotFoundException;
import kr.or.ddit.paging.PaginationInfo;
import kr.or.ddit.vo.ProdVO;

import java.util.List;

/**
 * 상품관리(CRUD) Business Logic Layer
 *
 */
public interface ProdService {
    /** 상품의 아이디는 등록전에 생성함.
     * @param prod
     * @return OK, FAIL
     */
    public ServiceResult createProd(ProdVO prod);
    /**
     * @param prodId
     * @return
     * @throws PKNotFoundException
     */
    public ProdVO readProd(String prodId) throws PKNotFoundException;
    /**
     * 차후 페이징과 검색 예정
     * @return
     */
    public List<ProdVO> readProdList(PaginationInfo<ProdVO> paging);
    /**
     * @param prod
     * @return OK, FAIL
     */
    public ServiceResult modifyProd(ProdVO prod);
}









