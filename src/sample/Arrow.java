package sample;

public class Arrow {

    double[] x_points;
    double[] y_points;
    final int num_points = 3;

    /*
    * boolean arc_arrow = true if the arrow goes at the end of an arc-line
    *                     false if the arrow goes at the end of the straight line
    * int x = x used to draw arc-line; if straight line use x2
    * int y = y used to draw arc-line; if straight line use y2
    *
    */
    public Arrow(boolean arc_arrow, int x, int y){
        if (arc_arrow) {
            this.set_x_points_arc(x);
            this.set_y_points_arc(y);
        } else {
            this.set_x_points_line(x);
            this.set_y_points_line(y);
        }
    }

    private void set_x_points_arc(int x) {
        this.x_points = new double[]{x+104, x+86, x+86};
    }

    private void set_y_points_arc(int y) {
        this.y_points = new double[]{y+78, y+85, y+64};
    }

    private void set_x_points_line(int x) {
        this.x_points = new double[]{x, x-15, x-15};
    }

    private void set_y_points_line(int y) {
        this.y_points = new double[]{y, y-10, y+10};
    }

    public double[] get_x_points() {
        return this.x_points;
    }

    public double[] get_y_points() {
        return this.y_points;
    }

    public int get_num_points() {
        return this.num_points;
    }

}
