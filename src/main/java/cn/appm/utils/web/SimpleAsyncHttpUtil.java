package cn.appm.utils.web;

import java.io.IOException;
import java.nio.charset.Charset;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import org.apache.commons.lang.time.StopWatch;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.config.RequestConfig.Builder;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.nio.client.CloseableHttpAsyncClient;
import org.apache.http.impl.nio.client.HttpAsyncClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 异步 http 请求工具
 * 
 * @author flatychen
 * @date 2014-10-9
 * @version
 */
public class SimpleAsyncHttpUtil {
	
	private static CloseableHttpAsyncClient httpclient  = HttpAsyncClients.createDefault();

	private static StopWatch watch = new StopWatch();
	
	private static Logger log = LoggerFactory.getLogger(SimpleAsyncHttpUtil.class);

	private static final String UTF8 = "utf-8";

	private static final Builder build = RequestConfig.custom();

	private static String asyncRequest(final HttpRequestBase requestBase, Charset charset) {
		try {
			httpclient.start();
			setRequestConfigBuilder();
			requestBase.setConfig(build.build());
			watch.start();
			Future<HttpResponse> httpResponseFuture = httpclient.execute(requestBase, null);
			HttpResponse httpResponse = null;
			try {
				httpResponse = httpResponseFuture.get();
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (ExecutionException e) {
				e.printStackTrace();
			}
			watch.stop();
			log.info(MessageFormat.format("#### {0} --> {1}  [ times:{2}ms ]", requestBase.getRequestLine(),httpResponse.getStatusLine(),watch.getTime()));
			HttpEntity httpEntity = httpResponse.getEntity();
			try {
				return EntityUtils.toString(httpEntity, charset);
			} catch (ParseException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return null;
		} finally {
			try {
				httpclient.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private static HttpPost buildRequestPost(String requestURI, Map<String, String> map,String charset) {
		HttpPost post = new HttpPost(requestURI);
		if (map == null)
			return post;
		List<NameValuePair> formparams = new ArrayList<NameValuePair>();
		Iterator<Map.Entry<String, String>> iterator = map.entrySet().iterator();
		while (iterator.hasNext()) {
			Map.Entry<String, String> entry = (Map.Entry<String, String>) iterator.next();
			formparams.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
		}
		post.setEntity(new UrlEncodedFormEntity(formparams, Charset.forName(charset)));
		return post;
	}

	private static void setRequestConfigBuilder() {
		build.setConnectionRequestTimeout(3000).setConnectTimeout(3000).setSocketTimeout(5000)
				.setExpectContinueEnabled(true);
	}

	/**
	 * GET 请求 ，默认编码UTF8
	 * 
	 * @author flatychen
	 * @date 2014-10-9
	 * @param requestURI uri
	 * @return
	 * @version
	 */
	public static String get(String requestURI) {
		return get(requestURI, UTF8);
	}

	/**
	 * GET 请求 ，自定义编码
	 * 
	 * @author flatychen
	 * @date 2014-10-9
	 * @param requestURI uri
	 * @param charset
	 *            编码
	 * @return
	 * @version
	 */
	public static String get(String requestURI, String charset) {
		HttpGet httpGet = new HttpGet(requestURI);
		return asyncRequest(httpGet, Charset.forName(charset));
	}

	/**
	 * post 请求 ，默认编码UTF8,以Map为参
	 * 
	 * @author flatychen
	 * @date 2014-10-9
	 * @param requestURI uri
	 * @param map 参数
	 * @return
	 * @version
	 */
	public static String postWithMap(String requestURI, Map<String, String> map) {
		return postWithMap(requestURI, map,UTF8);
	}

	/**
	 * post 请求 ，自定义编码，以Map为参
	 * 
	 * @author flatychen
	 * @date 2014-10-9
	 * @param requestURI uri
	 * @param map 参数
	 * @param charset 编码
	 * @return
	 * @version
	 */
	public static String postWithMap(String requestURI, Map<String, String> map, String charset) {
		HttpPost httpPost = buildRequestPost(requestURI, map,charset);
		return asyncRequest(httpPost, Charset.forName(charset));
	}
	
	
	
	/**
	 * post 请求 ，自定义编码， 以String为参
	 * @author flatychen
	 * @date 2014-10-10
	 * @param requestURI uri
	 * @param jsonParam String组合参数
	 * @param charset 编码
	 * @return
	 * @version 
	 */
	public static String postWithString(String requestURI, String jsonParam, String charset) {
		HttpPost httpPost = new HttpPost(requestURI);
		StringEntity entity = new StringEntity(jsonParam, charset);
		httpPost.setEntity(entity);
		return asyncRequest(httpPost, Charset.forName(charset));
	}
	
	
	
	/**
	 * post 请求 ，默认编码UTF8，以String为参
	 * @author flatychen
	 * @date 2014-10-10
	 * @param requestURI uri
	 * @param jsonParam String组合参数
	 * @return
	 * @version 
	 */
	public static String postWithString(String requestURI, String jsonParam) {
		return postWithString(requestURI, jsonParam, UTF8);
	}


}
