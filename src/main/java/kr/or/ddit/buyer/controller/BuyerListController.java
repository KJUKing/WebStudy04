package kr.or.ddit.buyer.controller;

import kr.or.ddit.buyer.service.BuyerService;
import kr.or.ddit.buyer.service.BuyerServiceImpl;
import kr.or.ddit.mvc.ViewResolverComposite;
import kr.or.ddit.vo.BuyerVO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


@WebServlet("/buyer/buyerList.do")
public class BuyerListController extends HttpServlet {

    private BuyerService service = new BuyerServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        List<BuyerVO> buyerList = service.readBuyerList();
        req.setAttribute("buyerList", buyerList);

        String lvn = "buyer/buyerList";

        new ViewResolverComposite().resolveView(lvn, req, resp);
    }
}
