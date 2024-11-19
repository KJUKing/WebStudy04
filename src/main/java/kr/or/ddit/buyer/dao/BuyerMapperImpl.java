package kr.or.ddit.buyer.dao;

import kr.or.ddit.mybatis.CustomSqlSessionFactoryBuilder;
import kr.or.ddit.vo.BuyerVO;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

public class BuyerMapperImpl implements BuyerMapper {

    private SqlSessionFactory sqlSessionFactory =
            CustomSqlSessionFactoryBuilder.getSqlSessionFactory();


    @Override
    public int insertBuyer(BuyerVO buyer) {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            int cnt = sqlSession.getMapper(BuyerMapper.class).insertBuyer(buyer);
            if (cnt > 0) sqlSession.commit();
            return cnt;
        }
    }

    @Override
    public BuyerVO selectBuyer(String buyerId) {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            return sqlSession.getMapper(BuyerMapper.class).selectBuyer(buyerId);
        }
    }

    @Override
    public List<BuyerVO> selectBuyerList() {
        try(SqlSession sqlSession = sqlSessionFactory.openSession()) {
            BuyerMapper mapperProxy = sqlSession.getMapper(BuyerMapper.class);
            return mapperProxy.selectBuyerList();
        }
    }

    @Override
    public int updateBuyer(BuyerVO buyer) {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            int cnt = sqlSession.getMapper(BuyerMapper.class).updateBuyer(buyer);
            if (cnt > 0) sqlSession.commit();
            return cnt;
        }
    }
}
