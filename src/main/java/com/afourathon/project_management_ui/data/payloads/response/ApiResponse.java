package com.afourathon.project_management_ui.data.payloads.response;

import org.springframework.http.HttpStatus;

import lombok.Data;

@Data
public class ApiResponse {
	
	private String message;
	private HttpStatus status;
	
	public ApiResponse() {
		
	}

    public ApiResponse(String message, HttpStatus status){
        this.message = message;
        this.status = status;
    }

    public void setMessage(String message){
        this.message = message;
    }

    public String getMessage(){
        return message;
    }

	public HttpStatus getStatus() {
		return status;
	}

	public void setStatus(HttpStatus status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "ApiResponse [message=" + message + ", status=" + status + "]";
	}
	
}
