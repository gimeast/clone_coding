package gimeast.restfulapiex2.advice;

import gimeast.restfulapiex2.member.exception.MemberTaskException;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
@Log4j2
public class TokenControllerAdvice {
    /**
     * 열거형 MemberExceptions 예외 발생시 핸들링
     * @param exception
     * @return
     */
    @ExceptionHandler(MemberTaskException.class)
    public ResponseEntity<Map<String, String>> handleTaskException(MemberTaskException exception) {
        log.error(exception.getMessage());

        String msg = exception.getMsg();
        int status = exception.getCode();

        Map<String, String> map = Map.of("error", msg);
        return ResponseEntity.status(status).body(map);
    }

    /**
     * 접근권한이 없는 사용자가 자원 접근시 핸들링
     * @param exception
     * @return
     */
    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<?> handleAccessDeniedException(AccessDeniedException exception) {
        log.info("handleAccessDeniedException...........");
        Map<String, Object> errors = new HashMap<>();
        errors.put("message", exception.getMessage());
        return new ResponseEntity<>(errors, HttpStatus.FORBIDDEN);
    }
}
