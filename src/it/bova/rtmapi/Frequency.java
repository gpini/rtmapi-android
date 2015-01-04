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
 * An enum for defining recurrence frequency in tasks (See {@link Recurrence} and {@link Task}). Values are self-explaining)
 * @author Giovanni Pini
 *
 */
public enum Frequency {
	DAILY, WEEKLY, MONTHLY, YEARLY;
	public static Frequency parseFrequency(String string) {
		if(string.equals("DAILY")) return DAILY;
		else if(string.equals("WEEKLY")) return WEEKLY;
		else if(string.equals("MONTHLY")) return MONTHLY;
		else if(string.equals("YEARLY")) return YEARLY;
		else return null;
	}
}
