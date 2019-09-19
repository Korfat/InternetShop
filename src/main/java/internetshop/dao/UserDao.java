package internetshop.dao;

import internetshop.model.User;

import java.util.List;

public interface UserDao {
    User create(User item);

    User get(Long id);

    List<User> getAll();

    User update(User item);

    User delete(Long id);

    User deleteByUser(User item);
}
