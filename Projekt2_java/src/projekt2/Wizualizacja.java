package projekt2;

import projekt2.rosliny.*;
import projekt2.zwierzeta.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JOptionPane;

public class Wizualizacja implements ActionListener, KeyListener {
    private Toolkit toolkit;
    private Dimension dimension;
    private JFrame frame;
    private JMenu menu;
    private JMenuItem nowaTura;
    private int x, y;
    private JTextArea naglowek;
    private JScrollPane areaScrollPane;
    private JPanel grid;
    private organizm organizmy[][];
    private JMenuItem nowySwiat, wczytaj, zapisz, legenda, wyjdz;
    private Swiat swiat;
    private final static String rodzaj[] = {"Barszcz sosnowskiego", "Guarana", "Mlecz", "Trawa", "Wilcze jagody",
            "Antylopa", "Cyber owca", "Lis", "Owca", "Wilk", "Zolw"};
    private final static Color kolor[] = {Color.CYAN, Color.RED.darker(), Color.YELLOW.brighter(), Color.GREEN.brighter(), Color.BLUE.darker(),
            Color.YELLOW.darker(), Color.GRAY, Color.PINK, Color.LIGHT_GRAY, Color.BLACK, Color.GREEN.darker(), Color.MAGENTA.darker()};

    public Wizualizacja() {
        toolkit = Toolkit.getDefaultToolkit();
        dimension = toolkit.getScreenSize();

        frame = new JFrame("świat");
        frame.setBounds((dimension.width - 800) / 2, (dimension.height - 600) / 2, 800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        naglowek = new JTextArea();
        naglowek.addKeyListener(this);

        menu = new JMenu("menu");
        nowySwiat = new JMenuItem("Nowy swiat");
        wczytaj = new JMenuItem("Wczytaj swiat");
        zapisz = new JMenuItem("Zapisz");
        legenda = new JMenuItem("Legenda");
        wyjdz = new JMenuItem("Wyjdz");
        nowaTura = new JMenuItem("Nowa tura");
        nowySwiat.addActionListener(this);
        wczytaj.addActionListener(this);
        zapisz.addActionListener(this);
        zapisz.setEnabled(false);
        legenda.addActionListener(this);
        wyjdz.addActionListener(this);
        nowaTura.addActionListener(this);
        nowaTura.setEnabled(false);
        menu.add(nowySwiat);
        menu.add(wczytaj);
        menu.add(zapisz);
        menu.add(legenda);
        menu.add(wyjdz);
        JMenuBar menuBar = new JMenuBar();
        menuBar.add(menu);
        menuBar.add(nowaTura);
        menuBar.setBackground(Color.lightGray);

        frame.setJMenuBar(menuBar);
        frame.setLayout(new BorderLayout());
        frame.getContentPane().setLayout(new FlowLayout());
        frame.addKeyListener(this);
        frame.getContentPane().addKeyListener(this);
        frame.setVisible(true);

        dimension = new Dimension((frame.getContentPane().getWidth() - (frame.getWidth() - frame.getContentPane().getWidth())) / 2, (frame.getContentPane().getHeight() - menu.getHeight() / 2));
    }

    private void generuj() {
        nowaTura.setEnabled(true);
        zapisz.setEnabled(true);
        organizmy = new organizm[x][y];
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                organizmy[i][j] = new organizm(j, i);
            }
        }
    }

    private void rysujSwiat() {
        grid = new JPanel();
        for (int i = 0; i < y; i++) {
            for (int j = 0; j < x; j++) {
                organizmy[j][i].setBackground(swiat.getSwiat()[j][i].rysuj());
                organizmy[j][i].odblokuj(organizmy[j][i].getBackground());
                grid.add(organizmy[j][i]);
            }
        }
        grid.setLayout(new GridLayout(y, x));
        grid.setPreferredSize(new Dimension(dimension.width, dimension.width));
        grid.addKeyListener(this);
        frame.getContentPane().add(grid);
    }

    private void rysujNaglowek() {
        String tekst = "Aleksandra Borsuk 184620\nCzlowiek porusza sie za pomoca strzalek\nAktywacja specjalnej umiejetnosci czlowieka nastepuje poprzez uzycie klawisza u\n";
        if (swiat.getKomentarze() != null)
            tekst = tekst + swiat.getKomentarze();
        naglowek.setText(tekst);
        naglowek.setFont(new Font("Serif", Font.ITALIC, 13));
        naglowek.setLineWrap(true);
        naglowek.setVisible(true);
        naglowek.setWrapStyleWord(true);
        naglowek.setEditable(false);

        areaScrollPane = new JScrollPane(naglowek);
        areaScrollPane.setVerticalScrollBarPolicy(
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        areaScrollPane.setPreferredSize(dimension);
        areaScrollPane.setVisible(true);
        areaScrollPane.addKeyListener(this);
        frame.getContentPane().add(areaScrollPane);
    }

    private void wyswietl() {
        if (areaScrollPane != null)
            frame.getContentPane().remove(areaScrollPane);
        if (grid != null) {
            frame.getContentPane().remove(grid);
        }
        rysujNaglowek();
        rysujSwiat();
        frame.pack();
    }

    private void zapis() {
        JOptionPane.showMessageDialog(
                null,
                swiat.zapiszSwiat());
    }

    private void wczytywanie() {
        swiat = new Swiat();
        swiat.wczytajSwiat();
        x = swiat.getX();
        y = swiat.getY();
        generuj();
        wyswietl();
    }

    private void pokazLegende() {
        JPanel[] polaLegendy = new JPanel[swiat.ILOSC_ORGANIZMOW + 2];
        polaLegendy[0] = new JPanel();
        JLabel tytul = new JLabel("Rodzaje organizmow:");
        tytul.setFont(new Font(null, Font.BOLD, 14));
        polaLegendy[0].add(tytul);
        int i = 1;
        while (i <= swiat.ILOSC_ORGANIZMOW + 1) {
            polaLegendy[i] = new JPanel();
            polaLegendy[i].setLayout(new BorderLayout());
            JButton kwadrat = new JButton();
            kwadrat.setBackground(kolor[i - 1]);
            kwadrat.setEnabled(false);
            polaLegendy[i].add(kwadrat, BorderLayout.WEST);
            JLabel label = new JLabel();
            if (i != swiat.ILOSC_ORGANIZMOW + 1)
                label.setText("  " + rodzaj[i - 1]);
            else
                label.setText("  " + "czlowiek");
            polaLegendy[i].add(label);
            i++;
        }
        JOptionPane.showMessageDialog(frame,
                polaLegendy,
                "Legenda",
                JOptionPane.PLAIN_MESSAGE);
    }

    private class organizm extends JButton implements ActionListener {
        private int j, i;

        public organizm(int j, int i) {
            this.j = j;
            this.i = i;
            setVisible(true);
            addActionListener(this);
        }

        public void odblokuj(Color color) {
            if (color == Color.WHITE)
                setEnabled(true);
            else
                setEnabled(false);
        }

        private void pokazWybor() {
            String s = (String) JOptionPane.showInputDialog(
                    this,
                    "Wybierz rodzaj organizmu ktory chcesz wstawic",
                    "Wybor organizmu",
                    JOptionPane.PLAIN_MESSAGE,
                    getIcon(),
                    rodzaj,
                    rodzaj[0]);
            if (s == rodzaj[0]) swiat.getSwiat()[i][j] = new BarszczSosnowskiego(new Pair(i, j), swiat);
            else if (s == rodzaj[1]) swiat.getSwiat()[i][j] = new Guarana(new Pair(i, j), swiat);
            else if (s == rodzaj[2]) swiat.getSwiat()[i][j] = new Mlecz(new Pair(i, j), swiat);
            else if (s == rodzaj[3]) swiat.getSwiat()[i][j] = new Trawa(new Pair(i, j), swiat);
            else if (s == rodzaj[4]) swiat.getSwiat()[i][j] = new WilczeJagody(new Pair(i, j), swiat);
            else if (s == rodzaj[5]) swiat.getSwiat()[i][j] = new Antylopa(new Pair(i, j), swiat);
            else if (s == rodzaj[6]) swiat.getSwiat()[i][j] = new CyberOwca(new Pair(i, j), swiat);
            else if (s == rodzaj[7]) swiat.getSwiat()[i][j] = new Lis(new Pair(i, j), swiat);
            else if (s == rodzaj[8]) swiat.getSwiat()[i][j] = new Owca(new Pair(i, j), swiat);
            else if (s == rodzaj[9]) swiat.getSwiat()[i][j] = new Wilk(new Pair(i, j), swiat);
            else if (s == rodzaj[10]) swiat.getSwiat()[i][j] = new Zolw(new Pair(i, j), swiat);
        }

        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == this) {
                pokazWybor();
                frame.getContentPane().remove(grid);
                rysujSwiat();
                frame.pack();
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == nowySwiat) {
            try {
                x = Integer.parseInt(JOptionPane.showInputDialog(
                        frame, "Podaj szerokość planszy",
                        "Wprowadź dane", JOptionPane.PLAIN_MESSAGE
                ));
                y = Integer.parseInt(JOptionPane.showInputDialog(
                        frame, "Podaj wysokość planszy",
                        "Wprowadź dane", JOptionPane.PLAIN_MESSAGE
                ));
                if (x * y >= swiat.ILOSC_ORGANIZMOW + 1) {
                    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    frame.getContentPane().setBackground(null);
                    swiat = new Swiat(x, y);
                    swiat.generujSwiat();
                    generuj();
                    wyswietl();
                } else
                    JOptionPane.showMessageDialog(frame, "Liczby pomnożone przez siebie muszą być większe od 11", "Error", JOptionPane.ERROR_MESSAGE);
            } catch (NumberFormatException exception) {
                JOptionPane.showMessageDialog(frame, "Dane nie zostały poprawnie wpisane", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
        if (e.getSource() == nowaTura) {
            swiat.wykonajTure();
            wyswietl();
        }
        if (e.getSource() == wczytaj)
            wczytywanie();
        if (e.getSource() == zapisz)
            zapis();
        if (e.getSource() == legenda)
            pokazLegende();
        if (e.getSource() == wyjdz)
            System.exit(0);
    }

    @Override
    public void keyTyped(KeyEvent e) {
        if (e.getKeyChar() == 'u') {
            JOptionPane.showMessageDialog(
                    frame, Czlowiek.stan(),
                    "Stan umiejetnosci specjalnej czlowieka",
                    JOptionPane.INFORMATION_MESSAGE);
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        boolean zmiana = false;
        if (e.getKeyCode() == 38) {
            Czlowiek.setKierunek(new Pair(0, -1));
            zmiana = true;
        } else if (e.getKeyCode() == 37) {
            Czlowiek.setKierunek(new Pair(-1, 0));
            zmiana = true;
        } else if (e.getKeyCode() == 40) {
            Czlowiek.setKierunek(new Pair(0, 1));
            zmiana = true;
        } else if (e.getKeyCode() == 39) {
            Czlowiek.setKierunek(new Pair(1, 0));
            zmiana = true;
        }
        if (zmiana == true) {
            swiat.wykonajTure();
            wyswietl();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
}
