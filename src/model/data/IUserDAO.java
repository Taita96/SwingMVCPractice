package model.data;

import model.entity.Roles;
import model.entity.User;

import java.time.LocalDate;
import java.util.List;

public interface IUserDAO {

     List<User> findAll();
     List<String> findAllByUserName();
     List<String> findAllByEmail();
     User findUserById(int iduser);

     boolean existByUsername(String username);
     boolean existByEmail(String email);
     boolean save(User user);
     boolean existById(User user);
     boolean update(User user);
     boolean deleteById(User user);
     void savedAdmin();
     void roleAdmin();
}
