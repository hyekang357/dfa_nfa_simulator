package sample;

import java.util.ArrayList;

public class NFA extends Graph {

    public NFA(ArrayList<Arrow> arrows, ArrayList<State> states, String input) {
        this.arrows = arrows;
        this.states = states;
        this.input = input;
        this.curr_state = null;
        this.input_index = -1;
        this.complete = false;
    }

    public Arrow evaluate_next() {
        // check the current state
        // if this is the very beginning
        if (this.curr_state == null) {
            // get the starting arrow
            Arrow starting_arrow = get_starting_arrow();
            // set the curr_state
            this.curr_state = starting_arrow.get_to_state();
            System.out.println("Current state is set to: " + this.curr_state.get_text());
            // set complete
            this.complete = check_complete(this.input, this.input_index);
            this.input_index++;
            return starting_arrow;
        } else {
            // Get input at index
            String evaluating_input = Character.toString(this.input.charAt(this.input_index));
            System.out.println("Evaluating input: " + evaluating_input);
            // Get next arrow
            Arrow next_arrow = get_next_arrow(this.curr_state, evaluating_input);
            // if next_arrow is not null set curr_state, increment input_index, set_complete
            if (next_arrow != null) {
                this.curr_state = next_arrow.get_to_state();
                this.complete = check_complete(this.input, this.input_index);
                this.input_index++;
                return next_arrow;
            } else {
                // if next_arrow not found then implicit reject state
                set_implicit_reject_state();
                return null;
            }
        }
    }

    private Arrow get_next_arrow(State current_state, String evaluating_input) {
        // find the arrow that is from s, text of evaluating char
//        System.out.println("Looking for next arrow from curr_state ->" + current_state.get_text() + "<-");
//        System.out.println("    with arrow value of ->" + evaluating_input + "<-");
        for (Arrow a: this.arrows) {
            if (a.get_from_state() != null) {
//                System.out.println("checking arrow from state: ->" + a.get_from_state().get_text() + "<-");
//                System.out.println("    with arrow text: ->" + a.get_text() + "<-");
                if (a.get_from_state().get_text().equals(current_state.get_text()) && a.get_text().equals(evaluating_input)) {
                    System.out.println("found next arrow");
                    return a;
                }
            }
        }
        // Look for epsilon arrows
        for (Arrow a: this.arrows) {
            if (a.get_from_state() != null) {
                if (a.get_from_state().get_text().equals(current_state.get_text()) && a.get_text().equals("E")) {
                    System.out.println("found next epsilon arrow");
                    return a;
                }
            }
        }
        System.out.println("did not find next arrow");
        return null;
    }
}
