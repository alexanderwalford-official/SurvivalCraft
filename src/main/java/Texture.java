import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Texture {
    public int[] pixels;
    private String loc;
    public final int SIZE;
    public int counter = 0;

    public Texture(String location, int size) {
        loc = location;
        SIZE = size;
        pixels = new int[SIZE * SIZE];
        load();
    }

    private void load() {
        counter++;
        System.out.println("Trying to load texture " + counter);
        try {
            BufferedImage image = ImageIO.read(new File(loc));
            int w = image.getWidth();
            int h = image.getHeight();
            image.getRGB(0, 0, w, h, pixels, 0, w);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Texture log = new Texture("src/main/src/log.jpg", 64);
    public static Texture stone = new Texture("src/main/src/stone.jpg", 64);
    public static Texture cobblestone = new Texture("src/main/src/cobblestone.jpg", 64);
    public static Texture dirt = new Texture("src/main/src/dirt.jpg", 64);
    public static Texture grass = new Texture("src/main/src/grass.jpg", 64);
    public static Texture leaves = new Texture("src/main/src/leaves.jpg", 64);
    public static Texture water = new Texture("src/main/src/water.jpg", 64);
}