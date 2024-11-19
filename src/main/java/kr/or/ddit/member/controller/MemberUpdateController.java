//package kr.or.ddit.member.controller;
//
//import kr.or.ddit.commons.enumpkg.ServiceResult;
//import kr.or.ddit.member.service.AuthenticateService;
//import kr.or.ddit.member.service.AuthenticateServiceImpl;
//import kr.or.ddit.member.service.MemberService;
//import kr.or.ddit.member.service.MemberServiceImpl;
//import kr.or.ddit.mvc.ViewResolverComposite;
//import kr.or.ddit.mvc.multipart.MultipartFile;
//import kr.or.ddit.mvc.multipart.MultipartHttpServletRequest;
//import kr.or.ddit.utils.PopulateUtils;
//import kr.or.ddit.utils.ValidateUtils;
//import kr.or.ddit.validate.UpdateGroup;
//import kr.or.ddit.vo.MemberVO;
//
//import javax.servlet.ServletException;
//import javax.servlet.annotation.MultipartConfig;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//@WebServlet("/member/memberUpdate.do")
//@MultipartConfig
//public class MemberUpdateController extends HttpServlet{
//    private MemberService service = new MemberServiceImpl();
//    private AuthenticateService authService = new AuthenticateServiceImpl();
//
//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        String memId = req.getUserPrincipal().getName();
//        MemberVO member = service.readMember(memId);
//        req.setAttribute("member", member);
//        String lvn = "member/memberEdit";
//
//        new ViewResolverComposite().resolveView(lvn, req, resp);
//    }
//
//    @Override
//    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
////		1. 요청 파라미터 획득(17개)
////		2. MemberVO 에 파라미터 바인드
//        MemberVO member = new MemberVO();
//        req.setAttribute("member", member);
//
//        PopulateUtils.populate(member, req.getParameterMap());
//        if(req instanceof MultipartHttpServletRequest) {
//            MultipartFile memImage = ((MultipartHttpServletRequest) req).getFile("memImage");
//            member.setMemImage(memImage);
//        }
//
////		3. 유효성 검증
//        Map<String, List<String>> errors = new HashMap<>();
//        req.setAttribute("errors", errors);
//        ValidateUtils.validate(member, errors, UpdateGroup.class);
//        String lvn = null;
//        if (errors.isEmpty()) {
////		4. 통과
////			1) 로직 실행(createMember)
//            ServiceResult result = service.modifyMember(member);
//            switch (result) {
////			2)  성공 : 마이 페이지로 이동(redirect) : PRG
//                case OK:
//                    lvn = "redirect:/mypage";
//                    changeAuthMember(member, req);
//                    break;
////				비번인증실패 : memberEdit 이동(forward) (기존 입력 데이터와 알림 메시지 전달)
//                case INVALIDPASSWORD:
//                    lvn = "member/memberEdit";
//                    req.setAttribute("message", "비밀번호 인증 실패.");
//                    break;
//
////				실패 : memberEdit 이동(forward) (기존 입력 데이터와 알림 메시지 전달)
//                default:
//                    lvn = "member/memberEdit";
//                    req.setAttribute("message", "서버 오류, 잠시 뒤 다시 가입해보셈.");
//                    break;
//            }
//
//        } else {
////		5. 실패
////		memberEdit 이동(forward) (기존 입력 데이터와 검증 에러 메시지 전달)
//            lvn = "member/memberEdit";
//        }
//
//        new ViewResolverComposite().resolveView(lvn, req, resp);
//
//    }
//
//    private void changeAuthMember(MemberVO member, HttpServletRequest req) {
//        MemberVO authMember = authService.authenticate(member);
//        req.getSession().setAttribute("authMember", authMember);
//    }
//
//}
//
//
//
//
//
//
//
//
//
//
//
