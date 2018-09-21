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

    private void drawAcceptState(GraphicsContext gc, int x, int y, int size) {
        gc.strokeOval(x, y, size, size);
        gc.strokeOval(x-8, y-8, size+16, size+16);
    }

    private void drawRejectState(GraphicsContext gc, int x, int y, int size) {
        gc.strokeOval(x, y, size, size);
    }

    private void drawTransistionToItself(GraphicsContext gc, int x, int y, int size) {
        gc.strokeArc(x+5, y-78, size, size, 320, 260, ArcType.OPEN);
        Arrow aa = new Arrow(true, x+5, y-78);
        gc.fillPolygon(aa.get_x_points(), aa.get_y_points(), aa.get_num_points());
    }

    private void drawArrowLine(GraphicsContext gc, int x1, int y1, int x2, int y2) {
        gc.strokeLine(x1, y1, x2, y2);
        Arrow sa = new Arrow(false, x2, y2);
        gc.fillPolygon(sa.get_x_points(), sa.get_y_points(), sa.get_num_points());
    }

    public void drawTest1() {

        Canvas c = new Canvas(918.0, 483.0);
        GraphicsContext GC = c.getGraphicsContext2D();

        System.out.println("hello...");

        // the corners of the canvas
        GC.strokeText("TL", 0, 10);
        GC.strokeText("TR", 900, 10);
        GC.strokeText("BL", 0, 477);
        GC.strokeText("BR", 900, 477);

        GC.setLineWidth(3.0);

        drawArrowLine(GC, 100, 258, 200, 258);
        //q0
        drawAcceptState(GC, 208, 208, 100);
        drawTransistionToItself(GC, 208, 208, 100);
        drawArrowLine(GC, 318, 258, 400, 258);
        //q1
        drawAcceptState(GC, 408, 208, 100);
        drawTransistionToItself(GC, 408, 208, 100);
        drawArrowLine(GC, 516, 258, 607, 258);
        // q2
        drawRejectState(GC,608, 208, 100);
        drawTransistionToItself(GC, 608, 208, 100);

        VBoxForCanvas.getChildren().remove(0);
        VBoxForCanvas.getChildren().add(0, c);

    }

}
