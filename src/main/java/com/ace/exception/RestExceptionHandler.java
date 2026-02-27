package com.ace.exception;

import java.sql.SQLException;

import javax.persistence.EntityNotFoundException;
import javax.persistence.LockTimeoutException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.OptimisticLockException;
import javax.persistence.PessimisticLockException;
import javax.persistence.QueryTimeoutException;
import javax.validation.ConstraintViolationException;

import com.ace.AceStatusCode;
// import com.ace.module.integration.habanero.dto.FromProvider.response.FundTransferResponseDto;
// import com.ace.module.integration.habanero.exception.HabaneroException;
// import com.ace.module.integration.pragmatic.core.PragmaticException;
// import com.ace.module.integration.pragmatic.core.payload.PragmaticBaseResponse;
// import com.ace.module.integration.saba.dto.FromProvider.response.SabaErrorResponseDTO;
// import com.ace.module.integration.saba.exception.SabaException;
// import com.ace.module.system.payload.AceErrorPayload;

import com.ace.payload.AceErrorPayload;

import org.hibernate.StaleObjectStateException;
import org.hibernate.StaleStateException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DeadlockLoserDataAccessException;
import org.springframework.dao.PessimisticLockingFailureException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.orm.jpa.JpaObjectRetrievalFailureException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Order(Ordered.HIGHEST_PRECEDENCE)
@RestControllerAdvice
@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

        @Setter
        @Getter
        @Accessors(chain = true)
        static class Error {
                private AceStatusCode status;
                private String message;
        }

        private static final Logger logger = LoggerFactory.getLogger(RestExceptionHandler.class);

        // @ExceptionHandler(PragmaticException.class)
        // public ResponseEntity<?> handlePragmaticException(PragmaticException ex) {
        // PragmaticBaseResponse response = new
        // PragmaticBaseResponse(ex.getStatusResponse());
        // ex.printStackTrace();
        // log.error(response.getError() + ": " + response.getDescription());
        // return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        // }

        // @ExceptionHandler(com.ace.module.integration.playtech.exception.PlaytechException.class)
        // public ResponseEntity<?> handlePlaytechException(
        // com.ace.module.integration.playtech.exception.PlaytechException ex) {

        // log.error("[Playtech] Exception: {} - {}", ex.getError().getCode(),
        // ex.getError().getDescription());

        // var body = new
        // com.ace.module.integration.playtech.dto.response.PlaytechErrorResponse(
        // ex.getRequestId(),
        // ex.getError());

        // return ResponseEntity
        // .status(ex.getHttpStatus())
        // .body(body);
        // }

        // @ExceptionHandler(SabaException.class)
        // public ResponseEntity<?> handleSabaException(SabaException ex) {
        // log.error("[SABA] Exception: {} - {}", ex.getStatus(), ex.getMsg());
        // var body = new SabaErrorResponseDTO()
        // .setStatus(ex.getStatus())
        // .setMsg(ex.getMsg());
        // return ResponseEntity.status(ex.getHttpStatus()).body(body);
        // }

        // @ExceptionHandler(AceException.class)
        // @ResponseBody
        // public Object handleAceException(AceException ex) {
        // return ResponseEntity.status(ex.getStatus().getHttpStatus())
        // .body(
        // new Error()
        // .setStatus(ex.getStatus())
        // .setMessage(ex.getMessage()));
        // }

        // @ExceptionHandler(HabaneroException.class)
        // public ResponseEntity<?> handleHabaneroException(HabaneroException ex) {

        // if (ex.getResponseBody() != null) {
        // return ResponseEntity
        // .status(ex.getHttpStatus())
        // .body(ex.getResponseBody());
        // }

        // FundTransferResponseDto.FundTransferResponse.Status status = new
        // FundTransferResponseDto.FundTransferResponse.Status(
        // false, // success
        // true,
        // null,
        // null,
        // null,
        // null);

        // FundTransferResponseDto.FundTransferResponse fundTransferResponse = new
        // FundTransferResponseDto.FundTransferResponse(
        // status, null, null);

        // FundTransferResponseDto body = new
        // FundTransferResponseDto(fundTransferResponse);

        // return ResponseEntity.status(ex.getHttpStatus()).body(body);
        // }

        // region Common
        @ExceptionHandler({ MethodArgumentTypeMismatchException.class })
        public ResponseEntity<?> handleMethodArgumentTypeMismatch(MethodArgumentTypeMismatchException ex,
                        WebRequest request) {
                log.warn("MethodArgumentTypeMismatchException: {}", ex.getMessage());
                // KEY_INVALID_PARAMETER: Invalid parameter. Please check your input.
                return buildResponseEntity(new AceErrorPayload(AceStatusCode.BAD_REQUEST, "KEY_INVALID_PARAMETER", ex),
                                request);
        }

        @ExceptionHandler({ ConstraintViolationException.class })
        public ResponseEntity<?> handleConstraintViolation(ConstraintViolationException ex, WebRequest request) {
                log.warn("ConstraintViolationException: {}", ex.getMessage());
                // KEY_VALIDATION_ERROR: Please check your information and try again.
                return buildResponseEntity(new AceErrorPayload(AceStatusCode.BAD_REQUEST, "KEY_VALIDATION_ERROR", ex),
                                request);
        }

        // 403 FORBIDDEN
        @ExceptionHandler(AccessDeniedException.class)
        public ResponseEntity<?> forbidden(AccessDeniedException ex, WebRequest request) {
                log.warn("AccessDeniedException: {}", ex.getMessage());
                // KEY_ACCESS_DENIED: You don't have permission to access this resource.
                return buildResponseEntity(new AceErrorPayload(AceStatusCode.FORBIDDEN, "KEY_ACCESS_DENIED", ex),
                                request);
        }

        // region Data Exceptions with User-Friendly Messages
        @ExceptionHandler({
                        SQLException.class,
                        DataAccessException.class,
                        DataIntegrityViolationException.class,
                        ObjectOptimisticLockingFailureException.class,
                        OptimisticLockException.class,
                        EntityNotFoundException.class,
                        JpaObjectRetrievalFailureException.class,
                        StaleStateException.class,
                        StaleObjectStateException.class,
                        LockTimeoutException.class,
                        PessimisticLockException.class,
                        PessimisticLockingFailureException.class,
                        DeadlockLoserDataAccessException.class,
                        QueryTimeoutException.class,
                        QueryTimeoutException.class,
                        TransactionSystemException.class,
        })
        public ResponseEntity<?> dataException(Exception ex, WebRequest request) {
                log.error("DataException: {}", ex.getMessage());
                ex.printStackTrace();

                // DataIntegrityViolationException - unique constraint violation
                // KEY_DATA_CONFLICT: Dữ liệu xung đột, vui lòng thử lại
                if (ex instanceof DataIntegrityViolationException) {
                        log.warn("DataIntegrityViolationException: {}", ex.getMessage());
                        return buildResponseEntity(
                                        new AceErrorPayload(AceStatusCode.BAD_REQUEST, "KEY_DATA_CONFLICT", ex),
                                        request);
                }

                // OptimisticLockException - concurrent update
                // KEY_DATA_CHANGED_PLEASE_REFRESH: Dữ liệu đã thay đổi, vui lòng làm mới và thử
                // lại
                if (ex instanceof ObjectOptimisticLockingFailureException
                                || ex instanceof OptimisticLockException) {
                        log.warn("OptimisticLockException: {}", ex.getMessage());
                        return buildResponseEntity(
                                        new AceErrorPayload(AceStatusCode.BAD_REQUEST,
                                                        "KEY_DATA_CHANGED_PLEASE_REFRESH", ex),
                                        request);
                }

                // EntityNotFoundException - record not found
                // KEY_RECORD_NOT_FOUND: Không tìm thấy bản ghi
                if (ex instanceof EntityNotFoundException
                                || ex instanceof JpaObjectRetrievalFailureException) {
                        log.warn("EntityNotFoundException: {}", ex.getMessage());
                        return buildResponseEntity(
                                        new AceErrorPayload(AceStatusCode.NOT_FOUND, "KEY_RECORD_NOT_FOUND", ex),
                                        request);
                }

                // StaleStateException - record deleted during operation
                // KEY_RECORD_DELETED: Bản ghi đã bị xóa
                if (ex instanceof StaleStateException
                                || ex instanceof StaleObjectStateException) {
                        log.warn("StaleStateException: {}", ex.getMessage());
                        return buildResponseEntity(
                                        new AceErrorPayload(AceStatusCode.NOT_FOUND, "KEY_RECORD_DELETED", ex),
                                        request);
                }

                // Lock/Deadlock exceptions
                // KEY_SERVER_BUSY_PLEASE_RETRY: Server đang bận, vui lòng thử lại
                if (ex instanceof LockTimeoutException
                                || ex instanceof PessimisticLockException
                                || ex instanceof PessimisticLockingFailureException
                                || ex instanceof DeadlockLoserDataAccessException) {
                        log.warn("LockException: {}", ex.getMessage());
                        return buildResponseEntity(
                                        new AceErrorPayload(AceStatusCode.SERVICE_UNAVAILABLE,
                                                        "KEY_SERVER_BUSY_PLEASE_RETRY", ex),
                                        request);
                }

                // QueryTimeoutException - query timeout
                // KEY_REQUEST_TIMEOUT: Yêu cầu quá thời gian, vui lòng thử lại
                if (ex instanceof QueryTimeoutException
                                || ex instanceof QueryTimeoutException) {
                        log.warn("QueryTimeoutException: {}", ex.getMessage());
                        return buildResponseEntity(
                                        new AceErrorPayload(AceStatusCode.GATEWAY_TIMEOUT, "KEY_REQUEST_TIMEOUT", ex),
                                        request);
                }

                // TransactionSystemException - transaction failed
                if (ex instanceof TransactionSystemException) {
                        log.warn("TransactionSystemException: {}", ex.getMessage());
                        Throwable rootCause = ((TransactionSystemException) ex).getRootCause();
                        // KEY_VALIDATION_ERROR: Lỗi validation
                        if (rootCause instanceof ConstraintViolationException) {
                                return buildResponseEntity(
                                                new AceErrorPayload(AceStatusCode.BAD_REQUEST, "KEY_VALIDATION_ERROR",
                                                                ex),
                                                request);
                        }
                        // KEY_TRANSACTION_FAILED: Giao dịch thất bại, vui lòng thử lại
                        return buildResponseEntity(
                                        new AceErrorPayload(AceStatusCode.INTERNAL_SERVER_ERROR,
                                                        "KEY_TRANSACTION_FAILED", ex),
                                        request);
                }

                // NonUniqueResultException - duplicate data
                // KEY_DUPLICATE_DATA_PLEASE_RETRY: Dữ liệu trùng lặp, vui lòng thử lại
                Throwable cause = ex.getCause();
                while (cause != null) {
                        if (cause instanceof NonUniqueResultException) {
                                log.warn("NonUniqueResultException detected: {}", ex.getMessage());
                                return buildResponseEntity(
                                                new AceErrorPayload(AceStatusCode.BAD_REQUEST,
                                                                "KEY_DUPLICATE_DATA_PLEASE_RETRY", ex),
                                                request);
                        }
                        cause = cause.getCause();
                }

                // KEY_DATA_ERROR: Lỗi dữ liệu, vui lòng liên hệ hỗ trợ
                return buildResponseEntity(
                                new AceErrorPayload(AceStatusCode.INTERNAL_SERVER_ERROR, "KEY_DATA_ERROR", ex),
                                request);
        }
        // endregion

        // 500 - Generic exception
        // @ExceptionHandler({ Exception.class })
        // public ResponseEntity<?> internalServerError(Exception ex, WebRequest
        // request) {
        // if (ex instanceof
        // com.ace.module.integration.playtech.exception.PlaytechException) {
        // throw (com.ace.module.integration.playtech.exception.PlaytechException) ex;
        // }
        // log.error("InternalServerError: {}", ex.getMessage());
        // ex.printStackTrace();
        // // KEY_INTERNAL_ERROR: Lỗi hệ thống, vui lòng liên hệ hỗ trợ
        // return buildResponseEntity(
        // new AceErrorPayload(AceStatusCode.INTERNAL_SERVER_ERROR,
        // "KEY_INTERNAL_ERROR", ex),
        // request);
        // }
        // endregion

        // region Inherit
        @Override
        protected ResponseEntity<Object> handleExceptionInternal(Exception ex,
                        Object body,
                        HttpHeaders headers,
                        HttpStatus status,
                        WebRequest request) {
                return buildResponseEntity(
                                new AceErrorPayload(
                                                AceStatusCode.INTERNAL_SERVER_ERROR,
                                                ex.getMessage(),
                                                ex),
                                request);
        }

        @Override
        public ResponseEntity<Object> handleMethodArgumentNotValid(
                        MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status,
                        WebRequest request) {
                return buildResponseEntity(new AceErrorPayload(AceStatusCode.BAD_REQUEST, ex.getMessage(), ex), null);
        }
        // endregion

        public static final ResponseEntity<Object> buildResponseEntity(AceErrorPayload aceErrorPayload,
                        WebRequest request) {
                aceErrorPayload.setRequest(request);
                return new ResponseEntity<>(aceErrorPayload, aceErrorPayload.getHttpStatus());
        }
}