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
 * An enum for the possible server permissions
 * @author Giovanni Pini
 *
 */
public enum Permission {
	/**
	 * No permission
	 */
	NONE("none"),
	/**
	 * gives the ability to read task, contact, group and list details and contents.
	 */
	READ("read"),
	/**
	 * gives the ability to add and modify task, contact, group and list details and contents (also allows you to <b>read</b>).
	 */
	WRITE("write"),
	/**
	 * gives the ability to delete tasks, contacts, groups and lists (also allows you to <b>read</b> and <b>write</b>).
	 */
	DELETE("delete");
	private String permission;
	private Permission(String perm) {this.permission = perm; }
	public String toString() {return this.permission; }
}