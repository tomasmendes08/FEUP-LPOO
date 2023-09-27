package MainPackage.gui;

import com.googlecode.lanterna.graphics.TextGraphics;
import MainPackage.data.Position;

public abstract class ElementView {
    public abstract void draw(TextGraphics textGraphics, Position position);
}
