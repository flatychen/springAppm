package cn.appm.utils.web.file.imageDecorate;


/**
 * 图片裁剪信息
 * @author flatychen
 * @date 2014-5-30
 * @version 
 */
public class ImageClipInfo {

	private int w = 0;
	private int h = 0;
	private int x = 0;
	private int y = 0;

	public boolean isValid() {
		if (w < 0 || h < 0 || x < 0 || y < 0)
			return false;
		else
			return true;
	}

	public boolean isNeedClip(){
		if(x==0 & y==0 & w==0 & h==0){
			return false;
		}
		return true;
	}
	
	public ImageClipInfo(int w, int h, int x, int y) {
		super();
		this.w = w;
		this.h = h;
		this.x = x;
		this.y = y;
	}

	public ImageClipInfo() {
		super();
	}

	public void setW(int w) {
		this.w = w;
	}

	public void setH(int h) {
		this.h = h;
	}

	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}


	public int getW() {
		return w;
	}

	public int getH() {
		return h;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

}
