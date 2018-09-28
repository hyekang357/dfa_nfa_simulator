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
    public Canvas myCanvas;
    GraphicsContext GC;

    public Test1 Test;

    double DWidth = 5.0;
    Arrow PrevArrow = null;

    private void initializeCanvas() {
        GC = myCanvas.getGraphicsContext2D();
        GC.clearRect(0, 0, 918.0, 483.0);
        GC.setLineWidth(DWidth);
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
        GC.setLineWidth(DWidth);
    }
    
    private void drawArrowToItself(Arrow a) {
        int size = 100;
        GC.strokeArc(a.get_x1(), a.get_y1(), size, size, 320, 258, ArcType.OPEN);
        GC.fillPolygon(a.get_x_points(), a.get_y_points(), a.get_num_points());
        // write text
        GC.setLineWidth(1.0);
        GC.setFont(new Font("Arial", 24));
        GC.fillText(a.get_text(), a.get_x1()+(size*0.95), a.get_y1()+(size*0.2));
        GC.setLineWidth(DWidth);
    }
    
    private void drawArrowLine(Arrow a) {
        GC.strokeLine(a.get_x1(), a.get_y1(), a.get_x2(), a.get_y2());
        GC.fillPolygon(a.get_x_points(), a.get_y_points(), a.get_num_points());
        if (!a.get_text().isEmpty()) {
            GC.setLineWidth(1.0);
            GC.setFont(new Font("Arial", 24));
            if(a.get_direction() == 'R')
                GC.fillText(a.get_text(), (a.get_x1()+a.get_x2()-14)/2, ((a.get_y1()+a.get_y2())/2)-20);
            else if(a.get_direction() == 'U')
                GC.fillText(a.get_text(), (((a.get_x1()+a.get_x2())/2)-30), (a.get_y1()+a.get_y2()+10)/2);
            else if(a.get_direction() == 'D')
                GC.fillText(a.get_text(), (a.get_x1()+a.get_x2()+30)/2, ((a.get_y1()+a.get_y2())/2)+8);
            else
                GC.fillText(a.get_text(), (a.get_x1()+a.get_x2()-14)/2, ((a.get_y1()+a.get_y2())/2)+20);
            GC.setLineWidth(DWidth);
        }
    }
    
    private void highlightArrow(Arrow a) {
        //set stroke color to red
        GC.setStroke(Color.RED);
        GC.setFill(Color.RED);
        // draw arrow
        if (a.get_arc_arrow()) {
            int size = 100;
            GC.strokeArc(a.get_x1(), a.get_y1(), size, size, 320, 258, ArcType.OPEN);
            GC.fillPolygon(a.get_x_points(), a.get_y_points(), a.get_num_points());
        } else {
            GC.strokeLine(a.get_x1(), a.get_y1(), a.get_x2(), a.get_y2());
            GC.fillPolygon(a.get_x_points(), a.get_y_points(), a.get_num_points());
        }
        // reset
        GC.setStroke(Color.BLACK);
        GC.setFill(Color.BLACK);
    }

    private void removeHighlightArrow() {
        if (PrevArrow != null) {
            if (PrevArrow.get_arc_arrow()) {
                int size = 100;
                GC.strokeArc(PrevArrow.get_x1(), PrevArrow.get_y1(), size, size, 320, 258, ArcType.OPEN);
                GC.fillPolygon(PrevArrow.get_x_points(), PrevArrow.get_y_points(), PrevArrow.get_num_points());
            } else {
                GC.strokeLine(PrevArrow.get_x1(), PrevArrow.get_y1(), PrevArrow.get_x2(), PrevArrow.get_y2());
                GC.fillPolygon(PrevArrow.get_x_points(), PrevArrow.get_y_points(), PrevArrow.get_num_points());
            }
        }
    }
    
    public void drawTest1() {
        System.out.println("Drawing Test 1...");
        
        // Initialize canvas
        initializeCanvas();

        // Create states
        State q0 = new State(true, 208, 208, 100, "q0");
        State q1 = new State(true, 408, 208, 100, "q1");
        State q2 = new State(false, 608, 208, 100, "q2");

        // Create arrows
        Arrow sa1 = new Arrow(false, 100, 258, 196, 258, q0);
        Arrow sa2 = new Arrow(false, 318, 258, 396, 258, q0, q1, "1");
        Arrow aa1 = new Arrow(true, 208, 208, q0, "0");
        Arrow aa2 = new Arrow(true, 408, 208, q1, "1");
        Arrow sa3 = new Arrow(false, 516, 258, 597, 258, q1, q2, "0");
        Arrow aa3 = new Arrow(true, 608, 208, q2, "0,1");

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
        // draw q1 -> q2 arrow
        drawArrowLine(sa3);
        //q2
        drawState(q2);
        drawArrowToItself(aa3);

        // Create Test object
        ArrayList<Arrow> arrows = new ArrayList<>();
        arrows.addAll(Arrays.asList(sa1, sa2, aa1, aa2, sa3, aa3));
        ArrayList<State> states = new ArrayList<>();
        states.addAll(Arrays.asList(q0, q1, q2));

        this.Test = new Test1(arrows, states, "0");

    }
    
    public void drawTest2() {
        System.out.println("Drawing Test 2...");
        // Initialize canvas
        initializeCanvas();

        // Create states
        State q0 = new State(false, 208, 208, 100, "q0");
        State q1 = new State(true, 408, 208, 100, "q1");
        State q2 = new State(false, 408, 10, 100, "q2");
        State q3 = new State(false, 608, 208, 100, "q3");
        State q4 = new State(true, 608, 10, 100, "q4");

        // Create arrows
        Arrow sa0 = new Arrow(false, 100, 258, 196,  258, q0); //start  horizontal
        Arrow sa1 = new Arrow(false, 318, 258, 396, 258, q0, q1, "b"); // q0 -> q1 horizontal
        Arrow sa2 = new Arrow(false, 450, 200, 450, 122, q1, q2, "b"); // q1 -> q2
        Arrow sa3 = new Arrow(false, 466, 118, 466, 196, q2, q1, "b"); // q2 -> q1
        Arrow sa4 = new Arrow(false, 518, 258, 596, 258, q1, q3,"a"); // q1 -> q3 horizontal
        Arrow sa5 = new Arrow(false, 650, 200, 650, 122, q3, q4,"a"); // q3 -> q4
        Arrow sa6 = new Arrow(false, 666, 118, 666, 196, q4, q3, "a"); // q4 -> q3


        // Draw the objects
        drawArrowLine(sa0);
        
        //q0
        drawState(q0);
        drawArrowLine(sa1);
        
        //q1
        drawState(q1);
        drawArrowLine(sa2);
        drawArrowLine(sa4);
        
        //q2
        drawState(q2);
        drawArrowLine(sa3);
        
        //q3
        drawState(q3);
        drawArrowLine(sa5);
        
        //q4
        drawState(q4);
        drawArrowLine(sa6);
    }
    
    public void drawTest3() {
        
        System.out.println("Drawing Test 3...");
        
        // Initialize canvas
        initializeCanvas();

        // Create states
        State q0 = new State(false, 208, 208, 100, "q0");
        State q1 = new State(false, 408, 208, 100, "q1");
        State q2 = new State(true, 608, 208, 100, "q2");

        Arrow sa1 = new Arrow(false, 100, 258, 196, 258, q0);
        Arrow sa2 = new Arrow(false, 318, 258, 396, 258, q0, q1, "E");
        Arrow aa1 = new Arrow(true, 208, 208, q0, "0");
        Arrow aa2 = new Arrow(true, 408, 208, q1, "1");
        Arrow sa3 = new Arrow(false, 516, 258, 597, 258, q1, q2, "0");

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
        // draw q1 -> q2 arrow
        drawArrowLine(sa3);
        //q2
        drawState(q2);

    }
    
    public void clearCanvas() {
        initializeCanvas();
    }
    
    public void clickNext() {
        // if complete, highlight last state
        System.out.println("hext is clicked");
        if (this.Test.get_complete()) {

            System.out.println("COMPLETE!!");
            // Test.get_ending_state
            // highlight the ending state
        }
        // if not complete, highlight next arrow
        else {
            System.out.println("evaluating next");
            Arrow next = this.Test.evaluate_next();
            if (next != null) {
                removeHighlightArrow();
                highlightArrow(next);
                this.PrevArrow = next;
            } else {
                // at implicit reject state
            }
        }
    }
}
