<!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -->
<!-- B2 (SE-1)
-->
# B2: *se1-play*, branch *b2-streams*

<!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -->

Assignment *b2-streams* demonstrates the use of the
[*Java Streams API*](https://docs.oracle.com/en/java/javase/23/docs/api/java.base/java/util/stream/Stream.html).
Code of this assignment is isolated from the other developments
in the project in branches *main* and *b1-numbers* of the
[*se1-play*](../../tree/main) project.

Steps:

1. [Introduction to the Java *Streams API*](#1-introduction-to-the-java-streams-api)

1. [Branch Structure](#2-branch-structure)

1. [Branch *Setup*](#3-branch-setup)

1. [*Build* and *Run*](#4-build-and-run)

1. [The *Stream* - Interface](#5-the-stream---interface)

1. [Implement: *tenRandomNumbers()*](#6-implement-tenrandomnumbers)

1. [Implement Remaining *Streams*-Functions](#7-implement-remaining-streams-functions)

1. [Final Tests](#8-final-tests)

1. [Release](#9-release)


<!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -->

&nbsp;

## 1. Introduction to the Java *Streams API*

The
[*Java Streams API*](https://docs.oracle.com/en/java/javase/23/docs/api/java.base/java/util/stream/Stream.html)
has been introduced with Java version 8 (2014) to support *data-streams* and *stream-based programming*.

A `Stream` consists of three parts:

1. A streams starts with a `Source` from where data originates or is emitted,

    - e.g. a *Collection* (List, Array, ...), a *Range* or a *Supplier*.

1. A sequence of *chained*
    [*functions*](https://docs.oracle.com/en/java/javase/25/docs/api/java.base/java/util/stream/Stream.html)
    that is applied to each data object passing through the stream,

    - examples: *map()*, *filter()*, *findAny()*, *sorted()*, etc.

1. A `Sink` that *pulls data* from the stream producing a *result* by applying a *terminal*
    [*function*](https://docs.oracle.com/en/java/javase/25/docs/api/java.base/java/util/stream/Stream.html)

    - such as *reduce()*, *sum()*, *collect()*, *forEach()*.

<img src="https://s1.o7planning.com/web-rs/web-image/en/arf-1189995-vi.webp" width="600"/>


<!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -->

&nbsp;

## 2. Branch Structure

This assignment will use a separate branch [*b2-streams*](../../tree/b2-streams)
starting from the same *base* commit.

The commit graph will have three branches:

<img src="https://raw.githubusercontent.com/sgra64/se1-play/refs/heads/markup/streams/git-1.png" width="600"/>

Switch to the main branch and show the commit log:

```sh
git switch main                     # switch to branch 'main'

git log --oneline                   # show current commits on branch 'main'
```
```
772bc52 (HEAD -> main, tag: base) branch commit (empty)     <-- base commit
ef51f55 add junit tests                                     <-- commit 5
ff4e2b0 add src                                             <-- commit 4
e7f3fa5 add .gitmodules                                     <-- commit 3
9988b69 add .gitignore                                      <-- commit 2
e38d285 (tag: root) root commit (empty)                     <-- commit 1 (empty root commit)
```

Create a new branch: `b2-streams` and switch to the new branch.


<!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -->

&nbsp;

## 3. Branch *Setup*

The structure of the project directory ("*working tree*") on this branch is:

```sh
<se1-play>              # project directory
 |
 # content of branch: 'b2-streams' with new package 'streams'
 +-<src>
 |  +-<main>                    # Java source code
 |  |  +--module-info.java          # description of module 'se1.play'
 |  |  |
 |  |  +-<application>              # existing package 'application'
 |  |  |  +--Application.java       # program with main()-method
 |  |  |  +--Runner.java            # new interface
 |  |  |  +--...
 |  |  |
 |  |  +-<streams>                 # new package 'streams' from remote branch 'b2-streams'
 |  |    +--Streams.java           # interface with methods to implement
 |  |    +--StreamsRunner.java     # driver code to run the application from the command line
 |  |    +--package-info.java      # package documentation
 |  | 
 |  |+-<tests>                  # 'streams' test code
 |  |  +-<streams>                  # package 'streams' with unit tests
 |  |     +--Streams_1_tenRandomNumbers_Tests.java
 |  |     +--Streams_2_tenEvenRandomNumbers_Tests.java
 |  |     +--Streams_3_tenSortedEvenRandomNumbers_Tests.java
 |  |     +--Streams_4_filteredNumbers_Tests.java
 |  |     +--Streams_5_filteredNames_Tests.java
 |  |     +--Streams_6_sortedNames_Tests.java
 |  |     +--Streams_7_sortedNamesByLength_Tests.java
 |  |     +--Streams_8_calculateOrderValue_Tests.java
 |  |     +--Streams_9_sortByOrderValue_Tests.java
 |  |
 |  +-<resources>               # none-Java sources, properties files
 |     +--application.properties    # application configuration
 |     +--log4j2.properties         # logger configuration
 |     +-<META-INF>
 |        +--MANIFEST.MF            # packaging information for created .jar
```

Test that the URL of the remote repository has been set:

```sh
git remote -v
```
```
se1-repo   https://github.com/sgra64/se1-play.git (fetch)
se1-repo   https://github.com/sgra64/se1-play.git (push)
```

If the URL is not present, set the URL of the remote repository:

```sh
# set URL to repository to fetch remote branches
git remote add se1-repo https://github.com/sgra64/se1-play.git
```

Two methods exist to pull content from the remote branch *b2-streams*:

- `fetch`, `merge` and `commit` or

- `pull` and `commit` ("*pull*" combines "*fetch*" and "*merge*").

Choose one method to obtain content from the remote branch *b2-streams*
and make it available in the new local branch: `b2-streams`.

```sh
# fetch branch 'b1-numbers' from the remote repository 'se1-play-repo'
git fetch se1-repo b2-streams

# merge content of branch 'b2-streams' into the 'main' branch of the project with:
# '--squash' combine all incoming commits into one local commit
# '--allow-unrelated-histories' allows merging from a repository with no shared history
# '--strategy-option theirs' resolves merge conflicts favoring incoming changes
# 
git merge se1-repo/b2-streams \
    --squash \
    --allow-unrelated-histories \
    --strategy-option theirs
```

Or *"pull"* content (*fetch* and *merge*) from the remote branch. Mind the
similarity to the prior method:

```sh
# pull remote branch 'b2-streams'
git pull se1-repo b2-streams \
    --squash --allow-unrelated-histories --strategy-option theirs
```
```
From github.com:sgra64/se1-play
 * branch            b2-streams -> FETCH_HEAD
Auto-merging src/main/application/Application.java
Squash commit -- not updating HEAD
Automatic merge went well; stopped before committing as requested
```

In both cases, the *merge* is *open* with uncommitted changes:

```sh
git status                      # show status of open merge
```

*Git* shows new or modified files with green lines (staged) that have
not yet been committed:

<img src="https://raw.githubusercontent.com/sgra64/se1-play/refs/heads/markup/streams/git-log-post-merge.png" width="600"/>


The *merge* can now be committed:

```sh
# commit the open merge
git commit -m "pull branch se1-repo/b2-streams"
```
```
[b2-streams 50afb96] pull branch se1-play-repo/b2-streams
 5 files changed, 336 insertions(+), 5 deletions(-)
 create mode 100644 src/application/Runner.java
 create mode 100644 src/streams/Streams.java
 create mode 100644 src/streams/StreamsRunner.java
 create mode 100644 src/streams/package-info.java
```


<!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -->

&nbsp;

## 4. *Build* and *Run*

*Build* the project and *run* the program:

```sh
mk compile run                  # build and run the program
```
```
Hello, se1_play (modular)
Exception in thread "main" java.lang.UnsupportedOperationException: Unimplemente
d method 'getInstance()' in interface 'Streams'. Create an implementation class
and return.
        at se1_play/streams.Streams.getInstance(Streams.java:157)
        at se1_play/application.Application.main(Application.java:27)
```

Solve the problem. Consider how the problem was solved in
[*Step 3*](../../tree/b1-numbers?tab=readme-ov-file#4-build-and-run-the-project)
of the
[*B1 Numbers*](../../tree/b1-numbers) assignment.

*Build* and *run* the program again:

```sh
mk compile run                  # re-build and run the program
```
```
Hello, se1.play (modular)
```

Commit the state of the project with:

- commit message: `"add implementation class StreamsImpl.java"`

```sh
git log --oneline               # show commit log
```

Branch *b2-streams* has advanced showing the new commit:

<img src="https://raw.githubusercontent.com/sgra64/se1-play/refs/heads/markup/streams/git-log-post-streamsimpl-added.png" width="600"/>

<!-- 
```
812dd09 (HEAD -> b1) add implementation class StreamsImpl.java
e58c38a pull branch se1-repo/b2-streams
1e53db5 (tag: base, main) add src/tests, update src/main/module-info.java
3c9b586 add src/resources
d24d184 add src/main
a8f215c add .gitmodules
15d3c87 add .gitignore
cbc8dc0 (tag: root) root commit (empty)
```
-->


<!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -->

&nbsp;

## 5. The *Stream* - Interface

Interface [*src/streams/Streams.java*](../../tree/b2-streams/src/streams/Streams.java)
defines methods to implement in this assignment using the *Java Stream API*:

```java
package streams;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Stream;

import application.Runner;

/**
 * Public interface with functions for the <i>"b2-streams"</i> assignment.
 * 
 * @version <code style=color:green>{@value application.package_info#Version}</code>
 * @author <code style=color:blue>{@value application.package_info#Author}</code>
 */
public interface Streams {

    /**
     * Aufgabe 1: Return 10 random integer numbers in the range [0..999].
     * @return a {@code Stream<Integer>} from which 10 random numbers can be drawn
     */
    Stream<Integer> tenRandomNumbers();

    /**
     * Aufgabe 2: Return 10 even random integer numbers in the range [0..999].
     * @return a {@code Stream<Integer>} from which 10 even random numbers can be drawn
     */
    Stream<Integer> tenEvenRandomNumbers();

    /**
     * Aufgabe 3: Return 10 even sorted random integer numbers in the range [0..999].
     * @return a {@code Stream<Integer>} from which 10 sorted even random numbers can be drawn
     */
    Stream<Integer> tenSortedEvenRandomNumbers();

    /**
     * Map of filter functions for filteredNumbers().
     * 
     * Add a function for name "prime3" to {@link filterFunctions} that returns
     * true for three-digit prime numbers.
     */
    static Map<String, Function<Integer, Boolean>> filterFunctions = Map.of(
        "even", n -> n % 2 == 0,    // filter even numbers
        "div3", n -> n % 3 == 0,    // filter numbers divisible by three
        "prime3", n -> true         // add: filter for three-digit prime numbers
    );

    /**
     * Aufgabe 4: Apply a function from map {@link filterFunctions} to a stream
     * of random integer numbers in the range [0..999] returning only numbers
     * matching the selected filter.
     * @param filter name of the filter function in {@link filterFunctions}
     * @param limit maximum amount of numbers returned
     * @return numbers matching the selected filter
     */
    List<Integer> filteredNumbers(String filter, int limit);


    /*
     * Names used in methods below.
     */
    static final List<String> names = List.of(
        "Hendricks", "Raymond", "Pena", "Gonzalez", "Nielsen", "Hamilton",
        "Graham", "Gill", "Vance", "Howe", "Ray", "Talley", "Brock", "Hall",
        "Gomez", "Bernard", "Witt", "Joyner", "Rutledge", "Petty", "Strong",
        "Soto", "Duncan", "Lott", "Case", "Richardson", "Crane", "Cleveland",
        "Casey", "Buckner", "Hardin", "Marquez", "Navarro"
    );

    /**
     * Aufgabe 5: Return a sub-list of names filtered by a regular expression
     * (see: {@link java.util.regex.Pattern}). The order of names remains unchanged.
     * @param names input names
     * @param regex regular expression according to {@link java.util.regex.Pattern}
     * @return list of names matching the regular expression
     */
    List<String> filteredNames(List<String> names, String regex);

    /**
     * Aufgabe 6: Return names alphabetically sorted up to a given limit.
     * @param names input names
     * @param limit maximum number of names returned
     * @return alphabetically sorted list of names up to the given limit
     */
    List<String> sortedNames(List<String> names, int limit);

    /**
     * Aufgabe 7: Return names sorted by name length as first criteria and
     * within same-length names alphabetically sorted as second criteria.
     * @param names input names
     * @return names sorted by name length
     */
    List<String> sortedNamesByLength(List<String> names);


    /**
     * Aufgabe 8: Class {@link Order} defines an order (Bestellung) of
     * n (units) of an article at a price per unit (in Cent).
     */
    class Order {
        private final String article;
        private final long units;
        private final long unitPrice;
        //
        public Order(String description, long units, long unitPrice) {
            this.article = description;
            this.units = units;
            this.unitPrice = unitPrice;
        }

        // getter methods
        public String article() { return article; }

        public long units() { return units; }

        public long unitPrice() { return unitPrice; }

        // text conversion method
        public String toString() {
            return String.format("%-7s %dx %4d = %6d", article + ",", units, unitPrice, units * unitPrice);
        }
    }

    /*
     * Orders used in methods below.
     */
    static final List<Order> orders = List.of(
        new Order("Becher", 2,  199),   // 2x  199 =  398
        new Order("Tasse",  7,  249),   // 7x  249 = 1743
        new Order("Stift",  4,   49),   // 4x   49 =  196
        new Order("Vase",   2,  999),   // 2x  999 = 1998
        new Order("Kanne",  5, 1499),   // 5x 1499 = 7495
        new Order("Lampe",  2, 1999),   // 2x 1999 = 3998
        new Order("Messer", 6,  789)    // 6x  789 = 4734
    );                                  // Summe:   20562 = 205,62€

    /**
     * Aufgabe 8: Calculate the total value of all orders.
     * @param orders list of orders to process
     * @return total value of orders
     */
    long calculateOrderValue(List<Order> orders);

    /**
     * Aufgabe 9: Return a list of orders sorted by order value (highest-value first).
     * @param orders list of orders to sort
     * @return orders sorted by order value (highest-value first)
     */
    List<Order> sortOrdersByValue(List<Order> orders);

    /**
     * Static getter method that returns an instance of an implementation class of
     * the {@link Streams} interface.
     * @return instance of an implementation class of the {@link Streams} interface
     */
    static Streams getInstance() {
        throw new UnsupportedOperationException("Unimplemented method 'getInstance()' "
            + "in interface 'Streams'. Create an implementation class and return.");
        // return new StreamsImpl();
    }

    /**
     * Factory method that creates instance of the {@link Runner} interface.
     * @param streams instance of the {@link Streams} interface used by the runner
     * @return instance of the {@link Runner} interface
     */
    static Runner createRunner(Streams streams) {
        return new StreamsRunner(streams);
    }
}
```


<!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -->

&nbsp;

## 6. Implement: *tenRandomNumbers()*

Implement the first method: *tenRandomNumbers()* in your implementation class.
The method should create a *Stream source* that generates a random number in
the range `[0..1000]` at each invocation with a limit of 10 numbers.

Rebuild and try the implementation:

```sh
mk compile run tenRandomNumbers
```
```
Hello, se1.play (modular)
 - tenRandomNumbers() -> [275, 24, 206, 757, 283, 103, 180, 863, 975, 659]
```

Run with multiple function calls:

```sh
mk run tenRandomNumbers \
    tenRandomNumbers \
    tenRandomNumbers \
    tenRandomNumbers
```

Output will produce 4 sets of 10 random numbers in the range `[0..1000]`:

```
Hello, se1.play (modular)
 - tenRandomNumbers() -> [617, 546, 22, 470, 81, 796, 575, 124, 723, 312]
 - tenRandomNumbers() -> [274, 356, 844, 854, 502, 563, 29, 141, 310, 186]
 - tenRandomNumbers() -> [70, 994, 376, 664, 752, 719, 958, 415, 611, 899]
 - tenRandomNumbers() -> [178, 437, 686, 299, 199, 761, 28, 221, 218, 87]
```

Add JUnit-tests for the method. Fetch *JUnit-tests* from the remote branch
`b2-streams-tests`:

```sh
# fetch branch 'b1-numbers' from the remote repository 'se1-play-repo'
git fetch se1-repo b2-streams-tests
```
```
remote: Enumerating objects: 43, done.
remote: Counting objects: 100% (43/43), done.
remote: Compressing objects: 100% (15/15), done.
remote: Total 42 (delta 25), reused 37 (delta 22), pack-reused 0 (from 0)
Unpacking objects: 100% (42/42), 11.64 KiB | 28.00 KiB/s, done.
From github.com:sgra64/se1-play
 * branch            b2-streams-tests -> FETCH_HEAD
 * [new branch]      b2-streams-tests -> se1-repo/b2-streams-tests
```

A local copy of the remote branch was created. Show the new remote brach:

```sh
git branch -avv             # show all branches stored in the local git repository
```

The new remote branch `b2-streams-tests` is shown among other branches. It is not
yet merged into the current branch.

```
remotes/se1-play-repo/b2-streams-tests f184a3f update .gitignore, added .tgz
```

Create (restore) the *JUnit-test* for method *tenRandomNumbers()* in the current branch
from the remote branch:

```sh
git restore --source se1-repo/b2-streams-tests -- \
    src/tests/streams/Streams_1_tenRandomNumbers_Tests.java

find src/tests              # show the new test under 'tests/streams'
```
```
src/tests
src/tests/application
src/tests/application/Application_0_always_pass_Tests.java
src/tests/streams
src/tests/streams/Streams_1_tenRandomNumbers_Tests.java
```

Compile and run tests:

```sh
mk clean compile compile-tests      # re-build the project with tests

mk run-tests                        # run tests
```
```
╷
├─ JUnit Jupiter ✔
│  ├─ Application_0_always_pass_Tests ✔
│  │  ├─ test_001_always_pass() ✔
│  │  └─ test_002_always_pass() ✔
│  └─ Streams_1_tenRandomNumbers_Tests ✔
│     └─ test100_tenRandomNumbers_regular() ✔      <-- new test
├─ JUnit Vintage ✔
└─ JUnit Platform Suite ✔

Test run finished after 235 ms
[         3 tests successful      ]
[         0 tests failed          ]
```

Run *JUnit-tests* also in the IDE.

<img src="https://raw.githubusercontent.com/sgra64/se1-play/refs/heads/markup/streams/vscode-tests-tenRandomNumbers.png" width="600"/>


&nbsp;

When tests are passing, commit the state of the implemented method *tenRandomNumbers()*
to branch *b2-streams* with message:

- commit message: `"Aufgabe 1.) Stream<Integer> tenRandomNumbers()"`

Branch *b2-streams* has advanced showing the new commit:
<!-- 
<img src="https://raw.githubusercontent.com/sgra64/se1-play/refs/heads/markup/streams/git-log-post-tenRandomNumbers.png" width="600"/>
-->
```
6fca743 (HEAD -> b2) Aufgabe 1.) Stream<Integer> tenRandomNumbers()
812dd09 add implementation class StreamsImpl.java
e58c38a pull branch se1-repo/b2-streams
1e53db5 (tag: base, main) add src/tests, update src/main/module-info.java
```

The commit should only contain the affected files:

1. the modified implementation class: `StreamsImpl.java` and

1. the new test: `Streams_1_tenRandomNumbers_Tests.java`.

Verify by comparing the two last commits:

```sh
git diff HEAD~1..HEAD --name-status     # compare the two last commits
```
```
M  src/streams/StreamsImpl.java                         <-- M: modified file
A  tests/streams/Streams_1_tenRandomNumbers_Tests.java  <-- A: added file
```


<!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -->

&nbsp;

## 7. Implement Remaining *Streams*-Functions

Implement the remaining methods of the *Streams.java* interface one after the
other.

Commit each implemented function with the corresponding *JUnit-test*. Commit
only when the test passes.

After completion, the commit-log on branch `b2-streams` shows the following
commits:

```
d27dac1 (HEAD -> b2-streams) Aufgabe 9.) List<Order> sortOrdersByValue(List<Order> orders)
b81471b Aufgabe 8.) long calculateOrderValue(List<Order> orders)
fc3fb82 Aufgabe 7.) List<String> sortedNamesByLength(List<String> names)
3c41f04 Aufgabe 6.) List<String> sortedNames(List<String> names, int limit)
0fddc52 Aufgabe 5.) List<String> filteredNames(List<String> names, String regex)
97d8477 Aufgabe 4.) List<Integer> filteredNumbers(String filter, int limit)
2739663 Aufgabe 3.) Stream<Integer> tenSortedEvenRandomNumbers()
1f6780c Aufgabe 2.) Stream<Integer> tenEvenRandomNumbers()
d75654e Aufgabe 1.) Stream<Integer> tenRandomNumbers()
7e4ae57 merge commit se1-repo/b2-streams
1e53db5 (tag: base, main) add src/tests, update src/main/module-info.java
...
```


<!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -->

&nbsp;

## 8. Final Tests

The final result will show all tests passing. Leave out tests that are
not passing.

```sh
mk clean compile compile-tests run-tests    # run all tests
```

Or run tests individually (remove tests that are failing):

```sh
# or run tests selectively (remove tests that are failing)
mk run-tests \
    -c application.Application_0_always_pass_Tests \
    -c streams.Streams_1_tenRandomNumbers_Tests \
    -c streams.Streams_2_tenEvenRandomNumbers_Tests \
    -c streams.Streams_3_tenSortedEvenRandomNumbers_Tests \
    -c streams.Streams_4_filteredNumbers_Tests \
    -c streams.Streams_5_filteredNames_Tests \
    -c streams.Streams_6_sortedNames_Tests \
    -c streams.Streams_7_sortedNamesByLength_Tests \
    -c streams.Streams_8_calculateOrderValue_Tests \
    -c streams.Streams_9_sortByOrderValue_Tests
```

Output with all tests passing:

```
╷
├─ JUnit Jupiter ✔
│  ├─ Streams_5_filteredNames_Tests ✔
│  │  ├─ test500_filteredNames_regular() ✔
│  │  ├─ test590_filteredNames_irregularNamesNull() ✔
│  │  ├─ test591_filteredNames_irregularRegexNull() ✔
│  │  └─ test592_filteredNames_irregularNamesAndRegexNull() ✔
│  ├─ Streams_6_sortedNames_Tests ✔
│  │  ├─ test600_sortedNames_regular() ✔
│  │  ├─ test601_sortedNames_regular() ✔
│  │  ├─ test610_sortedNames_emptyNames() ✔
│  │  ├─ test690_sortedNames_irregularNamesNull() ✔
│  │  ├─ test691_sortedNames_irregularLimitNegativ() ✔
│  │  └─ test692_sortedNames_irregularNamesNullAndLimitNegativ() ✔
│  ├─ Streams_7_sortedNamesByLength_Tests ✔
│  │  ├─ test700_sortedNamesByLength_regular() ✔
│  │  ├─ test710_sortedNamesByLength_emptyNames() ✔
│  │  └─ test790_sortedNamesByLength_irregular_names_Null() ✔
│  ├─ Streams_2_tenEvenRandomNumbers_Tests ✔
│  │  └─ test200_tenEvenRandomNumbers_regular() ✔
│  ├─ Application_0_always_pass_Tests ✔
│  │  ├─ test_001_always_pass() ✔
│  │  └─ test_002_always_pass() ✔
│  ├─ Streams_9_sortByOrderValue_Tests ✔
│  │  ├─ test900_sortByOrderValue_regular() ✔
│  │  ├─ test901_sortByOrderValue_regular() ✔
│  │  ├─ test910_sortByOrderValue_emptyOrders() ✔
│  │  └─ test990_sortByOrderValue_irregular_orders_Null() ✔
│  ├─ Streams_4_filteredNumbers_Tests ✔
│  │  ├─ test400_filteredNumbers_50evenNumbers_regular() ✔
│  │  ├─ test410_filteredNumbers_50divisibleBy3Numbers_regular() ✔
│  │  ├─ test420_filteredNumbers_50primeNumbers_regular() ✔
│  │  ├─ test430_filteredNumbers_different_even_numbers_returned() ✔
│  │  ├─ test431_filteredNumbers_different_div_by_three_numbers_returned() ✔
│  │  ├─ test432_filteredNumbers_different_prime_numbers_returned() ✔
│  │  ├─ test490_filteredNumbers_50evenNumbers_illegalFilter_null() ✔
│  │  ├─ test491_filteredNumbers_50evenNumbers_illegalFilter_empty() ✔
│  │  ├─ test492_filteredNumbers_50evenNumbers_illegalFilter_unknown() ✔
│  │  └─ test495_filteredNumbers_50evenNumbers_illegalLimit_negativ() ✔
│  ├─ Streams_1_tenRandomNumbers_Tests ✔
│  │  └─ test100_tenRandomNumbers_regular() ✔
│  ├─ Streams_3_tenSortedEvenRandomNumbers_Tests ✔
│  │  └─ test300_tenSortedEvenRandomNumbers_regular() ✔
│  └─ Streams_8_calculateOrderValue_Tests ✔
│     ├─ test800_calculateValue_regular() ✔
│     ├─ test801_calculateValue_regular() ✔
│     ├─ test810_calculateValue_emptyOrders() ✔
│     └─ test890_calculateValue_irregular_orders_Null() ✔
├─ JUnit Vintage ✔
└─ JUnit Platform Suite ✔

Test run finished after 283 ms
[        36 tests successful      ]     <-- 36 tests are passing
[         0 tests failed          ]     <--  0 tests failed
```


<!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -->

&nbsp;

## 9. Release

When tests are passing, both branches `b1-numbers` and `b2-streams` will be
combined on a new branch named `release-prep` that is used to perform final
tests preping a release.

Create two new branches off the `base`-commit:

- Branch `release-prep` to merge branches `b1-numbers` and `b2-streams` and perform
    final tests.

- Branch `release` to hold the commit of the final release tagged with `"RELEASE-1.0.0"`.

<img src="https://raw.githubusercontent.com/sgra64/se1-play/refs/heads/markup/img/git-release.png" width="1000"/>


&nbsp;

### 9.1 Create Branch *"release-prep"*

Merge branch `b1-numbers` to branch `release-prep` as a single commit.

Show *src* to see content of the merged branch has arrived:

```sh
find src
```
```
src
src/main
src/main/application
src/main/application/Application.java
src/main/application/package-info.java
src/main/application/Runner.java
src/main/module-info.java
src/main/numbers
src/main/numbers/Numbers.java
src/main/numbers/NumbersData.java
src/main/numbers/NumbersImpl.java
src/main/numbers/NumbersImpl_FindAllSums.java
src/main/numbers/NumbersRunner.java
src/resources
src/resources/application.properties
src/resources/log4j2.properties
src/resources/META-INF
src/resources/META-INF/MANIFEST.MF
src/tests
src/tests/application
src/tests/application/Application_0_always_pass_Tests.java
src/tests/numbers
src/tests/numbers/Matchers.java
src/tests/numbers/Numbers_1_sum_Tests.java
src/tests/numbers/Numbers_2_sum_positive_even_Tests.java
src/tests/numbers/Numbers_3_sum_recursion_Tests.java
src/tests/numbers/Numbers_4_find_first_Tests.java
src/tests/numbers/Numbers_5_find_last_Tests.java
src/tests/numbers/Numbers_6_find_all_Tests.java
src/tests/numbers/Numbers_7a_find_sums_Tests.java
src/tests/numbers/Numbers_7b_find_sums_duplicates_Tests.java
src/tests/numbers/Numbers_8a_find_all_sums_Tests.java
src/tests/numbers/Numbers_8b_find_all_sums_XL_Tests.java
```

Make sure the merge builds and runs tests:

```sh
mk build                    # clean project build:
                            # - clean compile compile-tests run-tests package
```

The clean project build also runs tests:

```
Test run finished after 8295 ms
[        80 tests successful      ]     <-- 80 tests from 'b1-numbers'
[         0 tests failed          ]     <--  0 tests failed
```

Test the final artifact with example:

```sh
java -jar target/application-1.0.0-SNAPSHOT.jar findAllSums numb_3 sum=1000
```
```
Hello, se1-play
 - findAllSums(numb_3, sum=1000) -> [
    - [500, 7, 493],
    - [500, 485, 15],
    - [485, 7, 493, 15],
    - [36, 408, 78, 15, 463],
    - [36, 23, 440, 408, 78, 15]
   ], solutions: 5
```

If all this works, commit with merge with message `"merge b1-numbers"`.


&nbsp;

### 9.2 Merge branch *"b2-streams"* to Branch *"release-prep"*

Next, merge branch `b2-streams` to branch `release-prep` as single commit.
You will likely receive a *merge-conflict*:

```
Auto-merging src/main/application/Application.java
CONFLICT (content): Merge conflict in src/main/application/Application.java
Automatic merge failed; fix conflicts and then commit the result.
```

First, show *src* to see content of both merged branched has arrived:

```sh
find src
```
```
src
src/main
src/main/application
src/main/application/Application.java
src/main/application/package-info.java
src/main/application/Runner.java
src/main/module-info.java
src/main/numbers                        <-- package 'numbers' from branch 'b1-numbers'
src/main/numbers/Numbers.java
src/main/numbers/NumbersData.java
src/main/numbers/NumbersImpl.java
src/main/numbers/NumbersImpl_FindAllSums.java
src/main/numbers/NumbersRunner.java
src/main/streams                        <-- package 'streams' from branch 'b2-streams'
src/main/streams/Streams.java
src/main/streams/StreamsImpl.java
src/main/streams/StreamsRunner.java
src/resources
src/resources/application.properties
src/resources/log4j2.properties
src/resources/META-INF
src/resources/META-INF/MANIFEST.MF
src/tests
src/tests/application
src/tests/application/Application_0_always_pass_Tests.java
src/tests/numbers                       <-- tests for 'numbers' from branch 'b1-numbers'
src/tests/numbers/Matchers.java
src/tests/numbers/Numbers_1_sum_Tests.java
src/tests/numbers/Numbers_2_sum_positive_even_Tests.java
src/tests/numbers/Numbers_3_sum_recursion_Tests.java
...
src/tests/streams                       <-- tests for 'streams' from branch 'b2-streams'
src/tests/streams/Streams_1_tenRandomNumbers_Tests.java
src/tests/streams/Streams_2_tenEvenRandomNumbers_Tests.java
src/tests/streams/Streams_3_tenSortedEvenRandomNumbers_Tests.java
...
```

Next, resolve the *merge conflict* such that both *Runners* created from *Numbers*
and from *Streams* run.


&nbsp;

### 9.3 Final Test on Branch *"release-prep"*

Then, make sure the merge builds and runs tests:

```sh
mk build                    # clean project build:
                            # - clean compile compile-tests run-tests package
```

The clean project build also runs tests:

```
Test run finished after 8295 ms
[       114 tests successful      ]     <-- 114 tests from 'b1-numbers' and 'b2-streams'
[         0 tests failed          ]     <--   0 tests failed
```

Test the final artifact with a *numbers*-example:

```sh
java -jar target/application-1.0.0-SNAPSHOT.jar findAllSums numb_3 sum=1000
```
```
Hello, se1-play
 - findAllSums(numb_3, sum=1000) -> [
    - [500, 7, 493],
    - [500, 485, 15],
    - [485, 7, 493, 15],
    - [36, 408, 78, 15, 463],
    - [36, 23, 440, 408, 78, 15]
   ], solutions: 5
```

Test the final artifact with a *streams*-example:

```sh
java -jar target/application-1.0.0-SNAPSHOT.jar tenSortedEvenRandomNumbers
```
```
Hello, se1-play
 - tenSortedEvenRandomNumbers() -> [18, 30, 48, 260, 310, 358, 492, 528, 618, 898]
```

If all this works, commit with merge with message `"merge b2-streams"` and
show the commit log:

```sh
git log --first-parent --oneline release-prep
```

Output shows two commits added on branch *"release-prep"* that was started off
the *"base"* commit:

```
e0bb53b (HEAD -> release-prep) merge b2-streams
ebc0c71 merge b1-numbers
1e53db5 (tag: base, release, main) add src/tests, update src/main/module-info.java
...
3c9b586 add src/resources
d24d184 add src/main
a8f215c add .gitmodules
15d3c87 add .gitignore
cbc8dc0 (tag: root) root commit (empty)
```


&nbsp;

### 9.4 Release

For release, merge Branch *"release-prep"* to Branch *"release"* as a single
commit and tag with "*RELEASE-1.0.0*".

Perform a final test on Branch *"release"*:

```sh
mk build
```
```
Test run finished after 8295 ms
[       114 tests successful      ]     <-- 114 tests from 'b1-numbers' and 'b2-streams'
[         0 tests failed          ]     <--   0 tests failed
```

Test the final artifact with a *streams*-example:

```sh
java -jar target/application-1.0.0-SNAPSHOT.jar \
    findAllSums numb_3 sum=1000 \
    tenSortedEvenRandomNumbers
```

Output shows results for the *numbers* and *streams* examples:

```
Hello, se1-play
 - findAllSums(numb_3, sum=1000) -> [
    - [500, 7, 493],
    - [500, 485, 15],
    - [485, 7, 493, 15],
    - [36, 408, 78, 15, 463],
    - [36, 23, 440, 408, 78, 15]
   ], solutions: 5
 -
 - tenSortedEvenRandomNumbers() -> [18, 172, 290, 376, 594, 636, 686, 728, 880, 916]
```

If all this works, commit with merge with message `"merge prelease-prep"`.


&nbsp;

### 9.4.1 Release Notes

Add file `RELEASE-NOTES.md` to the project directory
([*example*](https://blog.releasenotes.io/changelog-vs-release-notes/)):

```
## Version 1.0.0 - First Release

We're proud to announce our software, designed to supercharge your productivity!

New features:

**Numbers processing**: perform powerful numbers processing tasks.

**Streams processing**: Gain deeper insights into your data.

**Unbeaten Performance**: We've turbocharged our software, resulting in 
   50% faster processing times.

**Bug Fixes and Improvements**:
   - Fixed: The pesky timezone issue affecting our international users
   - Improved: Concurrent editing now works seamlessly for team collaboration
   - Enhanced: GDPR compliance with new data export feature

❗ **Important**: This version drops support for Java 11. 
   Please upgrade to a modern Java JDK to enjoy all new features.

[Update Now] [Read Full Documentation]
```


&nbsp;

### 9.4.2 Changelog for Release

Add file `CHANGELOG.md` to the project directory
([*example*](https://blog.releasenotes.io/changelog-vs-release-notes/)):

```
## [2.1.0] - 2026-03-25
### Added
- New dark mode feature for improved nighttime viewing (#2468)
- API endpoint for exporting user data in compliance with GDPR (/api/v1/user/export)

### Changed
- Upgraded React.js to version 18.0 for improved performance (#3579)
- Refactored database queries to optimize load times on the dashboard

### Deprecated
- Legacy authentication method using API keys (to be removed in v3.0)

### Removed
- Support for Internet Explorer 11 (#4321)

### Fixed
- Resolved race condition in concurrent user edits (#5432)
- Corrected timezone handling for international users (#6543)

### Security
- Implemented rate limiting on login attempts to prevent brute force attacks
- Updated bcrypt library to address potential vulnerability (CVE-2024-XXXX)
```

Commit with message `"add RELEASE-NOTES.md, CHANGELOG.md"`.
Tag the commit with `RELEASE-1.0.0`.

Show the commit log:

```sh
git log --first-parent --oneline release
```

Output shows two commits added on branch *"release-prep"* that was started off
the *"base"* commit:

<img src="https://raw.githubusercontent.com/sgra64/se1-play/refs/heads/markup/streams/git-log-release.png" width="600"/>

<!-- 
```
fcbc4cc (HEAD -> release, tag: RELEASE-1.0.0) add RELEASE-NOTES.md, CHANGELOG.md
837396b merge prelease-prep
1e53db5 (tag: base, main) add src/tests, update src/main/module-info.java
3c9b586 add src/resources
d24d184 add src/main
a8f215c add .gitmodules
15d3c87 add .gitignore
cbc8dc0 (tag: root) root commit (empty)
```
-->
<!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -->
<!-- B1 (SE-2)
-->
# B1: *se1-play*, branch: *b1-numbers*

<!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -->

The assignment demonstrates *git* branches to isolate the development work of
this assignment from the previous parts of the [*se1-play*](../../tree/main)
project.

- The assignment also demonstrates Java *interfaces* and *implementation
    classes* and the use of the
    [*Singleton Pattern*](https://www.digitalocean.com/community/tutorials/java-singleton-design-pattern-best-practices-examples).

- Challenges from a catalog of
    [*coding interview*](https://github.com/jwasham/coding-interview-university#interview-prep-books)
    questions are presented.

<!-- &nbsp; -->

Steps:

1. [Create a new Branch: *b1-numbers*](#1-create-a-new-branch-b1-numbers)

1. [Supplement Content to the Branch](#2-supplement-content-to-the-branch)

1. [*Build* and *Run* the Project](#3-build-and-run-the-project)

1. [Implement: *sum()*](#4-implement-sum)

1. [Create *JUnit*-Tests for *sum()*](#5-create-junit-tests-for-sum)

1. [Implement more *Numbers*-Functions:](#6-implement-more-numbers-functions) (with tests):
    - `sum_positive_even_numbers(numbers[])`, [link](#2-sum_positive_even_numbers).
    - `sum_recursive(numbers[], int i)`, [link](#3-sum_recursive).
    - `findFirst(numbers[], int x)`, [link](#4-findfirst).
    - `findLast(numbers[], int x)`, [link](#5-findlast).
    - `findAll(numbers[], int x)`, [link](#6-findall).
    - `findSums(numbers[], int sum)`, [link](#7-findsums).
    - `findAllSums(numbers[], int sum)`, [link](#8-findallsums).
    - `findAllSums(), XXL`, [link](#9-findallsums-xxl).

1. [Final Result](#7-final-result)

Code and *JUnit-tests* must work in both environments, in the IDE and in the terminal.


<!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -->

&nbsp;

## 1. Create a new Branch: *b1-numbers*

The main reason to use *branches* is to separate developments in a repository.

There are currently six commits on the `main` branch from the
[*previous assignment (A1)*](../../tree/main):

```sh
git log --oneline                   # show current commits on branch 'main'
```
```
79a79e3 (HEAD -> main) add src/tests, ...       <-- commit 6
5b9376a add src/resources                       <-- commit 5
cd172e6 add src/main                            <-- commit 4
57cb976 add .gitmodules                         <-- commit 3
4681caa add .gitignore                          <-- commit 2 (.gitignore)
ab7b126 (tag: root) root commit (empty)         <-- commit 1 (empty root commit)
```

The commit history (commit graph) is visualized:

<img src="https://raw.githubusercontent.com/sgra64/se1-play/refs/heads/markup/numbers/git-1.png" width="600"/>

The first commit was tagged as *"root"*. It is an empty commit with no
content. Project content was added with the following commits.

In order to isolate the development of this assignment (*"b1-numbers"*)
from the prior and from other later developments, a new branch `b1-bumbers`
will be used for this assignment:

```sh
git tag base                    # tag commit #6 as "base"

git switch -c b1-numbers        # create new branch 'b1-numbers' off the "base" commit
                                # and switch branch, '-c' creates a new branch
```

<img src="https://raw.githubusercontent.com/sgra64/se1-play/refs/heads/markup/numbers/git-2.png" width="600"/>

A simple description is to see a
[*branch as simply a pointer*](https://stackoverflow.com/questions/34921623/git-branches-what-is-branch)
at which a commit chain continues.

The `HEAD`-pointer is another pointer that indicates to which commit the
project directory (the *"working tree"*) is *synchronized*, which refers
to the files and directories you see in the project. Changes made to the
*working tree* are reported with command: `git status`.

Show the current branch:

```sh
git branch                      # show branches, (*) marks the current branch
```
```
* b1-numbers                    <-- active branch (*)
  git-modules
  main
```

Branch `b1-numbers` is the *active branch* (also shown in green), which means
it will receive forthcoming commits. These commits will not impact commits of
the `main` branch.


<!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -->

&nbsp;

## 2. Supplement Content to the Branch

The project directory of branch `b1-numbers` is supplemented with new content:

```sh
<se1-play>              # project directory
 | ...
 |
 # content from remote branch: 'b1-numbers' with new package 'numbers'
 +-<src>
 |  +-<main>                    # Java source code
 |  |  +--module-info.java          # description of module 'se1.play'
 |  |  |
 |  |  +-<application>              # existing package 'application'
 |  |  |  +--Application.java       # program with main()-method
 |  |  |  +--Runner.java            # new interface
 |  |  |  +--...
 |  |  |
 |  |  +-<numbers>                  # new package 'numbers' from remote branch 'b1-numbers'
 |  |     +--Numbers.java           # interface with methods to implement
 |  |     +--NumbersData.java       # numbers data sets
 |  |     +--NumbersRunner.java     # driver code to perform calculations from command line
 |  |     +--package-info.java      # package information
 |  |   
 |  +-<tests>                   # JUnit tests for 'b1-numbers'
 |  |  +-<numbers>
 |  |    +--Matchers.java
 |  |    +--Numbers_1_sum_Tests.java
 |  |    +--Numbers_2_sum_positive_even_Tests.java
 |  |    +--Numbers_3_sum_recursion_Tests.java
 |  |    +--Numbers_4_find_first_Tests.java
 |  |    +--Numbers_5_find_last_Tests.java
 |  |    +--Numbers_6_find_all_Tests.java
 |  |    +--Numbers_7a_find_sums_Tests.java
 |  |    +--Numbers_7b_find_sums_duplicates_Tests.java
 |  |    +--Numbers_8a_find_all_sums_Tests.java
 |  |    +--Numbers_8b_find_all_sums_XL_Tests.java
 |  |
 |  +-<resources>               # none-Java sources, properties files
 |     +--application.properties    # application configuration
 |     +--log4j2.properties         # logger configuration
 |     +-<META-INF>
 |        +--MANIFEST.MF            # packaging information for created .jar
```

We fetch content of the branch from an external repository under the name:
*se1-repo*:

```sh
# set remote repository URL under name: 'se1-remote-repo'
git remote add se1-repo https://github.com/sgra64/se1-play.git

git remote -v                       # show name and URL of the new remote

# fetch branch 'b1-numbers' from the remote repository
git fetch se1-repo b1-numbers
```

Content is fetched from the remote repository, but will not be visible in the
working tree (project directory) right away.

```
remote: Enumerating objects: 15, done.
remote: Counting objects: 100% (3/3), done.
remote: Compressing objects: 100% (3/3), done.
remote: Total 15 (delta 0), reused 0 (delta 0), pack-reused 12 (from 1)
Unpacking objects: 100% (15/15), 17.39 KiB | 214.00 KiB/s, done.
From https://github.com/sgra64/se1-play
 * branch            b1-numbers -> FETCH_HEAD
 * [new branch]      b1-numbers -> se1-repo/b1-numbers
```

Merge the arrived content into branch *b1-numbers*:

```sh
git branch                          # make sure you are on branch 'b1-numbers'

git status                          # make sure you have a clean working tree

# merge fetched branch 'se1-repo/b1-numbers' into local branch 'b1-numbers'
git merge se1-repo/b1-numbers \
    --allow-unrelated-histories \
    --squash \
    --strategy-option theirs
```
```
Squash commit -- not updating HEAD
Automatic merge went well; stopped before committing as requested
```

Find out the meaning of flags: `--allow-unrelated-histories`, `--squash` and
`--strategy-option theirs`.

Show the newly arrived content:

```sh
git status                          # show new content from branch 'se1-repo/b1-numbers'
```
```
On branch b1-numbers
Changes to be committed:
  (use "git restore --staged <file>..." to unstage)
        new file:   README.md
        modified:   src/main/application/Application.java
        new file:   src/main/application/Runner.java
        new file:   src/main/numbers/Numbers.java
        new file:   src/main/numbers/NumbersData.java
        new file:   src/main/numbers/NumbersRunner.java
```

Remove unwanted content:

```sh
rm README.md && git rm README.md    # remove file 'README.md'

git status                          # show new content from branch 'se1-repo/b1-numbers'
```
File *README.md* is gone.
<!-- 
```
On branch b1-numbers
Changes to be committed:
  (use "git restore --staged <file>..." to unstage)
        modified:   src/main/application/Application.java
        new file:   src/main/application/Runner.java
        new file:   src/main/numbers/Numbers.java
        new file:   src/main/numbers/NumbersData.java
        new file:   src/main/numbers/NumbersRunner.java
```
 -->

Commit and close the *"open merge"*:

```sh
git commit -m "merge commit se1-repo/b1-numbers"

git log --oneline               # show commit log
```
```
546c826 (HEAD -> b1-numbers) merge commit se1-repo/b1-numbers   <-- merge commit on branch 'b1-numbers'
79a79e3 (main) add src/tests, update src/main/module-info.java  <-- branch 'main'
5b9376a add src/resources
cd172e6 add src/main
57cb976 add .gitmodules
4681caa add .gitignore
ab7b126 (tag: root) root commit (empty)
```

After the merge, the fetched remote branch can be removed:

```sh
git branch -rd se1-repo/b1-numbers      # remove fetched remote branch
```
```
Deleted remote-tracking branch se1-repo/b1-numbers (was 4ece164).
```

&nbsp;

Verify new content under *src*:

```sh
find src                        # show content under 'src'
```
```
src
src/main
src/main/application
src/main/application/Application.java       <-- modified
src/main/application/package-info.java
src/main/application/Runner.java            <-- new file
src/main/module-info.java
src/main/numbers                        <-- new package
src/main/numbers/Numbers.java               <-- new file
src/main/numbers/NumbersData.java           <-- new file
src/main/numbers/NumbersRunner.java         <-- new file
src/resources
src/resources/application.properties
src/resources/log4j2.properties
src/resources/META-INF
src/resources/META-INF/MANIFEST.MF
src/tests
src/tests/application
src/tests/application/Application_0_always_pass_Tests.java
```

Inspect the modification in file `src/main/application/Application.java`:

```sh
# show modifications in file 'Application.java' by incoming changes
git diff HEAD~1..HEAD -- src/main/application/Application.java
```

The *git diff* command shows lines in red that were removed from the original file
`Application.java` and new lines added during the merge in green:

<img src="https://raw.githubusercontent.com/sgra64/se1-play/refs/heads/markup/numbers/git-diff-merge-Application.png" width="800"/>


&nbsp;

Changes show new classes from the new `numbers` package in the *main()* - function:

```java
Numbers numbers = Numbers.getInstance();
Runner runner = Numbers.createRunner(numbers);
runner.run(args);
```


<!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -->

&nbsp;

## 3. *Build* and *Run* the Project

*Source* and *Build* the project (no compile errors should appear):

```sh
source .env/env.sh                  # source the project (if not yet)

mk clean compile compile-tests      # re-build sources and tests
```

Run tests (should pass):

```sh
mk run-tests                        # run JUnit tests (only Application_0_always_pass_Tests)
```

Run the program:

```sh
mk run                              # running the program fails
```
```
run:
  java -p $MODULEPATH -m "se1.play/application.Application"
---
Hello, se1.play (modular)
Exception in thread "main" java.lang.UnsupportedOperationException: Unimplemente
d method 'getInstance()' in interface 'Numbers'. Create an implementation class
and return.
        at se1.play/numbers.Numbers.getInstance(Numbers.java:101)
        at se1.play/application.Application.main(Application.java:28)
```

The reason is an un-implemented method in the interface
[*src/main/numbers/Numbers.java*](src/main/numbers/Numbers.java):

```java
package numbers;

import java.util.List;
import java.util.Set;

import application.Runner;

/**
 * Public interface with functions for the <i>"b1-numbers"</i> assignment.
 * 
 * @version <code style=color:green>{@value application.package_info#Version}</code>
 * @author <code style=color:blue>{@value application.package_info#Author}</code>
 */
public interface Numbers {

    /**
     * Aufgabe 1.) Calculate the sum of numbers[].
     * @param numbers input
     * @return sum of numbers[]
     */
    long sum(int[] numbers);

    /*
     * Further methods to insert:
     * 
     * Aufgabe 2.) Calculate the sum of positive even numbers[].
     * - long sum_positive_even_numbers(int[] numbers);
     * 
     * Aufgabe 3.) Calculate the sum of numbers[] recursively without using loops
     * - long sum_recursive(int[] numbers, int i);
     * 
     * Aufgabe 4.) Return index of first occurrence of x in numbers[]
     * - int findFirst(int[] numbers, int x);
     * 
     * Aufgabe 5.) Return index of last occurrence of x in numbers[]
     * - int findLast(int[] numbers, int x);
     * 
     * Aufgabe 6.) Return list of all indices of number x in numbers[].
     * - List<Integer> findAll(int[] numbers, int x);
     * 
     * Aufgabe 7.) Return all pairs (a, b) in numbers[] matching a + b = sum.
     * - Set<Pair> findSums(int[] numbers, int sum);
     * 
     * Aufgabe 8.) Find all combinations of numbers in numbers[] that add to sum.
     * - Set<Set<Integer>> findAllSums(int[] numbers, int sum);
     */

    /**
     * Static getter that returns an instance that implements the {@link Numbers}
     * interface.
     * @return instance of the {@link Numbers} interface
     */
    static Numbers getInstance() {
        throw new UnsupportedOperationException("Unimplemented method 'getInstance()' "
            + "in interface 'Numbers'. Create an implementation class and return.");
    }

    /**
     * Factory method that creates an instance of the {@link Runner} interface.
     * @param numbers instance of the {@link Numbers} interface used by the runner
     * @return instance of the {@link Runner} interface
     */
    static Runner createRunner(Numbers numbers) {
        return new NumbersRunner(numbers);
    }
}
```

Resolve the problem by implementing a new class `NumbersImpl.java` in package
`numbers` according to the
[*"lazy" Singleton Pattern*](https://www.digitalocean.com/community/tutorials/java-singleton-design-pattern-best-practices-examples)
that also implements the
[*Numbers*](src/main/numbers/Numbers.java) interface.

Change the *getInstance()* - method in `Numbers.java` to no longer throw
the *UnsupportedOperationException* and instead return the reference of the
*NumbersImpl* singleton instance.

Mind that class `NumbersImpl.java` should not be public.


<!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -->

&nbsp;

## 4. Implement: *Sum()*

Class [*NumbersData.java*](src/main/numbers/NumbersData.java) defines numbers
data sets named: *numb*, *numb_1*, *numb_2* and *numb_3*:

<!-- @@ src.main.numbers.NumbersData.java @BEGIN -->
```java
package numbers;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

/**
 * Non-public class with numbers data accessible by name via the
 * {@link Map} interface.
 */
class NumbersData extends HashMap<String, List<Integer>> {

    /*
     * Numbers with negative numbers and duplicates.
     */
    static final List<Integer> numb = Arrays.asList(-2, 4, 9, 4, -3, 4, 9, 5);

    /*
     * Numbers with no negative numbers and no duplicates.
     */
    static final List<Integer> numb_1 = Arrays.asList(8, 10, 7, 2, 14, 5, 4);

    /*
     * Larger set of 24 numbers, no negatives, no duplicates.
     */
    static final List<Integer> numb_2 = Arrays.asList(   // 24 numbers
        371,  682,  446,  754,  205,  972,  600,  163,  541,  672,
         27,  170,  226,    7,  190,  639,   87,  773,  651,  370,
        125,  774,  903,  636//,225,  463,  286,  569,  384,    9,
    ); // add more numbers to find more solutions

    /*
     * Even larger set of 63 numbers, no negatives, no duplicates.
     */
    static final List<Integer> numb_3 = Arrays.asList(
        799, 2377,  936, 3498, 1342,  493, 1635, 4676, 1613, 3851,
       1445, 4506, 3346,    7, 2141, 2064, 1491,  908,   78, 3325,
       1756, 3691,   23, 1995, 1800,   15, 2784, 4305,   36, 2532,
       4292, 4802, 2522, 4183, 3261, 2610,  803, 2656,  498, 1668,
       2038, 2194,  440,  463, 4047, 4235, 3931,  756,  521, 4042,
       3302,  485, 1002,  408, 4691, 3387, 3104, 3658, 2241, 4382,
       1220, 3656,  500
    );

    /**
     * Constructor stores names with data in {@link Map}.
     */
    NumbersData() {
        super.put("numb", numb);
        super.put("numb_1", numb_1);
        super.put("numb_2", numb_2);
        super.put("numb_3", numb_3);
    }

    /**
     * Retrieve data by name and return as {@code int[]}.
     * Return empty array (not null) if name is not found.
     * @param key name of data to return
     * @return data set as {@code int[]}
     */
    public int[] getArr(String key) {
        return (Optional.ofNullable(get(key))
            .orElse(List.of()))
                .stream().mapToInt(i->i).toArray(); // convert to int[]
    }
}
```
<!-- @@ src.main.numbers.NumbersData.java @END -->

Implement method `long sum(int[] numbers)` in class *NumbersImpl.java*
and run the code:

```sh
mk compile                          # re-compile the code

mk run sum numb                     # run the program for sum() with data array 'numb'
```
```
Hello, se1.play (modular)
 - sum(numb) -> 30                  <-- method sum() returns 30 for data array 'numb'
```

Run the program for other data arrays:

```sh
mk run sum numb \
    sum numb_1 \
    sum numb_2 \
    sum numb_3
```
```
Hello, se1.play (modular)
 - sum(numb) -> 30
 - sum(numb_1) -> 50
 - sum(numb_2) -> 10984
 - sum(numb_3) -> 141466
```
<!-- 
Commit this state of development on branch `b1-branch`:

```sh
git status                          # working tree is "dirty"
```
```
On branch b1-numbers
Changes not staged for commit:
  (use "git add <file>..." to update what will be committed)
  (use "git restore <file>..." to discard changes in working directory)
        modified:   src/numbers/Numbers.java

Untracked files:
  (use "git add <file>..." to include in what will be committed)
        src/numbers/NumbersImpl.java

no changes added to commit (use "git add" and/or "git commit -a")
```

Show changes in file `src/numbers/Numbers.java`:

```sh
git diff src/numbers/Numbers.java
```

<img src="https://raw.githubusercontent.com/sgra64/se1-play/refs/heads/markup/numbers/git-diff-merge-Numbers.png" width="600"/>


&nbsp;

Commit changes with message: `"implemented sum()"`.

Show the commit log:

<img src="https://raw.githubusercontent.com/sgra64/se1-play/refs/heads/markup/numbers/git-log-sum.png" width="600"/> -->


<!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -->

&nbsp;

## 5. Create *JUnit*-Tests for *sum()*

After `sum(int[] numbers)` has been implemented, *JUnit*-tests are added to verify
the correct function.

Read about *JUnit-tests:*

- Carsten Gips, [*Testen mit JUnit5*](https://www.hsbi.de/elearning/data/FH-Bielefeld/lm_data/lm_1359639/testing/junit-basics.html).

- Tobias Trelle, [*JUnit 5*](https://www.codecentric.de/wissens-hub/blog/junit5-junit-5).


<!-- &nbsp; -->

Install a new test class `Numbers_1_sum_Tests.java` under `src/tests/numbers`.

```sh
mkdir -p src/tests/numbers          # create new package 'numbers' under 'src/tests'
touch src/tests/numbers/Numbers_1_sum_Tests.java

find src/tests/numbers              # show the new test class
```
```
src/tests/
src/tests/application
src/tests/application/Application_0_always_pass_Tests.java
src/tests/numbers
src/tests/numbers/Numbers_1_sum_Tests.java      <-- new test class (empty)
```

Test methods perform various tests for the `sum()` method for *regular, corner*
and *exception* cases:

<!-- @@ src.tests.numbers.Numbers_1_sum_Tests.java @BEGIN -->
```java
package numbers;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;


/**
 * Test class of an instance that implements the {@link Numbers} interface.
 * Method under test: {@code long sum(int[] numbers)}.
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class Numbers_1_sum_Tests {

    /*
     * tested object, instance that implements the {@link Numbers} interface
     */
    private final Numbers testObj;

    /*
     * test data used in tests
     */
    private final NumbersData testData;

    /**
     * Constructor that initializes test instances.
     */
    Numbers_1_sum_Tests() {
        this.testObj = Numbers.getInstance();
        this.testData = new NumbersData();
    }

    /**
     * Tests for 'regular' cases.
     */
    @Test @Order(100)
    void test100_sum_regular() {
        int[] testData_ = testData.getArr("numb");
        long expected = 30L;    // expected result of test
        long actual = testObj.sum(testData_);   // invoke sum()
        //
        // compare test results, test passes if expected==actual
        // make sure to compare 'long' values
        assertEquals(expected, actual);
    }

    @Test @Order(101)
    void test101_sum_regular() {
        assertEquals(50L, testObj.sum(testData.getArr("numb_1")));
    }

    @Test @Order(102)
    void test102_sum_regular() {
        assertEquals(10984L, testObj.sum(testData.getArr("numb_2")));
    }

    @Test @Order(103)
    void test103_sum_regular() {
        assertEquals(141466L, testObj.sum(testData.getArr("numb_3")));
    }

    /**
     * Tests for 'corner' cases.
     */

    /**
     * Tests for 'exception' cases.
     */
}
```
<!-- @@ src.tests.numbers.Numbers_1_sum_Tests.java @END -->

Compile tests and run:

```sh
# compile code and tests and run tests
mk clean compile compile-tests run-tests

# or run specific tests specified by the '-c' flag
java -cp "$JUNIT_CLASSPATH" org.junit.platform.console.ConsoleLauncher $JUNIT_OPTIONS \
  -c application.Application_0_always_pass_Tests \
  -c numbers.Numbers_1_sum_Tests
```

Output:

```
╷
├─ JUnit Jupiter ✔
│  ├─ Application_0_always_pass_Tests ✔
│  │  ├─ test_001_always_pass() ✔
│  │  └─ test_002_always_pass() ✔
│  └─ Numbers_1_sum_Tests ✔            <-- new test class
│     ├─ test100_sum_regular() ✔
│     ├─ test101_sum_regular() ✔
│     ├─ test102_sum_regular() ✔
│     └─ test103_sum_regular() ✔
├─ JUnit Vintage ✔
└─ JUnit Platform Suite ✔

Test run finished after 187 ms
[         5 containers found      ]
[         0 containers skipped    ]
[         5 containers started    ]
[         0 containers aborted    ]
[         5 containers successful ]
[         0 containers failed     ]
[         6 tests found           ]
[         0 tests skipped         ]
[         6 tests started         ]
[         0 tests aborted         ]
[         6 tests successful      ]     <-- all tests have been successful
[         0 tests failed          ]     <-- no test failed
```

Show that unit tests also run in the IDE:

<img src="https://raw.githubusercontent.com/sgra64/se1-play/refs/heads/markup/numbers/vscode-test-sum-1.png" width="800"/>


&nbsp;

Add tests for *"corner cases"* and make them work one after another.

Test: *(200)* explores cases of empty numbers arrays:

```java
/**
 * Tests for 'corner' cases.
 */
@Test @Order(200)
void test200_sum_corner_empty_array() {
    int[] testData = {};        // empty array
    assertEquals(0L, testObj.sum(testData));

    testData = new int[0];      // array of length 0
    assertEquals(0L, testObj.sum(testData));

    testData = new int[1];      // array of length 1
    testData[0] = 1;
    assertEquals(1L, testObj.sum(testData));
}
```

Test: *(210)* explores the case of a big (extreme) numbers arrays:

```java
@Test @Order(210)
void test210_sum_corner_big_array() {
    int big = Integer.MAX_VALUE;        // 32-bit, 0x7fffffff, 2147483647
    // --> java.lang.OutOfMemoryError: Requested array size exceeds VM limit
    // --> java.lang.OutOfMemoryError: Java heap space
    big = 2147483647;                   // Integer.MAX_VALUE for comparison
    big = 1000000000;                   // reduce to not throw heap space exception
    int[] testData = new int[big];      // big array
    for(int i=0; i < big; i++) {
        testData[i] = 1;    // initialize with 1's
    }
    long expected = big;
    long actual = testObj.sum(testData);
    assertEquals(expected, actual);
}
```

Test: *(212)* explores the cases of a big (extreme) numbers array filled
with a numbers sequence: `[0, 1, 2, 3, 4, 5, 6 ... ]` leading to a big
sum: *499,999,999,500,000,000*.

```java
@Test @Order(212)
void test212_sum_corner_big_array_number_series() {
    long big = 1000000000;
    int[] testData = new int[Long.valueOf(big).intValue()];
    for(int i=0; i < big; i++) {
        testData[i] = i;
    }
    long expected = big * (big - 1) / 2;    // 499,999,999,500,000,000
    long actual = testObj.sum(testData);
    // System.out.println(String.format("-exp-> %d\n-act-> %d", expected, actual));
    assertEquals(expected, actual);
}
```

If tests fail, fix the code that is tested, which is method:
`long sum(int[] numbers)` in `NumbersImpl.java`.

Run tests in the terminal:

```
╷
├─ JUnit Jupiter ✔
│  ├─ Application_0_always_pass_Tests ✔
│  │  ├─ test_001_always_pass() ✔
│  │  └─ test_002_always_pass() ✔
|  |
│  └─ Numbers_1_sum_Tests ✔            <-- new test class
│     ├─ test100_sum_regular() ✔
│     ├─ test101_sum_regular() ✔
│     ├─ test102_sum_regular() ✔
│     ├─ test103_sum_regular() ✔
│     ├─ test200_sum_corner_empty_array() ✔
│     ├─ test210_sum_corner_big_array() ✔
│     ├─ test212_sum_corner_big_array_number_series() ✔
│     └─ test300_sum_exception_null_arg() ✔
├─ JUnit Vintage ✔
└─ JUnit Platform Suite ✔

Test run finished after 4895 ms
[         5 containers found      ]
[         0 containers skipped    ]
[         5 containers started    ]
[         0 containers aborted    ]
[         5 containers successful ]
[         0 containers failed     ]
[        10 tests found           ]
[         0 tests skipped         ]
[        10 tests started         ]
[         0 tests aborted         ]
[        10 tests successful      ]     <-- all tests have been successful
[         0 tests failed          ]     <-- no test failed
```

Show tests also in the IDE:

<img src="https://raw.githubusercontent.com/sgra64/se1-play/refs/heads/markup/numbers/vscode-test-sum-2.png" width="800"/>


&nbsp;

When tests are passing, this stage of the development should be committed
to branch *b1-numbers*.

Show the changes to be committed and commit 

- with message: `"Aufgabe 1.) long sum(int[] numbers); src, tests"`

to branch *b1-numbers*.

Show the commit log:

<img src="https://raw.githubusercontent.com/sgra64/se1-play/refs/heads/markup/numbers/git-log-sum.png" width="600"/>

The commit has been added to branch *b1-numbers*, which now has advanced two
commits from the *main* branch.


&nbsp;

## 6. Implement more *Numbers*-Functions

Interface: [*Numbers.java*](src/numbers/Numbers.java) defines eight functions.
Implement functions one after another:

1. `sum(int[] numbers)` (already done).

1. `sum_positive_even_numbers(numbers[])`, [link](#2-sum_positive_even_numbers).

1. `sum_recursive(numbers[], int i)`, [link](#3-sum_recursive).

1. `findFirst(numbers[], int x)`, [link](#4-findfirst).

1. `findLast(numbers[], int x)`, [link](#5-findlast).

1. `findAll(numbers[], int x)`, [link](#6-findall).

1. `findSums(numbers[], int sum)`, [link](#7-findsums).

1. `findAllSums(numbers[], int sum)`, [link](#8-findallsums).

1. `findAllSums(), XXL`, [link](#9-findallsums-xxl).


<!-- @@ src.main.numbers.Numbers.java @BEGIN -->
```java
package numbers;

import java.util.List;
import java.util.Set;

import application.Runner;

/**
 * Public interface with functions for the <i>"b1-numbers"</i> assignment.
 * 
 * @version <code style=color:green>{@value application.package_info#Version}</code>
 * @author <code style=color:blue>{@value application.package_info#Author}</code>
 */
public interface Numbers {

    /**
     * Aufgabe 1.) Calculate the sum of numbers[].
     * @param numbers input
     * @return sum of numbers[]
     */
    long sum(int[] numbers);

    /**
     * Aufgabe 2.) Calculate sum of positive even numbers[].
     * @param numbers input
     * @return sum of positive even numbers[]
     */
    long sum_positive_even_numbers(int[] numbers);

    /**
     * Aufgabe 3.) Calculate sum of numbers[] recursively without using loops
     * (for, while, do/while).
     * @param numbers input numbers
     * @param i start index, calculate sum from index i in numbers[]
     * @return sum of numbers[]
     */
    long sum_recursive(int[] numbers, int i);

    /**
     * Aufgabe 4.) Return index of first occurrence of x in numbers[]
     * or return -1 if x was not found.
     * @param numbers input
     * @param x number to find
     * @return index of first occurrence of x in numbers[] or -1 if not found
     */
    int findFirst(int[] numbers, int x);

    /**
     * Aufgabe 5.) Return index of last occurrence of x in numbers[]
     * or return -1 if x was not found.
     * @param numbers input
     * @param x number to find
     * @return index of last occurrence of x in numbers[] or -1 if not found
     */
    int findLast(int[] numbers, int x);

    /**
     * Aufgabe 6.) Return list of all indices of number x in numbers[].
     * Return empty list, if x was not found.
     * @param numbers input
     * @param x number to find
     * @return list with all indices of x in numbers[]
     */
    List<Integer> findAll(int[] numbers, int x);

    /**
     * Immutable pair of integer values a and b used by {@code Set<Pair>
     * findSums(int[] numbers, int sum)}.
     * @param a first element of pair
     * @param b second element of pair
     */
    record Pair(int a, int b) {
        public String toString() { return String.format("(%d,%d)", a, b); }
    };

    /**
     * Aufgabe 7.) Return all pairs (a, b) in numbers[] matching a + b = sum.
     * Mirror copies (a, b), (b, a) are included once, either (a, b) or (b, a),
     * not both.
     * @param numbers input array of numbers
     * @param sum to match
     * @return all pairs (a, b) that add to sum
     */
    Set<Pair> findSums(int[] numbers, int sum);

    /**
     * Aufgabe 8.) Find all combinations of numbers in numbers[] that add to sum.
     * @param numbers input array of numbers
     * @param sum to match
     * @return all combinations of numbers that add to sum
     */
    Set<Set<Integer>> findAllSums(int[] numbers, int sum);


    /**
     * Static getter that returns an instance that implements the {@link Numbers}
     * interface.
     * @return instance of the {@link Numbers} interface
     */
    static Numbers getInstance() {
        // throw new UnsupportedOperationException("Unimplemented method 'getInstance()' "
        //     + "in interface 'Numbers'. Create an implementation class and return.");
        // 
        return NumbersImpl.getInstance();
    }

    /**
     * Factory method that creates an instance of the {@link Runner} interface.
     * @param numbers instance of the {@link Numbers} interface used by the runner
     * @return instance of the {@link Runner} interface
     */
    static Runner createRunner(Numbers numbers) {
        return new NumbersRunner(numbers);
    }
}
```
<!-- @@ src.main.numbers.Numbers.java @END -->

<!-- 
When you are done with one function,

- install the corresponding JUnit test from:
    [*tests/numbers*](../../tree/b1-numbers-tests/src/tests/numbers).

- also install class with JUnit test matchers:
    [*Matchers.java*](../../tree/b1-numbers-tests/src/tests/numbers/Matchers.java).
 -->


&nbsp;

### 2. *sum_positive_even_numbers()*

Function: `sum_positive_even_numbers(numbers[], int i)` returns the sum of
positive and even numbers from `numbers[]`.

Implement the function and demonstrate:

```sh
mk run sum_positive_even_numbers numb \
    sum_positive_even_numbers numb_1 \
    sum_positive_even_numbers numb_2 \
    sum_positive_even_numbers numb_3
```

Output shows the correct results:

```
java application.Runtime sum numbers sum numb_1 sum numb_2 sum numb_3
NumbersDriver executing NumbersImpl
 - sum_positive_even_numbers(numbers) -> 12
 - sum_positive_even_numbers(numb_1) -> 38
 - sum_positive_even_numbers(numb_2) -> 6492
 - sum_positive_even_numbers(numb_3) -> 80012
done.
```

Install the *JUnit*-test for the function
[*Numbers_2_sum_positive_even_Tests.java*](../../tree/b1-numbers-tests/src/tests/numbers/Numbers_2_sum_positive_even_Tests.java):

```sh
url="https://raw.githubusercontent.com/sgra64/se1-play/refs/heads/b1-numbers-tests/src/tests/numbers"
test="Numbers_2_sum_positive_even_Tests.java"

# install test from URL locally under 'src/tests/numbers'
curl -o "src/tests/numbers/$test" "$url/$test"
```

Compile and run the test:

```sh
mk compile-tests run-tests \
  -c numbers.Numbers_2_sum_positive_even_Tests

# or run with
mk run-tests -c numbers.Numbers_2_sum_positive_even_Tests
```

Output shows all 200's tests passing:

```
╷
├─ JUnit Jupiter ✔
│  └─ Numbers_2_sum_positive_even_Tests ✔
│     ├─ test200_sum_positive_even_numbers_regular() ✔
│     ├─ test201_sum_positive_even_numbers_regular() ✔
│     ├─ test202_sum_positive_even_numbers_regular() ✔
│     ├─ test203_sum_positive_even_numbers_regular() ✔
│     ├─ test210_sum_positive_even_numbers_corner_empty_array() ✔
│     ├─ test220_sum_positive_even_numbers_corner_big_array() ✔
│     ├─ test222_sum_positive_even_numbers_corner_big_array_number_series() ✔
│     └─ test230_sum_positive_even_numbers_exception_null_arg() ✔
├─ JUnit Vintage ✔
└─ JUnit Platform Suite ✔

[         8 tests successful      ]     <-- all tests have been successful
[         0 tests failed          ]     <-- no test failed

```

Show tests also in the IDE.

Commit the state of the development with commit message:
`"Aufgabe 2.) long sum_positive_even_numbers(int[] numbers), code and tests"`.

```sh
git add src                 # stage the commit

git commit -m "Aufgabe 2.) long sum_positive_even_numbers(int[] numbers), code and tests"

git log --oneline           # show commit log
```
```
d58908f Aufgabe 2.) long sum_positive_even_numbers(int[] numbers), code and tests
d7ab4db Aufgabe 1.) long sum(int[] numbers), code and tests
d416fa7 merge commit se1-play/b1-numbers
79a79e3 (tag: base) add src/tests, update src/main/module-info.java
...
```


&nbsp;

### 3. *sum_recursive()*

Function: `sum_recursive(numbers[], int i)` returns the sum of `numbers[]`,
but uses recursion instead of loops (`for`, `while`, `do-while`).

Implement the function (no loops!) and demonstrate:

```sh
mk run sum_recursive numb \
    sum_recursive numb_1 \
    sum_recursive numb_2 \
    sum_recursive numb_3
```

Output shows the correct results:

```
java application.Runtime sum numbers sum numb_1 sum numb_2 sum numb_3
NumbersDriver executing NumbersImpl
 - sum_recursive(numbers) -> 30
 - sum_recursive(numb_1) -> 50
 - sum_recursive(numb_2) -> 10984
 - sum_recursive(numb_3) -> 141466
done.
```

Install the *JUnit*-test class
[*Numbers_3_sum_recursion_Tests.java*](../../tree/b1-numbers-tests/src/tests/numbers/Numbers_3_sum_recursion_Tests.java)
and run the test:

```sh
mk compile-tests run-tests \
    -c numbers.Numbers_3_sum_recursion_Tests
```

Output shows 300's tests passing:

```
╷
├─ JUnit Jupiter ✔
│  └─ Numbers_3_sum_recursion_Tests ✔
│     ├─ test300_sum_recursion_regular() ✔
│     ├─ test300_sum_recursion_corner_empty_array() ✔
│     ├─ test301_sum_recursion_regular() ✔
│     ├─ test102_sum_recursion_regular() ✔
│     ├─ test103_sum_recursion_regular() ✔
│     ├─ test310_sum_recursion_corner_big_array() ✔
│     ├─ test312_sum_recursion_corner_big_array_number_series() ✔
│     └─ test340_sum_recursion_exception_null_arg() ✔
├─ JUnit Vintage ✔
└─ JUnit Platform Suite ✔

Test run finished after 169 ms
[         8 tests successful      ]
[         0 tests failed          ]
```

Commit the state of the development with commit message:
`"Aufgabe 3.) long sum_recursive(int[] numbers, int i), code and tests"`.

```sh
git log --oneline           # show commit log
```
```
0050dfb Aufgabe 3.) long sum_recursive(int[] numbers, int i)
d58908f Aufgabe 2.) long sum_positive_even_numbers(int[] numbers), code and tests
d7ab4db Aufgabe 1.) long sum(int[] numbers), code and tests
d416fa7 merge commit se1-play/b1-numbers
79a79e3 (tag: base) add src/tests, update src/main/module-info.java
...
```


&nbsp;

### 4. *findFirst()*

Function: `findFirst(numbers[], int x)` returns index of the first occurence
of `x` in `numbers[]` or `-1` if `x` is not found.

For example, number `4` occurs 3x in `numbers: [-2, 4, 9, 4, -3, 4, 9, 5]`.
Index `1` is the first occurence.

Implement and run the function:

```sh
mk run findFirst numb x=4 \
    findFirst numb x=-3 \
    findFirst numb x=1
```
```
 - findFirst(numbers, x=4) -> 1
 - findFirst(numbers, x=-3) -> 4
 - findFirst(numbers, x=1) -> -1
```

Add and run tests
[*Numbers_4_find_first_Tests.java*](../../tree/b1-numbers-tests/src/tests/numbers/Numbers_4_find_first_Tests.java):

```sh
mk run-tests -c numbers.Numbers_4_find_first_Tests
```

Output shows 400's tests passing:

```
╷
├─ JUnit Jupiter ✔
│  └─ Numbers_4_find_first_Tests ✔
│     ├─ test400_find_first_regular() ✔
│     ├─ test401_find_first_regular_neg_element() ✔
│     ├─ test402_find_first_regular_duplicates() ✔
│     ├─ test403_find_first_regular_last() ✔
│     ├─ test404_find_first_regular_not_present() ✔
│     ├─ test410_find_first_regular_numb_1() ✔
│     ├─ test412_find_first_regular_numb_2() ✔
│     ├─ test414_find_first_regular_numb_3() ✔
│     ├─ test420_find_first_corner_empty_array() ✔
│     ├─ test430_find_first_corner_big_array() ✔
│     └─ test440_find_first_exception_null_arg() ✔
├─ JUnit Vintage ✔
└─ JUnit Platform Suite ✔

Test run finished after 482 ms
[        11 tests successful      ]
[         0 tests failed          ]

```

Commit the state of the development with commit message:
`"Aufgabe 4.) int findFirst(int[] numbers, int x), code and tests"`.

```sh
git log --oneline           # show commit log
```
```
095f5e5 Aufgabe 4.) int findFirst(int[] numbers, int x), code and tests
0050dfb Aufgabe 3.) long sum_recursive(int[] numbers, int i)
d58908f Aufgabe 2.) long sum_positive_even_numbers(int[] numbers), code and tests
d7ab4db Aufgabe 1.) long sum(int[] numbers), code and tests
d416fa7 merge commit se1-play/b1-numbers
79a79e3 (tag: base) add src/tests, update src/main/module-info.java
...
```


&nbsp;

### 5. *findLast()*

Function: `findLast(numbers[], int x)` returns index of the last occurence
of `x` in `numbers[]` or `-1` if `x` is not found.

For example, number `4` occurs 3x in `numb[]: [-2, 4, 9, 4, -3, 4, 9, 5]`.
Index `5` is the last occurence.

- *findFirst()* is an efficient function. It can immediately return
    when *x* is found - giving it a *"speed"* of *n/2* on average
    with *n* as the length of the array.

    - What is the efficiency of *findLast()*? Can it also return *"early"* or has
    it to visit the entire array - giving it a *"speed"* of *n*.

    - Can you find an implementation that runs at *"speed"* *n/2*?


Implement and run the function:

```sh
mk run findLast numb x=4 \
    findLast numb x=-3 \
    findLast numb x=1
```
```
 - findLast(numbers, x=4) -> 5
 - findLast(numbers, x=-3) -> 4
 - findLast(numbers, x=1) -> -1
```

Add and run tests
[*Numbers_5_find_last_Tests.java*](../../tree/b1-numbers-tests/src/tests/numbers/Numbers_5_find_last_Tests.java):

```sh
mk run-tests -c numbers.Numbers_5_find_last_Tests
```

Output shows 500's tests passing:

```
╷
├─ JUnit Jupiter ✔
│  └─ Numbers_5_find_last_Tests ✔
│     ├─ test500_find_last_regular() ✔
│     ├─ test501_find_last_regular_neg_element() ✔
│     ├─ test502_find_last_regular_duplicates() ✔
│     ├─ test503_find_last_regular_last() ✔
│     ├─ test504_find_last_regular_not_present() ✔
│     ├─ test510_find_last_regular_numb_1() ✔
│     ├─ test512_find_last_regular_numb_2() ✔
│     ├─ test514_find_last_regular_numb_3() ✔
│     ├─ test520_find_last_corner_empty_array() ✔
│     ├─ test530_find_last_corner_big_array() ✔
│     └─ test540_find_last_exception_null_arg() ✔
├─ JUnit Vintage ✔
└─ JUnit Platform Suite ✔

Test run finished after 364 ms
[        11 tests successful      ]
[         0 tests failed          ]
```

Commit the state of the development with commit message:
`"Aufgabe 5.) int findLast(int[] numbers, int x), code and tests"`.

```sh
git log --oneline           # show commit log
```
```
efe6c64 Aufgabe 5.) int findLast(int[] numbers, int x), code and tests
095f5e5 Aufgabe 4.) int findFirst(int[] numbers, int x), code and tests
0050dfb Aufgabe 3.) long sum_recursive(int[] numbers, int i)
d58908f Aufgabe 2.) long sum_positive_even_numbers(int[] numbers), code and tests
d7ab4db Aufgabe 1.) long sum(int[] numbers), code and tests
d416fa7 merge commit se1-play/b1-numbers
79a79e3 (tag: base) add src/tests, update src/main/module-info.java
...
```


&nbsp;

### 6. *findAll()*

Function: `List<Integer> findAll(int[] numbers, int x)` returns indices of all
occurences of `x` in `numbers[]` as a `List<Integer>`.

Implement and run the function:

```sh
mk run findAll numb x=4 \
    findAll numb x=-3 \
    findAll numb x=1
```

Output shows that `x=4` was found 3x in `numbers[]: [-2, 4, 9, 4, -3, 4, 9, 5]`
at indices: `[1, 3, 5]`, `x=-3` was found once at index `[4]` and `x=1` was
not found returning an empty result `[]`.

```
 - findAll(numbers, x=4) -> [1, 3, 5]
 - findAll(numbers, x=-3) -> [4]
 - findAll(numbers, x=1) -> []
```

Unit tests need to compare collections (lists) as *expected* or *actual* values.

1. Install class:
[*Matchers.java*](../../tree/b1-numbers-tests/src/tests/numbers/Matchers.java)
for this purpose.

2. Then, install:
[*Numbers_6_find_all_Tests.java*](../../tree/b1-numbers-tests/src/tests/numbers/Numbers_6_find_all_Tests.java).

Run tests:

```sh
mk run-tests -c numbers.Numbers_6_find_all_Tests
```

Output shows 600's tests passing:

```
╷
├─ JUnit Jupiter ✔
│  └─ Numbers_6_find_all_Tests ✔
│     ├─ test600_find_all_regular() ✔
│     ├─ test601_find_all_regular() ✔
│     ├─ test602_find_all_regular() ✔
│     ├─ test603_find_all_regular() ✔
│     └─ test640_find_all_exception_null_arg() ✔
├─ JUnit Vintage ✔
└─ JUnit Platform Suite ✔

Test run finished after 253 ms
[         5 tests successful      ]
[         0 tests failed          ]
```

Commit the state of the development with commit message:
`"Aufgabe 6.) List<Integer> findAll(int[] numbers, int x), code and tests"`.

```sh
git log --oneline           # show commit log
```
```
14f7939 Aufgabe 6.) List<Integer> findAll(int[] numbers, int x), code and tests
efe6c64 Aufgabe 5.) int findLast(int[] numbers, int x), code and tests
095f5e5 Aufgabe 4.) int findFirst(int[] numbers, int x), code and tests
0050dfb Aufgabe 3.) long sum_recursive(int[] numbers, int i)
d58908f Aufgabe 2.) long sum_positive_even_numbers(int[] numbers), code and tests
d7ab4db Aufgabe 1.) long sum(int[] numbers), code and tests
d416fa7 merge commit se1-play/b1-numbers
79a79e3 (tag: base) add src/tests, update src/main/module-info.java
...
```


&nbsp;

### 7. *findSums()*

Function: `Set<Pair> findSums(int[] numbers, int sum)` returns a set of
pairs: `(x, y)` from `numbers[]` with `x + y = sum`.

For example: `sum=12` can be created from `numb_1[]: [8, 10, 7, 2, 14, 5, 4]`
with pairs: `[ (5,7), (4,8), (2,10) ]`.

Duplicates should be avoided and included only once, which means either
`(5,7)` or `(7,5)` and not both.

Interface `Numbers.java` defines `Pair` as Java-Record:

```java
/**
 * Immutable pair of integer values a and b.
 * @param a first element of pair
 * @param b second element of pair
 */
record Pair(int a, int b) {
    public String toString() { return String.format("(%d,%d)", a, b); }
};
```

Implementing the functions yields results using array `numb_1[]`:

```sh
mk run findSums numb_1 sum=10 \
    findSums numb_1 sum=12 \
    findSums numb_1 sum=15 \
    findSums numb_3 sum=500
```

Output shows that `sum=10` can be added by `2+8` from array
`numb_1[]: [8, 10, 7, 2, 14, 5, 4]`. There is only one solution.

There are three pairs of numbers from `numb_1[]` that add to `sum=12`:
`[ (5,7), (4,8), (2,10) ]` and two pairs that add to `sum=15`.

Sum `500` can be added from pairs: `[ (7,493), (485,15) ]` from array `numb_3[]`.

```
 - findSums(numb_1, sum=10) -> [(8,2)], solutions: 1
 - findSums(numb_1, sum=12) -> [(10,2), (8,4), (7,5)], solutions: 3
 - findSums(numb_1, sum=15) -> [(10,5), (8,7)], solutions: 2
 - findSums(numb_3, sum=500) -> [(15,485), (493,7)], solutions: 2
```

Add and run tests
[*Numbers_7a_find_sums_Tests.java*](../../tree/b1-numbers-tests/src/tests/numbers/Numbers_7a_find_sums_Tests.java)
and
[*Numbers_7b_find_sums_duplicates_Tests.java*](../../tree/b1-numbers-tests/src/tests/numbers/Numbers_7b_find_sums_duplicates_Tests.java):

Leave out tests `7b` if duplicate tests are not passing.

```sh
mk run-tests \
  -c numbers.Numbers_7a_find_sums_Tests \
  -c numbers.Numbers_7b_find_sums_duplicates_Tests
```

Output shows 700's tests passing:

```
╷
├─ JUnit Jupiter ✔
│  ├─ Numbers_7a_find_sums_Tests ✔
│  │  ├─ test700_find_sums_regular() ✔
│  │  ├─ test701_find_sums_regular() ✔
│  │  ├─ test702_find_sums_regular() ✔
│  │  ├─ test703_find_sums_regular() ✔
│  │  ├─ test704_find_sums_regular() ✔
│  │  ├─ test705_find_sums_regular() ✔
│  │  ├─ test706_find_sums_regular() ✔
│  │  └─ test720_find_sums_exception_null_arg() ✔
│  └─ Numbers_7b_find_sums_duplicates_Tests ✔
│     ├─ test710_find_sums_duplicates() ✔
│     ├─ test711_find_sums_same_duplicates() ✔
│     ├─ test712_find_sums_mirror_duplicates() ✔
│     └─ test713_find_sums_regular_duplicates() ✔
├─ JUnit Vintage ✔
└─ JUnit Platform Suite ✔

Test run finished after 277 ms
[        12 tests successful      ]
[         0 tests failed          ]
```

Commit the state of the development with commit message:
`"Aufgabe 7.) Set<Pair> findSums(int[] numbers, int sum), code and tests"`.

```sh
git log --oneline           # show commit log
```
```
cd849e5 Aufgabe 7.) Set<Pair> findSums(int[] numbers, int sum), code and tests
14f7939 Aufgabe 6.) List<Integer> findAll(int[] numbers, int x), code and tests
efe6c64 Aufgabe 5.) int findLast(int[] numbers, int x), code and tests
095f5e5 Aufgabe 4.) int findFirst(int[] numbers, int x), code and tests
0050dfb Aufgabe 3.) long sum_recursive(int[] numbers, int i)
d58908f Aufgabe 2.) long sum_positive_even_numbers(int[] numbers), code and tests
d7ab4db Aufgabe 1.) long sum(int[] numbers), code and tests
d416fa7 merge commit se1-play/b1-numbers
79a79e3 (tag: base) add src/tests, update src/main/module-info.java
...
```


&nbsp;

### 8. *findAllSums()*

Function: `Set<Set<Integer>> findAllSums(int[] numbers, int sum)` returns all
combinations of numbers from the array that add to `sum`.

For example: `sum=10` can be created from `numb_1[]: [8, 10, 7, 2, 14, 5, 4]`
with: `[10]` and `[2, 8]`,
`sum=14` can be created with: `[14], [4, 10], [2, 4, 8], [2, 5, 7]`.

Duplicates such as `[4,10]` or `[10,4]`should be avoided and included only once.

```sh
mk run \
    findAllSums numb_1 sum=10 \
    findAllSums numb_1 sum=12 \
    findAllSums numb_1 sum=14 \
    findAllSums numb_1 sum=15 \
    findAllSums numb_1 sum=20
```

```
 - findAllSums(numb_1, sum=10) -> {[10], [2, 8]}, solutions: 2
 - findAllSums(numb_1, sum=12) -> {[4, 8], [2, 10], [5, 7]}, solutions: 3
 - findAllSums(numb_1, sum=14) -> {[14], [4, 10], [2, 4, 8], [2, 5, 7]}, solutions: 4
 - findAllSums(numb_1, sum=15) -> {[7, 8], [5, 10], [2, 5, 8]}, solutions: 3
 - findAllSums(numb_1, sum=20) -> {[2, 8, 10], [5, 7, 8], [2, 4, 14]}, solutions: 3
```

Explore more combinations from the (larger) `numb_2[]` array:

```sh
mk run \
    findAllSums numb_2 sum=1000 \
    findAllSums numb_2 sum=999
```

```
 - findAllSums(numb_2, sum=1000) -> [
    - [226, 774],
    - [754, 87, 7, 27, 125],
    - [7, 27, 636, 125, 205],
    - [7, 651, 27, 125, 190],
    - [7, 27, 125, 205, 190, 446]
   ], solutions: 5

 - findAllSums(numb_2, sum=999) -> [
    - [226, 773],
    - [27, 972],
    - [371, 87, 541],
    - [170, 190, 639],
    - [226, 27, 541, 205],
    - [163, 170, 125, 541],
    - [163, 170, 27, 639],
    - [163, 7, 190, 639],
    - [226, 371, 170, 27, 205],
    - [226, 371, 7, 205, 190],
    - [226, 371, 87, 125, 190],
    - [226, 163, 371, 7, 27, 205],
    - [226, 163, 371, 87, 27, 125]
   ], solutions: 13
```

Add and run tests
[*Numbers_8a_find_all_sums_Tests.java*](../../tree/b1-numbers-tests/src/tests/numbers/Numbers_8a_find_all_sums_Tests.java):

```sh
mk run-tests -c numbers.Numbers_8a_find_all_sums_Tests
```

Output shows 800(a)'s tests passing:

```
╷
├─ JUnit Jupiter ✔
│  └─ Numbers_8a_find_all_sums_Tests ✔
│     ├─ test800_find_all_sums_regular() ✔
│     ├─ test801_find_all_sums_regular() ✔
│     ├─ test802_find_all_sums_regular() ✔
│     ├─ test802_find_all_sums_regular_no_match() ✔
│     ├─ test821_find_all_sums_regular_numb_2_sum999() ✔
│     └─ test830_find_all_sums_exception_null_arg() ✔
├─ JUnit Vintage ✔
└─ JUnit Platform Suite ✔

Test run finished after 176 ms
[         6 tests successful      ]
[         0 tests failed          ]
```

Commit the state of the development with commit message:
`"Aufgabe 8.) Set<Set<Integer>> findAllSums(int[] numbers, int sum)"`.

```sh
git log --oneline           # show commit log
```
```
8bdc9fe Aufgabe 8.) Set<Set<Integer>> findAllSums(int[] numbers, int sum)
cd849e5 Aufgabe 7.) Set<Pair> findSums(int[] numbers, int sum), code and tests
14f7939 Aufgabe 6.) List<Integer> findAll(int[] numbers, int x), code and tests
efe6c64 Aufgabe 5.) int findLast(int[] numbers, int x), code and tests
095f5e5 Aufgabe 4.) int findFirst(int[] numbers, int x), code and tests
0050dfb Aufgabe 3.) long sum_recursive(int[] numbers, int i)
d58908f Aufgabe 2.) long sum_positive_even_numbers(int[] numbers), code and tests
d7ab4db Aufgabe 1.) long sum(int[] numbers), code and tests
d416fa7 merge commit se1-play/b1-numbers
79a79e3 (tag: base) add src/tests, update src/main/module-info.java
...
```


&nbsp;

### 9. *findAllSums(), XXL*

Array `numb_2[]` with `24` numbers is still small.

```java
/*
 * Larger set of 24 numbers, no negatives, no duplicates.
 */
static final int[] numb_2 = {   // 24 numbers
    371,  682,  446,  754,  205,  972,  600,  163,  541,  672,
     27,  170,  226,    7,  190,  639,   87,  773,  651,  370,
    125,  774,  903,  636//,225,  463,  286,  569,  384,    9,
}; // add more numbers to find more solutions
```

Run the function for `sum=999` with 24 numbers from `numb_2[]`:

```sh
mk run findAllSums numb_2 sum=999
```

Output:

```
 - findAllSums(numb_2, sum=999) -> [
    - [27, 972],
    - [226, 773],
    - [371, 87, 541],
    - [170, 190, 639],
    - [226, 27, 541, 205],
    - [163, 170, 125, 541],
    - [163, 170, 27, 639],
    - [163, 7, 190, 639],
    - [226, 371, 170, 27, 205],
    - [226, 371, 7, 205, 190],
    - [226, 371, 87, 125, 190],
    - [226, 163, 371, 7, 27, 205],
    - [226, 163, 371, 87, 27, 125]
   ], solutions: 13
```

Add numbers `225` und `463` to `numb_2[]` (remove comments) and repeat:

```sh
mk run findAllSums numb_2 sum=999
```

More solutions are found with the new numbers `225` und `463`:

```
 - findAllSums(numb_2, sum=999) -> [
    - [226, 773],
    - [225, 774],
    - [27, 972],
    - [371, 87, 541],
    - [170, 190, 639],
    - [163, 7, 190, 639],
    - [226, 27, 541, 205],
    - [163, 170, 125, 541],
    - [163, 170, 27, 639],
    - [225, 226, 7, 541],
    - [226, 371, 170, 27, 205],
    - [226, 371, 7, 205, 190],
    - [225, 226, 371, 7, 170],
    - [226, 371, 87, 125, 190],
    - [226, 163, 371, 7, 27, 205],
    - [226, 163, 371, 87, 27, 125],
    - [225, 87, 7, 27, 190, 463]
   ], solutions: 17
```

Incrementally add more numbers to `numb_2[]` and repeat:

- add number `286` --> 19 solutions,

- add number `569` --> 21 solutions,

- add number `384` --> 24 solutions,

- add number `9` --> 44 solutions:

```
-> findAllSums(sum=999, numb_2) -> [
    - [226, 773],                   - [371, 27, 125, 286, 190],
    - [27, 972],                    - [225, 226, 371, 7, 170],
    - [225, 774],                   - [226, 7, 569, 170, 27],
    - [170, 190, 639],              - [225, 370, 9, 205, 190],
    - [371, 87, 541],               - [226, 163, 371, 7, 27, 205],
    - [225, 569, 205],              - [226, 163, 371, 87, 27, 125],
    - [903, 87, 9],                 - [226, 163, 9, 125, 286, 190],
    - [226, 9, 125, 639],           - [225, 7, 9, 170, 125, 463],
    - [163, 9, 541, 286],           - [384, 7, 170, 27, 125, 286],
    - [163, 170, 27, 639],          - [384, 226, 163, 9, 27, 190],
    - [225, 226, 7, 541],           - [225, 7, 9, 27, 541, 190],
    - [163, 170, 125, 541],         - [225, 87, 7, 27, 190, 463],
    - [163, 7, 190, 639],           - [384, 226, 87, 7, 9, 286],
    - [773, 9, 27, 190],            - [7, 9, 125, 205, 190, 463],
    - [226, 27, 541, 205],          - [9, 170, 27, 125, 205, 463],
    - [163, 87, 286, 463],          - [225, 87, 9, 27, 205, 446],
    - [384, 27, 125, 463],          - [384, 226, 87, 7, 170, 125],
    - [226, 371, 87, 125, 190],     - [225, 370, 163, 9, 27, 205],
    - [226, 371, 170, 27, 205],     - [225, 371, 7, 9, 170, 27, 190],
    - [226, 371, 7, 205, 190],      - [163, 7, 9, 27, 125, 205, 463],
    - [163, 371, 9, 170, 286],      - [370, 226, 7, 9, 170, 27, 190],
    - [225, 87, 9, 651, 27],        - [384, 87, 7, 9, 170, 27, 125, 190]
   ], solutions: 44
```

A *"bruteforce"* algorithm will take increasing time from the 24th number
and not end if numbers continue to be added.

Alterative algorithms need to be used that can cope with larger number
arrays such as `numb_3`:

```java
/*
* Even larger set of 63 numbers, no negatives, no duplicates (n=3).
*/
static final int numb_3[] = {
     799, 2377,  936, 3498, 1342,  493, 1635, 4676, 1613, 3851,
    1445, 4506, 3346,    7, 2141, 2064, 1491,  908,   78, 3325,
    1756, 3691,   23, 1995, 1800,   15, 2784, 4305,   36, 2532,
    4292, 4802, 2522, 4183, 3261, 2610,  803, 2656,  498, 1668,
    2038, 2194,  440,  463, 4047, 4235, 3931,  756,  521, 4042,
    3302,  485, 1002,  408, 4691, 3387, 3104, 3658, 2241, 4382,
    1220, 3656,  500,
};
```

Try to find such algorithms (e.g. *"branch-and-bound"*) and implement
function `findAllSums(int sum)` such that it works with numbers from
`numb_3`, e.g. for `sum=999`:

```sh
mk run findAllSums numb_3 sum=999
```

Although there are only 10 solutions for `sum=999`, the number space
to explore is large: *2^63*.

```
 - findAllSums(numb_3, sum=999) -> [
    - [521, 463, 15],
    - [500, 36, 463],
    - [36, 7, 493, 463],
    - [498, 408, 78, 15],
    - [498, 23, 463, 15],
    - [23, 440, 521, 15],
    - [500, 36, 23, 440],
    - [36, 485, 463, 15],
    - [36, 23, 7, 440, 493],
    - [36, 485, 23, 440, 15]
   ], solutions: 10
```

There are only 5 solutions for `sum=1000`:

```sh
mk run findAllSums numb_3 sum=1000
```
```
 - findAllSums(numb_3, sum=1000) -> [
    - [500, 7, 493],
    - [500, 485, 15],
    - [485, 7, 493, 15],
    - [36, 408, 78, 463, 15],
    - [36, 23, 408, 440, 78, 15]
   ], solutions: 5
```

If you found an implementation that works with the `numb_3[]` array,
add and run tests
[*Numbers_8b_find_all_sums_XL_Tests.java*](../../tree/b1-numbers-tests/src/tests/numbers/Numbers_8b_find_all_sums_XL_Tests.java):

```sh
mk run-tests -c numbers.Numbers_8b_find_all_sums_XL_Tests
```

Output:

```
╷
├─ JUnit Jupiter ✔
│  └─ Numbers_8b_find_all_sums_XL_Tests ✔
│     ├─ test824_find_all_sums_XL_24_numbers() ✔
│     ├─ test825_find_all_sums_XL_25_numbers() ✔
│     ├─ test826_find_all_sums_XL_26_numbers() ✔
│     ├─ test827_find_all_sums_XL_27_numbers() ✔
│     ├─ test828_find_all_sums_XL_28_numbers() ✔
│     ├─ test829_find_all_sums_XL_29_numbers() ✔
│     ├─ test830_find_all_sums_XL_30_numbers() ✔
│     ├─ test840_find_all_sums_XL_numb_3_999() ✔
│     └─ test841_find_all_sums_XL_numb_3_1000() ✔
├─ JUnit Vintage ✔
└─ JUnit Platform Suite ✔

Test run finished after 290 ms
[         9 tests successful      ]
[         0 tests failed          ]
```


&nbsp;

## 7. Final Result

The final result will show all tests passing. Leave out tests that are
not passing:

<img src="https://raw.githubusercontent.com/sgra64/se1-play/refs/heads/markup/numbers/b1-numbers-tests-vscode.png" width="800"/>


```sh
mk run-tests                    # run all tests
```

Or run tests selectively (remove tests that are failing):

```sh
# or run tests selectively (remove tests that are failing)
mk run-tests \
  -c application.Application_0_always_pass_Tests \
  -c numbers.Numbers_1_sum_Tests \
  -c numbers.Numbers_2_sum_positive_even_Tests \
  -c numbers.Numbers_3_sum_recursion_Tests \
  -c numbers.Numbers_4_find_first_Tests \
  -c numbers.Numbers_5_find_last_Tests \
  -c numbers.Numbers_6_find_all_Tests \
  -c numbers.Numbers_7a_find_sums_Tests \
  -c numbers.Numbers_7b_find_sums_duplicates_Tests \
  -c numbers.Numbers_8a_find_all_sums_Tests \
  -c numbers.Numbers_8b_find_all_sums_XL_Tests
```

The full [*test log*](https://raw.githubusercontent.com/sgra64/se1-play/refs/heads/markup/numbers/b1-numbers-tests-terminal.png)
shows all tests passing:

```
╷
├─ JUnit Jupiter ✔
│  ├─ Application_0_always_pass_Tests ✔
│  │  ├─ test_001_always_pass() ✔
│  │  └─ test_002_always_pass() ✔
│  ├─ Numbers_1_sum_Tests ✔
│  │  ├─ test100_sum_regular() ✔
│  │  ├─ test101_sum_regular() ✔
│  │  ├─ test102_sum_regular() ✔
│  │  ├─ test103_sum_regular() ✔
│  │  ├─ test110_sum_corner_empty_array() ✔
│  │  ├─ test120_sum_corner_big_array() ✔
│  │  ├─ test122_sum_corner_big_array_number_series() ✔
│  │  └─ test130_sum_exception_null_arg() ✔
│  ├─ Numbers_2_sum_positive_even_Tests ✔
│  │  ├─ test200_sum_positive_even_numbers_regular() ✔
│  │  ├─ test201_sum_positive_even_numbers_regular() ✔
│  │  ├─ test202_sum_positive_even_numbers_regular() ✔
│  │  ├─ test203_sum_positive_even_numbers_regular() ✔
│  │  ├─ test210_sum_positive_even_numbers_corner_empty_array() ✔
│  │  ├─ test220_sum_positive_even_numbers_corner_big_array() ✔
│  │  ├─ test222_sum_positive_even_numbers_corner_big_array_number_series() ✔
│  │  └─ test230_sum_positive_even_numbers_exception_null_arg() ✔
│  ├─ Numbers_3_sum_recursion_Tests ✔
│  │  ├─ test300_sum_recursion_regular() ✔
│  │  ├─ test300_sum_recursion_corner_empty_array() ✔
│  │  ├─ test301_sum_recursion_regular() ✔
│  │  ├─ test102_sum_recursion_regular() ✔
│  │  ├─ test103_sum_recursion_regular() ✔
│  │  ├─ test310_sum_recursion_corner_big_array() ✔
│  │  ├─ test312_sum_recursion_corner_big_array_number_series() ✔
│  │  └─ test340_sum_recursion_exception_null_arg() ✔
│  ├─ Numbers_4_find_first_Tests ✔
│  │  ├─ test400_find_first_regular() ✔
│  │  ├─ test401_find_first_regular_neg_element() ✔
│  │  ├─ test402_find_first_regular_duplicates() ✔
│  │  ├─ test403_find_first_regular_last() ✔
│  │  ├─ test404_find_first_regular_not_present() ✔
│  │  ├─ test410_find_first_regular_numb_1() ✔
│  │  ├─ test412_find_first_regular_numb_2() ✔
│  │  ├─ test414_find_first_regular_numb_3() ✔
│  │  ├─ test420_find_first_corner_empty_array() ✔
│  │  ├─ test430_find_first_corner_big_array() ✔
│  │  └─ test440_find_first_exception_null_arg() ✔
│  ├─ Numbers_5_find_last_Tests ✔
│  │  ├─ test500_find_last_regular() ✔
│  │  ├─ test501_find_last_regular_neg_element() ✔
│  │  ├─ test502_find_last_regular_duplicates() ✔
│  │  ├─ test503_find_last_regular_last() ✔
│  │  ├─ test504_find_last_regular_not_present() ✔
│  │  ├─ test510_find_last_regular_numb_1() ✔
│  │  ├─ test512_find_last_regular_numb_2() ✔
│  │  ├─ test514_find_last_regular_numb_3() ✔
│  │  ├─ test520_find_last_corner_empty_array() ✔
│  │  ├─ test530_find_last_corner_big_array() ✔
│  │  └─ test540_find_last_exception_null_arg() ✔
│  ├─ Numbers_6_find_all_Tests ✔
│  │  ├─ test600_find_all_regular() ✔
│  │  ├─ test601_find_all_regular() ✔
│  │  ├─ test602_find_all_regular() ✔
│  │  ├─ test603_find_all_regular() ✔
│  │  └─ test640_find_all_exception_null_arg() ✔
│  ├─ Numbers_7a_find_sums_Tests ✔
│  │  ├─ test700_find_sums_regular() ✔
│  │  ├─ test701_find_sums_regular() ✔
│  │  ├─ test702_find_sums_regular() ✔
│  │  ├─ test703_find_sums_regular() ✔
│  │  ├─ test704_find_sums_regular() ✔
│  │  ├─ test705_find_sums_regular() ✔
│  │  ├─ test706_find_sums_regular() ✔
│  │  └─ test720_find_sums_exception_null_arg() ✔
│  ├─ Numbers_7b_find_sums_duplicates_Tests ✔
│  │  ├─ test710_find_sums_duplicates() ✔
│  │  ├─ test711_find_sums_same_duplicates() ✔
│  │  ├─ test712_find_sums_mirror_duplicates() ✔
│  │  └─ test713_find_sums_regular_duplicates() ✔
│  ├─ Numbers_8a_find_all_sums_Tests ✔
│  │  ├─ test800_find_all_sums_regular() ✔
│  │  ├─ test801_find_all_sums_regular() ✔
│  │  ├─ test802_find_all_sums_regular() ✔
│  │  ├─ test802_find_all_sums_regular_no_match() ✔
│  │  ├─ test821_find_all_sums_regular_numb_2_sum999() ✔
│  │  └─ test830_find_all_sums_exception_null_arg() ✔
│  └─ Numbers_8b_find_all_sums_XL_Tests ✔
│     ├─ test824_find_all_sums_XL_24_numbers() ✔
│     ├─ test825_find_all_sums_XL_25_numbers() ✔
│     ├─ test826_find_all_sums_XL_26_numbers() ✔
│     ├─ test827_find_all_sums_XL_27_numbers() ✔
│     ├─ test828_find_all_sums_XL_28_numbers() ✔
│     ├─ test829_find_all_sums_XL_29_numbers() ✔
│     ├─ test830_find_all_sums_XL_30_numbers() ✔
│     ├─ test840_find_all_sums_XL_numb_3_999() ✔
│     └─ test841_find_all_sums_XL_numb_3_1000() ✔
├─ JUnit Vintage ✔
└─ JUnit Platform Suite ✔

Test run finished after 7401 ms
[        14 containers found      ]
[         0 containers skipped    ]
[        14 containers started    ]
[         0 containers aborted    ]
[        14 containers successful ]
[         0 containers failed     ]
[        80 tests found           ]
[         0 tests skipped         ]
[        80 tests started         ]
[         0 tests aborted         ]
[        80 tests successful      ]
[         0 tests failed          ]
```


Commit the state of the development with commit message:
`"Aufgabe 8.) Set<Set<Integer>> findAllSums(int[] numbers, int sum), XXL"`.

```sh
git log --oneline           # show commit log
```
```
8bdc9fe (HEAD -> b1-numbers) Aufgabe 8.) Set<Set<Integer>> findAllSums(int[] numbers, int sum), XXL
8bdc9fe Aufgabe 8.) Set<Set<Integer>> findAllSums(int[] numbers, int sum)
cd849e5 Aufgabe 7.) Set<Pair> findSums(int[] numbers, int sum), code and tests
14f7939 Aufgabe 6.) List<Integer> findAll(int[] numbers, int x), code and tests
efe6c64 Aufgabe 5.) int findLast(int[] numbers, int x), code and tests
095f5e5 Aufgabe 4.) int findFirst(int[] numbers, int x), code and tests
0050dfb Aufgabe 3.) long sum_recursive(int[] numbers, int i)
d58908f Aufgabe 2.) long sum_positive_even_numbers(int[] numbers), code and tests
d7ab4db Aufgabe 1.) long sum(int[] numbers), code and tests
d416fa7 merge commit se1-play/b1-numbers
79a79e3 (tag: base) add src/tests, update src/main/module-info.java
...
```
<!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -->
<!-- B2 (SE-1)
-->
# B2: *se1-play*, branch *b2-streams*

<!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -->

Assignment *b2-streams* demonstrates the use of the
[*Java Streams API*](https://docs.oracle.com/en/java/javase/23/docs/api/java.base/java/util/stream/Stream.html).
Code of this assignment is isolated from the other developments
in the project in branches *main* and *b1-numbers* of the
[*se1-play*](../../tree/main) project.

Steps:

1. [Introduction to the Java *Streams API*](#1-introduction-to-the-java-streams-api)

1. [Branch Structure](#2-branch-structure)

1. [Branch *Setup*](#3-branch-setup)

1. [*Build* and *Run*](#4-build-and-run)

1. [The *Stream* - Interface](#5-the-stream---interface)

1. [Implement: *tenRandomNumbers()*](#6-implement-tenrandomnumbers)

1. [Implement Remaining *Streams*-Functions](#7-implement-remaining-streams-functions)

1. [Final Tests](#8-final-tests)

1. [Release](#9-release)


<!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -->

&nbsp;

## 1. Introduction to the Java *Streams API*

The
[*Java Streams API*](https://docs.oracle.com/en/java/javase/23/docs/api/java.base/java/util/stream/Stream.html)
has been introduced with Java version 8 (2014) to support *data-streams* and *stream-based programming*.

A `Stream` consists of three parts:

1. A streams starts with a `Source` from where data originates or is emitted,

    - e.g. a *Collection* (List, Array, ...), a *Range* or a *Supplier*.

1. A sequence of *chained*
    [*functions*](https://docs.oracle.com/en/java/javase/25/docs/api/java.base/java/util/stream/Stream.html)
    that is applied to each data object passing through the stream,

    - examples: *map()*, *filter()*, *findAny()*, *sorted()*, etc.

1. A `Sink` that *pulls data* from the stream producing a *result* by applying a *terminal*
    [*function*](https://docs.oracle.com/en/java/javase/25/docs/api/java.base/java/util/stream/Stream.html)

    - such as *reduce()*, *sum()*, *collect()*, *forEach()*.

<img src="https://s1.o7planning.com/web-rs/web-image/en/arf-1189995-vi.webp" width="600"/>


<!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -->

&nbsp;

## 2. Branch Structure

This assignment will use a separate branch [*b2-streams*](../../tree/b2-streams)
starting from the same *base* commit.

The commit graph will have three branches:

<img src="https://raw.githubusercontent.com/sgra64/se1-play/refs/heads/markup/streams/git-1.png" width="600"/>

Switch to the main branch and show the commit log:

```sh
git switch main                     # switch to branch 'main'

git log --oneline                   # show current commits on branch 'main'
```
```
772bc52 (HEAD -> main, tag: base) branch commit (empty)     <-- base commit
ef51f55 add junit tests                                     <-- commit 5
ff4e2b0 add src                                             <-- commit 4
e7f3fa5 add .gitmodules                                     <-- commit 3
9988b69 add .gitignore                                      <-- commit 2
e38d285 (tag: root) root commit (empty)                     <-- commit 1 (empty root commit)
```

Create a new branch: `b2-streams` and switch to the new branch.


<!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -->

&nbsp;

## 3. Branch *Setup*

The structure of the project directory ("*working tree*") on this branch is:

```sh
<se1-play>              # project directory
 |
 # content of branch: 'b2-streams' with new package 'streams'
 +-<src>
 |  +-<main>                    # Java source code
 |  |  +--module-info.java          # description of module 'se1.play'
 |  |  |
 |  |  +-<application>              # existing package 'application'
 |  |  |  +--Application.java       # program with main()-method
 |  |  |  +--Runner.java            # new interface
 |  |  |  +--...
 |  |  |
 |  |  +-<streams>                 # new package 'streams' from remote branch 'b2-streams'
 |  |    +--Streams.java           # interface with methods to implement
 |  |    +--StreamsRunner.java     # driver code to run the application from the command line
 |  |    +--package-info.java      # package documentation
 |  | 
 |  |+-<tests>                  # 'streams' test code
 |  |  +-<streams>                  # package 'streams' with unit tests
 |  |     +--Streams_1_tenRandomNumbers_Tests.java
 |  |     +--Streams_2_tenEvenRandomNumbers_Tests.java
 |  |     +--Streams_3_tenSortedEvenRandomNumbers_Tests.java
 |  |     +--Streams_4_filteredNumbers_Tests.java
 |  |     +--Streams_5_filteredNames_Tests.java
 |  |     +--Streams_6_sortedNames_Tests.java
 |  |     +--Streams_7_sortedNamesByLength_Tests.java
 |  |     +--Streams_8_calculateOrderValue_Tests.java
 |  |     +--Streams_9_sortByOrderValue_Tests.java
 |  |
 |  +-<resources>               # none-Java sources, properties files
 |     +--application.properties    # application configuration
 |     +--log4j2.properties         # logger configuration
 |     +-<META-INF>
 |        +--MANIFEST.MF            # packaging information for created .jar
```

Test that the URL of the remote repository has been set:

```sh
git remote -v
```
```
se1-repo   https://github.com/sgra64/se1-play.git (fetch)
se1-repo   https://github.com/sgra64/se1-play.git (push)
```

If the URL is not present, set the URL of the remote repository:

```sh
# set URL to repository to fetch remote branches
git remote add se1-repo https://github.com/sgra64/se1-play.git
```

Two methods exist to pull content from the remote branch *b2-streams*:

- `fetch`, `merge` and `commit` or

- `pull` and `commit` ("*pull*" combines "*fetch*" and "*merge*").

Choose one method to obtain content from the remote branch *b2-streams*
and make it available in the new local branch: `b2-streams`.

```sh
# fetch branch 'b1-numbers' from the remote repository 'se1-play-repo'
git fetch se1-repo b2-streams

# merge content of branch 'b2-streams' into the 'main' branch of the project with:
# '--squash' combine all incoming commits into one local commit
# '--allow-unrelated-histories' allows merging from a repository with no shared history
# '--strategy-option theirs' resolves merge conflicts favoring incoming changes
# 
git merge se1-repo/b2-streams \
    --squash \
    --allow-unrelated-histories \
    --strategy-option theirs
```

Or *"pull"* content (*fetch* and *merge*) from the remote branch. Mind the
similarity to the prior method:

```sh
# pull remote branch 'b2-streams'
git pull se1-repo b2-streams \
    --squash --allow-unrelated-histories --strategy-option theirs
```
```
From github.com:sgra64/se1-play
 * branch            b2-streams -> FETCH_HEAD
Auto-merging src/main/application/Application.java
Squash commit -- not updating HEAD
Automatic merge went well; stopped before committing as requested
```

In both cases, the *merge* is *open* with uncommitted changes:

```sh
git status                      # show status of open merge
```

*Git* shows new or modified files with green lines (staged) that have
not yet been committed:

<img src="https://raw.githubusercontent.com/sgra64/se1-play/refs/heads/markup/streams/git-log-post-merge.png" width="600"/>


The *merge* can now be committed:

```sh
# commit the open merge
git commit -m "pull branch se1-repo/b2-streams"
```
```
[b2-streams 50afb96] pull branch se1-play-repo/b2-streams
 5 files changed, 336 insertions(+), 5 deletions(-)
 create mode 100644 src/application/Runner.java
 create mode 100644 src/streams/Streams.java
 create mode 100644 src/streams/StreamsRunner.java
 create mode 100644 src/streams/package-info.java
```


<!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -->

&nbsp;

## 4. *Build* and *Run*

*Build* the project and *run* the program:

```sh
mk compile run                  # build and run the program
```
```
Hello, se1_play (modular)
Exception in thread "main" java.lang.UnsupportedOperationException: Unimplemente
d method 'getInstance()' in interface 'Streams'. Create an implementation class
and return.
        at se1_play/streams.Streams.getInstance(Streams.java:157)
        at se1_play/application.Application.main(Application.java:27)
```

Solve the problem. Consider how the problem was solved in
[*Step 3*](../../tree/b1-numbers?tab=readme-ov-file#4-build-and-run-the-project)
of the
[*B1 Numbers*](../../tree/b1-numbers) assignment.

*Build* and *run* the program again:

```sh
mk compile run                  # re-build and run the program
```
```
Hello, se1.play (modular)
```

Commit the state of the project with:

- commit message: `"add implementation class StreamsImpl.java"`

```sh
git log --oneline               # show commit log
```

Branch *b2-streams* has advanced showing the new commit:

<img src="https://raw.githubusercontent.com/sgra64/se1-play/refs/heads/markup/streams/git-log-post-streamsimpl-added.png" width="600"/>

<!-- 
```
812dd09 (HEAD -> b1) add implementation class StreamsImpl.java
e58c38a pull branch se1-repo/b2-streams
1e53db5 (tag: base, main) add src/tests, update src/main/module-info.java
3c9b586 add src/resources
d24d184 add src/main
a8f215c add .gitmodules
15d3c87 add .gitignore
cbc8dc0 (tag: root) root commit (empty)
```
-->


<!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -->

&nbsp;

## 5. The *Stream* - Interface

Interface [*src/streams/Streams.java*](../../tree/b2-streams/src/streams/Streams.java)
defines methods to implement in this assignment using the *Java Stream API*:

```java
package streams;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Stream;

import application.Runner;

/**
 * Public interface with functions for the <i>"b2-streams"</i> assignment.
 * 
 * @version <code style=color:green>{@value application.package_info#Version}</code>
 * @author <code style=color:blue>{@value application.package_info#Author}</code>
 */
public interface Streams {

    /**
     * Aufgabe 1: Return 10 random integer numbers in the range [0..999].
     * @return a {@code Stream<Integer>} from which 10 random numbers can be drawn
     */
    Stream<Integer> tenRandomNumbers();

    /**
     * Aufgabe 2: Return 10 even random integer numbers in the range [0..999].
     * @return a {@code Stream<Integer>} from which 10 even random numbers can be drawn
     */
    Stream<Integer> tenEvenRandomNumbers();

    /**
     * Aufgabe 3: Return 10 even sorted random integer numbers in the range [0..999].
     * @return a {@code Stream<Integer>} from which 10 sorted even random numbers can be drawn
     */
    Stream<Integer> tenSortedEvenRandomNumbers();

    /**
     * Map of filter functions for filteredNumbers().
     * 
     * Add a function for name "prime3" to {@link filterFunctions} that returns
     * true for three-digit prime numbers.
     */
    static Map<String, Function<Integer, Boolean>> filterFunctions = Map.of(
        "even", n -> n % 2 == 0,    // filter even numbers
        "div3", n -> n % 3 == 0,    // filter numbers divisible by three
        "prime3", n -> true         // add: filter for three-digit prime numbers
    );

    /**
     * Aufgabe 4: Apply a function from map {@link filterFunctions} to a stream
     * of random integer numbers in the range [0..999] returning only numbers
     * matching the selected filter.
     * @param filter name of the filter function in {@link filterFunctions}
     * @param limit maximum amount of numbers returned
     * @return numbers matching the selected filter
     */
    List<Integer> filteredNumbers(String filter, int limit);


    /*
     * Names used in methods below.
     */
    static final List<String> names = List.of(
        "Hendricks", "Raymond", "Pena", "Gonzalez", "Nielsen", "Hamilton",
        "Graham", "Gill", "Vance", "Howe", "Ray", "Talley", "Brock", "Hall",
        "Gomez", "Bernard", "Witt", "Joyner", "Rutledge", "Petty", "Strong",
        "Soto", "Duncan", "Lott", "Case", "Richardson", "Crane", "Cleveland",
        "Casey", "Buckner", "Hardin", "Marquez", "Navarro"
    );

    /**
     * Aufgabe 5: Return a sub-list of names filtered by a regular expression
     * (see: {@link java.util.regex.Pattern}). The order of names remains unchanged.
     * @param names input names
     * @param regex regular expression according to {@link java.util.regex.Pattern}
     * @return list of names matching the regular expression
     */
    List<String> filteredNames(List<String> names, String regex);

    /**
     * Aufgabe 6: Return names alphabetically sorted up to a given limit.
     * @param names input names
     * @param limit maximum number of names returned
     * @return alphabetically sorted list of names up to the given limit
     */
    List<String> sortedNames(List<String> names, int limit);

    /**
     * Aufgabe 7: Return names sorted by name length as first criteria and
     * within same-length names alphabetically sorted as second criteria.
     * @param names input names
     * @return names sorted by name length
     */
    List<String> sortedNamesByLength(List<String> names);


    /**
     * Aufgabe 8: Class {@link Order} defines an order (Bestellung) of
     * n (units) of an article at a price per unit (in Cent).
     */
    class Order {
        private final String article;
        private final long units;
        private final long unitPrice;
        //
        public Order(String description, long units, long unitPrice) {
            this.article = description;
            this.units = units;
            this.unitPrice = unitPrice;
        }

        // getter methods
        public String article() { return article; }

        public long units() { return units; }

        public long unitPrice() { return unitPrice; }

        // text conversion method
        public String toString() {
            return String.format("%-7s %dx %4d = %6d", article + ",", units, unitPrice, units * unitPrice);
        }
    }

    /*
     * Orders used in methods below.
     */
    static final List<Order> orders = List.of(
        new Order("Becher", 2,  199),   // 2x  199 =  398
        new Order("Tasse",  7,  249),   // 7x  249 = 1743
        new Order("Stift",  4,   49),   // 4x   49 =  196
        new Order("Vase",   2,  999),   // 2x  999 = 1998
        new Order("Kanne",  5, 1499),   // 5x 1499 = 7495
        new Order("Lampe",  2, 1999),   // 2x 1999 = 3998
        new Order("Messer", 6,  789)    // 6x  789 = 4734
    );                                  // Summe:   20562 = 205,62€

    /**
     * Aufgabe 8: Calculate the total value of all orders.
     * @param orders list of orders to process
     * @return total value of orders
     */
    long calculateOrderValue(List<Order> orders);

    /**
     * Aufgabe 9: Return a list of orders sorted by order value (highest-value first).
     * @param orders list of orders to sort
     * @return orders sorted by order value (highest-value first)
     */
    List<Order> sortOrdersByValue(List<Order> orders);

    /**
     * Static getter method that returns an instance of an implementation class of
     * the {@link Streams} interface.
     * @return instance of an implementation class of the {@link Streams} interface
     */
    static Streams getInstance() {
        throw new UnsupportedOperationException("Unimplemented method 'getInstance()' "
            + "in interface 'Streams'. Create an implementation class and return.");
        // return new StreamsImpl();
    }

    /**
     * Factory method that creates instance of the {@link Runner} interface.
     * @param streams instance of the {@link Streams} interface used by the runner
     * @return instance of the {@link Runner} interface
     */
    static Runner createRunner(Streams streams) {
        return new StreamsRunner(streams);
    }
}
```


<!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -->

&nbsp;

## 6. Implement: *tenRandomNumbers()*

Implement the first method: *tenRandomNumbers()* in your implementation class.
The method should create a *Stream source* that generates a random number in
the range `[0..1000]` at each invocation with a limit of 10 numbers.

Rebuild and try the implementation:

```sh
mk compile run tenRandomNumbers
```
```
Hello, se1.play (modular)
 - tenRandomNumbers() -> [275, 24, 206, 757, 283, 103, 180, 863, 975, 659]
```

Run with multiple function calls:

```sh
mk run tenRandomNumbers \
    tenRandomNumbers \
    tenRandomNumbers \
    tenRandomNumbers
```

Output will produce 4 sets of 10 random numbers in the range `[0..1000]`:

```
Hello, se1.play (modular)
 - tenRandomNumbers() -> [617, 546, 22, 470, 81, 796, 575, 124, 723, 312]
 - tenRandomNumbers() -> [274, 356, 844, 854, 502, 563, 29, 141, 310, 186]
 - tenRandomNumbers() -> [70, 994, 376, 664, 752, 719, 958, 415, 611, 899]
 - tenRandomNumbers() -> [178, 437, 686, 299, 199, 761, 28, 221, 218, 87]
```

Add JUnit-tests for the method. Fetch *JUnit-tests* from the remote branch
`b2-streams-tests`:

```sh
# fetch branch 'b1-numbers' from the remote repository 'se1-play-repo'
git fetch se1-repo b2-streams-tests
```
```
remote: Enumerating objects: 43, done.
remote: Counting objects: 100% (43/43), done.
remote: Compressing objects: 100% (15/15), done.
remote: Total 42 (delta 25), reused 37 (delta 22), pack-reused 0 (from 0)
Unpacking objects: 100% (42/42), 11.64 KiB | 28.00 KiB/s, done.
From github.com:sgra64/se1-play
 * branch            b2-streams-tests -> FETCH_HEAD
 * [new branch]      b2-streams-tests -> se1-repo/b2-streams-tests
```

A local copy of the remote branch was created. Show the new remote brach:

```sh
git branch -avv             # show all branches stored in the local git repository
```

The new remote branch `b2-streams-tests` is shown among other branches. It is not
yet merged into the current branch.

```
remotes/se1-play-repo/b2-streams-tests f184a3f update .gitignore, added .tgz
```

Create (restore) the *JUnit-test* for method *tenRandomNumbers()* in the current branch
from the remote branch:

```sh
git restore --source se1-repo/b2-streams-tests -- \
    src/tests/streams/Streams_1_tenRandomNumbers_Tests.java

find src/tests              # show the new test under 'tests/streams'
```
```
src/tests
src/tests/application
src/tests/application/Application_0_always_pass_Tests.java
src/tests/streams
src/tests/streams/Streams_1_tenRandomNumbers_Tests.java
```

Compile and run tests:

```sh
mk clean compile compile-tests      # re-build the project with tests

mk run-tests                        # run tests
```
```
╷
├─ JUnit Jupiter ✔
│  ├─ Application_0_always_pass_Tests ✔
│  │  ├─ test_001_always_pass() ✔
│  │  └─ test_002_always_pass() ✔
│  └─ Streams_1_tenRandomNumbers_Tests ✔
│     └─ test100_tenRandomNumbers_regular() ✔      <-- new test
├─ JUnit Vintage ✔
└─ JUnit Platform Suite ✔

Test run finished after 235 ms
[         3 tests successful      ]
[         0 tests failed          ]
```

Run *JUnit-tests* also in the IDE.

<img src="https://raw.githubusercontent.com/sgra64/se1-play/refs/heads/markup/streams/vscode-tests-tenRandomNumbers.png" width="600"/>


&nbsp;

When tests are passing, commit the state of the implemented method *tenRandomNumbers()*
to branch *b2-streams* with message:

- commit message: `"Aufgabe 1.) Stream<Integer> tenRandomNumbers()"`

Branch *b2-streams* has advanced showing the new commit:
<!-- 
<img src="https://raw.githubusercontent.com/sgra64/se1-play/refs/heads/markup/streams/git-log-post-tenRandomNumbers.png" width="600"/>
-->
```
6fca743 (HEAD -> b2) Aufgabe 1.) Stream<Integer> tenRandomNumbers()
812dd09 add implementation class StreamsImpl.java
e58c38a pull branch se1-repo/b2-streams
1e53db5 (tag: base, main) add src/tests, update src/main/module-info.java
```

The commit should only contain the affected files:

1. the modified implementation class: `StreamsImpl.java` and

1. the new test: `Streams_1_tenRandomNumbers_Tests.java`.

Verify by comparing the two last commits:

```sh
git diff HEAD~1..HEAD --name-status     # compare the two last commits
```
```
M  src/streams/StreamsImpl.java                         <-- M: modified file
A  tests/streams/Streams_1_tenRandomNumbers_Tests.java  <-- A: added file
```


<!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -->

&nbsp;

## 7. Implement Remaining *Streams*-Functions

Implement the remaining methods of the *Streams.java* interface one after the
other.

Commit each implemented function with the corresponding *JUnit-test*. Commit
only when the test passes.

After completion, the commit-log on branch `b2-streams` shows the following
commits:

```
d27dac1 (HEAD -> b2-streams) Aufgabe 9.) List<Order> sortOrdersByValue(List<Order> orders)
b81471b Aufgabe 8.) long calculateOrderValue(List<Order> orders)
fc3fb82 Aufgabe 7.) List<String> sortedNamesByLength(List<String> names)
3c41f04 Aufgabe 6.) List<String> sortedNames(List<String> names, int limit)
0fddc52 Aufgabe 5.) List<String> filteredNames(List<String> names, String regex)
97d8477 Aufgabe 4.) List<Integer> filteredNumbers(String filter, int limit)
2739663 Aufgabe 3.) Stream<Integer> tenSortedEvenRandomNumbers()
1f6780c Aufgabe 2.) Stream<Integer> tenEvenRandomNumbers()
d75654e Aufgabe 1.) Stream<Integer> tenRandomNumbers()
7e4ae57 merge commit se1-repo/b2-streams
1e53db5 (tag: base, main) add src/tests, update src/main/module-info.java
...
```


<!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -->

&nbsp;

## 8. Final Tests

The final result will show all tests passing. Leave out tests that are
not passing.

```sh
mk clean compile compile-tests run-tests    # run all tests
```

Or run tests individually (remove tests that are failing):

```sh
# or run tests selectively (remove tests that are failing)
mk run-tests \
    -c application.Application_0_always_pass_Tests \
    -c streams.Streams_1_tenRandomNumbers_Tests \
    -c streams.Streams_2_tenEvenRandomNumbers_Tests \
    -c streams.Streams_3_tenSortedEvenRandomNumbers_Tests \
    -c streams.Streams_4_filteredNumbers_Tests \
    -c streams.Streams_5_filteredNames_Tests \
    -c streams.Streams_6_sortedNames_Tests \
    -c streams.Streams_7_sortedNamesByLength_Tests \
    -c streams.Streams_8_calculateOrderValue_Tests \
    -c streams.Streams_9_sortByOrderValue_Tests
```

Output with all tests passing:

```
╷
├─ JUnit Jupiter ✔
│  ├─ Streams_5_filteredNames_Tests ✔
│  │  ├─ test500_filteredNames_regular() ✔
│  │  ├─ test590_filteredNames_irregularNamesNull() ✔
│  │  ├─ test591_filteredNames_irregularRegexNull() ✔
│  │  └─ test592_filteredNames_irregularNamesAndRegexNull() ✔
│  ├─ Streams_6_sortedNames_Tests ✔
│  │  ├─ test600_sortedNames_regular() ✔
│  │  ├─ test601_sortedNames_regular() ✔
│  │  ├─ test610_sortedNames_emptyNames() ✔
│  │  ├─ test690_sortedNames_irregularNamesNull() ✔
│  │  ├─ test691_sortedNames_irregularLimitNegativ() ✔
│  │  └─ test692_sortedNames_irregularNamesNullAndLimitNegativ() ✔
│  ├─ Streams_7_sortedNamesByLength_Tests ✔
│  │  ├─ test700_sortedNamesByLength_regular() ✔
│  │  ├─ test710_sortedNamesByLength_emptyNames() ✔
│  │  └─ test790_sortedNamesByLength_irregular_names_Null() ✔
│  ├─ Streams_2_tenEvenRandomNumbers_Tests ✔
│  │  └─ test200_tenEvenRandomNumbers_regular() ✔
│  ├─ Application_0_always_pass_Tests ✔
│  │  ├─ test_001_always_pass() ✔
│  │  └─ test_002_always_pass() ✔
│  ├─ Streams_9_sortByOrderValue_Tests ✔
│  │  ├─ test900_sortByOrderValue_regular() ✔
│  │  ├─ test901_sortByOrderValue_regular() ✔
│  │  ├─ test910_sortByOrderValue_emptyOrders() ✔
│  │  └─ test990_sortByOrderValue_irregular_orders_Null() ✔
│  ├─ Streams_4_filteredNumbers_Tests ✔
│  │  ├─ test400_filteredNumbers_50evenNumbers_regular() ✔
│  │  ├─ test410_filteredNumbers_50divisibleBy3Numbers_regular() ✔
│  │  ├─ test420_filteredNumbers_50primeNumbers_regular() ✔
│  │  ├─ test430_filteredNumbers_different_even_numbers_returned() ✔
│  │  ├─ test431_filteredNumbers_different_div_by_three_numbers_returned() ✔
│  │  ├─ test432_filteredNumbers_different_prime_numbers_returned() ✔
│  │  ├─ test490_filteredNumbers_50evenNumbers_illegalFilter_null() ✔
│  │  ├─ test491_filteredNumbers_50evenNumbers_illegalFilter_empty() ✔
│  │  ├─ test492_filteredNumbers_50evenNumbers_illegalFilter_unknown() ✔
│  │  └─ test495_filteredNumbers_50evenNumbers_illegalLimit_negativ() ✔
│  ├─ Streams_1_tenRandomNumbers_Tests ✔
│  │  └─ test100_tenRandomNumbers_regular() ✔
│  ├─ Streams_3_tenSortedEvenRandomNumbers_Tests ✔
│  │  └─ test300_tenSortedEvenRandomNumbers_regular() ✔
│  └─ Streams_8_calculateOrderValue_Tests ✔
│     ├─ test800_calculateValue_regular() ✔
│     ├─ test801_calculateValue_regular() ✔
│     ├─ test810_calculateValue_emptyOrders() ✔
│     └─ test890_calculateValue_irregular_orders_Null() ✔
├─ JUnit Vintage ✔
└─ JUnit Platform Suite ✔

Test run finished after 283 ms
[        36 tests successful      ]     <-- 36 tests are passing
[         0 tests failed          ]     <--  0 tests failed
```


<!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -->

&nbsp;

## 9. Release

When tests are passing, both branches `b1-numbers` and `b2-streams` will be
combined on a new branch named `release-prep` that is used to perform final
tests preping a release.

Create two new branches off the `base`-commit:

- Branch `release-prep` to merge branches `b1-numbers` and `b2-streams` and perform
    final tests.

- Branch `release` to hold the commit of the final release tagged with `"RELEASE-1.0.0"`.

<img src="https://raw.githubusercontent.com/sgra64/se1-play/refs/heads/markup/img/git-release.png" width="1000"/>


&nbsp;

### 9.1 Create Branch *"release-prep"*

Merge branch `b1-numbers` to branch `release-prep` as a single commit.

Show *src* to see content of the merged branch has arrived:

```sh
find src
```
```
src
src/main
src/main/application
src/main/application/Application.java
src/main/application/package-info.java
src/main/application/Runner.java
src/main/module-info.java
src/main/numbers
src/main/numbers/Numbers.java
src/main/numbers/NumbersData.java
src/main/numbers/NumbersImpl.java
src/main/numbers/NumbersImpl_FindAllSums.java
src/main/numbers/NumbersRunner.java
src/resources
src/resources/application.properties
src/resources/log4j2.properties
src/resources/META-INF
src/resources/META-INF/MANIFEST.MF
src/tests
src/tests/application
src/tests/application/Application_0_always_pass_Tests.java
src/tests/numbers
src/tests/numbers/Matchers.java
src/tests/numbers/Numbers_1_sum_Tests.java
src/tests/numbers/Numbers_2_sum_positive_even_Tests.java
src/tests/numbers/Numbers_3_sum_recursion_Tests.java
src/tests/numbers/Numbers_4_find_first_Tests.java
src/tests/numbers/Numbers_5_find_last_Tests.java
src/tests/numbers/Numbers_6_find_all_Tests.java
src/tests/numbers/Numbers_7a_find_sums_Tests.java
src/tests/numbers/Numbers_7b_find_sums_duplicates_Tests.java
src/tests/numbers/Numbers_8a_find_all_sums_Tests.java
src/tests/numbers/Numbers_8b_find_all_sums_XL_Tests.java
```

Make sure the merge builds and runs tests:

```sh
mk build                    # clean project build:
                            # - clean compile compile-tests run-tests package
```

The clean project build also runs tests:

```
Test run finished after 8295 ms
[        80 tests successful      ]     <-- 80 tests from 'b1-numbers'
[         0 tests failed          ]     <--  0 tests failed
```

Test the final artifact with example:

```sh
java -jar target/application-1.0.0-SNAPSHOT.jar findAllSums numb_3 sum=1000
```
```
Hello, se1-play
 - findAllSums(numb_3, sum=1000) -> [
    - [500, 7, 493],
    - [500, 485, 15],
    - [485, 7, 493, 15],
    - [36, 408, 78, 15, 463],
    - [36, 23, 440, 408, 78, 15]
   ], solutions: 5
```

If all this works, commit with merge with message `"merge b1-numbers"`.


&nbsp;

### 9.2 Merge branch *"b2-streams"* to Branch *"release-prep"*

Next, merge branch `b2-streams` to branch `release-prep` as single commit.
You will likely receive a *merge-conflict*:

```
Auto-merging src/main/application/Application.java
CONFLICT (content): Merge conflict in src/main/application/Application.java
Automatic merge failed; fix conflicts and then commit the result.
```

First, show *src* to see content of both merged branched has arrived:

```sh
find src
```
```
src
src/main
src/main/application
src/main/application/Application.java
src/main/application/package-info.java
src/main/application/Runner.java
src/main/module-info.java
src/main/numbers                        <-- package 'numbers' from branch 'b1-numbers'
src/main/numbers/Numbers.java
src/main/numbers/NumbersData.java
src/main/numbers/NumbersImpl.java
src/main/numbers/NumbersImpl_FindAllSums.java
src/main/numbers/NumbersRunner.java
src/main/streams                        <-- package 'streams' from branch 'b2-streams'
src/main/streams/Streams.java
src/main/streams/StreamsImpl.java
src/main/streams/StreamsRunner.java
src/resources
src/resources/application.properties
src/resources/log4j2.properties
src/resources/META-INF
src/resources/META-INF/MANIFEST.MF
src/tests
src/tests/application
src/tests/application/Application_0_always_pass_Tests.java
src/tests/numbers                       <-- tests for 'numbers' from branch 'b1-numbers'
src/tests/numbers/Matchers.java
src/tests/numbers/Numbers_1_sum_Tests.java
src/tests/numbers/Numbers_2_sum_positive_even_Tests.java
src/tests/numbers/Numbers_3_sum_recursion_Tests.java
...
src/tests/streams                       <-- tests for 'streams' from branch 'b2-streams'
src/tests/streams/Streams_1_tenRandomNumbers_Tests.java
src/tests/streams/Streams_2_tenEvenRandomNumbers_Tests.java
src/tests/streams/Streams_3_tenSortedEvenRandomNumbers_Tests.java
...
```

Next, resolve the *merge conflict* such that both *Runners* created from *Numbers*
and from *Streams* run.


&nbsp;

### 9.3 Final Test on Branch *"release-prep"*

Then, make sure the merge builds and runs tests:

```sh
mk build                    # clean project build:
                            # - clean compile compile-tests run-tests package
```

The clean project build also runs tests:

```
Test run finished after 8295 ms
[       114 tests successful      ]     <-- 114 tests from 'b1-numbers' and 'b2-streams'
[         0 tests failed          ]     <--   0 tests failed
```

Test the final artifact with a *numbers*-example:

```sh
java -jar target/application-1.0.0-SNAPSHOT.jar findAllSums numb_3 sum=1000
```
```
Hello, se1-play
 - findAllSums(numb_3, sum=1000) -> [
    - [500, 7, 493],
    - [500, 485, 15],
    - [485, 7, 493, 15],
    - [36, 408, 78, 15, 463],
    - [36, 23, 440, 408, 78, 15]
   ], solutions: 5
```

Test the final artifact with a *streams*-example:

```sh
java -jar target/application-1.0.0-SNAPSHOT.jar tenSortedEvenRandomNumbers
```
```
Hello, se1-play
 - tenSortedEvenRandomNumbers() -> [18, 30, 48, 260, 310, 358, 492, 528, 618, 898]
```

If all this works, commit with merge with message `"merge b2-streams"` and
show the commit log:

```sh
git log --first-parent --oneline release-prep
```

Output shows two commits added on branch *"release-prep"* that was started off
the *"base"* commit:

```
e0bb53b (HEAD -> release-prep) merge b2-streams
ebc0c71 merge b1-numbers
1e53db5 (tag: base, release, main) add src/tests, update src/main/module-info.java
...
3c9b586 add src/resources
d24d184 add src/main
a8f215c add .gitmodules
15d3c87 add .gitignore
cbc8dc0 (tag: root) root commit (empty)
```


&nbsp;

### 9.4 Release

For release, merge Branch *"release-prep"* to Branch *"release"* as a single
commit and tag with "*RELEASE-1.0.0*".

Perform a final test on Branch *"release"*:

```sh
mk build
```
```
Test run finished after 8295 ms
[       114 tests successful      ]     <-- 114 tests from 'b1-numbers' and 'b2-streams'
[         0 tests failed          ]     <--   0 tests failed
```

Test the final artifact with a *streams*-example:

```sh
java -jar target/application-1.0.0-SNAPSHOT.jar \
    findAllSums numb_3 sum=1000 \
    tenSortedEvenRandomNumbers
```

Output shows results for the *numbers* and *streams* examples:

```
Hello, se1-play
 - findAllSums(numb_3, sum=1000) -> [
    - [500, 7, 493],
    - [500, 485, 15],
    - [485, 7, 493, 15],
    - [36, 408, 78, 15, 463],
    - [36, 23, 440, 408, 78, 15]
   ], solutions: 5
 -
 - tenSortedEvenRandomNumbers() -> [18, 172, 290, 376, 594, 636, 686, 728, 880, 916]
```

If all this works, commit with merge with message `"merge prelease-prep"`.


&nbsp;

### 9.4.1 Release Notes

Add file `RELEASE-NOTES.md` to the project directory
([*example*](https://blog.releasenotes.io/changelog-vs-release-notes/)):

```
## Version 1.0.0 - First Release

We're proud to announce our software, designed to supercharge your productivity!

New features:

**Numbers processing**: perform powerful numbers processing tasks.

**Streams processing**: Gain deeper insights into your data.

**Unbeaten Performance**: We've turbocharged our software, resulting in 
   50% faster processing times.

**Bug Fixes and Improvements**:
   - Fixed: The pesky timezone issue affecting our international users
   - Improved: Concurrent editing now works seamlessly for team collaboration
   - Enhanced: GDPR compliance with new data export feature

❗ **Important**: This version drops support for Java 11. 
   Please upgrade to a modern Java JDK to enjoy all new features.

[Update Now] [Read Full Documentation]
```


&nbsp;

### 9.4.2 Changelog for Release

Add file `CHANGELOG.md` to the project directory
([*example*](https://blog.releasenotes.io/changelog-vs-release-notes/)):

```
## [2.1.0] - 2026-03-25
### Added
- New dark mode feature for improved nighttime viewing (#2468)
- API endpoint for exporting user data in compliance with GDPR (/api/v1/user/export)

### Changed
- Upgraded React.js to version 18.0 for improved performance (#3579)
- Refactored database queries to optimize load times on the dashboard

### Deprecated
- Legacy authentication method using API keys (to be removed in v3.0)

### Removed
- Support for Internet Explorer 11 (#4321)

### Fixed
- Resolved race condition in concurrent user edits (#5432)
- Corrected timezone handling for international users (#6543)

### Security
- Implemented rate limiting on login attempts to prevent brute force attacks
- Updated bcrypt library to address potential vulnerability (CVE-2024-XXXX)
```

Commit with message `"add RELEASE-NOTES.md, CHANGELOG.md"`.
Tag the commit with `RELEASE-1.0.0`.

Show the commit log:

```sh
git log --first-parent --oneline release
```

Output shows two commits added on branch *"release-prep"* that was started off
the *"base"* commit:

<img src="https://raw.githubusercontent.com/sgra64/se1-play/refs/heads/markup/streams/git-log-release.png" width="600"/>

<!-- 
```
fcbc4cc (HEAD -> release, tag: RELEASE-1.0.0) add RELEASE-NOTES.md, CHANGELOG.md
837396b merge prelease-prep
1e53db5 (tag: base, main) add src/tests, update src/main/module-info.java
3c9b586 add src/resources
d24d184 add src/main
a8f215c add .gitmodules
15d3c87 add .gitignore
cbc8dc0 (tag: root) root commit (empty)
```
-->
