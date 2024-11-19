package kr.or.ddit.commons.exception;

/**
 * primary key를 검색 조건으로 사용하는 경우, 해당 레코드가 존재하지 않는 상황에 대한 예외
 */
public class PKNotFoundException extends RuntimeException{

    public PKNotFoundException() {
        super();
    }

    public PKNotFoundException(String message) {
        super(message);
    }

    public PKNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public PKNotFoundException(Throwable cause) {
        super(cause);
    }

    protected PKNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
