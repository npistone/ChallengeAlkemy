/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package disney.repositorios;

import disney.entidades.ERol;
import disney.entidades.Rol;
import java.util.Optional;

/**
 *
 * @author NPist
 */
public interface RolRepositorio {
    Optional<Rol> findByName(ERol name);
}
