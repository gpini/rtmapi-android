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
 * An object for a Remember the Milk contact
 * <ul>
 * <li> <b>id</b> - The string unique ID
 * <li> <b>fullname</b> - The full name of the contact
 * <li> <b>username</b> - The username of the contact, used for login
 * </ul>
 * @author Giovanni Pini
 *
 */
public class Contact implements RtmObject,Serializable {

	private String id;
	private String fullname;
	private String username;

	public Contact(String id, String fullname, String username) {
		this.id = id;
		this.fullname = fullname;
		this.username = username;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return username;
	}
	public String getFullname() {
		return fullname;
	}
	public void setFullname(String fullname) {
		this.fullname = fullname;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	
	@Override
	public boolean equals(Object o) {
		if (o instanceof Contact) return this.getId().equals(((Contact)o).getId());
		else return false;
	}
	
	@Override
	public String toString() {
		return "RtmContact [id=" + id + ", fullname=" + fullname
				+ ", username=" + username + "]";
	}
}
