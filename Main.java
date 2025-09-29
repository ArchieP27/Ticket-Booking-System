import java.awt.*;

public class Main {

    public static void main(String[] args) {

        // Current Date : 29th September 2025

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int width = (int) (screenSize.width * 0.8);
        int height = (int) (screenSize.height * 0.8);

        HOMEPAGE homepage =  new HOMEPAGE(width,height);

    }
}
