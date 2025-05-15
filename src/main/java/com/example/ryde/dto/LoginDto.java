/** Data transfer object class for login info.
 * @author Dodi Mircea Ovidiu
 * @version 20 11 2024
 */

package com.example.ryde.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LoginDto {
    private String username;
    private String password;
}
