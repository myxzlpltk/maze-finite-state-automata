import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Maze extends JComponent implements MouseListener, MouseMotionListener {

    /* Gambar utama */
    BufferedImage image;
    /* Gambar tersembunyi */
    BufferedImage hiddenImage;
    /* Gambar start */
    BufferedImage start;
    /* Gambar game over */
    BufferedImage gameOver;
    /* Gambar game win */
    BufferedImage gameWin;
    /* Gambar saat ini */
    BufferedImage currentImage;
    /* State */
    State q0, q1, q2, q3, q4, q5, q6, q7, q8, q9, q10, q11;
    State currentState;
    /* List State */
    List<State> states = new ArrayList<State>();

    /**
     * Konstruktor
     *
     * @throws IOException
     */
    public Maze() throws IOException{
        /* Membaca image dengan ImageIO ke BufferedImage */
        image = ImageIO.read(getClass().getResource("resources/maze.png"));
        hiddenImage = ImageIO.read(getClass().getResource("resources/hidden-maze.png"));
        start = ImageIO.read(getClass().getResource("resources/start.png"));
        gameOver = ImageIO.read(getClass().getResource("resources/game-over.png"));
        gameWin = ImageIO.read(getClass().getResource("resources/game-win.png"));

        /* Menetapkan gambar saat ini menjadi sekarang */
        currentImage = start;

        /* Menginisialisasi state */
        states.add(q0 = new State("resources/q0.png", -12797832));
        states.add(q1 = new State("resources/q1.png", -8600202));
        states.add(q2 = new State("resources/q2.png", -16728077));
        states.add(q3 = new State("resources/q3.png", -14894156));
        states.add(q4 = new State("resources/q4.png", -12284725));
        states.add(q5 = new State("resources/q5.png", -11111239));
        states.add(q6 = new State("resources/q6.png", -5450893));
        states.add(q7 = new State("resources/q7.png", -282787));
        states.add(q8 = new State("resources/q8.png", -8036184));
        states.add(q9 = new State("resources/q9.png", -10462040));
        states.add(q10 = new State("resources/q10.png", -618922));
        states.add(q11 = new State("resources/q11.png", -889777));
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

    /**
     * Menentukan checkpoint
     *
     * @param color
     * @return bool
     */
    public boolean checkPoint(int color){
        /* Loopign semua state */
        for (State state : states){
            /* Jika state ditemukan */
            if (state.getColor() == color){
                /* Pasang checkpoint */
                currentState = state;

                /* Checkpoint ditemukan */
                return true;
            }
        }

        /* Checkpoint gagal */
        return false;
    }

    /**
     * Mengambil warna RGB dari gambar
     * @param x
     * @param y
     * @return int
     */
    private int getRGB(int x, int y){
        return (currentImage == image ? hiddenImage : currentImage).getRGB(x, y);
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(600, 600);
    }

    @Override
    protected void paintComponent(Graphics g) {
        /* Menentukan warna background */
        g.setColor(new Color(-2560));
        /* Membuat ukuran frame */
        g.fillRect(0, 0, 600, 600);

        /* Menggambar pada frame */
        g.drawImage(currentImage, 0, 0, null);
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        // No Actions
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        /* Mendapatkan koordinat x,y pada kartesius */
        int x = e.getX();
        int y = e.getY();

        /* Mengambil nilai RGB */
        int color = getRGB(x, y);

        /* Mendefinisikan warna */
        final int pathColor = -2560; // Yellow
        final int startEndColor = q11.getColor();

        /* Jika warna sama dengan warna jalur */
        if(color == pathColor) {
            // Continue
        }
        else if(color == startEndColor) {
            if(currentImage == start){
                currentImage = image;
            }
            else if(currentImage == image){
                currentImage = gameWin;
            }
        }
        else if(currentState != null && currentImage == currentState.getImage() && color == currentState.getColor()){
            currentImage = image;
        }
        else{
            if(currentImage == image && !checkPoint(color)) {
                if(currentState == null){
                    currentImage = gameOver;
                }
                else{
                    currentImage = currentState.getImage();
                }
            }
        }

        System.out.println(color);

        /* Render */
        repaint();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        /* Jika game selesai */
        if(currentImage == gameOver || currentImage == gameWin){
            /* Mulai awal */
            currentState = null;
            currentImage = start;
        }

        /* Render */
        repaint();
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
