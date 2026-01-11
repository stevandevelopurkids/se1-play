package streams;

import java.util.List;
import java.util.Random;
import java.util.stream.Stream;
import java.util.Comparator;
import java.util.Collection;

public class StreamsImpl implements Streams {

    @Override
    public Stream<Integer> tenRandomNumbers() {
        return new Random()
                .ints(10, 0, 1000)
                .boxed();
    }

    @Override
    public Stream<Integer> tenEvenRandomNumbers() {
        return new Random().ints(0, 999).filter(n -> n % 2 == 0).limit(10).boxed();
    }

    @Override
    public Stream<Integer> tenSortedEvenRandomNumbers() {
        return new Random().ints(0, 999).filter(n -> n % 2 == 0).limit(10).sorted().boxed();
    }

    @Override
    public List<Integer> filteredNumbers(String filter, int limit) {

        if (filter == null || filter.isEmpty() || !filterFunctions.containsKey(filter)) {
            throw new IllegalArgumentException("filter null, empty or unknown: \"" + filter + "\"");
        }

        if (limit < 0) {
            throw new IllegalArgumentException("negative limit: " + limit);
        }
        return new Random()
                .ints(0, 1000) // 1. Stream von Zufallszahlen [0..999]
                .boxed() // 2. Umwandeln in Objekte
                .filter(n -> filterFunctions.get(filter).apply(n)) // 3. Dynamischen Filter aus der Map holen
                .limit(limit) // 4. Maximal so viele, wie 'limit' sagt
                .toList(); // 5. Als Liste speichern und zurückgeben
    }

    @Override
    public List<String> filteredNames(List<String> names, String regex) {
        if (names == null || regex == null) {
            throw new IllegalArgumentException("names or regex argument is null.");
        }

        return names.stream().filter(n -> n.matches(regex)) // prüft ob n zum regex passt
                .toList();
    }

    @Override
    public List<String> sortedNames(List<String> names, int limit) {

        // 1. Check: Names darf nicht null sein
        if (names == null) {
            throw new IllegalArgumentException("names argument is null.");
        }

        // 2. Check: Limit darf nicht negativ sein (0 ist okay!)
        if (limit < 0) {
            throw new IllegalArgumentException("limit argument is negative: " + limit + ".");
        }

        // 3. Logik: ERST sortieren, DANN limitieren
        return names.stream()
                .sorted()
                .limit(limit)
                .toList();
    }

    @Override
    public List<String> sortedNamesByLength(List<String> names) {
        if (names == null) {
            throw new IllegalArgumentException("names argument is null.");
        }

        return names.stream().sorted(Comparator.comparingInt(String::length).thenComparing(Comparator.naturalOrder()))
                .toList();
    }

    @Override
    public long calculateOrderValue(List<Order> orders) {

        if (orders == null) {
            throw new IllegalArgumentException("orders argument is null.");
        }
        return orders.stream().mapToLong(n -> n.units() * n.unitPrice()).sum();
    }

    @Override
    public List<Order> sortOrdersByValue(List<Order> orders) {
        if (orders == null) {
            throw new IllegalArgumentException("orders argument is null.");
        }

        return orders.stream()
                .sorted(Comparator.comparingLong((Order n) -> n.units() * n.unitPrice()).reversed()).toList();

        /*
         * Warum hält Java n plötzlich für ein Object?
         * Der Compiler arbeitet von links nach rechts und versucht, den Typ von n zu
         * bestimmen:
         * 
         * Ohne .reversed(): Java sieht .sorted(...), weiß, dass der Stream aus
         * Order-Objekten besteht, und schließt daraus:
         * "Ok, dann muss n wohl auch eine Order sein".
         * 
         * Mit .reversed(): Du rufst eine Methode auf dem Ergebnis von comparingLong
         * auf, bevor das Ganze überhaupt an .sorted() übergeben wird. In diesem Moment
         * "vergisst" Java den Zusammenhang zum Stream und fällt auf den kleinsten
         * gemeinsamen Nenner zurück: die Klasse Object.
         * 
         * Da die Klasse Object keine Methoden wie units() oder unitPrice() besitzt,
         * markiert der Compiler sie als rot.
         */
    }
}
