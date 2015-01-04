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

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


class Md5Helper {
	

	final static String md5(String text) throws RtmApiException {
		MessageDigest md;
		try {
			md = MessageDigest.getInstance("MD5");
			byte[] md5hash = new byte[32];
			byte[] input = text.getBytes("UTF-8");
			md5hash = md.digest(input);	
			// Create Hex String
	        StringBuffer md5HexHash = new StringBuffer();
	        for (int i = 0; i < md5hash.length; i++) {
	            String h = Integer.toHexString(0xFF & md5hash[i]);
	            while (h.length() < 2)
	                h = "0" + h;
	            md5HexHash.append(h);
	        }
			return md5HexHash.toString();
		} catch (NoSuchAlgorithmException e) {
			//return ""; //will invalid signature
			throw new RtmApiException("Md5 error: NoSuchAlgorithmException - " + e.getMessage());
		} catch (UnsupportedEncodingException e) {
			//return ""; //will invalid signature
			throw new RtmApiException("Md5 error: UnsupportedEncodingException - " + e.getMessage());
		}

	}

}
