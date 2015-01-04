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
 * A class for the definition of a repetition rule for a Task. The fields are
 * <ul>
 * <li> <b>every</b> - A boolean value that is true if the repetition rule is "every" (independent form completion)
 * , false if the rule is "after" (after completion)
 * <li> <b>frequency</b> - The Frequency enum of repetition (e.g. yearly, monthly, etc.)
 * <li> <b>interval</b> - The interval of repetition associated to frequency (e.g. <b>3</b> months, <b>6</b> weeks, etc.)
 * <li> <b>optionName</b> - An optional repetition option (currently known options: BYDAY, BYMONTHDAY, COUNT.
 * See {@link https://www.rememberthemilk.com/help/answers/basics/repeatformat.rtm}
 * <li> <b>optionValue</b> - The value defining better the above mentioned option (e.g. the last Saturday, 20 times, etc.)
 * See {@link https://www.rememberthemilk.com/help/answers/basics/repeatformat.rtm}
 * @author Giovanni Pini
 *
 */
public class Recurrence implements Serializable{
	
	private boolean every;
	private Frequency frequency;
	private int interval = 0;
	private RecurrenceOption option;
	private String optionValue = "";
	
	public enum RecurrenceOption {
		BYDAY("BYDAY"),BYMONTHDAY("BYMONTHDAY"),COUNT("COUNT"),UNTIL("UNTIL");
		private String option;
		RecurrenceOption(String s) {option = s;}
		String getOption() {return option;}
	}
	
	public Frequency getFrequency() {return this.frequency;}
	public int getInterval() {return this.interval;}
	public boolean isEvery() {return this.every;}
	public boolean hasOption() { return this.option != null; }
	public RecurrenceOption getOption() {return this.option;}
	public String getOptionValue() {return this.optionValue;}

	public Recurrence(boolean every, int interval, Frequency freq,
			RecurrenceOption option, String optionValue) {
		this.every = every;
		this.interval = interval;
		this.frequency = freq;
		this.option = option;
		this.optionValue = optionValue;
	}
	
	public Recurrence(boolean every, String string) {
		this.every = every;
		String[] tokens = string.split(";");
		for (int i = 0; i < tokens.length; i++) {
			String[] valuePair = tokens[i].split("=");
			if (valuePair.length == 2) {
				if (valuePair[0].equals("FREQ")) this.frequency = Frequency.parseFrequency(valuePair[1]);
				else if (valuePair[0].equals("INTERVAL")) this.interval = Integer.valueOf(valuePair[1]);
				else if (valuePair[0].equalsIgnoreCase("BYDAY")) {
					option = RecurrenceOption.BYDAY;
					this.optionValue = valuePair[1];
				}
				else if (valuePair[0].equalsIgnoreCase("COUNT")) {
					option = RecurrenceOption.COUNT;
					this.optionValue = valuePair[1];
				}
				else if (valuePair[0].equalsIgnoreCase("UNTIL")) {
					option = RecurrenceOption.UNTIL;
					this.optionValue = valuePair[1];
				}
				else if (valuePair[0].equalsIgnoreCase("BYMONTHDAY")) {
					option = RecurrenceOption.BYMONTHDAY;
					this.optionValue = valuePair[1];
				}
				else {
					option = null;
					this.optionValue = "";
				}
			}	
		}
	}

	@Override
	public String toString() {
		return "Recurrence [every=" + every + ", frequency=" + frequency
				+ ", interval=" + interval + ", optionName=" + option
				+ ", optionValue=" + optionValue + "]";
	}
}

