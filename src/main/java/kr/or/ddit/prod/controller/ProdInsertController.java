package kr.or.ddit.prod.controller;

import kr.or.ddit.buyer.dao.BuyerMapper;
import kr.or.ddit.commons.enumpkg.ServiceResult;
import kr.or.ddit.lprod.dao.LprodMapper;
import kr.or.ddit.prod.service.ProdService;
import kr.or.ddit.prod.service.ProdServiceImpl;
import kr.or.ddit.vo.ProdVO;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/prod/prodInsert.do")
@MultipartConfig
public class ProdInsertController extends HttpServlet{
    private ProdService service = new ProdServiceImpl();
    private LprodMapper lprodMapper ;
    private BuyerMapper buyerMapper;
    private ServletContext application;
    private File saveFolder;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        application = getServletContext();
        String realPath = application.getRealPath("/resources/prodImages");
        saveFolder = new File(realPath);
        if(!saveFolder.exists()) {
            saveFolder.mkdirs();
        }
    }

    private void addAttribute(HttpServletRequest req) {
        req.setAttribute("lprodList", lprodMapper.selectLprodList());
        req.setAttribute("buyerList", buyerMapper.selectBuyerList());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        addAttribute(req);
        String lvn = "prod/prodForm";

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        addAttribute(req);

        //		1. 요청 파라미터 획득
//		2. ProdVO 에 파라미터 바인드
        ProdVO prod = new ProdVO();
        req.setAttribute("prod", prod);


//		3. 유효성 검증
        Map<String, List<String>> errors = new HashMap<>();
        req.setAttribute("errors", errors);


        String lvn = null;
        if (errors.isEmpty()) {

//		4. 통과
//			1) 로직 실행(createprod)
            ServiceResult result = service.createProd(prod);
            switch (result) {
//			2)  성공 : 상세 페이지로 이동(redirect) : PRG
                case OK:
                    req.getSession().setAttribute("lastCreated", prod);
                    lvn = "redirect:/prod/prodList.do";
                    // 상품 등록에 성공하면, 이진데이터 처리
                    processProdImage(prod);
                    break;

//				실패 : prodForm 이동(forward) (기존 입력 데이터와 알림 메시지 전달)
                default:
                    lvn = "prod/prodForm";
                    req.setAttribute("message", "서버 오류, 잠시 뒤 다시 가입해보셈.");
                    break;
            }

        } else {
//		5. 실패
//		prodForm 이동(forward) (기존 입력 데이터와 검증 에러 메시지 전달)
            lvn = "prod/prodForm";
        }

    }

    private void processProdImage(ProdVO prod) throws IOException {
//		이미지 업로드 처리
        String saveName = prod.getProdImg();
        File saveFile = new File(saveFolder, saveName);


    }
}










