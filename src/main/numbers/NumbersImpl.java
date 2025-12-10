package numbers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

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

         // Optional: Fehlerbehandlung für null-Argumente
    if (numbers == null) {
        throw new IllegalArgumentException(String.format("illegal argument: %s", "null"));
    }
        // 1. Basisfall (Abbruchmechanismus): Wir sind außerhalb des Arrays
    if (i >= numbers.length) {
        // Nichts mehr zu addieren
        return 0; 
    }
    
    // 2. Rekursiver Fall:
    // Aktuellen Wert nehmen (numbers[i]) und das Ergebnis des nächsten rekursiven Aufrufs addieren
    return numbers[i] + sum_recursive(numbers, i + 1);
    }

    @Override
    public int findFirst(int[] numbers, int x) {
        if (numbers == null) {
        throw new IllegalArgumentException(String.format("illegal argument: %s", "null"));
    }
       
        for(int i = 0; i<numbers.length;i++) {
            if(numbers[i]==x){
               return i; 
            }
        }
        return -1;
        // 1. Array in einen IntStream umwandeln
   // return IntStream.range(0, numbers.length)
                    // 2. Den Stream filtern, um nur die Indizes zu behalten,
                    //    deren Wert numbers[i] gleich dem gesuchten Wert x ist
               //     .filter(i -> numbers[i] == x)
                    // 3. Den allerersten Index aus dem gefilterten Stream nehmen
                 //   .findFirst() 
                    // 4. Den gefundenen Index zurückgeben oder -1, wenn der Stream leer ist
                 //   .orElse(-1);
    }

    @Override
    public int findLast(int[] numbers, int x) {
        if (numbers == null) {
        throw new IllegalArgumentException(String.format("illegal argument: %s", "null"));
    }
        //zählt immer vorwärts
        return IntStream.range(0,numbers.length)
        // 2. Kehre die Reihenfolge um: numbers.length - 1 - i
        .map(i->numbers.length-1-i)
        // 3. Filtere den rückwärts laufenden Index
        .filter(i->numbers[i]==x)
        // 4. Da der Stream rückwärts läuft, ist das erste gefundene Element 
                    //    automatisch das LETZTE Vorkommen im ursprünglichen Array.
        .findFirst()
        //sonst
        .orElse(-1);

        //ok, also ich iteriere von dem letzten Element bis zum ersten und nehme den zuerst gefundenen

    }

    @Override
    public List<Integer> findAll(int[] numbers, int x) {
        if (numbers == null) {
        throw new IllegalArgumentException(String.format("illegal argument: %s", "null"));
    }
/* 
        //Standard-Java-Collections (wie List, Set, Map) können keine primitiven Typen speichern, sondern nur Objekte. Um die Elemente aus dem Stream in eine List<Integer> zu sammeln, musst du sie zuerst in die Objektform bringen.
        // numbers enthält primitive Datentypen, die wohl nicht in eine Collection eingefügt werden können
        //den IntStream filtern
       IntStream filteredStream = Arrays.stream(numbers)
       .filter(i-> numbers[i] == x);
       // die Indizes kommen aus dem Stream, ich gucke in filter() für jedes Einzelne, ob es gleich i ist
       //primitive Int-Werte in das Wrapper Objekt umwandeln
       Stream<Integer> objectStream = filteredStream.boxed();
       //führt den Prozess des Boxing durch. Boxing ist die Umwandlung eines primitiven Werts (z.B. int) in sein entsprechendes Wrapper-Objekt (z.B. Integer).
       List<Integer> resultList = objectStream.collect(Collectors.toList());

       return resultList;
       */
// ACHTUNG: Der Stream muss mit IntStream.range() starten, um Indizes zu liefern!
    return IntStream.range(0, numbers.length)      // 1. Stream der Indizes (0, 1, 2, ...)
                    // Jetzt ist 'i' der Index. Wir prüfen den Wert numbers[i]
                    .filter(i -> numbers[i] == x)     // 2. Nur Indizes filtern, bei denen der Wert passt
                    .boxed()                        // 3. primitive int-Indizes -> Integer-Objekte
                    .collect(Collectors.toList());    // 4. In einer Liste sammeln

    }

    @Override
    public Set<Pair> findSums(int[] numbers, int sum) {

        if (numbers == null) {
        throw new IllegalArgumentException(String.format("illegal argument: %s", "null"));
    }

        // bsp: numbers: [2,5,2,4]
        //Set ist eine Collection. eine Liste; es ist eine alternative Form der Sammlung, die sich durch das Fehlen von Duplikaten auszeichnet und die Reihenfolge ignoriert.
        // alle möglichen Paare bilden, dann gucken ob die Paare die Summe ergeben, sammeln, returnen
        //1.Paare bilden: jedes a mit jedem Möglichen b und dann jedes b mit jedem Möglichen a
        //Logik: a alle nehmen, b alle nehmen, c alle nehmen( 1Ste Schliefe geht über a,b,c: 2te Schleife noch mal über a,b,c)
        //Obwohl HashSet keine Key-Value-Sammlung ist, basiert es intern auf einer HashMap zur Speicherung. Das HashSet verwendet das zu speichernde Element selbst als Key in der internen HashMap und speichert einen Platzhalterwert als Value. Dadurch profitiert das HashSet von der extrem schnellen Suche, die die Hash-Tabelle bietet.
        Set<Numbers.Pair> pairSet = new HashSet<>();
        //Numbers.Pair, weil das Interface Numbers den record Pair enthält...ajaidohajgldhjlhj
        for(int i = 0; i<numbers.length;i++) {
            for(int x=i+1 ; x<numbers.length;x++){
            int a = numbers[i];
            int b = numbers[x];

            
            
            if(a+b == sum){
            Numbers.Pair pair = new Numbers.Pair(a, b);
            pairSet.add(pair);
            }
        }
        
    }
    return pairSet;
    /* 
    Set<Pair> filteredPairSet = new HashSet<>();
    //jetzt iwie den Set filtern? Das HashSet ist die Implementierung, die automatisch dafür sorgt, dass keine Duplikate gespeichert werden.
      for(Numbers.Pair pair: pairSet){
           if(sum == pair.a()+pair.b()){
             filteredPairSet.add(pair);
           }
      }
      return filteredPairSet;
    */}

    @Override
    public Set<Set<Integer>> findAllSums(int[] numbers, int sum) {
        if (numbers == null) {
        throw new IllegalArgumentException(String.format("illegal argument: %s", "null"));
    }

 // Das Set für alle gefundenen Lösungen (Endergebnis)
    Set<Set<Integer>> allSolutions = new HashSet<>();
    
    // Das Set für die aktuelle Kombination, die wir gerade aufbauen
    Set<Integer> currentSubset = new HashSet<>();

    // Starte die rekursive Suche bei Index 0
    findAllSumsRecursive(numbers, sum, 0, currentSubset, allSolutions);

    return allSolutions;
}

    private void findAllSumsRecursive(int[] numbers, 
                                  int targetSum, 
                                  int index, 
                                  Set<Integer> currentSubset, 
                                  Set<Set<Integer>> allSolutions) {
    
    // ----------------------------------------------------
    // 1. BASISTÄLLE (Abbruchbedingungen)
    // ----------------------------------------------------
    
    // A. Erfolgsfall: Summe wurde exakt erreicht.
    if (targetSum == 0) {
        // Füge eine KOPIE der aktuellen Lösung zum Endergebnis hinzu!
        // (Wichtig: Wir speichern ein neues HashSet, da sich currentSubset noch ändert)
        allSolutions.add(new HashSet<>(currentSubset));
        return;
    }

    // B. Misserfolgsfall: Entweder Summe ist unterschritten oder Array ist durchsucht.
    if (index >= numbers.length || targetSum < 0) {
        return;
    }
    
    // ----------------------------------------------------
    // 2. REKURSIVER SCHRITT: ELEMENTE NEHMEN ODER ÜBERSPRINGEN
    // ----------------------------------------------------
    
    int currentNumber = numbers[index];
    
    // ----------------------------------------------------
    // Entscheidungsbaum-Pfad 1: ELEMENT NEHMEN ("Take")
    // ----------------------------------------------------
    
    // Füge die aktuelle Zahl zur Kombination hinzu
    currentSubset.add(currentNumber);
    
    // Rekursiver Aufruf: Gehe zum nächsten Index (index + 1) und reduziere die Summe
    findAllSumsRecursive(numbers, targetSum - currentNumber, index + 1, currentSubset, allSolutions);
    
    // ----------------------------------------------------
    // Entscheidungsbaum-Pfad 2: BACKTRACKING (Aufräumen)
    // ----------------------------------------------------
    
    // Mache die Änderung von Pfad 1 rückgängig (Backtracking),
    // damit der nächste Pfad (Pfad 3: NICHT NEHMEN) mit einem sauberen Subset arbeiten kann.
    currentSubset.remove(currentNumber);
    
    // ----------------------------------------------------
    // Entscheidungsbaum-Pfad 3: ELEMENT NICHT NEHMEN ("Skip")
    // ----------------------------------------------------
    
    // Rekursiver Aufruf: Gehe zum nächsten Index (index + 1), Summe bleibt gleich.
    // Das Element wurde bereits oben aus currentSubset entfernt.
    findAllSumsRecursive(numbers, targetSum, index + 1, currentSubset, allSolutions);


}
}
