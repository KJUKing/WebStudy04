package kr.or.ddit.buyer.controller;

import kr.or.ddit.buyer.service.BuyerService;
import kr.or.ddit.buyer.service.BuyerServiceImpl;
import kr.or.ddit.commons.enumpkg.ServiceResult;
import kr.or.ddit.lprod.dao.LprodMapper;
import kr.or.ddit.validate.UpdateGroup;
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

@WebServlet("/buyer/buyerUpdate.do")
public class BuyerUpdateController extends HttpServlet {

    private BuyerService service = new BuyerServiceImpl();
    private LprodMapper lprodMapper = new LprodMapperImpl();

    private void addAttribute(HttpServletRequest req) {
        req.setAttribute("lprodList", lprodMapper.selectLprodList());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        addAttribute(req);

        String buyerId = req.getParameter("buyerId");
        if (buyerId == null || buyerId.isEmpty()) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "구매처 ID는 필수입니다.");
            return;
        }

        BuyerVO buyer = service.readBuyer(buyerId);
        req.setAttribute("buyer", buyer);

        String lvn = "buyer/buyerEditForm";
        new ViewResolverComposite().resolveView(lvn, req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        BuyerVO buyer = new BuyerVO();
        req.setAttribute("buyer", buyer);
        PopulateUtils.populate(buyer, req.getParameterMap());

        Map<String, List<String>> errors = new HashMap<>();
        req.setAttribute("errors", errors);
        ValidateUtils.validate(buyer, errors, UpdateGroup.class);

        String lvn = null;
        if (errors.isEmpty()) {
            // 3. 구매처 정보를 수정합니다.
            ServiceResult result = service.modifyBuyer(buyer);
            switch (result) {
                case OK:
                    // 수정 성공 시 상세 페이지로 리다이렉트
                    lvn = "redirect:/buyer/buyerDetail.do?what=" + buyer.getBuyerId();
                    break;
                default:
                    // 수정 실패 시 에러 메시지와 함께 폼으로 포워딩
                    lvn = "buyer/buyerEditForm";
                    req.setAttribute("message", "서버 오류, 다시 시도해주세요.");
                    break;
            }
        } else {
            // 유효성 검사 실패 시 폼으로 포워딩
            lvn = "buyer/buyerEditForm";
        }
        new ViewResolverComposite().resolveView(lvn, req, resp);
    }
}
