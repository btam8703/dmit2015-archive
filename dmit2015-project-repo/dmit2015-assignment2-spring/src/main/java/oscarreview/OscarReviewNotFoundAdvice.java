package oscarreview;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 This class contains the advice for an unfound OscarReview
 @author Benjamin Tam
 @version 1.0
 @since 2021-04-21
 */

@ControllerAdvice
class OscarReviewNotFoundAdvice {

    @ResponseBody
    @ExceptionHandler(OscarReviewNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String employeeNotFoundHandler(OscarReviewNotFoundException ex) {
        return ex.getMessage();
    }
}