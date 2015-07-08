package cn.appm.utils.web;

import java.io.IOException;
import java.nio.charset.Charset;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.time.StopWatch;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.config.RequestConfig.Builder;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
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
public class SimpleHttpUtil {

	private static CloseableHttpClient httpclient = HttpClients.createDefault();

	private static StopWatch watch = new StopWatch();

	private static Logger log = LoggerFactory.getLogger(SimpleHttpUtil.class);

	private static final String UTF8 = "utf-8";

	private static final Builder build = RequestConfig.custom();

	private static String syncRequest(final HttpRequestBase requestBase,
			Charset charset) throws HttpRequestException {
		setRequestConfigBuilder();
		requestBase.setConfig(build.build());
		CloseableHttpResponse response = null;
		try {
			try {
				watch.start();
				response = httpclient.execute(requestBase);
				watch.stop();
				log.info(MessageFormat.format(
						"#### {0} --> {1}  [ times:{2}ms ]",
						requestBase.getRequestLine(), response.getStatusLine(),
						watch.getTime()));
			} catch (ClientProtocolException e) {
				throw new HttpRequestException();
			} catch (IOException e) {
				throw new HttpRequestException();
			}
			HttpEntity httpEntity = response.getEntity();
			String result = "";
			try {
				result = EntityUtils.toString(httpEntity, charset);
			} catch (ParseException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return result;
		} finally {
			try {
				response.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private static HttpPost buildRequestPost(String requestURI,
			Map<String, String> map, String charset) {
		HttpPost post = new HttpPost(requestURI);
		if (map == null)
			return post;
		List<NameValuePair> formparams = new ArrayList<NameValuePair>();
		Iterator<Map.Entry<String, String>> iterator = map.entrySet()
				.iterator();
		while (iterator.hasNext()) {
			Map.Entry<String, String> entry = (Map.Entry<String, String>) iterator
					.next();
			formparams.add(new BasicNameValuePair(entry.getKey(), entry
					.getValue()));
		}
		post.setEntity(new UrlEncodedFormEntity(formparams, Charset
				.forName(charset)));
		return post;
	}

	private static void setRequestConfigBuilder() {
		build.setConnectionRequestTimeout(3000).setConnectTimeout(3000)
				.setSocketTimeout(5000).setExpectContinueEnabled(true);
	}

	/**
	 * GET 请求 ，默认编码UTF8
	 * 
	 * @author flatychen
	 * @date 2014-10-9
	 * @param requestURI
	 *            uri
	 * @return
	 * @version
	 * @throws HttpRequestException
	 * @throws IOException
	 * @throws ParseException
	 */
	public static String get(String requestURI) throws HttpRequestException {
		return get(requestURI, UTF8);
	}

	/**
	 * GET 请求 ，自定义编码
	 * 
	 * @author flatychen
	 * @date 2014-10-9
	 * @param requestURI
	 *            uri
	 * @param charset
	 *            编码
	 * @return
	 * @version
	 * @throws HttpRequestException
	 * @throws IOException
	 * @throws ParseException
	 */
	public static String get(String requestURI, String charset)
			throws HttpRequestException {
		HttpGet httpGet = new HttpGet(requestURI);
		return syncRequest(httpGet, Charset.forName(charset));
	}

	/**
	 * post 请求 ，默认编码UTF8,以Map为参
	 * 
	 * @author flatychen
	 * @date 2014-10-9
	 * @param requestURI
	 *            uri
	 * @param map
	 *            参数
	 * @return
	 * @version
	 * @throws HttpRequestException
	 * @throws IOException
	 * @throws ParseException
	 */
	public static String postWithMap(String requestURI, Map<String, String> map)
			throws HttpRequestException {
		return postWithMap(requestURI, map, UTF8);
	}

	/**
	 * post 请求 ，自定义编码，以Map为参
	 * 
	 * @author flatychen
	 * @date 2014-10-9
	 * @param requestURI
	 *            uri
	 * @param map
	 *            参数
	 * @param charset
	 *            编码
	 * @return
	 * @version
	 * @throws HttpRequestException
	 * @throws IOException
	 * @throws ParseException
	 */
	public static String postWithMap(String requestURI,
			Map<String, String> map, String charset)
			throws HttpRequestException {
		HttpPost httpPost = buildRequestPost(requestURI, map, charset);
		return syncRequest(httpPost, Charset.forName(charset));
	}

	/**
	 * post 请求 ，自定义编码， 以String为参
	 * 
	 * @author flatychen
	 * @date 2014-10-10
	 * @param requestURI
	 *            uri
	 * @param jsonParam
	 *            String组合参数
	 * @param charset
	 *            编码
	 * @return
	 * @version
	 * @throws HttpRequestException
	 * @throws IOException
	 * @throws ParseException
	 */
	public static String postWithString(String requestURI, String jsonParam,
			String charset) throws HttpRequestException {
		HttpPost httpPost = new HttpPost(requestURI);
		StringEntity entity = new StringEntity(jsonParam, charset);
		httpPost.setEntity(entity);
		return syncRequest(httpPost, Charset.forName(charset));
	}

	/**
	 * post 请求 ，默认编码UTF8，以String为参
	 * 
	 * @author flatychen
	 * @date 2014-10-10
	 * @param requestURI
	 *            uri
	 * @param jsonParam
	 *            String组合参数
	 * @return
	 * @version
	 * @throws HttpRequestException
	 * @throws IOException
	 * @throws ParseException
	 */
	public static String postWithString(String requestURI, String jsonParam)
			throws HttpRequestException {
		return postWithString(requestURI, jsonParam, UTF8);
	}

	public static class HttpRequestException extends Exception {

		private static final long serialVersionUID = 1L;
	}

}
