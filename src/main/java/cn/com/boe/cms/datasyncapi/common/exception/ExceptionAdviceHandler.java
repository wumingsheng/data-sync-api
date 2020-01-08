package cn.com.boe.cms.datasyncapi.common.exception;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import cn.com.boe.cms.datasyncapi.common.entity.ResponseResult;
import cn.com.boe.cms.datasyncapi.common.valid.BeanValidator;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.HttpStatus;

@RestControllerAdvice
@Slf4j
public class ExceptionAdviceHandler {

	/** json 格式错误全局异常处理 */
	@ExceptionHandler(value = JsonErrException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ResponseBody
	public Object jsonErrException(Exception e, HttpServletResponse response) {
		String message = e.getMessage();
		log.error(message);
		return f(ExceptionEnum.JSON_PARSE_ERR, e.getMessage());
	}

	/** 请求参数校验错误全局异常处理 */
	@ExceptionHandler(value = ArgumentsValidateException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ResponseBody
	public Object argumentsValidateException(ArgumentsValidateException e) {
		List<String> list = BeanValidator.extractPropertyAndMessageAsList(e);
		return f(ExceptionEnum.ARGS_VALIDATE_ERR, list);
	}

	/** 类型转换错误错误全局异常处理 */
	@ExceptionHandler(value = ClassCastException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ResponseBody
	public Object classCastException(ClassCastException e) {
		log.error(ExceptionUtils.getStackTrace(e));
		return f(ExceptionEnum.ARGS_VALIDATE_ERR, e.getMessage());
	}

	/** 参数null全局异常处理 */
	@ExceptionHandler(value = ParamMissException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ResponseBody
	public Object paramMissException(ParamMissException e) {
		log.error(e.getMessage(), e);
		return f(ExceptionEnum.ARGS_VALIDATE_ERR, e.getMessage());
	}

	private Object f(ExceptionEnum exceptionEnum, Object obj) {
		return new ResponseResult<>(exceptionEnum, obj);
		 
	}

}
