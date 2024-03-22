package com.asm3.prj321x.thienhtfx17332.dto.userDTO;

import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LockUserDTO {
    private int userId;
    private int isActive;
    private String description;

}

