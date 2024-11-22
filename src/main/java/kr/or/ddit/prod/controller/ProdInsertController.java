package kr.or.ddit.prod.controller;

import kr.or.ddit.commons.enumpkg.ServiceResult;
import kr.or.ddit.prod.service.ProdService;
import kr.or.ddit.validate.InsertGroup;
import kr.or.ddit.vo.ProdVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.servlet.ServletContext;
import java.io.IOException;

//@MultipartConfig 이건 디스패처와 필터링으로 넘어감
@RequestMapping("/prod/prodInsert.do")
@Controller
public class ProdInsertController {
    @Autowired
    private ProdService service;

    @Inject
    private WebApplicationContext container;

    private ServletContext application;

    @PostConstruct
    public void init() {
        this.application = container.getServletContext();
    }


    public static final String MODELNAME = "lastCreated";

    @ModelAttribute(MODELNAME)
    public ProdVO prod() {
        return new ProdVO();
    }


    /**
     * model : lprodList, buyerlist
     * view : prod/prodForm
     * @param prod
     * @return
     */
    @GetMapping
    public String doGet(@ModelAttribute(MODELNAME) ProdVO prod
    ) {
        return "prod/prodForm";
    }

    /**
     * model : prod, BindingResult.MODEL_KEY_PREFIX+"prod"
     * view :
     *       성공 : redirect:/prod/prodList.do
     *       실패 : prod/prodForm, redirect:/prod/prodInsert.do
     * @return
     */
    @PostMapping
    protected String doPost(
            @Validated(InsertGroup.class) @ModelAttribute(MODELNAME) ProdVO prod,
            Errors errors,
            RedirectAttributes redirectAttributes
    ) throws IOException {

        //DataBinder -populateUtil대용

        String lvn = null;
        redirectAttributes.addFlashAttribute(MODELNAME, prod);

        if (!errors.hasErrors()) {
            ServiceResult result = service.createProd(prod);
            switch (result) {
                case OK:
                    lvn = "redirect:/prod/prodList.do";
                    break;
                default:
                    lvn = "redirect:/prod/prodInsert.do";
                    redirectAttributes.addFlashAttribute("message", "서버 오류, 잠시 뒤 다시 가입해보셈.");
                    break;
            }
        } else {
            String errAttrName = BindingResult.MODEL_KEY_PREFIX + MODELNAME;
            redirectAttributes.addFlashAttribute(errAttrName, errors);
            lvn = "redirect:/prod/prodInsert.do";
        }
        return lvn;
    }


}










