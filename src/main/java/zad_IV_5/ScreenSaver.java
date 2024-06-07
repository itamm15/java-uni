package zad_IV_5;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

interface IFlyable {
    void setRandomLocation();
    void draw(Graphics graphics);
}

class FlyingObject extends Thread {
    private int x;
    private int y;
    private Color color;
    private int width;
    private int height;
    private ScreenSaver screenSaver;

    public FlyingObject(ScreenSaver w) {
        this.screenSaver = w;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public ScreenSaver getScreenSaver() {
        return screenSaver;
    }

    public void setScreenSaver(ScreenSaver screenSaver) {
        this.screenSaver = screenSaver;
    }
}

class FlyingCircle extends FlyingObject {
    private int r;
    public FlyingCircle(ScreenSaver w) {
        super(w);
    }

    public void setRandomLocation() {
        Random r = new Random();
        ScreenSaver window = getScreenSaver();

        setY(r.nextInt(window.getHeight() + 1));
        setX(r.nextInt(window.getWidth() + 1));
    }

    public void draw(Graphics g) {
        g.drawOval(getX(), getY(), r, r);
    }

    public void run() {
        while(true) {
            setRandomLocation();
            getScreenSaver().repaint();
            try { Thread.sleep(500); } catch(Exception e) {};
        }
    }

    public int getR() {
        return r;
    }

    public void setR(int r) {
        this.r = r;
    }
}

class FlyingText extends FlyingObject {
    private Font font;
    private String text;

    public FlyingText(ScreenSaver w) {
        super(w);
    }

    public void setRandomLocation() {
        Random r = new Random();
        ScreenSaver window = getScreenSaver();
        Graphics g = window.getGraphics();
        int tempWidth = g.getFontMetrics(font).stringWidth(text);
        int tempHeight = g.getFontMetrics(font).getHeight();
        setWidth(tempWidth);
        setHeight(tempHeight);

        setY(tempHeight  + r.nextInt(window.getHeight() - tempHeight + 1));
        setX(r.nextInt(window.getWidth() - tempWidth + 1));
    }

    public void draw(Graphics g) {
        Font tempFont = g.getFont();
        Color tempColor = g.getColor();

        g.drawString(text, getX(), getY());

        g.setFont(tempFont);
        g.setColor(tempColor);
    }

    public void run() {
        while(true) {
            setRandomLocation();
            getScreenSaver().repaint();
            try { Thread.sleep(500); } catch(Exception e) {};
        }
    }

    public Font getFont() {
        return font;
    }

    public void setFont(Font font) {
        this.font = font;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}

class FlyingRectangle extends FlyingObject {

    public FlyingRectangle(ScreenSaver w) {
        super(w);
    }

    public void setRandomLocation() {
        Random r = new Random();
        ScreenSaver window = getScreenSaver();

        setY(r.nextInt(window.getHeight() + 1));
        setX(r.nextInt(window.getWidth() + 1));
    }

    public void draw(Graphics g) {
        g.drawRect(getX(), getY(), getWidth(), getHeight());
    }

    public void run() {
        while(true) {
            setRandomLocation();
            getScreenSaver().repaint();
            try { Thread.sleep(500); } catch(Exception e) {};
        }
    }
}

public class ScreenSaver extends JPanel {
    private FlyingText[] texts;
    private FlyingCircle firstFlyingCircle;

    private FlyingRectangle firstFlyingRectangle;
    
    ScreenSaver() {
        super();
        setDoubleBuffered(true);
    }

    public void startAnimation() {
        FlyingText firstFlyingText = new FlyingText(this);
        firstFlyingText.setText("Java is cool");
        firstFlyingText.setFont(new Font("Arial", Font.PLAIN, 12));
        firstFlyingText.setColor(Color.RED);

        FlyingText secondFlyingText = new FlyingText(this);
        secondFlyingText.setText("C# is cool");
        secondFlyingText.setFont(new Font("Verdana", Font.ITALIC, 50));
        secondFlyingText.setColor(Color.BLUE);

        texts = new FlyingText[] {firstFlyingText, secondFlyingText};
        for(FlyingText t:texts) t.start();

        firstFlyingCircle = new FlyingCircle(this);
        firstFlyingCircle.setR(15);
        firstFlyingCircle.setColor(Color.BLUE);
        firstFlyingCircle.start();

        firstFlyingRectangle = new FlyingRectangle(this);
        firstFlyingRectangle.setColor(Color.RED);
        firstFlyingRectangle.setHeight(5);
        firstFlyingRectangle.setWidth(15);
        firstFlyingRectangle.start();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if(texts != null)
            for(FlyingText t:texts) t.draw(g);

        firstFlyingCircle.draw(g);
        firstFlyingRectangle.draw(g);
    }                    
}

class ScreenSaverWindow extends JFrame {

    private ScreenSaver flyingSpace;

    ScreenSaverWindow() {
        super("Screen saver - flying texts");        
        init();
        setVisible(true);
        flyingSpace.startAnimation();
    }

    private void init() {
        setSize(400, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        flyingSpace = new ScreenSaver();        
        add(flyingSpace);
    }

    public static void main(String[] a) {
        new ScreenSaverWindow();
    }
}
