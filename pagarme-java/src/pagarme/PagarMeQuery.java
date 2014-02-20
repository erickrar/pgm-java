package pagarme;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.ws.rs.core.UriBuilder;

import pagarme.exception.PagarMeException;
import pagarme.util.MapUtil;

import com.google.common.collect.ImmutableMap;



public class PagarMeQuery {

	private static final String API_ENDPOINT =  "https://api.pagar.me/1/";
	private static final String USER_AGENT = "pagarme-net 12.0.0";

	private String method;
	private String path;
	private List<Map<String,String>> queries;
	private int take;
	private HttpURLConnection request;
	private PagarMeQueryResponse pagarMeResponse;
	private int responseCode;
	private InputStream is;
	private BufferedReader reader;


	public PagarMeQuery(PagarMeProvider provider, String method, String path){
		this.path=path;
		this.method=method;
		queries = new ArrayList<Map<String,String>>();
		queries.add(ImmutableMap.of("api_key",provider.getApiKey()));
	}


	public void addQuery(String key,String value){
		queries.add(ImmutableMap.of(key,value));
	}

	public void addQueries(Map<String,Object> objectProperties){
		for(String key : objectProperties.keySet())
			addQuery(key, String.valueOf(objectProperties.get(key)));
	}

	public PagarMeQueryResponse execute(){
		try {
			UriBuilder builder = UriBuilder.fromPath(API_ENDPOINT);
			builder.path(path);


			StringBuilder query = new StringBuilder();
			query.append(MapUtil.listOfMapToString(queries));

			if(!method.toUpperCase().equals("POST") && !method.toUpperCase().equals("PUT")){
				builder.replaceQuery(MapUtil.listOfMapToString(queries));
			}

			if(take > 0)
				addQuery("count", ""+take);

			request = (HttpURLConnection) builder.build(this).toURL().openConnection();
			request.setRequestMethod(method);
			request.addRequestProperty("User-Agent",USER_AGENT);

			if(method.toUpperCase().equals("POST") || method.toUpperCase().equals("PUT")){
				byte[] payload = MapUtil.listOfMapToString(queries).getBytes();
				request.addRequestProperty("Content-Length",""+payload.length);
				request.addRequestProperty("Content-Type","application/x-www-form-urlencoded;charset=UTF-8");
				request.setDoInput(true);
				request.setDoOutput(true);
				DataOutputStream output =  new DataOutputStream(request.getOutputStream());
				output.write(payload, 0, payload.length);
				output.flush();
				output.close();
			}

			try {	
				is = request.getInputStream();
				responseCode = request.getResponseCode();	
			} catch (IOException e) {
				is = request.getErrorStream();
				responseCode = request.getResponseCode();
			}

			reader = new BufferedReader(new InputStreamReader(is,"UTF-8"));
			String line;
			StringBuffer rp = new StringBuffer(); 
			while((line = reader.readLine()) != null) {
				rp.append(line);
				rp.append('\r');
			}

			reader.close();
			pagarMeResponse = new PagarMeQueryResponse();
			pagarMeResponse.setStatus(responseCode);
			pagarMeResponse.setData(rp.toString());
			return pagarMeResponse;

		} catch (PagarMeException e) {
			pagarMeResponse = new PagarMeQueryResponse();
			pagarMeResponse.setData(e.getMessage());
			return pagarMeResponse;
		}catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

}
