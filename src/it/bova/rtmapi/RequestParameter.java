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

enum RequestParameter {
	
	API_KEY("api_key"),
	AUTH_TOKEN("auth_token"),
	CONTACT("contact"),
	CONTACT_ID("contact_id"),
	DATE_FORMAT("dateformat"),
	DIRECTION("direction"),
	DUE("due"),
	ESTIMATE("estimate"),
	FILTER("filter"),
	HAS_DUE_TIME("has_due_time"),
	NAME("name"),
	FORMAT("format"),
	FROB("frob"),
	FROM_LIST_ID("from_list_id"),
	FROM_TIMEZONE("from_timezone"),
	GROUP("group"),
	GROUP_ID("group_id"),
	LAST_SYNC("last_sync"),
	LIST_ID("list_id"),
	LOCATION_ID("location_id"),
	METHOD("method"),
	METHOD_NAME("method_name"),
	NOTE_ID("note_id"),
	PARSE("parse"),
	PERMISSION("perms"),
	PRIORITY("priority"),
	RECURRENCE("repeat"),
	TAGS("tags"),
	TASK_ID("task_id"),
	TASKSERIES_ID("taskseries_id"),
	TEXT("note_text"),
	TIME("time"),
	TIMELINE("timeline"),
	TIMEZONE("timezone"),
	TITLE("note_title"),
	TO_LIST_ID("to_list_id"),
	TO_TIMEZONE("to_timezone"),
	TRANSACTION_ID("transaction_id"),
	URL("url");
	
	private String parameter;
	private RequestParameter (String parameter) {this.parameter = parameter; }
	public String toString() {return this.parameter; }
	
}
