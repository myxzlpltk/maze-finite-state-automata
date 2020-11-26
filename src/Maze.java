import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Maze extends JComponent implements MouseListener, MouseMotionListener {

    /* Gambar utama */
    BufferedImage image;
    /* Gambar tersembunyi */
    BufferedImage hiddenImage;
    /* Gambar start */
    BufferedImage start;
    /* Gambar start */
    BufferedImage gameOver;
    /* Gambar saat ini */
    BufferedImage currentImage;

    /**
     * Konstruktor
     *
     * @throws IOException
     */
    public Maze() throws IOException{
        /* Membaca image dengan ImageIO ke BufferedImage */
        image = ImageIO.read(getClass().getResource("maze.png"));
        hiddenImage = ImageIO.read(getClass().getResource("hidden-maze.png"));
        start = ImageIO.read(getClass().getResource("start.png"));
        gameOver = ImageIO.read(getClass().getResource("game-over.png"));

        /* Menetapkan gambar saat ini menjadi sekarang */
        currentImage = start;
    }

    /**
     * Main method
     *
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        /* Membuat frame */
        JFrame window = new JFrame("Maze Finite State Automata");

        /* Menginisialisasi obyek */
        Maze game = new Maze();

        /* Menambahkan Component ke JFrame */
        window.add(game);
        /* Mengatur ukuran JFrame ke minimum */
        window.pack();
        /* Mengatur lokasi relative menjadi null */
        window.setLocationRelativeTo(null);
        /* Exit program ketika menutup window */
        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        /* Menayangkan JFrame */
        window.setVisible(true);

        /* Mengaktifkan MouseMotionListener */
        game.addMouseMotionListener(game);
        /* Mengaktifkan MouseListener */
        game.addMouseListener(game);
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(600, 600);
    }

    @Override
    protected void paintComponent(Graphics g) {
        g.setColor(Color.RED);
        g.fillRect(0, 0, 600, 600);

        g.drawImage(currentImage, 0, 0, null);
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        // No Actions
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();

        int color = (currentImage == image? hiddenImage : currentImage).getRGB(x, y);

        int gameOverColor = -16777216;

        if(color == gameOverColor){
            currentImage = gameOver;
        }

        System.out.println(color);

        repaint();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();

        int color = image.getRGB(x, y);

        if(currentImage == start){
            if(color == -2560) {
                currentImage = image;
                repaint();
            }
        }
        else if(currentImage == gameOver){
            currentImage = start;
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        // No Actions
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        // No Actions
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // No Actions
    }

    @Override
    public void mouseExited(MouseEvent e) {
        // No Actions
    }
}
