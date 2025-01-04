/** user repo interface
 * @author Dodi Mircea Ovidiu
 * @version 20 Nov 2025
 */

package com.example.ryde.repository;

import com.example.ryde.model.MyUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<MyUser, Long> {
    MyUser findByUsername(String username);
    MyUser findByEmail(String email);
}