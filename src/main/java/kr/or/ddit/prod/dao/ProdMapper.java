package kr.or.ddit.prod.dao;

import kr.or.ddit.vo.ProdVO;

import java.util.List;

/**
 * 상품관리(CRUD) persistence layer
 *
 */
public interface ProdMapper {
    /**
     * 상품 등록
     * @param prod
     * @return
     */
    public int insertProd(ProdVO prod);
    /**
     * 상품 상세조회
     * @param prodId
     * @return
     */
    public ProdVO selectProd(String prodId);
    /**
     * 상품 목록조회
     * @return
     */
    public List<ProdVO> selectProdList();
    /**
     * 상품 수정
     * @param prod
     * @return
     */
    public int updateProd(ProdVO prod);
//	public int deleteProd(String prodId);
}










