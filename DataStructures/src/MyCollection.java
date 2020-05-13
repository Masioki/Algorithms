import java.util.List;

public interface MyCollection<T extends Comparable<T>> {

     void insert(T s) ;
     void remove(T s);
     boolean contains(T s) ;
     T min();
     T max();
     T successor(T s);
     List<T> inorder();
     int size();
}
