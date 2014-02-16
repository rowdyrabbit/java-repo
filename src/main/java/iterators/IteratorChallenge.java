package iterators;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class IteratorChallenge {

    public static void main(String[] args) {
        final List<String> list = new ArrayList() {{ add("Hello"); }};
        final Iterator<String> iterator = list.iterator();
        System.out.println(iterator.next());
        list.add("World");
        // FIXME Challenge: how do we stop this from throwing a ConcurrentModificationException?
        System.out.println(iterator.next());
    }
}
