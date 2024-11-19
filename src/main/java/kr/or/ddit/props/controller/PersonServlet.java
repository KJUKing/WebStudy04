package kr.or.ddit.props.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import kr.or.ddit.props.service.PersonService;
import kr.or.ddit.props.service.PersonServiceImpl;
import kr.or.ddit.vo.PersonVO;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@WebServlet("/person/*")
@MultipartConfig
public class PersonServlet extends HttpServlet {

    private PersonService service = PersonServiceImpl.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String requestURI = req.getRequestURI();
        String contextPath = req.getContextPath();
        Pattern regex = Pattern.compile(contextPath + "/person/([\\w]+)");
        Matcher matcher = regex.matcher(requestURI);

        if (matcher.find()) {
            // 단건 조회
            String id = matcher.group(1);
            singlePerson(id, req, resp);
        } else {
            // 다건 조회
            allPerson(req, resp);
        }
    }

    private void singlePerson(String id, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (id == null || id.trim().isEmpty()) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "ID가 필요합니다.");
            return;
        }

        PersonVO person = service.readPerson(id);
        if (person == null) {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND, "해당 ID의 사용자가 없습니다.");
            return;
        }

        //charset을 필터부분 resp쪽부분에 적용했는데 혹시몰라서 그냥 붙혀두기로함
        resp.setContentType("application/json;charset=UTF-8");
        try (OutputStream os = resp.getOutputStream()) {
            new ObjectMapper().writeValue(os, person);
        }
    }

    private void allPerson(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<PersonVO> personList = service.readPersonList();
        resp.setContentType("application/json;charset=UTF-8");
        try (OutputStream os = resp.getOutputStream()) {
            new ObjectMapper().writeValue(os, personList);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        ObjectMapper mapper = new ObjectMapper();
        PersonVO person = mapper.readValue(req.getInputStream(), PersonVO.class);

        // 데이터 검증
        Map<String, String> errors = validatePerson(person);
        if (!errors.isEmpty()) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            resp.setContentType("application/json;charset=UTF-8");
            try (OutputStream os = resp.getOutputStream()) {
                mapper.writeValue(os, errors);
            }
            return;
        }

        boolean result = service.createPerson(person);
        if (result) {
            resp.setStatus(HttpServletResponse.SC_CREATED);
            resp.setContentType("application/json;charset=UTF-8");
            try (OutputStream os = resp.getOutputStream()) {
                mapper.writeValue(os, person);
            }
        } else {
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "사용자 등록에 실패했습니다.");
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        ObjectMapper mapper = new ObjectMapper();
        PersonVO person = mapper.readValue(req.getInputStream(), PersonVO.class);

        // 데이터 검증
        Map<String, String> errors = validatePerson(person);
        if (!errors.isEmpty()) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            resp.setContentType("application/json;charset=UTF-8");
            try (OutputStream os = resp.getOutputStream()) {
                mapper.writeValue(os, errors);
            }
            return;
        }

        boolean result = service.modifyPerson(person);
        if (result) {
            resp.setStatus(HttpServletResponse.SC_OK);
            resp.setContentType("application/json;charset=UTF-8");
            try (OutputStream os = resp.getOutputStream()) {
                mapper.writeValue(os, person);
            }
        } else {
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "사용자 정보 수정에 실패했습니다.");
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        /**
         * doDelete 메서드는 요청 본문을 파싱하거나 데이터 검증을 수행할 필요가 없다.
         * 즉 삭제 작업은 대상 리소스의 식별자만 알면 된다.
         * URL에서 추출한 ID를 사용하여 서비스 계층의 삭제 메서드를 호출하고, 그 결과에 따라 응답을 반환함.
         * 따라서 다른 메서드에 비해 로직이 더 간단하고 구조가 다르다.
         *
         * 예를 들어, doPost와 doPut에서는 다음과 같은 작업이 필요함
         *
         * 요청 본문에서 JSON 데이터 읽기 (req.getInputStream())
         * JSON 데이터를 PersonVO 객체로 변환 (ObjectMapper 사용)
         * 데이터 유효성 검사 (필수 필드 확인 등)
         * 서비스 계층 호출 및 결과 처리
         *
         * 반면에 doDelete에서는 ?
         *
         * URL에서 ID 추출 (req.getRequestURI() 및 정규식 사용)
         * 서비스 계층의 삭제 메서드 호출 (service.deletePerson(id))
         * 삭제 결과에 따라 응답 생성
         *
         * doDelete는 doGet에 더가깝다고 보면된다.
         *
         * 클라이언트 측 구현 방법
         *
         * 브라우저에서 GET 요청: 주소창에 URL을 입력하거나 링크를 클릭하면 자동으로 GET 요청이 보내짐
         *
         * 브라우저에서 DELETE 요청: 일반적인 HTML 폼으로는 DELETE 요청을 보낼 수 없으므로,
         * JavaScript를 사용하여 AJAX로 DELETE 요청을 보낸다
         */

        String requestURI = req.getRequestURI();
        String contextPath = req.getContextPath();
        Pattern regex = Pattern.compile(contextPath + "/person/([\\w]+)");
        Matcher matcher = regex.matcher(requestURI);

        if (matcher.find()) {
            String id = matcher.group(1);
            boolean result = service.removePerson(id);
            if (result) {
                resp.setStatus(HttpServletResponse.SC_OK);
                Map<String, Object> res = Collections.singletonMap("deleted", 1);
                resp.setContentType("application/json;charset=UTF-8");
                try (OutputStream os = resp.getOutputStream()) {
                    new ObjectMapper().writeValue(os, res);
                }
            } else {
                resp.sendError(HttpServletResponse.SC_NOT_FOUND, "해당 ID의 사용자가 없습니다.");
            }
        } else {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "ID가 필요합니다.");
        }
    }

    private Map<String, String> validatePerson(PersonVO person) {
        Map<String, String> errors = new HashMap<>();
        if (person.getId() == null || person.getId().trim().isEmpty()) {
            errors.put("id", "ID는 필수 입력 사항입니다.");
        }
        if (person.getName() == null || person.getName().trim().isEmpty()) {
            errors.put("name", "이름은 필수 입력 사항입니다.");
        }
        // 추가적인 검증 로직을 여기에 추가할 수 있습니다.
        return errors;
    }
}