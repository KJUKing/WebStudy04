package kr.or.ddit.member.service;

import kr.or.ddit.commons.enumpkg.ServiceResult;
import kr.or.ddit.commons.exception.PKNotFoundException;
import kr.or.ddit.vo.MemberVO;

import java.util.List;

/**
 * 회원관리용 Business Logic Layer
 *
 */
public interface MemberService {
     /**
      * 회원 가입
      * @param member
      * @return PKDUPLICATED, OK, FAIL
      */
     public ServiceResult createMember(MemberVO member);

     /**
      * 회원 상세 조회
      * @param memId
      * @return
      * @throws PKNotFoundException 존재하지 않는 경우, 발생할 예외
      */
     public MemberVO readMember(String memId) throws PKNotFoundException;
     /**
      * 회원 목록 조회(차후 페이징과 검색 적용)
      * @return
      */
     public List<MemberVO> readMemberList();
     /**
      * 회원 정보 수정
      * @param member
      * @return INVALIDPASSWORD, OK, FAIL
      */
     public ServiceResult modifyMember(MemberVO member);
     /**
      * 회원 탈퇴
      * @param inputData
      * @return INVALIDPASSWORD, OK, FAIL
      */
     public ServiceResult removeMember(MemberVO inputData);
}











