package hu.innobyte.rest;

import org.json.JSONObject;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RestResponse {
	public final static String OK = "ok";
	public final static String ERROR = "error";
	public final static String STATUS = "result";
	public final static String RESPONSE = "responseText";
	
	private String status;
	private String response;
	
	public static JSONObject createOkResponse(String response) {
		JSONObject jsonObject = new JSONObject();
		
		jsonObject.put(STATUS, OK);
		jsonObject.put(RESPONSE, response);
		
		return jsonObject;
	}
	
	public static JSONObject createErrorResponse(String errorResponse) {
		JSONObject jsonObject = new JSONObject();
		
		jsonObject.put(STATUS, ERROR);
		jsonObject.put(RESPONSE, errorResponse);
		
		return jsonObject;
	}
	
	public static JSONObject createErrorResponse(ErrorResponse errorResponse) {
		return createErrorResponse(errorResponse.getErrorResponseMessage());
	}
}
