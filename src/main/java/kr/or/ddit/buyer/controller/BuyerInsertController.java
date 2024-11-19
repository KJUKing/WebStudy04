package kr.or.ddit.buyer.controller;

import kr.or.ddit.buyer.service.BuyerService;
import kr.or.ddit.buyer.service.BuyerServiceImpl;
import kr.or.ddit.commons.enumpkg.ServiceResult;
import kr.or.ddit.lprod.dao.LprodMapper;
import kr.or.ddit.lprod.dao.LprodMapperImpl;
import kr.or.ddit.mvc.ViewResolverComposite;
import kr.or.ddit.utils.PopulateUtils;
import kr.or.ddit.utils.ValidateUtils;
import kr.or.ddit.validate.InsertGroup;
import kr.or.ddit.vo.BuyerVO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/buyer/buyerInsert.do")
public class BuyerInsertController extends HttpServlet {
    private BuyerService service = new BuyerServiceImpl();
    private LprodMapper lprodMapper = new LprodMapperImpl();

    private void addAttribute(HttpServletRequest req) {
        req.setAttribute("lprodList", lprodMapper.selectLprodList());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        addAttribute(req);
        String lvn = "buyer/buyerForm";
        new ViewResolverComposite().resolveView(lvn, req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        addAttribute(req);
        BuyerVO buyer = new BuyerVO();
        req.setAttribute("buyer", buyer);

        PopulateUtils.populate(buyer, req.getParameterMap());

        Map<String, List<String>> errors = new HashMap<>();
        req.setAttribute("errors", errors);
        ValidateUtils.validate(buyer, errors, InsertGroup.class);

        String lvn = null;
        if (errors.isEmpty()) {


            ServiceResult result = service.createBuyer(buyer);
            switch (result) {
                case OK:
                    req.getSession().setAttribute("lastCreated", buyer);
                    lvn = "redirect:/buyer/buyerList.do";
                    break;

                default:
                    lvn = "buyer/buyerForm";
                    req.setAttribute("message", "서버 오류, 잠시 뒤 다시 가입해보셈.");
                    break;
            }

        }else{
            lvn = "buyer/buyerForm";
        }

        new ViewResolverComposite().resolveView(lvn, req, resp);
    }

}
