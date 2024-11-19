//package kr.or.ddit.lprod.dao;
//
//import kr.or.ddit.mybatis.CustomSqlSessionFactoryBuilder;
//import kr.or.ddit.vo.LprodVO;
//import org.apache.ibatis.session.SqlSession;
//import org.apache.ibatis.session.SqlSessionFactory;
//
//import java.util.List;
//
//public class LprodMapperImpl implements LprodMapper {
//
//    private SqlSessionFactory sqlSessionFactory =
//            CustomSqlSessionFactoryBuilder.getSqlSessionFactory();
//
//
//    @Override
//    public List<LprodVO> selectLprodList() {
//        try(SqlSession sqlSession = sqlSessionFactory.openSession()) {
//            LprodMapper mapperProxy = sqlSession.getMapper(LprodMapper.class);
//            return mapperProxy.selectLprodList();
//        }
//    }
//}
