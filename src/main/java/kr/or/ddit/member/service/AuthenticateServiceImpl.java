package kr.or.ddit.member.service;

import kr.or.ddit.member.dao.MemberDAO;
import kr.or.ddit.vo.MemberVO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticateServiceImpl implements AuthenticateService {

    private final MemberDAO dao;

    @Override
    public MemberVO authenticate(MemberVO inputData) {
        MemberVO saved = dao.selectMemberForAuth(inputData.getMemId());
        if (saved != null) {
            String savedPass = saved.getMemPass();
            String inputPass = inputData.getMemPass();
            if (savedPass.equals(inputPass)) {
                return saved;
            }
        }
        return null;
    }
}
