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
import java.util.Date;

/**
 * An object containing relevant information about a deleted task (See {@link SynchedTasks}).
 * It is used in synchronization methods of RtmApi.
 * The fields are:
 * <ul>
 * <li> <b>id</b> - The id of the deleted task
 * <li> <b>taskSerieId</b> - The id of the taskeserie, to which the task was belonging
 * <li> <b>listId</b> - The id of the list, in which the task was inserted
 * <li> <b>deleted</b> - The id of the list, in which the task was inserted
 * </ul>
 * @author Giovanni Pini
 *
 */
public class DeletedTask implements Serializable{
	
	private String taskSerieId;
	private String listId;
	private String id;
	private Date deleted;
	
	public DeletedTask(String taskSerieId, String listId, String id, Date deleted) {
		this.taskSerieId = taskSerieId;
		this.id = id;
		this.deleted = deleted;
	}

	public String getTaskSerieId() {
		return taskSerieId;
	}

	public void setTaskSerieId(String taskSerieId) {
		this.taskSerieId = taskSerieId;
	}
	
	public String getlistId() {
		return listId;
	}

	public void setlistId(String listId) {
		this.listId = listId;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Date getDeleted() {
		return deleted;
	}

	public void setDeleted(Date deleted) {
		this.deleted = deleted;
	}

	@Override
	public String toString() {
		return "DeletedTask [taskSerieId=" + taskSerieId + ", listId=" + listId
				+ ", id=" + id + ", deleted=" + deleted + "]";
	}

}
