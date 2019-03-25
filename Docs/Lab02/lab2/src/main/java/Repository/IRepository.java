package Repository;

import java.util.List;

public interface IRepository<T,ID>
{
    boolean add(T t);
    boolean remove(ID id);
    boolean update(ID id,T t);
    T find(ID id);
    List<T> getAll();
}
