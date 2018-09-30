package sample;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.TextArea;
import javafx.scene.shape.ArcType;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import java.util.Arrays;
import java.util.ArrayList;

public class Controller {
    
    public TextArea TextAreaInput;
    public TextArea TextAreaOutput;
    public Canvas myCanvas;
    GraphicsContext GC;
    final double DWidth = 5.0;

    public Graph Test;
    Arrow PrevArrow;

    private void initializeCanvas() {
        GC = myCanvas.getGraphicsContext2D();
        GC.clearRect(0, 0, 918.0, 483.0);
        GC.setLineWidth(DWidth);
        GC.setFont(new Font(16));
        TextAreaOutput.setFont(new Font("Arial", 24));
        TextAreaOutput.setText("");

        Test = null;
        PrevArrow = null;
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

        int x = ((int) (GC.getCanvas().getWidth() / 2)) - 300;
        int y = 0;
        
        // Create states
        State q0 = new State(true, 208 + x, 208 + y, 100, "q0");
        State q1 = new State(true, 408 + x, 208 + y, 100, "q1");

        // Create arrows
        Arrow a0 = new Arrow(false, 100 + x, 258 + y, 196 + x, 258 + y, q0);
        Arrow a1 = new Arrow(true, 208 + x, 208 + y, q0, "0");
        Arrow a2 = new Arrow(false, 318 + x, 258 + y, 396 + x, 258 + y, q0, q1, "1");
        Arrow a3 = new Arrow(true, 408 + x, 208 + y, q1, "1");

        // Draw the objects
        drawArrowLine(a0);
        //q0
        drawState(q0);
        drawArrowToItself(a1);
        // draw q0 -> q1 arrow
        drawArrowLine(a2);
        //q1
        drawState(q1);
        drawArrowToItself(a3);

        // Create Test object
        ArrayList<Arrow> arrows = new ArrayList<>();
        arrows.addAll(Arrays.asList(a0, a1, a2, a3));
        ArrayList<State> states = new ArrayList<>();
        states.addAll(Arrays.asList(q0, q1));

        // Get input from input box
        String input = TextAreaInput.getText();
        System.out.println("Testing Test1 with given input: " + input);

        // Create test object
        this.Test = new DFA(arrows, states, input);

    }
    
    public void drawTest2() {
        System.out.println("Drawing Test 2...");
        // Initialize canvas
        initializeCanvas();

        int x = ((int) (GC.getCanvas().getWidth() / 2)) - 400;
        int y = ((int) (GC.getCanvas().getHeight() / 2)) - 165;
        
        // Create states
        State q0 = new State(false, 208 + x, 208 + y, 100, "q0");
        State q1 = new State(true, 408 + x, 208 + y, 100, "q1");
        State q2 = new State(false, 408 + x, 10 + y, 100, "q2");
        State q3 = new State(false, 608 + x, 208 + y, 100, "q3");
        State q4 = new State(true, 608 + x, 10 + y, 100, "q4");

        // Create arrows
        Arrow a0 = new Arrow(false, 100 + x, 258 + y, 196 + x, 258 + y, q0); //start  horizontal
        Arrow a1 = new Arrow(false, 318 + x, 258 + y, 396 + x, 258 + y, q0, q1, "b"); // q0 -> q1 horizontal
        Arrow a2 = new Arrow(false, 450 + x, 200 + y, 450 + x, 122 + y, q1, q2, "b"); // q1 -> q2
        Arrow a3 = new Arrow(false, 466 + x, 118 + y, 466 + x, 196 + y, q2, q1, "b"); // q2 -> q1
        Arrow a4 = new Arrow(false, 518 + x, 258 + y, 596 + x, 258 + y, q1, q3,"a"); // q1 -> q3 horizontal
        Arrow a5 = new Arrow(false, 650 + x, 200 + y, 650 + x, 122 + y, q3, q4,"a"); // q3 -> q4
        Arrow a6 = new Arrow(false, 666 + x, 118 + y, 666 + x, 196 + y, q4, q3, "a"); // q4 -> q3


        // Draw the objects
        drawArrowLine(a0);
        
        //q0
        drawState(q0);
        drawArrowLine(a1);
        
        //q1
        drawState(q1);
        drawArrowLine(a2);
        drawArrowLine(a4);
        
        //q2
        drawState(q2);
        drawArrowLine(a3);
        
        //q3
        drawState(q3);
        drawArrowLine(a5);
        
        //q4
        drawState(q4);
        drawArrowLine(a6);

        // Create Test object
        ArrayList<Arrow> arrows = new ArrayList<>();
        arrows.addAll(Arrays.asList(a0, a1, a2, a3, a4, a5, a6));
        ArrayList<State> states = new ArrayList<>();
        states.addAll(Arrays.asList(q0, q1, q2, q3, q4));

        // Get input from input box
        String input = TextAreaInput.getText();
        System.out.println("Testing Test2 with given input: " + input);

        this.Test = new DFA(arrows, states, input);
    }
    
