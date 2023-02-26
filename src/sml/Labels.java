package sml;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

// TODO: write a JavaDoc for the class

/**
 * Represents an identifier for an instruction.
 *</p>
 * The identifier is optional in an instruction line.
 */
public final class Labels {
	private final Map<String, Integer> labels = new HashMap<>();

	/**
	 * Adds a label with the associated address to the map.
	 *
	 * @param label the label
	 * @param address the address the label refers to
	 */
	public void addLabel(String label, int address) throws NullPointerException, IllegalArgumentException {
		Objects.requireNonNull(label, "Null not allowed");
		// TODO: Add a check that there are no label duplicates.
		if (labels.containsKey(label)) {
			throw new IllegalArgumentException("Duplicate label found: " + label + ". " + "Check input file.");
		} else {
			labels.put(label, address);
		}
	}

	/**
	 * Returns the address associated with the label.
	 *
	 * @param label the label
	 * @return the address the label refers to
	 */
	public int getAddress(String label) throws IllegalArgumentException {
		// TODO: Where can NullPointerException be thrown here?
		//       (Write an explanation.)
		// A NullPointerException can occur if specified key is null and map does not permit null keys (see addLabel method)
		// Add code to deal with non-existent labels.
		if (label == null) {
			throw new IllegalArgumentException("Label is null");
		} else {
			return labels.get(label);
		}
	}

	/**
	 * representation of this instance,
	 * in the form "[label -> address, label -> address, ..., label -> address]"
	 *
	 * @return the string representation of the labels map
	 */
	@Override
	public String toString() {
		// TODO: Implement the method using the Stream API (see also class Registers).
		return labels.entrySet().stream()
				.sorted(Map.Entry.comparingByKey())
				.map(e -> e.getKey() + " = " + e.getValue())
				.collect(Collectors.joining(", ", "[", "]")) ;
	}

	// TODO: Implement equals and hashCode (needed in class Machine).


	@Override
	public boolean equals(Object o) {

		if (o instanceof Labels other) {
			return labels.equals(other.labels);
		}
		return false;
	}

	@Override
	public int hashCode() {
		return Objects.hash(labels);
	}

	/**
	 * Removes the labels
	 */
	public void reset() {
		labels.clear();
	}
}
