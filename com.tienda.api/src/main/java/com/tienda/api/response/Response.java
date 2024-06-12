package com.tienda.api.response;

public class Response {

	public Response(String message) {
		super();
		this.message=message;
	}
	
	/**
	 * Mensaje de error.
	 */
	String message;

	/**
	 * @return the mensaje
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param mensaje the mensaje to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Response [");
		if (message != null) {
			builder.append("message=");
			builder.append(message);
		}
		builder.append("]");
		return builder.toString();
	}
}
