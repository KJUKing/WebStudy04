package kr.or.ddit.vo;

import kr.or.ddit.validate.InsertGroup;
import kr.or.ddit.validate.UpdateGroup;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.UUID;

/**
 * Data Mapper 나 ORM 계열의 프레임워크에서 두개 이상의 테이블을 조인하는 방법.
 *
 * 1. Main 엔터티 결정
 *  ex) 상품 상세 조회시 해당 상품의 제조사와 함께 조회함. : PROD
 *  ex) 제조사 상세 조회시 해당 제조사와의 거래 품목을 함께 조회함. : BUYER
 * 2. Main 엔터티를 중심(1)으로 나머지 엔터티와의 관계 정의
 * 		PROD(1) : BUYER(1)
 * 		BUYER(1) : PROD(N)
 * 3. 각 엔터티의 스키마를 반영한 domain 객체 설계 , ProdVO, BuyerVO
 * 4. 각 domain에 엔터티의 관계를 반영함. (Has)
 * 		ex) ProdVO Has A BuyerVO
 *      ex) BuyerVO Has Many ProdVO
 * 5. 조인 쿼리 작성 (mapper xml)
 * 6. resultType 대신 resultMap 으로 결과 바인드 (automapping 활용)
 * 7. Has 관계에 대한 수동 바인드 설정
 *    has a : association
 *    has many : collection
 *
 */
@Data
@EqualsAndHashCode(of = "prodId")
@ToString
public class ProdVO implements Serializable{
    @NotBlank(groups = UpdateGroup.class)
    private String prodId;
    @NotBlank
    private String prodName;
    @NotBlank(groups = InsertGroup.class)
    @Size(min=4, max=4, groups = InsertGroup.class)
    private String prodLgu;
    @NotBlank(groups = InsertGroup.class)
    @Size(min=6, max=6, groups = InsertGroup.class)
    private String prodBuyer;
    @NotNull
    @Min(0)
    private Long prodCost;
    @NotNull
    @Min(0)
    private Long prodPrice;
    @NotNull
    @Min(0)
    private Long prodSale;
    @NotBlank
    private String prodOutline;
    @ToString.Exclude
    private String prodDetail;

    @NotBlank(groups = InsertGroup.class)
    private String prodImg; // 데이터베이스 지원

    private MultipartFile prodImage; // 클라이언트로부터 전송된 파일 지원
    public void setProdImage(MultipartFile prodImage) {
        if(prodImage==null || prodImage.isEmpty()) return;

        this.prodImage = prodImage;
        this.prodImg = UUID.randomUUID().toString();
    }

    @NotNull
    private Long prodTotalstock;
    private LocalDate prodInsdate;
    @NotNull
    private Long prodProperstock;
    private String prodSize;
    private String prodColor;
    private String prodDelivery;
    private String prodUnit;
    private Long prodQtyin;
    private Long prodQtysale;
    private Long prodMileage;

    @ToString.Exclude
    private LprodVO lprod; // ProdVO Has A LprodVO (1:1)
    @ToString.Exclude
    private BuyerVO buyer; // ProdVO Has A BuyerVO (1:1)
}












