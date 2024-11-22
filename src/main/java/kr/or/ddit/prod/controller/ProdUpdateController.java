package kr.or.ddit.prod.controller;

import kr.or.ddit.commons.enumpkg.ServiceResult;
import kr.or.ddit.prod.service.ProdService;
import kr.or.ddit.validate.UpdateGroup;
import kr.or.ddit.vo.ProdVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@RequestMapping("/prod/prodUpdate.do")
@Controller
public class ProdUpdateController{

    @Autowired
    private ProdService service;

    public static final String MODELNAME = "lastCreated";


    @GetMapping
    public String doGet(@RequestParam String what, Model model) {

        if (!model.containsAttribute(MODELNAME)) {
            ProdVO prod = service.readProd(what);
            model.addAttribute(MODELNAME, prod);
        }
        return "prod/prodEdit";
    }

    @PostMapping
    public String doPost(
            @Validated(UpdateGroup.class) @ModelAttribute(MODELNAME) ProdVO prod,
            Errors errors,
            RedirectAttributes redirectAttributes){

        String lvn = null;
        redirectAttributes.addFlashAttribute(MODELNAME, prod);

        if (!errors.hasErrors()) {
            ServiceResult result = service.modifyProd(prod);
            switch (result) {
                case OK:
                    lvn = "redirect:/prod/prodList.do";
                    break;
                default:
                    lvn = "redirect:/prod/prodUpdate.do?what" + prod.getProdId();
                    redirectAttributes.addFlashAttribute("message", "서버 오류, 잠시 뒤 다시 가입해보셈.");
                    break;
            }
        } else {
            String errAttrName = BindingResult.MODEL_KEY_PREFIX + MODELNAME;
            redirectAttributes.addFlashAttribute(errAttrName, errors);
            lvn = "redirect:/prod/prodUpdate.do?what" + prod.getProdId();
        }
        return lvn;
    }
}
