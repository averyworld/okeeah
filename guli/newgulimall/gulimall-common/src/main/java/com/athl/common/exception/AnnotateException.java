package com.athl.common.exception;


import com.athl.common.utils.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.io.IOException;

@ControllerAdvice
@Slf4j
public class AnnotateException {
    /**
     * Description : 全局异常捕捉处理
     * @param ex
     * @return
     */
    @ExceptionHandler(IOException.class)
    public R bizException(IOException ex) {
        log.error("bizException 异常抛出：{}", ex);
        return R.error(String.valueOf(ex));
    }

}

