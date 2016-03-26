package inne;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class WriteImageType {
	public static final String RESULT = System.getProperty("user.home") + "/Desktop/java_pdf/";

	
  static public void main(String args[]) throws Exception {
    try {
      int width = 200, height = 200;

      // TYPE_INT_ARGB specifies the image format: 8-bit RGBA packed
      // into integer pixels
      BufferedImage bi = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

      Graphics2D ig2 = bi.createGraphics();
      ig2.setColor(Color.RED);

      Font font = new Font("TimesRoman", Font.BOLD, 20);
      ig2.setFont(font);
      String message = "www.java2s.com!";
      FontMetrics fontMetrics = ig2.getFontMetrics();
      int stringWidth = fontMetrics.stringWidth(message);
      int stringHeight = fontMetrics.getAscent();
      ig2.setPaint(Color.red);
      ig2.drawString(message, (width - stringWidth) / 2, height / 2 + stringHeight / 4);
      ig2.setPaint(Color.GREEN);
      ig2.fillArc(0, 0, 40, 40, 0, 270);
      ImageIO.write(bi, "PNG", new File(RESULT+"yourImageName.PNG"));
      //http://www.postgresql.org/docs/7.4/static/jdbc-binary-data.html
    } catch (IOException ie) {
      ie.printStackTrace();
    }

  }
}
