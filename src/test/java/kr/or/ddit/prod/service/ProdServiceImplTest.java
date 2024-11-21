package kr.or.ddit.prod.service;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;

@Slf4j
@SpringJUnitWebConfig(locations = "file:src/main/resources/kr/or/ddit/spring/context-*.xml")
//@RootContextWebConfig
class ProdServiceImplTest {

    @Autowired
    ProdService service;

    @Test
    void createProd() {
    }

    @Test
    void readProd() {
    }

    @Test
    void readProdList() {

    }

    @Test
    void modifyProd() {
    }
}