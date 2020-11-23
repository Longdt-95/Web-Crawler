package TestURLConnection;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.imageio.ImageIO;

public class GetImageFromUrl {
	
	public static void main(String[] args) {
		File file = new File("images.PNG");
		URL url = null;
		try {
			url = new URL("https://cdn.tgdd.vn/Products/Images/42/190325/iphone-xr-hopmoi-den-400x400-400x400.png");
		} catch (MalformedURLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		BufferedImage image = null;
		image = new BufferedImage(500, 700, BufferedImage.TYPE_4BYTE_ABGR);
		try {
			image = ImageIO.read(url);
			ImageIO.write(image, "PNG", file);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
