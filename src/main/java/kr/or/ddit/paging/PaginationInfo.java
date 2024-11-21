package kr.or.ddit.paging;

import kr.or.ddit.vo.ProdVO;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

@Getter
public class PaginationInfo<T> {

    public PaginationInfo() {
        this(10, 5);
    }

    public PaginationInfo(int screenSize, int blockSize) {
        super();
        this.screenSize = screenSize;
        this.blockSize = blockSize;
        this.variousConditions = new HashMap<>();
    }

    @Setter
    private SimpleCondition simpleCondition;

    @Setter
    private T detailCondition;

    @Setter
    private Map<String, Object> variousConditions;

    public void addVariousCondition(String conditionName, Object conditionValue) {
        variousConditions.put(conditionName, conditionValue);
    }

    @Setter
    private int totalRecord; // DB 로부터 조회

    private int screenSize; // 임의 결정
    private int blockSize; // 임의 결정

    @Setter
    private int currentPage; // 요청 파라미터

    public int getTotalPage() {
        int totalPage = (totalRecord + (screenSize-1)) / screenSize;
        return totalPage;
    }

    public int getEndRow() {
        int endRow = currentPage * screenSize;
        return endRow;
    }

    public int getStartRow() {
        int startRow = getEndRow() - (screenSize - 1);
        return startRow;
    }

    public int getEndPage() {
        int endPage = ((currentPage + (blockSize - 1)) / blockSize) * blockSize;
        return endPage;
    }

    public int getStartPage() {
        int startPage = getEndPage() - (blockSize - 1);
        return startPage;
    }
}