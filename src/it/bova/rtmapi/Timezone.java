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
 * An object containing the timezone information. The fields are:
 * <ul>
 * <li> <b>id</b> - The string id of the timezone. 
 * <li> <b>name</b> - The timezone name (e.g. "Asia/Hong_Kong")
 * <li> <b>dst</b> - Daylight Saving Time for the timezone at the moment of the request, expressed as hour offset
 * <li> <b>currentOffset</b> - The current offset between server and the timezone in seconds (including DST)
 * <li> <b>offset</b> - The current offset between server and the timezone in seconds (excluding DST)
 * <ul>
 * @author Giovanni Pini
 *
 */
public class Timezone implements RtmObject,Serializable{

	private String id;
	private String name;
	private int dst;
	private int currentOffset;
	private int offset;
	
	public Timezone(String id, String name, int dst, int currentOffset,
			int offset) {
		this.id = id;
		this.name = name;
		this.dst = dst;
		this.currentOffset = currentOffset;
		this.offset = offset;
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

	public int getDst() {
		return dst;
	}

	public void setDst(int dst) {
		this.dst = dst;
	}

	public int getCurrentOffset() {
		return currentOffset;
	}

	public void setCurrentOffset(int currentOffset) {
		this.currentOffset = currentOffset;
	}

	public int getOffset() {
		return offset;
	}

	public void setOffset(int offset) {
		this.offset = offset;
	}
	
	@Override
	public boolean equals(Object o) {
		if (o instanceof Timezone) return false;
		else return this.getId().equals(((Timezone)o).getId());
	}

	@Override
	public String toString() {
		return "RtmTimezone [id=" + id + ", name=" + name + ", dst=" + dst
				+ ", currentOffset=" + currentOffset + ", offset=" + offset
				+ "]";
	}

}
