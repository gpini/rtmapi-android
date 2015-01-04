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
 * An object containing a note for Task objects. The fields are:
 * <ul>
 * <li> <b>id</b> - The string unique ID
 * <li> <b>title</b> - The title of the note
 * <li> <b>text</b> - The text inside the note
 * <li> <b>created</b> - A Date object identifying when the note has been created
 * <li> <b>created</b> - A Date object identifying when the note has been modified. It is null if the note has never been modified 
 * </ul>
 * @author Giovanni Pini
 *
 */
public class Note implements Serializable {
	
	private String id;
	private String title;
	private String text;
	private Date created;
	private Date modified;
	
	public Note(String id, String title, String text, Date created, Date modified) {
		this.id = id;
		this.title = title;
		this.text = text;
		this.created = created;
		this.modified = modified;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
	
	public Date getCreated() {
		return this.created;
	}
	
	public void setCreated(Date created) {
		this.created = created;
	}
	
	public Date getModified() {
		return this.modified;
	}
	
	public void setModified(Date modified) {
		this.modified = modified;
	}

	@Override
	public String toString() {
		return "RtmNote [id=" + id + ", title=" + title + ", text=" + text
				+ ", created=" + created + ", modified=" + modified + "]";
	}
	
	

}