    public void drawTest3() {
        
        System.out.println("Drawing Test 3...");
        
        // Initialize canvas
        initializeCanvas();

        int x = 50;
        int y = 0;
        
        // Create states
        State q0 = new State(false, 208 + x, 208 + y, 100, "q0");
        State q1 = new State(false, 408 + x, 208 + y, 100, "q1");
        State q2 = new State(true, 608 + x, 208 + y, 100, "q2");

        Arrow a0 = new Arrow(false, 100 + x, 258 + y, 196 + x, 258 + y, q0);
        Arrow a1 = new Arrow(true, 208 + x, 208 + y, q0, "0");
        Arrow a2 = new Arrow(false, 318 + x, 258 + y, 396 + x, 258 + y, q0, q1, "E");
        Arrow a3 = new Arrow(true, 408 + x, 208 + y, q1, "1");
        Arrow a4 = new Arrow(false, 516 + x, 258 + y, 597 + x, 258 + y, q1, q2, "0");

        // Draw the objects
        drawArrowLine(a0);
        //q0
        drawState(q0);
        drawArrowToItself(a1);
        // draw q0 -> q1 arrow
        drawArrowLine(a2);
        //q1
        drawState(q1);
        drawArrowToItself(a3);
        // draw q1 -> q2 arrow
        drawArrowLine(a4);
        //q2
        drawState(q2);

        // Create Test object
        ArrayList<Arrow> arrows = new ArrayList<>();
        arrows.addAll(Arrays.asList(a0, a1, a2, a3, a4));
        ArrayList<State> states = new ArrayList<>();
        states.addAll(Arrays.asList(q0, q1, q2));

        // Get input from input box
        String input = TextAreaInput.getText();
        System.out.println("Testing Test3 with given input: " + input);

        this.Test = new NFA(arrows, states, input);

    }
    
    public void clearCanvas() {
        initializeCanvas();
    }
    
    public void clickNext() {
        // if complete, highlight last state
        System.out.println("hext is clicked");
        if (this.Test != null) {
            if (this.Test.get_complete()) {
                System.out.println("COMPLETE!!");
                removeHighlightArrow();
                State ending_state = this.Test.get_ending_state();
                if (ending_state == null) {
                    TextAreaOutput.setText("Ending at implicit reject state\n" + TextAreaOutput.getText());
                } else {
                    TextAreaOutput.setText("Ending state: " + ending_state.get_text() + "\n" + TextAreaOutput.getText());
                    // TODO: highlight the ending state
                }
                TextAreaOutput.setText("Complete\n" + TextAreaOutput.getText());
            }
            // if not complete, highlight next arrow
            else {
                System.out.println("evaluating next");
                Arrow next = this.Test.evaluate_next();
                if (next != null) {
                    removeHighlightArrow();
                    highlightArrow(next);
                    TextAreaOutput.setText(this.Test.get_output_text());
                    this.PrevArrow = next;
                } else {
                    // at implicit reject state
                    removeHighlightArrow();
                    TextAreaOutput.setText(this.Test.get_output_text());
                    this.PrevArrow = null;
                }
            }
        }
    }
}
