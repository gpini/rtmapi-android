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

class RequestFactory {
	
	String apiKey = "";
	String sharedSecret = "";
	String token = "";
	
	RequestFactory(String apiKey, String sharedSecret, String token) {
		this.apiKey = apiKey;
		this.sharedSecret = sharedSecret;
		this.token = token;		
	}
	
	RequestFactory(String apiKey, String sharedSecret) {
		this.apiKey = apiKey;
		this.sharedSecret = sharedSecret;
	}
	
	Request createRequest(Method method) {
		return new Request(method, this.apiKey);
	}
	
	SignedRequest createSignedRequest(Method method) {
		return new SignedRequest(method, this.apiKey, this.sharedSecret);
	}
	
	AuthenticatedRequest createAuthenticatedRequest(Method method) {
		return new AuthenticatedRequest(method, this.apiKey, this.sharedSecret, this.token);
	}

	public String getApiKey() {
		return apiKey;
	}

	public void setApiKey(String apiKey) {
		this.apiKey = apiKey;
	}

	public String getSharedSecret() {
		return sharedSecret;
	}

	public void setSharedSecret(String sharedSecret) {
		this.sharedSecret = sharedSecret;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
	
}
