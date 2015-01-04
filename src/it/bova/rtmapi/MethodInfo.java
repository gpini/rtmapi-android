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

import java.util.Arrays;

/**
 * An object containing all the necessary information for a Remember the Milk API method.
 * <p>Used in reflections methods (See RtmApi}.
 * <p>The fields are:
 * <ul>
 * <li> <b>name</b> - The method name
 * <li> <b>description</b> - A brief description of the method
 * <li> <b>needsLogin</b> - A boolean value that is true if the method requires the user logged in
 * <li> <b>needsSigning</b> - A boolean value that is true if the method requires the request to be signed
 * <li> <b>permission</b> - The Permission required for the methos
 * <li> <b>argumentName</b> - A string array with all argument names that can be passed in the request
 * <li> <b>argumentIsOptional</b> - A boolean array, following the same indexes of argumentName, identifying if the corresponding argument is optional
 * <li> <b>argumentDescription</b> - A string array, following the same indexes of argumentName, explaining the corresponding argument meaning
 * <li> <b>errorCode</b> - A string array with all the possible errors that can be returned by the method
 * <li> <b>errorMessage</b> - A string array, following the same indexes of errorCode, with the corresponding error message
 * <li> <b>errorDescription</b> - A string array, following the same indexes of errorCode, with a detailed description of the corresponding error
 * <li> <b>exampleResponse</b> - An example response in XML format
 * @author Giovanni Pini
 * 
 */
public class MethodInfo {
	private String name = "";
	private String description = "";
	private boolean needsLogin = true;
	private boolean needsSigning = true;
	private Permission permission = Permission.DELETE;
	private String[] argumentName = new String[0];
	private boolean[] argumentIsOptional = new boolean[0];
	private String[] argumentDescription = new String[0];
	private int[] errorCode = new int[0];
	private String[] errorMessage = new String[0];
	private String[] errorDescription = new String[0];
	private String exampleResponse = "";
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public boolean isNeedsLogin() {
		return needsLogin;
	}
	public void setNeedsLogin(boolean needsLogin) {
		this.needsLogin = needsLogin;
	}
	public boolean isNeedsSigning() {
		return needsSigning;
	}
	public void setNeedsSigning(boolean needsSigning) {
		this.needsSigning = needsSigning;
	}
	public Permission getPermission() {
		return permission;
	}
	public void setPermission(Permission permission) {
		this.permission = permission;
	}
	public String[] getArgumentName() {
		return argumentName;
	}
	public void setArgumentName(String[] argumentName) {
		this.argumentName = argumentName;
	}
	public boolean[] getArgumentIsOptional() {
		return argumentIsOptional;
	}
	public void setArgumentIsOptional(boolean[] argumentIsOptional) {
		this.argumentIsOptional = argumentIsOptional;
	}
	public String[] getArgumentDescription() {
		return argumentDescription;
	}
	public void setArgumentDescription(String[] argumentDescription) {
		this.argumentDescription = argumentDescription;
	}
	public int[] getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(int[] errorCode) {
		this.errorCode = errorCode;
	}
	public String[] getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String[] errorMessage) {
		this.errorMessage = errorMessage;
	}
	public String[] getErrorDescription() {
		return errorDescription;
	}
	public void setErrorDescription(String[] errorDescription) {
		this.errorDescription = errorDescription;
	}
	public String getExampleResponse() {
		return exampleResponse;
	}
	public void setExampleResponse(String exampleResponse) {
		this.exampleResponse = exampleResponse;
	}
	@Override
	public String toString() {
		String info = "";
		info += this.name + "\n";
		if(this.isNeedsLogin()) info += "Needs login - ";
		if(this.isNeedsLogin()) info += "Needs signing - ";
		if(this.getPermission() == Permission.DELETE) info += "Delete permission required";
		else if(this.getPermission() == Permission.WRITE) info += "Write permission required";
		else if(this.getPermission() == Permission.READ) info += "Read permission required";
		else info += "No permission required";
		info += "\n";
		info += "\nDescription:\n " + this.description + "\n";
		if(this.argumentName.length > 0) info += "\nArguments:\n";
		String[] argsName = this.argumentName;
		boolean[] argsOpt = this.argumentIsOptional;
		String[] argsDesc = this.argumentDescription;
		for(int i = 0; i < this.argumentName.length; i++) {
			info += argsName[i];
			if(argsOpt[i]) info += " (optional)";
			info += ": " + argsDesc[i] + "\n";
		}
		int[] errCode = this.errorCode;
		String[] errMsg = this.errorMessage;
		String[] errDesc = this.errorDescription;
		if(this.errorCode.length > 0) info += "\nErrors:\n";
		for(int i = 0; i < this.errorCode.length; i++) {
			info += "Error " + errCode[i] + ": " + errMsg[i] + " - " + errDesc[i] + "\n";
		}
		info += "\nExample response:\n" + this.exampleResponse;
		return info;
	}
	
	
	
}
