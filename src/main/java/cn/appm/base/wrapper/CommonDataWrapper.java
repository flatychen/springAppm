package cn.appm.base.wrapper;

/**
 * 常用数据包装器
 * 
 * @author flatychen
 * 
 */
public class CommonDataWrapper<T> extends BaseDataWrapper {

	private T data;

	public T getData() {
		return data;
	}

	public CommonDataWrapper<T> setData(T data) {
		this.data = data;
		return this;
	}

}
