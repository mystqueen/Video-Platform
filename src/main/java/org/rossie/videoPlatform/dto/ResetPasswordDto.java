package org.rossie.videoPlatform.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
public class ResetPasswordDto {
    private UUID resetToken;
    private String newPassword;

}
