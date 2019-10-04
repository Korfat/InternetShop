package internetshop.service;

import internetshop.model.Role;

import java.util.Optional;

public interface RoleService {
    Optional<Role> create(Role role);

    Optional<Role> get(Long id);

    Optional<Role> update(Role role);

    void delete(Long id);
}
