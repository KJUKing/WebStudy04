package kr.or.ddit.prod.service;

import kr.or.ddit.commons.enumpkg.ServiceResult;
import kr.or.ddit.commons.exception.PKNotFoundException;
import kr.or.ddit.paging.PaginationInfo;
import kr.or.ddit.prod.dao.ProdMapper;
import kr.or.ddit.vo.ProdVO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Service
public class ProdServiceImpl implements ProdService {

    @Autowired
    private ProdMapper dao;

    //파일저장위치 주입받기
    @Value("#{dirInfo.prodImages}")
    private Resource saveDir;

    private void processProdImage(ProdVO prod) {
//		이미지 업로드 처리
        try {
            String saveName = prod.getProdImg();
            if (StringUtils.isNotBlank(saveName)) {
                File saveFile = new File(saveDir.getFile(), saveName);
                MultipartFile prodImage = prod.getProdImage();
                if (prodImage != null)
                    prodImage.transferTo(saveFile);
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }


    @Override
    public ServiceResult createProd(ProdVO prod) {
        if (dao.insertProd(prod) > 0) {

            processProdImage(prod);
            return ServiceResult.OK;
        } else {

            return ServiceResult.FAIL;
        }

    }

    @Override
    public ProdVO readProd(String prodId) throws PKNotFoundException {
        ProdVO prod = dao.selectProd(prodId);
        if(prod==null)
            throw new PKNotFoundException(String.format("%s 상품 없음.", prodId));
        return prod;
    }

    @Override
    public List<ProdVO> readProdList(PaginationInfo paging) {
        if (paging != null) {
            int totalRecord = dao.selectTotalRecord(paging);
            paging.setTotalRecord(totalRecord); // totalRecord 값을 설정합니다.
        }

        return dao.selectProdList(paging);
    }

    @Override
    public ServiceResult modifyProd(ProdVO prod) {
        if (dao.updateProd(prod) > 0) {

            processProdImage(prod);
            return ServiceResult.OK;
        } else {

            return ServiceResult.FAIL;
        }
    }

}











