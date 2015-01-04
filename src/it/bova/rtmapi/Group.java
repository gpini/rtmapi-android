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
import java.util.Arrays;

/**
 * An object for Remember the Milk groups of contacts
 * <ul>
 * <li> <b>id</b> - The string unique ID
 * <li> <b>name</b> - The name label of the group
 * <li> <b>contactIds</b> - A string array with the IDs of contacts contained in the group (See {@link Contact})
 * </ul>
 * @author Giovanni Pini
 *
 */
public class Group implements RtmObject,Serializable {
	
	private String id;
	private String name;
	private String[] contactIds;
	
	public Group(String id, String name, String[] contactIds) {
		this.id = id;
		this.name = name;
		this.contactIds = contactIds;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String[] getContacts() {
		return contactIds;
	}

	public void setContacts(String[] contacts) {
		this.contactIds = contacts;
	}
	
	@Override
	public boolean equals(Object o) {
		if (o instanceof Group) return this.getId().equals(((Group)o).getId());
		else return false;
	}

	@Override
	public String toString() {
		return "RtmGroup [id=" + id + ", name=" + name + ", contactIds="
				+ Arrays.toString(contactIds) + "]";
	}
	
	

}
