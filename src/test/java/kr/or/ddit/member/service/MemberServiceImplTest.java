package kr.or.ddit.member.service;

import kr.or.ddit.annotation.RootContextWebConfig;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@RootContextWebConfig
class MemberServiceImplTest {

    @Autowired
    private MemberService service;

    @Test
    void createMember() {
    }

    @Test
    void readMember() {
    }

    @Test
    void readMemberList() {
    }

    @Test
    void modifyMember() {
    }

    @Test
    void removeMember() {
    }
}