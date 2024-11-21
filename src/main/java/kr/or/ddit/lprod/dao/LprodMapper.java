package kr.or.ddit.lprod.dao;

import kr.or.ddit.vo.LprodVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface LprodMapper {
    public List<LprodVO> selectLprodList();
}
