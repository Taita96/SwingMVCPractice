package model.entity;

import java.util.Objects;

public class UserHasRoles {

    private Integer idUser;
    private String idRole;

    public UserHasRoles(Integer idUser, String idRole) {
        this.idUser = idUser;
        this.idRole = idRole;
    }

    public UserHasRoles(){

    }

    public Integer getIdUser() {
        return idUser;
    }

    public void setIdUser(Integer idUser) {
        this.idUser = idUser;
    }

    public String getIdRole() {
        return idRole;
    }

    public void setIdRole(String idRole) {
        this.idRole = idRole;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserHasRoles that = (UserHasRoles) o;
        return Objects.equals(idUser, that.idUser) &&
                Objects.equals(idRole, that.idRole);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idUser, idRole);
    }
}
