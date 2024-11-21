package kr.or.ddit.paging;

public class PageVO {

    private int start;        // 시작 게시물 번호
    private int end;          // 끝 게시물 번호
    private int startPage;    // 시작 페이지 번호
    private int endPage;      // 끝 페이지 번호
    private int totalPage;    // 전체 페이지 수
    private int currentPage;  // 현재 페이지 번호

    // 가변적인 값으로 변경
    private int perList;
    private int perPage;

    //기본생성자는 10과 5로 설정
    public PageVO() {
        this.perList = 10;
        this.perPage = 5;
    }

    public PageVO(int perList, int perPage) {
        this.perList = perList;
        this.perPage = perPage;
    }


    // 게시물의 시작 번호 반환
    public int getStart() {
        return start;
    }

    // 게시물의 시작 번호 설정
    public void setStart(int start) {
        this.start = start;
    }

    // 게시물의 끝 번호 반환
    public int getEnd() {
        return end;
    }

    // 게시물의 끝 번호 설정
    public void setEnd(int end) {
        this.end = end;
    }

    // 시작 페이지 번호 반환
    public int getStartPage() {
        return startPage;
    }

    // 시작 페이지 번호 설정
    public void setStartPage(int startPage) {
        this.startPage = startPage;
    }

    // 끝 페이지 번호 반환
    public int getEndPage() {
        return endPage;
    }

    // 끝 페이지 번호 설정
    public void setEndPage(int endPage) {
        this.endPage = endPage;
    }

    // 전체 페이지 수 반환
    public int getTotalPage() {
        return totalPage;
    }

    // 전체 페이지 수 설정
    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    // 현재 페이지 수 반환
    public int getCurrentPage() {
        return currentPage;
    }
    // 현재 페이지 수 설정
    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }


}
