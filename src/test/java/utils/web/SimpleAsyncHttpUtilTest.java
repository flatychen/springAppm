package utils.web;

import static org.junit.Assert.*;

import org.junit.Test;

import cn.appm.utils.web.SimpleAsyncHttpUtil;

public class SimpleAsyncHttpUtilTest {

	@Test
	public final void testGet() {
		String result = SimpleAsyncHttpUtil.get("http://www.jianshu.com/baiwjgg");
		System.out.println(result);
	}


}
