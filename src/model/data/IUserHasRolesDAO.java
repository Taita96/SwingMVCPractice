package model.data;

import model.entity.Roles;

public interface IUserHasRolesDAO {

    void addRoleToUser(int userId, String roleName);

    void removeRoleFromUser(int userId, String roleName);

    Roles findRolesByUser(int userId);

}
