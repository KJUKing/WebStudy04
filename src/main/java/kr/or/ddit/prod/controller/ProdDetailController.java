package kr.or.ddit.prod.controller;

import kr.or.ddit.mvc.ViewResolverComposite;
import kr.or.ddit.prod.service.ProdService;
import kr.or.ddit.prod.service.ProdServiceImpl;
import kr.or.ddit.vo.ProdVO;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/prod/prodDetail.do")
public class ProdDetailController extends HttpServlet{
    private ProdService service = new ProdServiceImpl();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // ?what=P101000001
        String prodId = req.getParameter("what");
        if(StringUtils.isBlank(prodId)) {
            resp.sendError(400, "필수 파라미터 누락");
            return;
        }
        ProdVO prod = service.readProd(prodId);
        req.setAttribute("prod", prod);
        String lvn = "/WEB-INF/views/prod/prodDetail";

        new ViewResolverComposite().resolveView(lvn, req, resp);
    }
}








