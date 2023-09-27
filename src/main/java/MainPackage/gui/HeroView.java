package MainPackage.gui;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.graphics.TextGraphics;
import MainPackage.data.Position;

public class HeroView extends ElementView {

    @Override
    public void draw(TextGraphics graphics, Position position) {
        graphics.putString(new TerminalPosition(position.getX(), position.getY()), Character.toString((char) 222));
    }
}
