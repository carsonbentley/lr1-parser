package parser;

import java.util.*;

public class States {
    private Set<State> stateSet;
    private List<State> states;
    private Map<Integer, Map<String, Integer>> transitions;  // from state -> symbol -> to state

    public States() {
        this.stateSet = new HashSet<>();
        this.states = new ArrayList<>();
        this.transitions = new HashMap<>();
    }

    public State getState(int name) {
        if (name >= states.size()) {
            return null;
        }
        return states.get(name);
    }
    /**
     * Adds a state to the collection and returns its ID
     */
    public int addState(State state) {
        // Don't add duplicate states
        int existingId = findState(state);
        if (existingId != -1) {
            return existingId;
        }

        int id = states.size();
        stateSet.add(state);
        states.add(state);
        transitions.put(id, new HashMap<>());
        return id;
    }

    /**
     * Finds a state in the collection and returns its ID, or -1 if not found
     */
    public int findState(State state) {
        for (int i = 0; i < states.size(); i++) {
            if (states.get(i).equals(state)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Adds a transition from one state to another on the given symbol
     */
    public void addTransition(int fromState, String symbol, int toState) {
        if (!transitions.containsKey(fromState)) {
            transitions.put(fromState, new HashMap<>());
        }
        transitions.get(fromState).put(symbol, toState);
    }

    /**
     * Gets the transition from a state on a symbol, or -1 if no transition exists
     */
    public int getTransition(int fromState, String symbol) {
        if (!transitions.containsKey(fromState)) {
            return -1;
        }
        Map<String, Integer> stateTransitions = transitions.get(fromState);
        return stateTransitions.getOrDefault(symbol, -1);
    }

    /**
     * Returns the number of states in the collection
     */
    public int size() {
        return states.size();
    }

    /**
     * Returns the transition table
     */
    public Map<Integer, Map<String, Integer>> getTransitions() {
        return transitions;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < states.size(); i++) {
            sb.append(i).append(": ").append(states.get(i)).append("\n");
        }
        return sb.toString();
    }
}