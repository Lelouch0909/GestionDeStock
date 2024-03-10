package com.lontsi.gestiondestock.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.flickr4java.flickr.Flickr;
import com.flickr4java.flickr.REST;
import com.flickr4java.flickr.RequestContext;
import com.flickr4java.flickr.auth.Auth;
import com.flickr4java.flickr.auth.Permission;

@Configuration
public class FlickConfiguration {

	@Value("${flickr.apikeys}")
	public String apikeys;

	@Value("${flickr.apisecret}")
	public String apiSecret;

	@Value("${flickr.appkeys}")
	private String appkey;

	@Value("${flickr.appsecret}")
	private String appsecret;

	/*
	 * @Bean public Flickr getFlickr() throws IOException, InterruptedException,
	 * ExecutionException {
	 * 
	 * Flickr flickr = new Flickr(apikeys, apiSecret, new REST());
	 * 
	 * OAuth10aService service = new ServiceBuilder(apikeys).apiSecret(apiSecret)
	 * .build(FlickrApi.instance(FlickrPerm.DELETE));
	 * 
	 * final Scanner scanner = new Scanner(System.in);
	 * 
	 * final OAuth1RequestToken request = service.getRequestToken();
	 * 
	 * final String authurlString = service.getAuthorizationUrl(request);
	 * 
	 * System.out.println(authurlString); System.out.println("paste it here");
	 * 
	 * final String authverifier = scanner.nextLine();
	 * 
	 * OAuth1AccessToken accessToken = service.getAccessToken(request,
	 * authverifier);
	 * System.out.println("--------------------------------------------------------"
	 * );
	 * 
	 * System.out.println(accessToken.getToken());
	 * System.out.println(accessToken.getTokenSecret());
	 * 
	 * return flickr; }
	 */

	@Bean
	public Flickr getFlickr() {

		Flickr flickr = new Flickr(apikeys, apiSecret, new REST());

		Auth auth = new Auth();
		auth.setPermission(Permission.DELETE);

		auth.setToken(apikeys);
		auth.setTokenSecret(appsecret);

		RequestContext requestContext = RequestContext.getRequestContext();
		requestContext.setAuth(auth);

		return flickr;

	}
}