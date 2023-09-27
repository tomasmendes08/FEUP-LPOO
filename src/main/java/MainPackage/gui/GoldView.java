package MainPackage.gui;

import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import MainPackage.data.Position;

public class GoldView extends ElementView{
    @Override
    public void draw(TextGraphics textGraphics, Position position) {
        textGraphics.setForegroundColor(TextColor.Factory.fromString("#FFD700"));
        textGraphics.enableModifiers(SGR.BOLD);
        textGraphics.putString(new TerminalPosition(position.getX(), position.getY()), "O");
    }
}
