package cn.appm.utils.web.file.imageDecorate;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.imageio.ImageIO;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang.StringUtils;

import cn.appm.utils.web.file.AbstractFileUpload;
import cn.appm.utils.web.file.DownloadResult;
import cn.appm.utils.web.file.upload.FileUploadUtil;

/**
 * 图片上传处理 适配<br>
 * 装饰一般的上传处理，使上传流可重复读;
 * @author flatychen
 * @date 2014-5-26
 */
public class FileToImageUploadAdapter  extends AbstractFileUpload   implements ImageFileUpload {


	/**
	 * 图片后续处理
	 */
	protected List<ImageDecorate> decoreates;
	
	
	public void setDecoreates(List<ImageDecorate> decoreates) {
		this.decoreates = decoreates;
	}

	@Override
	public final void writeFile(InputStream in, String fileAbsolutePath) throws IOException {
		ImageStreamWraper imageStreamWraper = new ImageStreamWraper(in);
		fileRepo.writeFile(imageStreamWraper.getInput(), fileAbsolutePath);
		if (decoreates != null) {
			for (ImageDecorate decorate : decoreates) {
					decorate.decorate(fileRepo,imageStreamWraper, fileAbsolutePath);
			}
		}
	}


	@Override
	public DownloadResult clipExistImage(String image,ImageClipInfo clipInfo) {
		if(StringUtils.isEmpty(image)){
			throw new NullPointerException("---->图片文件名不能为空.");
		}
		
		DownloadResult result = new DownloadResult();
		String extName =  FilenameUtils.getExtension(image);
		String fileName = FilenameUtils.removeExtension(image);
		InputStream fin = null;
		//
		// 读取已上传图片
		//
		try {
			fin = fileRepo.readUploadedFile(image);
		} catch (IOException e) {
			result.setSuccess(false);
			result.setMessage("---->读取文件出错:"+e.getMessage());
			e.printStackTrace();
			return result;
		}
		//
		// 开始裁剪图片
		//
		BufferedImage subImage = null;
		ByteArrayOutputStream bos = new ByteArrayOutputStream(4096);
		ByteArrayInputStream bin = null;
		try {
			subImage = ImageIO.read(fin).getSubimage(clipInfo.getX(), clipInfo.getY(), clipInfo.getW(), clipInfo.getH());
			fin.close();
			bos = new ByteArrayOutputStream(4096);
			bin = null;
			ImageIO.write(subImage, FilenameUtils.getExtension(image),bos);
		} catch (IOException e) {
			result.setSuccess(false);
			result.setMessage("---->裁剪图片出错:"+e.getMessage());
			e.printStackTrace();
			return result;
		}
		
		//
		// 写入裁剪图片
		//
		String clipedfile =  fileName +"_clip."+extName;
		try {
			byte buffer[] = bos.toByteArray();
			bin = new ByteArrayInputStream(buffer);
			fileRepo.writeFile(bin, FilenameUtils.normalize(clipedfile));
			bos.close();
			bin.close();
		} catch (IOException e) {
			result.setSuccess(false);
			result.setMessage("---->裁剪图片写入出错:"+e.getMessage());
			e.printStackTrace();
			return result;
		}
		result.setSuccess(true);
		result.setFile(fileName +"_clip."+extName);
		return result;
	}

}
