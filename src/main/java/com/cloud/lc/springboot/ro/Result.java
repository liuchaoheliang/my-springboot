package com.cloud.lc.springboot.ro;

import com.alibaba.fastjson.JSON;

/**
 * 业务执行返回体
 * @author 刘超
 */
public class Result {
	/**
	 * 成功
	 */
	public static final Integer RESP_CODE_SUCCESS = 1;
	
	/**
	 * 失败
	 */
	public static final Integer RESP_CODE_FAIL = 0;
	
	/**
	 * 响应码
	 */
	private Integer code;
	
	/**
	 * 响应消息
	 */
	private String msg;
	
	/**
	 * 响应对象
	 */
	private Object data;

	public Result(){
		
	}

	public Result(Integer code, String msg, Object data) {
		this.code = code;
		this.msg = msg;
		this.data = data;
	}

	public static Result succ( Object respObject ){
		return new Result( RESP_CODE_SUCCESS, null, respObject );
	}
	
	public static Result succ( String respMsg, Object respObject ){
		return new Result( RESP_CODE_SUCCESS, respMsg, respObject );
	}
	
	public static Result fail( String respMsg ){
		return new Result( RESP_CODE_FAIL, respMsg, null );
	}
	
	public Boolean isSucc(){
		return RESP_CODE_SUCCESS.equals( this.code );
	}
	
	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}
	

	@Override
	public String toString() {
		return JSON.toJSONString(this);
	}
	
}
