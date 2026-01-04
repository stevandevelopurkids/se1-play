package streams;

import java.util.List;
import java.util.Random;
import java.util.stream.Stream;

public class StreamsImpl implements Streams{

    @Override
    public Stream<Integer> tenRandomNumbers() {
       return new Random()
            .ints(10, 0, 1000)
            .boxed();
    }

    @Override
    public Stream<Integer> tenEvenRandomNumbers() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'tenEvenRandomNumbers'");
    }

    @Override
    public Stream<Integer> tenSortedEvenRandomNumbers() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'tenSortedEvenRandomNumbers'");
    }

    @Override
    public List<Integer> filteredNumbers(String filter, int limit) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'filteredNumbers'");
    }

    @Override
    public List<String> filteredNames(List<String> names, String regex) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'filteredNames'");
    }

    @Override
    public List<String> sortedNames(List<String> names, int limit) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'sortedNames'");
    }

    @Override
    public List<String> sortedNamesByLength(List<String> names) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'sortedNamesByLength'");
    }

    @Override
    public long calculateOrderValue(List<Order> orders) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'calculateOrderValue'");
    }

    @Override
    public List<Order> sortOrdersByValue(List<Order> orders) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'sortOrdersByValue'");
    }
    
}
