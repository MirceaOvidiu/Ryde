/** Data transfer object class for user fields
 * @author Dodi Mircea Ovidiu
 * @version 20 11 2024
 */

package com.example.ryde.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private long id;
    private String username;
    private String password;
    private String email;
    private String iban;
    private String role;
}
