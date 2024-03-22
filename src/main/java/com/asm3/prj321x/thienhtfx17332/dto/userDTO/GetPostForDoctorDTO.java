package com.asm3.prj321x.thienhtfx17332.dto.userDTO;

import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GetPostForDoctorDTO {
    private int postId;
    private String title;
    private int specId;
    private int clinId;
    private int writerId;
    private int confirm;
    private String cancel;

}
