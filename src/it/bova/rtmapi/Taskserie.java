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
import java.util.Date;

class Taskserie {
	
	private String id;
	private String name;
	private String locationId;
	private String listId;
	private Date created;
	private Date modified;
	private Date deleted;
	private Note[] notes;
	private Contact[] participants;
	private Recurrence recurrence;
	private String source;
	private String[] tags;
	private String url;

	public Taskserie(String id, String name, String locationId, String listId,
			Date created, Date modified, Note[] notes, Recurrence recurrence,
			Contact[] participants, String source, String[] tags, String url) {
		this.id = id;
		this.name = name;
		this.locationId = locationId;
		this.listId = listId;
		this.created = created;
		this.modified = modified;
		this.deleted = null;
		this.notes = notes;
		this.participants = participants;
		this.recurrence = recurrence;
		this.source = source;
		this.tags = tags;
		this.url = url;
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

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public Date getModified() {
		return modified;
	}

	public void setModified(Date modified) {
		this.modified = modified;
	}
	
	public void setDeleted(Date deleted) {
		this.deleted = deleted;
	}

	public Date getDeleted() {
		return deleted;
	}

	public Note[] getNotes() {
		return notes;
	}

	public void setNotes(Note[] notes) {
		this.notes = notes;
	}

	public Contact[] getParticipants() {
		return participants;
	}

	public void setParticipants(Contact[] participants) {
		this.participants = participants;
	}

	public Recurrence getRecurrence() {
		return recurrence;
	}

	public void setRecurrence(Recurrence recurrence) {
		this.recurrence = recurrence;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getLocationId() {
		return locationId;
	}

	public void setLocationId(String locationId) {
		this.locationId = locationId;
	}
	
	public String getListId() {
		return listId;
	}

	public void setListId(String listId) {
		this.listId = listId;
	}

	public String[] getTags() {
		return tags;
	}

	public void setTags(String[] tags) {
		this.tags = tags;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Override
	public String toString() {
		return "Taskserie [id=" + id + ", name=" + name + ", locationId="
				+ locationId + ", listId=" + listId + ", created=" + created
				+ ", modified=" + modified + ", deleted=" + deleted
				+ ", notes=" + Arrays.toString(notes) + ", participants="
				+ Arrays.toString(participants) + ", recurrence=" + recurrence
				+ ", source=" + source + ", tags=" + Arrays.toString(tags)
				+ ", url=" + url + "]";
	}

}
