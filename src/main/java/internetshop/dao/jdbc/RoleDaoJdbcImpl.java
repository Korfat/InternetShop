package internetshop.dao.jdbc;

import internetshop.dao.RoleDao;
import internetshop.lib.Dao;
import internetshop.model.Role;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Optional;

import org.apache.log4j.Logger;

@Dao
public class RoleDaoJdbcImpl extends AbstractDao<Role> implements RoleDao {
    private static Logger logger = Logger.getLogger(UserDaoJdbcImpl.class);

    public RoleDaoJdbcImpl(Connection connection) {
        super(connection);
    }

    @Override
    public Optional<Role> create(Role role) {
        String query = "INSERT INTO roles (name) VALUES (?);";
        try (PreparedStatement preparedStatement
                     = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, role.getName());
            preparedStatement.executeUpdate();
            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    role.setId(generatedKeys.getLong(1));
                    return Optional.of(role);
                } else {
                    throw new SQLException("Creating role failed, no ID obtained.");
                }
            }
        } catch (SQLException e) {
            logger.error("Can't create role", e);
        }
        return Optional.empty();
    }

    @Override
    public Optional<Role> get(Long id) {
        String query = "SELECT * FROM roles WHERE role_id = ?;";
        Role role = new Role();
        try (PreparedStatement preparedStatement
                     = connection.prepareStatement(query)) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Long roleId = resultSet.getLong("role_id");
                String name = resultSet.getString("name");
                role.setId(roleId);
                role.setName(name);
            }
            return Optional.of(role);
        } catch (SQLException e) {
            logger.error("Can't get role", e);
        }
        return Optional.empty();
    }

    @Override
    public Optional<Role> update(Role role) {
        String query = "UPDATE roles SET name = ? WHERE role_id = ?;";
        try (PreparedStatement preparedStatement
                     = connection.prepareStatement(query)) {
            preparedStatement.setString(1, role.getName());
            preparedStatement.setLong(2, role.getId());
            preparedStatement.executeUpdate();
            return Optional.of(role);
        } catch (SQLException e) {
            logger.error("Can't update role", e);
        }
        return Optional.empty();
    }

    @Override
    public void delete(Long id) {
        String query = "DELETE FROM roles WHERE role_id = ?;";
        try (PreparedStatement preparedStatement
                     = connection.prepareStatement(query)) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Can't delete role", e);
        }
    }
}
