package imp;

import java.sql.SQLException;
import java.util.List;

public interface GenericDao<T> {

    public void add(T enty) throws SQLException, Exception;
    public void update(T enty) throws SQLException, Exception;
    public void remove(T enty) throws SQLException, Exception;
    public List<T> findAll() throws  SQLException, Exception;
    public T findById(T enty) throws SQLException, Exception;

}
