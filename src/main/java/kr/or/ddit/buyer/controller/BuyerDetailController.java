package kr.or.ddit.buyer.controller;

import kr.or.ddit.buyer.service.BuyerService;
import kr.or.ddit.buyer.service.BuyerServiceImpl;
import kr.or.ddit.vo.BuyerVO;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/buyer/buyerDetail.do")
public class BuyerDetailController extends HttpServlet {

    private BuyerService service = new BuyerServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String buyerId = req.getParameter("what");
        if (StringUtils.isBlank(buyerId)) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "필수 파라미터 안넣은듯~");
            return;
        }

        BuyerVO buyer = service.readBuyer(buyerId);

        req.setAttribute("buyer", buyer);
        String lvn = "/WEB-INF/views/buyer/buyerDetail";

        new ViewResolverComposite().resolveView(lvn, req, resp);
    }
}
