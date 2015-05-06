package cn.appm.utils.web.file.imageDecorate;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import org.apache.commons.io.IOUtils;

import cn.appm.utils.web.file.FileValidChecker;

/**
 * FIXME 内存流？大图溢出？
 * 图片流包装，提供可重复读流;
 * @author flatychen
 * @date 2014-5-27
 */
public class ImageStreamWraper {



	private ByteArrayOutputStream bos = new ByteArrayOutputStream(4096);
	
	private byte[] bytes;
	

	public ImageStreamWraper(InputStream inputstream) {
		super();
		this.init(inputstream);
	}
	
	private void init(InputStream inputstream){
		try {
			IOUtils.copy(inputstream, bos);
			bytes = bos.toByteArray();
			if(bytes.length > FileValidChecker.MB * 5){
				throw new IllegalArgumentException("---->图片太大，禁止上传");  
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public byte[] getBytes(){
		return bytes;
	}
	
	/**
	 * 得到图片
	 * @author flatychen
	 * @date 2014-5-27
	 * @return
	 */
	public BufferedImage getImage(){
		InputStream in = new ByteArrayInputStream(bytes);
		try {
			return ImageIO.read(in);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	/**
	 * 得到图片对应输入流
	 * @author flatychen
	 * @date 2014-5-27
	 * @return
	 */
	public InputStream getInput(){
		ByteArrayInputStream inputStream = new ByteArrayInputStream(bytes);
		return inputStream;
	}
}
