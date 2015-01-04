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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

/**
 * An utility class for parsing and formattin in ISO8601, the format used by Remember the Milk Server
 * @author Giovanni Pini
 *
 */
public class DateParser {
	
	private static SimpleDateFormat df = new SimpleDateFormat( "yyyy-MM-dd'T'HH:mm:ss'Z'" );

	static {
		df.setTimeZone(TimeZone.getTimeZone("UTC"));
	}
	
	/**
	 * Parses a string in ISO8601 format into a Date object
	 * @param input the string date in ISO8601 format
	 * @return a Date object parsed corresponding to the input string
	 * @throws ParsingException if the string cannot be parsed
	 */
    public static Date parseDate(String date) throws ParsingException {
    	try {
    		if (date.equals("")) return null;
    		return df.parse(date);      
    	} catch (java.text.ParseException e) {
    		//try input with forgotten 'Z'
    		try {
				return df.parse(date + "Z");
			} catch (ParseException e1) {
				throw new ParsingException(e1.getMessage());
			}
    	}
    }

    /**
     * Convert a Date object in a string in ISO8601 format
     * @param date the Date object to convert
     * @return the string in ISO8601 format corresponding to the input date
     */
    public static String toISO8601(Date date) {   
        String output = df.format( date );
        String result = output;
        if(result.contains("GMT")) {
        	return result.substring(0, output.length() - 9); //remove GMT indication if present
        }
        else return result;
    }

}
