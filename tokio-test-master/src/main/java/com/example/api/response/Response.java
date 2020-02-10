package com.example.api.response;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Response<T> {

	private T data;
	private List<String> errors;
	private String message;

	public Response() {

	}

	public Response(T data) {
		this.data = data;
	}

	public Response(List<String> erros) {
		this.errors = erros;
	}
	
	public List<String> getErrors() {
		if (this.errors == null) {
			this.errors = new ArrayList<>();
		}
		return errors;
	}

}
