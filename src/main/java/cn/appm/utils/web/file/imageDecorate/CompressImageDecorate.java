package cn.appm.utils.web.file.imageDecorate;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Map;

import net.coobird.thumbnailator.Thumbnails;

import org.apache.commons.io.FilenameUtils;

import cn.appm.utils.web.file.upload.FileRepo;

/**
 * FIXME 内存流？大图溢出？ <br>
 *
 * 压缩图片处理
 * 
 * @author flatychen
 * @date 2014-5-26
 */
public class CompressImageDecorate  extends ImageDecorateSupport implements ImageDecorate {

	private static int DEFAULT_COMPRESSWIDTH = 1200;
	private static int DEFAULT_COMPRESSHEIGHT = 800;

	private int compressWidth = DEFAULT_COMPRESSWIDTH;

	private int compressHeight = DEFAULT_COMPRESSHEIGHT;

	public void setCompressWidth(int compressWidth) {
		this.compressWidth = compressWidth;
	}

	public void setCompressHeight(int compressHeight) {
		this.compressHeight = compressHeight;
	}

	public CompressImageDecorate(int compressWidth, int compressHeight) {
		super();
		this.compressWidth = compressWidth;
		this.compressHeight = compressHeight;
	}

	public CompressImageDecorate() {
		super();
	}

	@Override
	public void decorate(FileRepo fileUpload,ImageStreamWraper imageStreamWraper , String fileAbsolutePath) throws IOException{
		ByteArrayOutputStream out = new ByteArrayOutputStream(4096);
		ByteArrayInputStream in = null;
		String extName = FilenameUtils.getExtension(fileAbsolutePath);
		try {
			BufferedImage image = imageStreamWraper.getImage();
			Map<String,Integer> img_params = super.checkIfLarger(image, compressWidth, compressHeight);
			Thumbnails.of(imageStreamWraper.getImage()).size(img_params.get(ImageDecorateSupport.IMG_W), img_params.get(ImageDecorateSupport.IMG_H))
					.outputFormat(extName).toOutputStream(out);
		} catch (IOException e) {
			throw new IOException("---->压缩图片失败："+e.getMessage());
		}
		byte buffer[] = out.toByteArray();
		out.close();
		in = new ByteArrayInputStream(buffer);
		String _fileName = FilenameUtils.removeExtension(fileAbsolutePath)+getSuffix()+"."+extName;
		fileUpload.writeFile(in, FilenameUtils.normalize(_fileName));
		in.close();
	}

	public String getSuffix(){
		return "_" + compressWidth + "_" + compressHeight;
	}
	
}
