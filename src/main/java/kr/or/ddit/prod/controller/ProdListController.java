package kr.or.ddit.prod.controller;

import kr.or.ddit.mvc.ViewResolverComposite;
import kr.or.ddit.prod.service.ProdService;
import kr.or.ddit.prod.service.ProdServiceImpl;
import kr.or.ddit.vo.ProdVO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * 다건조회 : /prod/prodList.do (GET)
 * 단건조회 : /prod/prodDetail.do?what=P101000001 (GET)
 * 등록 : /prod/prodInsert.do (GET, POST)
 * 수정 : /prod/prodUpdate.do?what=P101000001 (GET, POST)
 *
 */
@WebServlet("/prod/prodList.do")
public class ProdListController extends HttpServlet{
    private ProdService service = new ProdServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<ProdVO> proList = service.readProdList();

        req.setAttribute("prodList", proList);

        String lvn = "prod/prodList";

        new ViewResolverComposite().resolveView(lvn, req, resp);
    }
}













