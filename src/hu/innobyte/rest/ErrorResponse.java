package hu.innobyte.rest;

import lombok.Getter;

@Getter
public enum ErrorResponse {
	InvalidRequest("Unknow JSON request format. Valid format is {\"queryText\" : \" query \"}"),
	UnknowQuestion("Unknow question: %s");
	
	
	private String errorResponseMessage;
	
	ErrorResponse(String errorResponseMessage) {
		this.errorResponseMessage = errorResponseMessage;
	}
}
