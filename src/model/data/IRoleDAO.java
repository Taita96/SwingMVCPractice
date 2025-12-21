package model.data;

import model.entity.Roles;

public interface IRoleDAO {

    Roles existsById(String idrole);
}
