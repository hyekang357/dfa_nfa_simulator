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
        gc.strokeOval(x-8, y-8, size+16, size+16);
    }

    private void drawTransistionToItself(GraphicsContext gc, int x, int y, int size, String s) {
        gc.strokeArc(x+5, y-78, size, size, 320, 258, ArcType.OPEN);
        Arrow aa = new Arrow(true, x+5, y-78);
        gc.fillPolygon(aa.get_x_points(), aa.get_y_points(), aa.get_num_points());
        // write text
        gc.setLineWidth(1.0);
        gc.setFont(new Font("Arial", 24));
        gc.fillText(s, x+size, y-(size*0.7));
        gc.setLineWidth(3.0);
    }

    private void drawArrowLine(GraphicsContext gc, int x1, int y1, int x2, int y2) {
        gc.strokeLine(x1, y1, x2, y2);
        Arrow sa = new Arrow(false, x2, y2);
        gc.fillPolygon(sa.get_x_points(), sa.get_y_points(), sa.get_num_points());
    }

    private void drawArrowLieWithText(GraphicsContext gc, int x1, int y1, int x2, int y2, String s) {
        // draw line
        gc.strokeLine(x1, y1, x2, y2);
        // draw arrow
        Arrow sa = new Arrow(false, x2, y2);
        gc.fillPolygon(sa.get_x_points(), sa.get_y_points(), sa.get_num_points());
        // write text
        gc.setLineWidth(1.0);
        gc.setFont(new Font("Arial", 24));
        gc.fillText(s, (x1+x2-14)/2, ((y1+y2)/2)-20);
        gc.setLineWidth(3.0);
    }

    private void drawTextInCircle(GraphicsContext gc, int x, int y, int size, String s) {
        gc.setLineWidth(1.0);
        gc.setFont(new Font("Arial", 24));
        gc.fillText(s, x+(size*0.4), y+(size*0.55));
        gc.setLineWidth(3.0);
    }

    private void drawTextInLine(GraphicsContext gc, int x1, int y1, int x2, int y2, String s) {
        gc.setLineWidth(1.0);
        gc.setFont(new Font("Arial", 24));
        gc.fillText(s, (x1+x2-14)/2, ((y1+y2)/2)-20);
        gc.setLineWidth(3.0);
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
        GC.setFont(new Font(16));

        drawArrowLine(GC, 100, 258, 196, 258);
        //q0
        drawAcceptState(GC, 208, 208, 100);
        drawTextInCircle(GC, 208, 208, 100, "q0");
        drawTransistionToItself(GC, 208, 208, 100, "0");

        drawArrowLieWithText(GC, 318, 258, 396, 258, "E");

        //q1
        drawAcceptState(GC, 408, 208, 100);
        drawTextInCircle(GC, 408, 208, 100, "q1");
        drawTransistionToItself(GC, 408, 208, 100, "1");

        drawArrowLieWithText(GC, 516, 258, 597, 258, "0");

        // q2
        drawRejectState(GC,608, 208, 100);
        drawTextInCircle(GC, 608, 208, 100, "q2");

        VBoxForCanvas.getChildren().remove(0);
        VBoxForCanvas.getChildren().add(0, c);
    }

    public void drawTest2() {
        Canvas c = new Canvas(918.0, 483.0);
        GraphicsContext GC = c.getGraphicsContext2D();

        // TODO: need to implement here

        VBoxForCanvas.getChildren().remove(0);
        VBoxForCanvas.getChildren().add(0, c);
    }

    public void drawTest3() {
        Canvas c = new Canvas(918.0, 483.0);
        GraphicsContext GC = c.getGraphicsContext2D();

        // TODO: need to implement here

        VBoxForCanvas.getChildren().remove(0);
        VBoxForCanvas.getChildren().add(0, c);
    }

    public void clearCanvas() {
        Canvas c = new Canvas(918.0, 483.0);
        GraphicsContext GC = c.getGraphicsContext2D();
        VBoxForCanvas.getChildren().remove(0);
        VBoxForCanvas.getChildren().add(0, c);
    }

}
