package cn.appm.utils.web.file.imageDecorate;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Positions;

import org.apache.commons.io.FilenameUtils;

import cn.appm.utils.web.file.upload.FileRepo;

/**
 * FIXME 内存流？大图溢出？ 
 * 水印处理
 * @author flatychen
 * @date 2014-5-26
 */
public class WaterMarkImageDecorate implements ImageDecorate {

	private String classPathWarkMarkImagePath;

	public void setWarkMarkImagePath(String classPathWarkMarkImagePath) {
		this.classPathWarkMarkImagePath = classPathWarkMarkImagePath;
	}

	private BufferedImage warkMarkImage;

	public WaterMarkImageDecorate() {
		super();
		this.init();
	}

	private void init() {
		try {
			warkMarkImage  = ImageIO.read(this.getClass().getClassLoader().getResourceAsStream(classPathWarkMarkImagePath)) ;
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void decorate(FileRepo fileUpload,ImageStreamWraper imageStreamWraper , String fileName) throws IOException {
		ByteArrayOutputStream out = new ByteArrayOutputStream(4096);
		ByteArrayInputStream in = null;
		String extName = FilenameUtils.getExtension(fileName);
		try {
		Thumbnails.of(imageStreamWraper.getImage()).outputFormat(extName)
				.watermark(Positions.BOTTOM_RIGHT, warkMarkImage, 1.0f).toOutputStream(out);
		} catch (IOException e) {
			throw new IOException("---->图片加水印失败："+e.getMessage());
		}
		byte buffer[] = out.toByteArray();
		in = new ByteArrayInputStream(buffer);
		//uploadResult.setFileName(fileUpload.genRandomFileName("_water"));
		fileUpload.writeFile(in, FilenameUtils.getBaseName(fileName)+getSuffix()+"."+extName);
		
	}

	@Override
	public String getSuffix() {
		return "_water";
	}

}
