package kr.or.ddit.paging;

public class PaginationUtil {
    public static PageVO getPageInfo(int page, int totalRecord, int perList, int perPage) {
        PageVO pvo = new PageVO();

        // 현재 페이지 설정
        pvo.setCurrentPage(page);

        //전체 페이지 수 계산
        int totalPage = (int) Math.ceil((double) totalRecord / perList);
        if (page > totalPage) {
            page = totalPage;
        }

        //시작과 끝 게시물 번호 계산
        int start = (page - 1) * perList + 1;
        int end = start + perList -1;
        if (end > totalRecord) {
            end = totalRecord;
        }

        //페이지 블록계산
        int startPage = ((page - 1) / perPage * perPage) + 1;
        int endPage = startPage + perPage - 1;
        if (endPage > totalPage) {
            endPage = totalPage;
        }

        //PageVO에 설정
        pvo.setStart(start);
        pvo.setEnd(end);
        pvo.setStartPage(startPage);
        pvo.setEndPage(endPage);
        pvo.setTotalPage(totalPage);

        return pvo;
    }
}

