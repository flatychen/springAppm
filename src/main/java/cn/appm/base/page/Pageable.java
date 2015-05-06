package cn.appm.base.page;

/**
 * 分页基础参数
 * @author flatychen
 * @date 2014-6-17
 * @version 
 */
public class Pageable {

	public static final int DEFAULT_PAGE_SIZE = 15;


	public static final int DEFAULT_PAGE_NO = 1;
	
	
	public static int  QUERY_ALL = -1;

	/**
	 * 页面大小
	 */
	private Integer pageSize = DEFAULT_PAGE_SIZE;
	
	private boolean isQueryAll = false;

	/**
	 * 请求页
	 */
	private Integer pageNo = DEFAULT_PAGE_NO;

	public Pageable(Integer pageNo, Integer pageSize) {
		super();
		this.setPageNo(pageNo);
		this.setPageSize(pageSize);
	}

	public Pageable() {
		super();
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public Pageable setPageSize(Integer pageSize) {
		if(pageSize == -1) this.isQueryAll = true;
		this.pageSize = initPageNumberValid(pageSize);
		return this;
	}

	public Integer getPageNo() {
		return pageNo;
	}

	public Pageable setPageNo(Integer pageNo) {
		this.pageNo = initPageNumberValid(pageNo);
		return this;
	}

	private int initPageNumberValid(Integer number) {
		return (number == null || number <= 0) ? 1 : number;
	}

	/**
	 * 分页偏移位置 (pageSize - 1) * pageNo;
	 * @author flatychen
	 * @date 2014-6-17
	 * @return
	 * @version 
	 */
	public int getOffset() {
		return (pageNo - 1) * pageSize;
	}

	/** 
	 * 分页限制 pageSize * pageNo;
	 * @author flatychen
	 * @date 2014-6-17
	 * @return
	 * @version 
	 */
	public int getLimit() {
		return pageSize * pageNo;
	}
	
	/**
	 * 是否查询全部
	 * @author flatychen
	 * @date 2014-6-26
	 * @return
	 * @version 
	 */
	public boolean getIsQueryAll(){
		return this.isQueryAll;
	}
}
