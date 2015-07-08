package utils.web;

import static org.junit.Assert.*;

import java.io.IOException;

import org.apache.http.ParseException;
import org.junit.Test;

import cn.appm.utils.web.SimpleAsyncHttpUtil;
import cn.appm.utils.web.SimpleHttpUtil;
import cn.appm.utils.web.SimpleHttpUtil.HttpRequestException;

public class SimpleHttpUtilTest {

	@Test
	public final void testGet()  {
		String result;
		try {
			result = SimpleHttpUtil.get("http://www.jianshu.com/baiwjgg");
			System.out.println(result);
		} catch (HttpRequestException e) {
			e.printStackTrace();
		}
	}


}
