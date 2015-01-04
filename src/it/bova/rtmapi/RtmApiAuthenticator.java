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

import java.io.IOException;

/**
 * The API with the methods for getting and checking the server token.
 * @author Giovanni Pini
 *
 */
public class RtmApiAuthenticator {
		
	private final String apiKey;
	private final String sharedSecret;
	private Token token;
	private RequestFactory requestFactory;
	
	/**
	 * Authenticator constructor
	 * @param key The API application key
	 * @param secret The shared secret of the application
	 */
	public RtmApiAuthenticator(String key, String secret) {
		this.apiKey = key;
		this.sharedSecret = secret;
		this.requestFactory = new RequestFactory(this.apiKey, this.sharedSecret);
	}
	
	/**
	 * Gets the URL for the authentication process of a Web Application (using callback URL)
	 * @param permission The permission required by the application {@link Permission}
	 * @return the authentication URL
	 * @throws RtmApiException API fatal error
	 */
	public String authGetWebUrl(Permission permission) throws RtmApiException {
		SignedRequest request = this.requestFactory.createSignedRequest(Method.NO_METHOD);
		request.put(RequestParameter.PERMISSION, permission.toString());
		return request.getUrl();
	}
	
	/**
	 * Gets the URL for the authentication process of a Desktop Application (not using callback URL)
	 * @param permission The permission required by the application {@link Permission}
	 * @return the authentication URL
	 * @throws RtmApiException API fatal error
	 */
	public String authGetDesktopUrl(Permission permission, String frob) throws RtmApiException {
		SignedRequest request = this.requestFactory.createSignedRequest(Method.NO_METHOD);
		request.put(RequestParameter.PERMISSION, permission.toString());
		request.put(RequestParameter.FROB, frob);
		return request.getUrl();
	}
	
	/**
	 * Returns a frob string to be used during authentication.
	 * @return the frob string
	 * @throws ServerException if server answer with an error message
	 * @throws RtmApiException API fatal error
	 * @throws IOException if server is unreachable for many reasons
	 */
	public String authGetFrob() throws ServerException, RtmApiException, IOException {
		SignedRequest request = this.requestFactory.createSignedRequest(Method.AUTH_GET_FROB);
		return new RestClient(request).execute().getString(ResponseParameter.FROB);
	}
	
	/**
	 * Returns the auth token for the given frob, if one has been attached.
	 * @param frob The frob to check
	 * @return the RtmToken object containing the token string and other useful user information
	 * @throws ServerException if server answer with an error message
	 * @throws RtmApiException API fatal error
	 * @throws IOException if server is unreachable for many reasons
	 */
	public Token authGetToken(String frob) throws ServerException, RtmApiException, IOException {
		SignedRequest request = this.requestFactory.createSignedRequest(Method.AUTH_GET_TOKEN);	
		request.put(RequestParameter.FROB,frob);	
		this.token = new RestClient(request).execute().getToken();
		return this.token;
	}
	
	/**
	 * Retrievs the credentials attached to an authentication token.
	 * @param authToken The string token to check
	 * @return the RtmToken object, if token is correct and valid 
	 * @throws ServerException if server answer with an error message
	 * @throws RtmApiException API fatal error
	 * @throws IOException if server is unreachable for many reasons
	 */
	public Token authCheckToken(String authToken) throws ServerException, RtmApiException, IOException {
		SignedRequest request = this.requestFactory.createSignedRequest(Method.AUTH_CHECK_TOKEN);	
		request.put(RequestParameter.AUTH_TOKEN,authToken);		
		return new RestClient(request).execute().getToken();
	}
	
	/**
	 * Retrievs the credentials attached to an authentication token.
	 * @param authToken The Token to check
	 * @return the RtmToken object, if token is correct and valid 
	 * @throws ServerException if server answer with an error message
	 * @throws RtmApiException API fatal error
	 * @throws IOException if server is unreachable for many reasons
	 */
	public Token authCheckToken(Token authToken) throws ServerException, RtmApiException, IOException {
		return this.authCheckToken(authToken.getToken());
	}
	
	/**
	 * Gets the auth token, if one is available
	 * @return the auth token if one was get by authGetToken or authCheckToken, otherwise null.
	 */
	public Token getToken() {
		return token;
	}

	/**
	 * Gets the defined API key
	 * @return the application key
	 */
	public String getApiKey() {
		return apiKey;
	}

	/**
	 * Gets the shared secret
	 * @return the application shared secret
	 */
	public String getSharedSecret() {
		return sharedSecret;
	}

}
