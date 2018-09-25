package sample;

import java.util.ArrayList;

public class Test1 {
    String curr_state;
    int index;
    boolean complete;

    ArrayList<Arrow> arrows;
    ArrayList<State> states;
    String input;

    public Test1(ArrayList<Arrow> arrows, ArrayList<State> states, String input) {
        this.arrows = arrows;
        this.states = states;
        this.input = input;

        this.curr_state = "";
        this.index = 0;
        this.complete = false;
    }

    public Arrow evaluateNext() {
        // check the current state
        if (this.curr_state.isEmpty()) {
            // get the starting arrow
            set_next_state();
            return get_starting_arrow();
        } else {
            // get the next input

            return arrows.get(0);
        }
    }

    public boolean get_complete() {
        return this.complete;
    }

    private void set_next_state() {
        for(Arrow a: this.arrows) {
            if (a.from_state == "start") {
                this.curr_state = a.get_to_state();
            }
        }
    }

    private Arrow get_starting_arrow() {
        for(Arrow a: this.arrows) {
            if (a.from_state == "start") {
                System.out.println("found start");
                return a;
            }
        }
        return this.arrows.get(0);
    }


}
