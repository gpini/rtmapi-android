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

import org.json.JSONObject;

/**
 * The lists for tasks as in Remember the Milk, both non-smart and smart. The contained fields are:
 * <ul>
 * <li> <b>id</b> - The string unique ID
 * <li> <b>name</b> - The list name
 * <li> <b>archived</b> - A boolean field that is true if the list was archived
 * <li> <b>deleted</b> - A boolean field that is true if the list was archived
 * <li> <b>locked</b> - A boolean field that is true if the list was locked
 * <li> <b>position</b> - The position order for display purpose
 * <li> <b>smart</b> - A boolean field that is true if the list is a smart list
 * <li> <b>sortOrder</b> - To be clarified; actually an int with unknown purpose
 * <ul>
 * @author Giovanni Pini
 *
 */
public class TaskList implements RtmObject,Serializable {
	
	private String id;
	private String name;
	private boolean archived;
	private boolean deleted;
	private boolean locked;
	private int position;
	private boolean smart;
	private int sortOrder;
	
	public TaskList(String id, String name, boolean archived, boolean deleted,
			boolean locked, int position, boolean smart, int sortOrder) {
		this.id = id;
		this.name = name;
		this.archived = archived;
		this.deleted = deleted;
		this.locked = locked;
		this.position = position;
		this.smart = smart;
		this.sortOrder = sortOrder;
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

	public boolean isArchived() {
		return archived;
	}

	public void setArchived(boolean archived) {
		this.archived = archived;
	}

	public boolean isDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}

	public boolean isLocked() {
		return locked;
	}

	public void setLocked(boolean locked) {
		this.locked = locked;
	}

	public int getPosition() {
		return position;
	}

	public void setPosition(int position) {
		this.position = position;
	}

	public boolean isSmart() {
		return smart;
	}

	public void setSmart(boolean smart) {
		this.smart = smart;
	}

	public int getSortOrder() {
		return sortOrder;
	}

	public void setSortOrder(int sortOrder) {
		this.sortOrder = sortOrder;
	}
	
	@Override
	public boolean equals(Object o) {
		if (o instanceof TaskList) return this.getId().equals(((TaskList)o).getId());
		else return false;
	}

	@Override
	public String toString() {
		return "RtmList [id=" + id + ", name=" + name + ", archived="
				+ archived + ", deleted=" + deleted + ", locked=" + locked
				+ ", position=" + position + ", smart=" + smart
				+ ", sortOrder=" + sortOrder + "]";
	}
	
}
