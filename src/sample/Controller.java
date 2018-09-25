package sample;

import javafx.event.ActionEvent;
import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import javafx.scene.shape.ArcType;
import javafx.scene.paint.Color;

import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.HashMap;

public class Controller {

    public TextArea TextAreaInput;
    public TextArea TextAreaOutput;
    public VBox VBoxForCanvas;

    public Test1 Test;

    Canvas C;
    GraphicsContext GC;

    private void initializeCanvas() {
        // the corners of the canvas
        C = new Canvas(918.0, 483.0);
        GC = C.getGraphicsContext2D();
        GC.setLineWidth(3.0);
        GC.setFont(new Font(16));
    }

    private void drawState(State q) {
        if (q.get_isAccept()) {
            drawAcceptState(q.get_x(), q.get_y(), q.get_size());
        } else {
            drawRejectState(q.get_x(), q.get_y(), q.get_size());
        }
        if (!q.get_text().isEmpty()) {
            drawTextInCircle(q.get_x(), q.get_y(), q.get_size(), q.get_text());
        }
    }

    private void drawAcceptState(int x, int y, int size) {
        GC.strokeOval(x, y, size, size);
        GC.strokeOval(x-8, y-8, size+16, size+16);
    }

    private void drawRejectState(int x, int y, int size) {
        GC.strokeOval(x-8, y-8, size+16, size+16);
    }

    private void drawTextInCircle(int x, int y, int size, String s) {
        GC.setLineWidth(1.0);
        GC.setFont(new Font("Arial", 24));
        GC.fillText(s, x+(size*0.4), y+(size*0.55));
        GC.setLineWidth(3.0);
    }

    private void drawArrowToItself(Arrow a) {
        int size = 100;
        GC.strokeArc(a.get_x1(), a.get_y1(), size, size, 320, 258, ArcType.OPEN);
        GC.fillPolygon(a.get_x_points(), a.get_y_points(), a.get_num_points());
        // write text
        GC.setLineWidth(1.0);
        GC.setFont(new Font("Arial", 24));
        GC.fillText(a.get_text(), a.get_x1()+(size*0.95), a.get_y1()+(size*0.2));
        GC.setLineWidth(3.0);
    }

    private void drawArrowLine(Arrow a) {
        GC.strokeLine(a.get_x1(), a.get_y1(), a.get_x2(), a.get_y2());
        GC.fillPolygon(a.get_x_points(), a.get_y_points(), a.get_num_points());
        if (!a.get_text().isEmpty()) {
            GC.setLineWidth(1.0);
            GC.setFont(new Font("Arial", 24));
            GC.fillText(a.get_text(), (a.get_x1()+a.get_x2()-14)/2, ((a.get_y1()+a.get_y2())/2)-20);
            GC.setLineWidth(3.0);
        }
    }

    private void highlightArrow(Arrow a) {
        //set stroke color to red
        GC.setStroke(Color.RED);
        GC.setFill(Color.RED);
        GC.setLineWidth(5.0);
        GC.strokeLine(a.get_x1(), a.get_y1(), a.get_x2(), a.get_y2());
        GC.fillPolygon(a.get_x_points(), a.get_y_points(), a.get_num_points());
        GC.setStroke(Color.BLACK);
        GC.setFill(Color.BLACK);
        GC.setLineWidth(3.0);
    }

    public void drawTest1() {
        System.out.println("Drawing Test 1...");

        // Initialize canvas
        initializeCanvas();

        // Create drawing components
        Arrow sa1 = new Arrow(false, 100, 258, 196, 258, "q0");
        Arrow sa2 = new Arrow(false, 318, 258, 396, 258, "q0", "q1", "1");
        Arrow aa1 = new Arrow(true, 208, 208, "q0", "0");
        Arrow aa2 = new Arrow(true, 408, 208, "q1", "1");

        State q0 = new State(true, 208, 208, 100, "q0");
        State q1 = new State(true, 408, 208, 100, "q1");


        // Draw the objects
        drawArrowLine(sa1);
        //q0
        drawState(q0);
        drawArrowToItself(aa1);
        // draw q0 -> q1 arrow
        drawArrowLine(sa2);
        //q1
        drawState(q1);
        drawArrowToItself(aa2);

        // Create Test object
        ArrayList<Arrow> arrows = new ArrayList<>();
        arrows.addAll(Arrays.asList(sa1, sa2, aa1, aa2));
        ArrayList<State> states = new ArrayList<>();
        states.addAll(Arrays.asList(q0, q1));

        this.Test = new Test1(arrows, states, "0");


        VBoxForCanvas.getChildren().remove(0);
        VBoxForCanvas.getChildren().add(0, C);
    }

    public void drawTest2() {

        // Initialize canvas
        initializeCanvas();

        // TODO: Create objects

        // TODO: Draw objects

        VBoxForCanvas.getChildren().remove(0);
        VBoxForCanvas.getChildren().add(0, C);
    }

    public void drawTest3() {

        // Initialize canvas
        initializeCanvas();

        // TODO: Create objects

        // TODO: Draw objects

        VBoxForCanvas.getChildren().remove(0);
        VBoxForCanvas.getChildren().add(0, C);
    }

    public void clearCanvas() {
        initializeCanvas();
        VBoxForCanvas.getChildren().remove(0);
        VBoxForCanvas.getChildren().add(0, C);
    }

    public void clickNext() {
        // if complete, highlight last state
        if (this.Test.get_complete()) {

        }
        // if not complete, highlight next arrow
        else {
            Arrow next = this.Test.evaluateNext();
            highlightArrow(next);
        }
        VBoxForCanvas.getChildren().remove(0);
        VBoxForCanvas.getChildren().add(0, C);




    }

}
