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
 * An object containing the user settings stored in the Remember the Milk server. The fields are:
 * <ul>
 * <li> <b>timezone</b> - The user's Olson timezone. Blank if the user has not set a timezone. 
 * <li> <b>dateformat</b> - 0 indicates an European date format (e.g. 14/02/06), 1 indicates an American date format (e.g. 02/14/06).
 * <li> <b>timeformat</b> - 0 indicates 12 hour time with day period (e.g. 5pm), 1 indicates 24 hour time (e.g. 17:00).
 * <li> <b>defaultlist</b> - The user's default TaskList. Blank if the user has not set a default list.
 * <li> <b>language</b> - The user's language (ISO 639-1 code).
 * <ul>
 * @author Giovanni Pini
 *
 */
public class Settings implements Serializable{
	
	private String timezone;
	private String language;
	private int dateFormat;
	private int timeFormat;
	private String defaultListId;
	
	public Settings(String timezone, String language, int dateFormat, int timeFormat,
			String defaultListId) {
		this.timezone = timezone;
		this.language = language;
		this.dateFormat = dateFormat;
		this.timeFormat = timeFormat;
		this.defaultListId = defaultListId;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getTimezone() {
		return timezone;
	}

	public void setTimezone(String timezone) {
		this.timezone = timezone;
	}

	public int getDateFormat() {
		return dateFormat;
	}

	public void setDateFormat(int dateFormat) {
		this.dateFormat = dateFormat;
	}

	public int getTimeFormat() {
		return timeFormat;
	}

	public void setTimeFormat(int timeFormat) {
		this.timeFormat = timeFormat;
	}

	public String getDefaultListId() {
		return defaultListId;
	}

	public void setDefaultListId(String defaultListId) {
		this.defaultListId = defaultListId;
	}	

	@Override
	public String toString() {
		return "RtmSettings [timezone=" + timezone + ", language=" + language
				+ ", dateFormat=" + dateFormat + ", timeFormat=" + timeFormat
				+ ", defaultListId=" + defaultListId + "]";
	}
}
