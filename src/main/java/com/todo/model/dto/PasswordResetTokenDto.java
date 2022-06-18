package com.todo.model.dto;

import java.util.Date;

import com.todo.model.PasswordResetToken;
import com.todo.model.Roles;
import com.todo.model.Utilisateur;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class PasswordResetTokenDto {

	 
	    private Integer id;
	 
	    private String token;
	 
	    private UtilisateurDto utilisateur;
	 
	    private Date expiryDate;
	    
	    public static PasswordResetTokenDto fromEntity(PasswordResetToken passwordResetToken) {
	    	
	    	if(passwordResetToken == null) {
				return null;
			}
			return PasswordResetTokenDto.builder()
					.id(passwordResetToken.getId())
					.token(passwordResetToken.getToken())
					.expiryDate(passwordResetToken.getExpiryDate())
					.utilisateur(UtilisateurDto.fromEntity(passwordResetToken.getUtilisateur()))
					.build();
	    }
	    
	    public static PasswordResetToken toEntity(PasswordResetTokenDto dto) {
			
			if(dto == null) {
				return null;
			}
			
			PasswordResetToken passwordResetToken = new PasswordResetToken();
			passwordResetToken.setId(dto.getId());
			passwordResetToken.setExpiryDate(dto.getExpiryDate());
			passwordResetToken.setToken(dto.getToken());
			return passwordResetToken;
		}
}
