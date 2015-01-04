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

import java.io.Serializable;

/**
 * An object containing the token string and other useful user information. The fields are:
 * <ul>
 * <li> <b>token</b> - The token string 
 * <li> <b>userId</b> - The user ID
 * <li> <b>userName</b> - The user short name
 * <li> <b>fullName</b> - The user full name
 * <li> <b>permission</b> - The permission granted by the server to the application for this user.
 * <ul>
 * @author Giovanni Pini
 *
 */
public class Token implements Serializable{
	
	private Permission permission;
	private String token;
	private String userId;
	private String userName;
	private String fullName;
	
	public Token(Permission permission, String token, String userId,
			String userName, String fullName) {
		this.permission = permission;
		this.token = token;
		this.userId = userId;
		this.userName = userName;
		this.fullName = fullName;
	}
	
	public Permission getPermission() {
		return permission;
	}
	public String getToken() {
		return token;
	}
	public String getUserId() {
		return userId;
	}
	public String getUserName() {
		return userName;
	}
	public String getFullName() {
		return fullName;
	}
	
	

}
