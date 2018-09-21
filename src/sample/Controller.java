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

    public void drawTest1() {

        Canvas c = new Canvas(918.0, 483.0);
        GraphicsContext GC = c.getGraphicsContext2D();

        System.out.println("hello");

        GC.strokeText("TL", 0, 10);
        GC.strokeText("TR", 900, 10);
        GC.strokeText("BL", 0, 477);
        GC.strokeText("BR", 900, 477);

        GC.setLineWidth(3.0);

        GC.strokeLine(100, 258, 200, 258);
        Arrow sa1 = new Arrow(false, 200, 258);
        GC.fillPolygon(sa1.get_x_points(), sa1.get_y_points(), sa1.get_num_points());

        //q0
        GC.strokeOval(208, 208, 100, 100);
        GC.strokeOval(200, 200, 116, 116);

        GC.strokeLine(318, 258, 400, 258);
        Arrow sa2 = new Arrow(false, 400, 258);
        GC.fillPolygon(sa2.get_x_points(), sa2.get_y_points(), sa2.get_num_points());

        //q1
        GC.strokeOval(408, 208, 100, 100);
        GC.strokeOval(400, 200, 116, 116);

        GC.strokeLine(516, 258, 607, 258);
        Arrow sa3 = new Arrow(false, 607, 258);
        GC.fillPolygon(sa3.get_x_points(), sa3.get_y_points(), sa3.get_num_points());

        // q2
        GC.strokeOval(608, 208, 100, 100);

        // transition to itself
        GC.strokeArc(210, 130, 100, 100, 320, 260, ArcType.OPEN);
        Arrow aa1 = new Arrow(true, 210, 130);
        GC.fillPolygon(aa1.get_x_points(), aa1.get_y_points(), aa1.get_num_points());


        GC.strokeArc(410, 130, 100, 100, 320, 260, ArcType.OPEN);
        Arrow aa2 = new Arrow(true, 410, 130);
        GC.fillPolygon(aa2.get_x_points(), aa2.get_y_points(), aa2.get_num_points());

        GC.strokeArc(610, 140, 100, 100, 320, 260, ArcType.OPEN);
        Arrow aa3 = new Arrow(true, 610, 140);
        GC.fillPolygon(aa3.get_x_points(), aa3.get_y_points(), aa3.get_num_points());


        VBoxForCanvas.getChildren().remove(0);
        VBoxForCanvas.getChildren().add(0, c);

    }

}
