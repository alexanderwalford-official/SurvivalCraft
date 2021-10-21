import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import java.io.File;
import java.io.IOException;

public class Game extends JFrame implements Runnable{

    private static final long serialVersionUID = 1L;
    public int mapWidth = 15;
    public int mapHeight = 15;
    private Thread thread;
    private boolean running;
    private BufferedImage image;
    public int[] pixels;
    public ArrayList<Texture> textures;
    public Camera camera;
    public Screen screen;
    public MouseListener mousemanager;

    public static int[][] map =
            {
                    // each value is a block
                    {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
                    {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
                    {1,0,1,0,2,0,3,0,4,0,5,0,6,0,7,0,0,1},
                    {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
                    {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
                    {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
                    {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
                    {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
                    {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
                    {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1}
            };


    public Game() throws IOException {
        thread = new Thread(this);
        image = new BufferedImage(1280, 720, BufferedImage.TYPE_INT_RGB);
        pixels = ((DataBufferInt)image.getRaster().getDataBuffer()).getData();

        textures = new ArrayList<Texture>(); // create the textures array
        textures.add(Texture.log);
        textures.add(Texture.stone);
        textures.add(Texture.cobblestone);
        textures.add(Texture.dirt);
        textures.add(Texture.grass);
        textures.add(Texture.leaves);
        textures.add(Texture.water);

        camera = new Camera(4.5, 4.5, 1, 0, 0, -.74); // set the position of the camera object
        screen = new Screen(map, mapWidth, mapHeight, textures, 1280, 720); // render the new screen class

        addMouseListener(mousemanager); // house handler
        addKeyListener(camera); // WASD keyboard input

        // render the GUI


        setSize(1280, 720); // set the window width and height
        setResizable(false); // set the resizeable window
        setTitle("Survival Craft - Version 0.1"); // set the window title
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBackground(Color.white); // window background colour
        setLocationRelativeTo(null);
        setVisible(true);
        start();
    }


    private synchronized void start() throws IOException {
        running = true;
        thread.start();
    }

    public synchronized void stop() {
        running = false;
        try {
            thread.join();
        } catch(InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void render() {
        BufferStrategy bs = getBufferStrategy();
        if(bs == null) {
            createBufferStrategy(3);
            return;
        }
        Graphics g = bs.getDrawGraphics();
        g.drawImage(image, 0, 0, image.getWidth(), image.getHeight(), null);
        bs.show();
    }

    public void run() {
        System.out.println("RUN method called..");
        long lastTime = System.nanoTime();
        final double ns = 1000000000.0 / 60.0;//60 times per second
        double delta = 0;
        requestFocus();
        while(running) {
            long now = System.nanoTime();
            delta = delta + ((now-lastTime) / ns);
            lastTime = now;
            while (delta >= 1)//Make sure update is only happening 60 times a second
            {
                //handles all of the logic restricted time
                screen.update(camera, pixels);
                camera.update(map);
                delta--;
            }
            render(); //displays to the screen unrestricted time
        }
    }

    public static void main() throws IOException
    {
        Game game = new Game();
    }
}