package numbers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

class NumbersImpl implements Numbers {

    // TEIL 1: Der Speicherplatz
    // Hier merken wir uns das EINE Objekt. "static" heißt: Es gehört zur Klasse,
    // nicht zu Instanzen.
    private static NumbersImpl instance;

    // TEIL 2: Der Türsteher (Der private Konstruktor)
    // "private" heißt: Niemand von außen darf "new NumbersImpl()" machen.
    // Wir verbieten die normale Objekterzeugung!
    private NumbersImpl() {
    }

    // TEIL 3: Der Zugang (Die Lazy Logic)
    public static NumbersImpl getInstance() {
        // "LAZY": Wir prüfen erst: Gibt es das Objekt schon?
        if (instance == null) {
            // Wenn nein: Wir erstellen es JETZT (und nur dieses eine Mal).
            instance = new NumbersImpl();
        }
        // Wenn es schon da war (oder gerade erstellt wurde): Gib es zurück.
        return instance;
    }
    // ...

    @Override
    public long sum(int[] numbers) {

        if (numbers == null) {
            throw new IllegalArgumentException(String.format("illegal argument: %s", numbers));
        }
        long result = 0;
        // der index kann ja int sein, das ist egal
        for (int x = 0; x < numbers.length; x++) {
            result += numbers[x];
        }
        return result;
        //return Arrays.stream(numbers).asLongStream().sum(); 
    }

    @Override
    public long sum_positive_even_numbers(int[] numbers) {

        if(numbers == null) {
            throw new IllegalArgumentException(String.format("illegal argument: %s", "null"));
        }
        List<Integer> positiveEvenNumbers = new ArrayList<>(); ; 
        for(int x = 0; x<numbers.length; x++) {
            if( numbers[x]%2 == 0 && numbers[x]>0 ){
              positiveEvenNumbers.add(numbers[x]);
            }
        }
        return positiveEvenNumbers.stream()
                           .mapToLong(i -> i) // Konvertiere Integer zu long
                           .sum();

                //long sum = Arrays.stream(numbers) // Startet einen IntStream
                //.filter(n -> n > 0 && n % 2 == 0) // Filtern der Elemente
                //.mapToLong(n -> n) // Wichtig für die große Summe: Konvertiere zu LongStream
                //.sum(); // Berechne die Summe

    }

    @Override
    public long sum_recursive(int[] numbers, int i) {
        
    }

    @Override
    public int findFirst(int[] numbers, int x) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findFirst'");
    }

    @Override
    public int findLast(int[] numbers, int x) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findLast'");
    }

    @Override
    public List<Integer> findAll(int[] numbers, int x) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findAll'");
    }

    @Override
    public Set<Pair> findSums(int[] numbers, int sum) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findSums'");
    }

    @Override
    public Set<Set<Integer>> findAllSums(int[] numbers, int sum) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findAllSums'");
    }
}
