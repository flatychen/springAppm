package cn.appm.utils.web;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.appm.web.WebConstants;


/**
 *  验证码工具
 * 
 * @author flatychen
 *
 */

public class SecurityCodeUtil extends HttpServlet {

	Random random = new Random();
	private static final long serialVersionUID = -5813134629255375160L;

	// 验证码图片的宽度
	private int width = 75;
	// 验证码图片的高度
	private int height = 33;
	// 验证码字符个数
	private int codeCount = 4;
	// 字体高度
	private int fontHeight = 20;
	// 干扰线个数
	private int lineCount = 6;
	// 随机数值
	char[] codeSequence = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'J', 'K',
			'L', 'M', 'N', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y',
			'Z', '2', '3', '4', '5', '6', '7', '8', '9' };

	/*
	 * 获得字体
	 */
	private Font getFont() {
		return new Font("Times New Roman", Font.BOLD, random.nextInt(10) + 15);
	}

	/*
	 * 获得颜色
	 */
	private Color getRandColor() {
		int r = random.nextInt(55);
		int g = random.nextInt(155);
		int b = random.nextInt(255);
		return new Color(r, g, b);
	}

	private Color getRandColor(int r, int g, int b) {
		return new Color(r, g, b);
	}

	/*
	 * 绘制干扰线
	 */
	private void drowLine(Graphics g) {
		int x = random.nextInt(width);
		int y = random.nextInt(height);
		int xl = random.nextInt(13);
		int yl = random.nextInt(15);
		g.drawLine(x, y, x + xl, y + yl);
	}

	/*
	 * 绘制字符串
	 */
	private String drowString(Graphics2D g, Color c, int x, int y) {
		String s = String.valueOf(codeSequence[random.nextInt(32)]);
		// 设置颜色
		g.setColor(c);
		g.setFont(getFont());
		g.rotate(0.05 - (random.nextInt(5)) / (50.0));
		// g.rotate(-0.05 );
		g.drawString(s, x, y);
		return s;
	}

	protected void service(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, java.io.IOException {

		// 定义图像buffer
		BufferedImage buffImg = new BufferedImage(width, height,
				BufferedImage.TYPE_INT_RGB);
		Graphics2D gd = buffImg.createGraphics();
		// 反矩尺
		this.AntiAlias(gd);
		// 将图像背景填充为白色
		gd.setColor(Color.WHITE);
		gd.fillRect(0, 0, width, height);
		gd.setFont(this.getFont());

		// 画边框
		gd.setColor(Color.gray);
		gd.drawRect(0, 0, width - 1, height - 1);

		// Codes用于保存随机产生的验证码
		StringBuffer Codes = new StringBuffer();

		// 随机产生codes的验证码
		for (int i = 0; i < codeCount; i++) {
			String s =drowString(gd, new Color(0, 0, 0), (i + 1) * (fontHeight - 5),
					fontHeight);
			// 将产生的四个随机数组合在
			Codes.append(s);
		}
		// 随机产生codes的验证码
		int avgWid = width / lineCount;
		for (int i = 0; i < lineCount; i++) {
			gd.setColor(Color.black);
			int x = random.nextInt(avgWid);
			int y = random.nextInt(height);
			int x1 = random.nextInt(avgWid);
			int y1 = random.nextInt(height);

			gd.drawLine(x + i * avgWid, y, x1 + i * avgWid, y1);

		}

		// 将四位数字的验证码保存到Session
		HttpSession session = req.getSession();
		session.setAttribute(WebConstants.SECURIYT_CODE, Codes.toString());

		// 禁止图像缓存
		resp.setHeader("Pragma", "no-cache");
		resp.setHeader("Cache-Control", "no-cache");
		resp.setDateHeader("Expires", 0);
		resp.setContentType("image/jpeg");
		ServletOutputStream sos = resp.getOutputStream();
		ImageIO.write(buffImg, "jpeg", sos);
		sos.close();
	}

	/**
	 * 作者:   flatychen
	 * 时间:   2013-7-2
	 * 功能:   TODO 图片反矩尺
	 * 参数:   @param g
	 * 返回值: void
	 */
	private void AntiAlias(Graphics2D g) {
		RenderingHints hints = new RenderingHints(null);
		hints.put(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		hints.put(RenderingHints.KEY_TEXT_ANTIALIASING,
				RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		g.setRenderingHints(hints);
	}

}
