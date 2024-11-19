package kr.or.ddit.props.dao;

import kr.or.ddit.mybatis.CustomSqlSessionFactoryBuilder;
import kr.or.ddit.vo.PersonVO;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

public class PersonDAOImpl implements PersonDAO {

    private SqlSessionFactory sqlSessionFactory =
            CustomSqlSessionFactoryBuilder.getSqlSessionFactory();

    @Override
    public int insertPerson(PersonVO person) {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            PersonDAO mapperProxy = sqlSession.getMapper(PersonDAO.class);
            int cnt = mapperProxy.insertPerson(person);
            if (cnt > 0) sqlSession.commit();
            return cnt;
        }
    }

    @Override
    public PersonVO selectPerson(String id) {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            return sqlSession.getMapper(PersonDAO.class).selectPerson(id);
        }
    }

    @Override
    public List<PersonVO> selectPersonList() {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            return sqlSession.getMapper(PersonDAO.class).selectPersonList();
        }
    }

    @Override
    public int updatePerson(PersonVO person) {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            int cnt = sqlSession.getMapper(PersonDAO.class).updatePerson(person);
            if (cnt > 0) sqlSession.commit();
            return cnt;
        }
    }

    @Override
    public int deletePerson(String id) {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            int cnt = sqlSession.getMapper(PersonDAO.class).deletePerson(id);
            if (cnt > 0) sqlSession.commit();
            return cnt;
        }
    }
}