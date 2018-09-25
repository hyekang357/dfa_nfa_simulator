package sample;

public class Arrow {

        int x1;
        int x2;
        int y1;
        int y2;
        String text;

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
    public Arrow(boolean arc_arrow, int x1, int y1, int x2, int y2){
            this.x1 = x1;
            this.x2 = x2;
            this.y1 = y1;
            this.y2 = y2;

            if (arc_arrow) {
                this.set_x_points_arc(this.x2);
                this.set_y_points_arc(this.y2);
            } else {
                this.set_x_points_line(this.x2);
                this.set_y_points_line(this.y2);
            }
            this.text = "";
    }

    public Arrow(boolean arc_arrow, int x1, int y1, int x2, int y2, String text){
        this.x1 = x1;
        this.x2 = x2;
        this.y1 = y1;
        this.y2 = y2;

        if (arc_arrow) {
            this.set_x_points_arc(this.x2);
            this.set_y_points_arc(this.y2);
        } else {
            this.set_x_points_line(this.x2);
            this.set_y_points_line(this.y2);
        }
        this.text = text;
    }

    public Arrow(boolean arc_arrow, int x1, int y1, String text){
        this.x1 = x1+5;
        this.y1 = y1-78;

        if (arc_arrow) {
            this.set_x_points_arc(this.x1);
            this.set_y_points_arc(this.y1);
        } else {
            this.set_x_points_line(this.x1);
            this.set_y_points_line(this.y1);
        }
        this.text = text;
    }

    private void set_x_points_arc(int x) {
        this.x_points = new double[]{x+104, x+86, x+86};
    }

    private void set_y_points_arc(int y) {
        this.y_points = new double[]{y+78, y+85, y+64};
    }

    private void set_x_points_line(int x) {
        this.x_points = new double[]{x+4, x-12, x-12};
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

    public int get_x1() {
        return this.x1;
    }

    public int get_y1() {
        return this.y1;
    }

    public int get_x2() {
        return this.x2;
    }

    public int get_y2() {
        return this.y2;
    }

    public String get_text() {
        return this.text;
    }


}
