package kr.or.ddit.login.auth;

import kr.or.ddit.vo.MemberVO;

import java.security.Principal;

public class MemberVOWrapper implements Principal {

    private MemberVO realUser;

    public MemberVOWrapper(MemberVO realUser) {
        super();
        this.realUser = realUser;
    }

    @Override
    public String getName() {
        return realUser.getMemId();
    }

    public MemberVO getRealUser() {
        return realUser;
    }

}
