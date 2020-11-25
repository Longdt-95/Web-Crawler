package TestURLConnection;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Scanner;

import javax.imageio.ImageIO;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class GetImageFromUrl {
	
	private Scanner scanner;
	
	public String InputURL() {
		scanner = new Scanner(System.in);
		System.out.println("nhập đường dẫn bạn muốn lấy ảnh: ");
		String url = scanner.nextLine();
		return url;
	}
	
	public String createFolder() {
		scanner = new Scanner(System.in);
		System.out.println("nhập tên thư mục chứa ảnh: ");
		String fileName = scanner.nextLine();
		File file = new File(fileName);
		file.mkdir();
		String absolutePath = file.getPath();
		return absolutePath;
	}
	
	public String createNameForImage(Element element) {
		String imageName = element.attr("src").substring(element.attr("src").lastIndexOf("/") + 1);
		return imageName;
	}
	
	public void getImageFromURL(String url) {
		File file = null;
		String folder = createFolder();
		try {
			Document document = Jsoup.connect(url).get();
			Elements elements = document.getElementsByTag("img");
			for (Element element : elements) {
				String urlImages = element.attr("src");
				if ((urlImages.startsWith("//") || urlImages.startsWith("http")) && urlImages.indexOf("?") == -1) {
					if (!urlImages.startsWith("https:")) {
						urlImages = "https:" + urlImages;
					}
					URL urlImage = new URL(urlImages);
					BufferedImage image = new BufferedImage(500, 700, BufferedImage.TYPE_4BYTE_ABGR);
					image = ImageIO.read(urlImage);
					String fileImage = createNameForImage(element);
					file = new File(folder + "/" + fileImage);
					ImageIO.write(image, "PNG", file);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public static void main(String[] args) {
		GetImageFromUrl fromUrl = new GetImageFromUrl();
		String url = fromUrl.InputURL();
		fromUrl.getImageFromURL(url);
	}
}
