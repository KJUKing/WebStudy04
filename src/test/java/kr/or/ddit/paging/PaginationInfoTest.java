package kr.or.ddit.paging;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PaginationInfoTest {

    @Test
    void getTotalPage() {
        PaginationInfo paging = new PaginationInfo();
        paging.setTotalRecord(103);
        assertEquals(11, paging.getTotalRecord());
        paging.setTotalRecord(110);
        assertEquals(11, paging.getTotalRecord());
    }

    @Test
    void getEndRow() {
    }

    @Test
    void getStartRow() {
    }

    @Test
    void getEndPage() {
    }

    @Test
    void startPage() {
    }
}