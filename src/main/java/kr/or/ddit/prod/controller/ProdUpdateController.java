package kr.or.ddit.prod.controller;

import kr.or.ddit.commons.enumpkg.ServiceResult;
import kr.or.ddit.mvc.ViewResolverComposite;
import kr.or.ddit.mvc.multipart.MultipartFile;
import kr.or.ddit.mvc.multipart.MultipartHttpServletRequest;
import kr.or.ddit.prod.service.ProdService;
import kr.or.ddit.prod.service.ProdServiceImpl;
import kr.or.ddit.utils.PopulateUtils;
import kr.or.ddit.utils.ValidateUtils;
import kr.or.ddit.validate.UpdateGroup;
import kr.or.ddit.vo.ProdVO;
import org.apache.commons.lang3.StringUtils;

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

@WebServlet("/prod/prodUpdate.do")
@MultipartConfig
public class ProdUpdateController extends HttpServlet{
    private ProdService service = new ProdServiceImpl();
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

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String prodId = req.getParameter("what");
        if(StringUtils.isBlank(prodId)) {
            resp.sendError(400, "필수 파라미터 누락");
            return;
        }

        ProdVO prod = service.readProd(prodId);
        req.setAttribute("prod", prod);
        String lvn = "prod/prodEdit";
        new ViewResolverComposite().resolveView(lvn, req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //		1. 요청 파라미터 획득
//		2. ProdVO 에 파라미터 바인드
        ProdVO prod = new ProdVO();
        req.setAttribute("prod", prod);

        PopulateUtils.populate(prod, req.getParameterMap());
        if(req instanceof MultipartHttpServletRequest) {
            MultipartFile prodImage = ((MultipartHttpServletRequest) req).getFile("prodImage");
            prod.setProdImage(prodImage);
        }

//		3. 유효성 검증
        Map<String, List<String>> errors = new HashMap<>();
        req.setAttribute("errors", errors);
        ValidateUtils.validate(prod, errors, UpdateGroup.class);

        String lvn = null;
        if (errors.isEmpty()) {

//		4. 통과
//			1) 로직 실행(modifyprod)
            ServiceResult result = service.modifyProd(prod);
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
                    lvn = "prod/prodEdit";
                    req.setAttribute("message", "서버 오류, 잠시 뒤 다시 가입해보셈.");
                    break;
            }

        } else {
//		5. 실패
//		prodForm 이동(forward) (기존 입력 데이터와 검증 에러 메시지 전달)
            lvn = "prod/prodEdit";
        }

        new ViewResolverComposite().resolveView(lvn, req, resp);
    }

    private void processProdImage(ProdVO prod) throws IOException {
//		이미지 업로드 처리
        String saveName = prod.getProdImg();
        if(StringUtils.isBlank(saveName)) return;

        File saveFile = new File(saveFolder, saveName);
        MultipartFile prodImage = prod.getProdImage();
        if(prodImage!=null)
            prodImage.transferTo(saveFile);

    }
}
