import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Maze extends JComponent implements MouseMotionListener {

    /* Gambar utama */
    BufferedImage image;
    /* Gambar tersembunyi */
    BufferedImage hiddenImage;

    /**
     * Konstruktor
     *
     * @throws IOException
     */
    public Maze() throws IOException{
        /* Membaca image dengan ImageIO ke BufferedImage */
        image = ImageIO.read(getClass().getResource("hidden-maze.png"));
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
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(600, 600);
    }

    @Override
    protected void paintComponent(Graphics g) {
        g.setColor(Color.RED);
        g.fillRect(0, 0, 600, 600);

        g.drawImage(image, 0, 0, null);
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        // No Actions
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();

        int color = image.getRGB(x, y);

        System.out.println(color);

        repaint();
    }
}
