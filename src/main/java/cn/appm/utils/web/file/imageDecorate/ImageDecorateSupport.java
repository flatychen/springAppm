package cn.appm.utils.web.file.imageDecorate;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import cn.appm.utils.web.file.upload.FileRepo;

/**
 * 图片装饰基础类
 * @author flatychen
 * @date 2014-5-30
 * @version 
 */
public class ImageDecorateSupport implements ImageDecorate {
	
	public static String IMG_H = "h";
	public static String IMG_W = "w";

	@Override
	public void decorate(FileRepo fileUpload, ImageStreamWraper imageStreamWraper, String fileName)
			throws IOException {
		throw new IOException("---->需实现decorate方法：") ;
	}

	@Override
	public String getSuffix() {
		throw new NullPointerException("---->需实现getSuffix方法：") ;
	}
	
	
	protected Map<String,Integer> checkIfLarger(BufferedImage image,int e_w ,int e_h){
		Map<String,Integer> map = new HashMap<String, Integer>(2);
		map.put(IMG_H, e_h);
		map.put(IMG_W, e_w);
		int w = image.getWidth();
		int h = image.getHeight();
		if(w <= e_h ){
			map.put(IMG_H, h);
		}
		if(h <= e_w){
			map.put(IMG_W, w);
		}
		return map;
	}

}
