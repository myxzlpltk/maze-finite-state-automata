import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class State {

    private String file;
    private BufferedImage image;
    private State next;
    private int color;

    public State(String file, int color) throws IOException {
        this.file = file;
        this.color = color;

        this.image = ImageIO.read(getClass().getResource(this.file));
    }

    public int getColor() {
        return color;
    }

    public BufferedImage getImage() {
        return image;
    }
}
