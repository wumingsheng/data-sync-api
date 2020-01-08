package cn.com.boe.cms.datasyncapi.common.exception;

public enum ExceptionEnum {
	
	
	JSON_PARSE_ERR(200001, "JSON解析错误"),
    
    ARGS_VALIDATE_ERR(200002, "请求参数错误");
	
	private Integer code;

    private String message;

    ExceptionEnum(Integer code, String message){
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }


}
