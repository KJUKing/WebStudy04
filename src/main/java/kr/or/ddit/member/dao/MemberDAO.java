package kr.or.ddit.member.dao;

import kr.or.ddit.vo.MemberVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 회원관리용 persistence layer
 *
 */

@Mapper
public interface MemberDAO {
    /**
     * 회원등록
     * @param member
     * @return 등록된 회원수
     */
    public int insertMember(MemberVO member);
    /**
     * 회원정보 상세 조회
     * @param memId 조회할 PK
     * @return 존재하지 않으면, null 반환
     */
    public MemberVO selectMember(@Param("memId") String memId);
    /**
     * 인증용 정보 조회(아이디, 비밀번호, 이름, 이메일, 휴대폰)
     * @param memId
     * @return 존재하지 않으면, null 반환
     */
    public MemberVO selectMemberForAuth(String memId);
    /**
     * 회원 목록 조회
     * @return 없어도, empty list 반환
     */
    public List<MemberVO> selectMemberList();
    /**
     * 회원정보 수정
     * @param member
     * @return 수정된 회원수
     */
    public int updateMember(MemberVO member);
    /**
     * 회원정보 삭제(??????)
     * @param memId
     * @return 삭제된(?????) 회원수
     */
    public int deleteMember(@Param("memId") String memId);
}









