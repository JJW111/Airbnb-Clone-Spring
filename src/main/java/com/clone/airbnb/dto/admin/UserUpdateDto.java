package com.clone.airbnb.dto.admin;

import java.util.Date;
import java.util.UUID;

import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import com.clone.airbnb.common.Common;
import com.clone.airbnb.entity.Avatar;
import com.clone.airbnb.entity.User;
import com.clone.airbnb.entity.enu.Currency;
import com.clone.airbnb.entity.enu.Gender;
import com.clone.airbnb.entity.enu.Language;
import com.clone.airbnb.entity.enu.LoginMethod;
import com.clone.airbnb.entity.enu.Role;
import com.clone.airbnb.utils.ValidUtils;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UserUpdateDto implements DtoToOriginalSwitcher<User> {
	private String username;
	private String password;
    @Size(min = 1, max = 30)
    private String firstName;
    @Size(min = 1, max = 30)
    private String lastName;
    private Avatar avatar;
    private MultipartFile avatarFile;
    @Size(max = 100)
    private String bio;
    @DateTimeFormat(pattern = Common.DATE_FORMAT)
    private Date birthdate;
    private Gender gender = Gender.OTHER;
    private Language language = Language.KOREAN;
    private Currency currency = Currency.KRW;
    private Boolean superhost = false;
	private Role role = Role.USER;
    private Boolean emailVerified = false;
    private String emailSecret = UUID.randomUUID().toString().replace("-", "");
    private LoginMethod loginMethod = LoginMethod.EMAIL;
    
    
    @Override
    public User toOriginal() {
    	User user = new User();
    	user.setFirstName(this.getFirstName());
    	user.setLastName(this.getLastName());
    	user.setBio(this.getBio());
    	user.setBirthdate(this.getBirthdate());
    	user.setGender(this.getGender());
    	user.setLanguage(this.getLanguage());
    	user.setCurrency(this.getCurrency());
    	user.setSuperhost(this.getSuperhost());
    	user.setRole(this.getRole());
    	user.setEmailVerified(this.getEmailVerified());
    	user.setEmailSecret(this.getEmailSecret());
    	user.setLoginMethod(this.getLoginMethod());
    	Avatar avatar = null;
    	if (ValidUtils.isValid(this.getAvatarFile())) {
        	avatar = new Avatar();
    		avatar.setFile(this.getAvatarFile());
    	} else {
    		avatar = this.getAvatar();
    	}
    	user.setAvatar(avatar);
    	
    	return user;
    }
}
