/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tunifast.esprit.iservice;

import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author ASUS
 */
public interface Iservice<T> {
  void ajouter(T t) throws SQLException;
    void delete(int id) throws SQLException;
    void update(T t, int id) throws SQLException;
    List<T> readAll() throws SQLException;
}




