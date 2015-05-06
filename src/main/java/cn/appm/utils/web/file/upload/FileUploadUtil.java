package cn.appm.utils.web.file.upload;

import java.io.File;
import java.util.Date;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateFormatUtils;

public class FileUploadUtil {

	
	
	public static final String ABSOLUTE_PREFIX = "absolute:";

	public static final String REALITIVE_PREFIX = "relative:";
	
	public static final String WEBAPPS_PREFIX = "webapps:";
	
	/**
	 * 得到路么的上父径；
	 * <br/>
	 * 如:"/a/b/c  ->  /a/b/ "
	 * 
	 * @param path
	 * @return
	 */
	public static  final String getParentPath(String path){
		return new File(path).getParent();
	}
	
	
	/**
	 * 生成随机文件名 , 包含当前日期目录 
	 * <br/>
	 * 如：/20140509/[md5]
	 * 
	 * @author flatychen
	 * @date 2014-4-30
	 * @param ur
	 */
	public static final String genRandomFileName(String Suffix) {
		if(StringUtils.isBlank(Suffix.trim())){
			Suffix = "";
		}
		Date nowDay = new Date();
		String fileName = DigestUtils.md5Hex(nowDay.getTime()+"_")+Suffix;
		String fileNameWithDir = FilenameUtils.normalize(DateFormatUtils.format(nowDay.getTime(), "yyyyMMdd")
				+ File.separator + fileName) ;
		return fileNameWithDir;
	}
}
