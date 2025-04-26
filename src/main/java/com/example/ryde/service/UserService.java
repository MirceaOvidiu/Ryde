/** interface class for user related operations
 * @author Dodi Mircea Ovidiu
 * @version 25 nov 2024
 */

package com.example.ryde.service;

import com.example.ryde.dto.LoginDto;
import com.example.ryde.dto.UserDto;

import java.util.List;

public interface UserService {
    UserDto createUser(UserDto userDto);
    UserDto loginUser(LoginDto loginDto);
    List<UserDto> getAllUsers();
    void deleteUser(Long id);
}
