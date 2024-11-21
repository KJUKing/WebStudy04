package kr.or.ddit.vo;

import kr.or.ddit.validate.DeleteGroup;
import kr.or.ddit.validate.InsertGroup;
import kr.or.ddit.validate.constraint.TelephoneNumber;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.*;
import javax.validation.groups.Default;
import java.io.IOException;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Base64;
import java.util.List;

/**
 * 회원관리용 Domain Layer
 *
 */
@Getter
@Setter
@EqualsAndHashCode(of = {"memId", "memRegno1", "memRegno2"})
@ToString
public class MemberVO implements Serializable{
    private int rnum;

    @NotBlank(groups = {Default.class, DeleteGroup.class})
    @Size(max = 15, groups = {Default.class, DeleteGroup.class})
    private String memId;
    @NotBlank(groups = {Default.class, DeleteGroup.class})
    @Size(min = 4, max = 8, groups = {Default.class, DeleteGroup.class})
    @ToString.Exclude
    private transient String memPass;
    @NotBlank
    private String memName;
    @NotBlank(groups = InsertGroup.class)
    @Size(min = 6, max = 6, groups = InsertGroup.class)
    @ToString.Exclude
    private transient String memRegno1;
    @NotBlank(groups = InsertGroup.class)
    @Size(min = 7, max = 7, groups = InsertGroup.class)
    @ToString.Exclude
    private transient String memRegno2;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime memBir;
    @NotBlank
    private String memZip;
    @NotBlank
    private String memAdd1;
    @NotBlank
    private String memAdd2;

    @TelephoneNumber()
    private String memHometel;
    @Pattern(regexp = "\\d{2,3}-\\d{3,4}-\\d{4}")
    private String memComtel;
    @Pattern(regexp = "\\d{3}-\\d{3,4}-\\d{4}")
    private String memHp;

    @NotBlank
    @Email
    private String memMail;
    private String memJob;
    private String memLike;
    private String memMemorial;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate memMemorialday;
    @Min(0)
    private Long memMileage;
    private boolean memDelete;
    private String memRole;

    // 회원 프로필 관리용 프러퍼티 (BLOB - Binary Large OBject)
    private byte[] memImg; // 데이터베이스 지원
    private MultipartFile memImage; // 클라이언트 업로드 파일 지원

    public void setMemImage(MultipartFile memImage) throws IOException {
        if(memImage==null || memImage.isEmpty()) return;

        this.memImage = memImage;
        this.memImg = memImage.getBytes();
    }

    public String getBase64Img() {
        if(memImg!=null && memImg.length > 0) {
            return Base64.getEncoder().encodeToString(memImg);
        }else {
            return null;
        }
    }

    // 구매기록용 프로퍼티
    @ToString.Exclude
    private List<CartVO> cartList;
}












