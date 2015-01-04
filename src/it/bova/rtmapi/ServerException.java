//Copyright 2012 Giovanni Pini
//Licensed under the Apache License, Version 2.0 (the "License");
//you may not use this file except in compliance with the License.
//You may obtain a copy of the License at
//http://www.apache.org/licenses/LICENSE-2.0
//Unless required by applicable law or agreed to in writing, software
//distributed under the License is distributed on an "AS IS" BASIS,
//WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
//See the License for the specific language governing permissions and
//limitations under the License.

package it.bova.rtmapi;

/**
 * An exception message thrown by the Remember the Milk server
 * @author Giovanni Pini
 *
 */
public class ServerException extends Exception {
	
	private int code;
	private String message;
	
	ServerException(int code, String msg) {
		this.code = code;
		this.message = msg;
	}
	
	/**
	 * Gets a string containing both error code and message
	 * @return a complete error description with error code and message
	 */
	public String getCompleteMessage() {
		return "Error " + this.code + " - " + this.message;
	}
	
	/**
	 * Gets the string message of the error
	 * @return the message associated to the error
	 */
	@Override
	public String getMessage() {
		return this.message;
	}
	
	/**
	 * Gets the numeric code of the error
	 * @return the code associated to the error
	 */
	public int getCode() {
		return this.code;
	}
	
	/**
	 * Determines if the server error is related to invalid token
	 * @return true if token is valid, false otherwise
	 */
	public boolean isLoginIssue() {
		if(this.code==98) return true;
		else return false;
	}


}
