package kr.or.ddit.prod.dao;

import kr.or.ddit.paging.PaginationInfo;
import kr.or.ddit.vo.ProdVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 상품관리(CRUD) persistence layer
 *
 */
@Mapper
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
     * 페이징 처리를 위한 검색 결과 레코드 수 조회
     * @return
     */
    public int selectTotalRecord(@Param("paging") PaginationInfo<ProdVO> paging);
    /**
     * 상품 검색결과 목록조회
     * @return
     */
    public List<ProdVO> selectProdList(@Param("paging") PaginationInfo<ProdVO> paging);
    /**
     * 상품 수정
     * @param prod
     * @return
     */
    public int updateProd(ProdVO prod);
//	public int deleteProd(String prodId);
}










