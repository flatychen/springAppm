package cn.appm.base.page;

import java.util.List;

/**
 * 基础分页数据包装器。不可修改。继承之
 * @author flatychen
 * @date 2014-6-17
 * @param <T>
 * @version 
 */
public class PageBean<T> {
	
	public final int DEFAULT_PAGE_SIZE = 15;
	
	private int pageSize = DEFAULT_PAGE_SIZE ;
	
	private int toPage;
	
	private int totalPage;
	
	private int totalRows;
	
	private List<T> objList;
	
	
	/**
	 * 得到分页封装对象
	 * @author flatychen
	 * @date 2014-6-19
	 * @param totalCount 分页总数量
	 * @param pageObject 分页参数对象 继承之Pageable
	 * @param objList 分页数据LIST
	 * @return
	 * @version 
	 */
	public static <T> PageBean<T> genPageBean(int totalCount,Pageable pageObject,List<T> objList){
		PageBean<T> page = new PageBean<T>(pageObject.getPageNo(), pageObject.getPageSize(), totalCount, objList);
		return page;
	}

	
	
	public PageBean(int toPage, int pageSize, int totalRows, List<T> objList) {
		super();
		this.objList = objList;
		initPageParams(toPage,pageSize,totalRows);
	}

	/**
	 * 初始化分页参数
	 * @author flatychen
	 * @date 2014-6-17
	 * @param toPage
	 * @param pageSize
	 * @param totalRows
	 * @version 
	 */
	private void initPageParams(int toPage, int pageSize, int totalRows) {
		//页大小
		if(pageSize > 0){
			this.pageSize = pageSize;
		}
		
		//分页数
		if(totalRows > 0){
			this.totalRows = totalRows;
			int total = totalRows / pageSize;
			if (totalRows % pageSize != 0) {
				total++;
			}
			totalPage= total;
		}else{
			totalPage = 1;
		}
		
		//请求页合法化，不合法置为1
		if (toPage <= 0 ) {
			this.toPage = 1;
		} else if(toPage >= totalPage){
			toPage= totalPage;
		} else{
			this.toPage = toPage;
		} 
		
		
	}

	public final int getToPage() {
		return toPage;
	}

	public final int getTotalPage() {
		return totalPage;
	}

	public final int getTotalRows() {
		return totalRows;
	}

	public final List<T> getObjList() {
		return objList;
	}


}
