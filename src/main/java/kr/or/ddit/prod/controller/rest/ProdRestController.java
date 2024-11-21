package kr.or.ddit.prod.controller.rest;

import kr.or.ddit.prod.service.ProdService;
import kr.or.ddit.vo.ProdVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * /prod Get
 * /prod/{prodId} <-P101000001 GET
 * /put/{prodId}
 * /delete/{prodId}
 * /prod POST
 */
@RestController
@RequestMapping(value = "/prod", produces = MediaType.APPLICATION_JSON_VALUE)
public class ProdRestController {

    @Autowired
    private ProdService service;

    @GetMapping
//    @ResponseBody //리스트로 바로보내겠다 그자체가 리턴값이된다
    public List<ProdVO> list(){
        return service.readProdList(null);

    }

    @GetMapping("{prodId}")
    public ProdVO detail(@PathVariable String prodId) {
        return service.readProd(prodId);
    }

}
