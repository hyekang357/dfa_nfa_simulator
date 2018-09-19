package sample;

import javafx.event.ActionEvent;
import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.ArcType;

import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.HashMap;

public class Controller {

    public TextArea TextAreaInput;
    public TextArea TextAreaOutput;
    public VBox VBoxForCanvas;

    private Circle drawState() {
        Circle c = new Circle(18);
        c.setStroke(Color.valueOf("b4b4b4"));
        c.setFill(Color.valueOf("e5e5e5"));
        c.setStrokeWidth(1);
        c.toFront();

        return c;
    }

    public void drawTest1() {

        Canvas c = new Canvas(918.0, 483.0);
        GraphicsContext GC = c.getGraphicsContext2D();

        System.out.println("hello");

        GC.strokeText("TL", 0, 10);
        GC.strokeText("TR", 900, 10);
        GC.strokeText("BL", 0, 477);
        GC.strokeText("BR", 900, 477);

        GC.strokeLine(100, 258, 200, 258);


        GC.strokeOval(208, 208, 100, 100);
        GC.strokeOval(200, 200, 116, 116);

        GC.strokeLine(318, 258, 400, 258);


        GC.strokeOval(408, 208, 100, 100);
        GC.strokeOval(400, 200, 116, 116);

        GC.strokeLine(516, 258, 607, 258);


        GC.strokeOval(608, 208, 100, 100);


        GC.strokeArc(210, 130, 100, 100, 320, 260, ArcType.OPEN);
        GC.strokeArc(410, 130, 100, 100, 320, 260, ArcType.OPEN);
        GC.strokeArc(610, 140, 100, 100, 320, 260, ArcType.OPEN);





//        GC.drawImage(drawState(), 100, 100);

        VBoxForCanvas.getChildren().remove(0);
        VBoxForCanvas.getChildren().add(0, c);

    }

}
