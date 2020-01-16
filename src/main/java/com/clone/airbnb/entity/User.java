package com.clone.airbnb.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.multipart.MultipartFile;

import com.clone.airbnb.admin.entity.AdminFormEntity;
import com.clone.airbnb.admin.form.annotation.CheckBoxForm;
import com.clone.airbnb.admin.form.annotation.DateForm;
import com.clone.airbnb.admin.form.annotation.EntityForm;
import com.clone.airbnb.admin.form.annotation.ImageUploadForm;
import com.clone.airbnb.admin.form.annotation.PasswordForm;
import com.clone.airbnb.admin.form.annotation.SelectBoxForm;
import com.clone.airbnb.admin.form.annotation.TextAreaForm;
import com.clone.airbnb.admin.form.annotation.TextForm;
import com.clone.airbnb.admin.form.annotation.UserEntity;
import com.clone.airbnb.admin.form.annotation.UsernameForm;
import com.clone.airbnb.common.Common;
import com.clone.airbnb.dto.SafeUser;
import com.clone.airbnb.entity.enu.Currency;
import com.clone.airbnb.entity.enu.Gender;
import com.clone.airbnb.entity.enu.Language;
import com.clone.airbnb.entity.enu.LoginMethod;
import com.clone.airbnb.entity.enu.Role;
import com.clone.airbnb.entity.file.Avatar;
import com.clone.airbnb.entity.sup.DateTimeModel;
import com.clone.airbnb.utils.BeanUtils;
import com.clone.airbnb.utils.ValidUtils;
import com.clone.airbnb.validator.annotation.UniqueUsername;

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@EntityForm
@UserEntity
@Entity
@Table(name = "user")
@ToString(exclude = { "password" }, callSuper = true)
@Getter
@Setter(AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode(callSuper = false, of = { "username" })
public class User extends DateTimeModel implements AdminFormEntity<User> {
	
    @Id
    @GeneratedValue
    private Integer id;

	
	
    @UsernameForm(maxlength = 150, placeholder = "airbnbclone@gmail.com")
    @Column(unique = true, nullable = false)
    @Length(max = 150)
    private String username;
    
    
    
    @PasswordForm(maxlength = 30, placeholder = "알파벳으로 시작해야 하며 8자리에서 30자리까지 입력가능합니다. 최소한 1개의 숫자를 포함해야 합니다.")
    @Column(nullable = false)
    private String password;

    
    
    @TextForm(blank = false, maxlength = 30)
    @Column(nullable = false)
    @Length(max = 30)
    private String firstName;
    
    
    
    @TextForm(blank = false, maxlength = 30)
    @Column(nullable = false)
    @Length(max = 30)
    private String lastName;
    
    
    
    @ImageUploadForm(formName = "avatarFile")
    @OneToOne(targetEntity = Avatar.class, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(referencedColumnName = "id")
    private Avatar avatar;

    
    
    @TextAreaForm(maxlength = 100, rows = 5, placeholder = "최대 100자 까지 자기소개를 입력하세요...")
    @Length(max = 100)
    private String bio;
  
    
    
    @DateForm
    @Temporal(TemporalType.DATE)
    private Date birthdate;
    
    
    
    @SelectBoxForm(blank = false, defaultOption="성별 선택")
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Gender gender;
    
    
    
    @SelectBoxForm(blank = false, defaultOption="언어 선택")
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Language language;
    
    
    
    @SelectBoxForm(blank = false, defaultOption="통화 선택")
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Currency currency;

    
    
    @CheckBoxForm
    @Column(nullable = false)
    private Boolean superhost;
    
    
    
    @SelectBoxForm(blank = false, defaultOption="권한 선택")
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
	private Role role;
    
    
    
    @Column(nullable = false)
    private Boolean emailVerified;
    
    
    
    private String emailSecret;
    
    
    
    @SelectBoxForm(blank = false, defaultOption="로그인 유형")
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private LoginMethod loginMethod;
    
    
    
    
    @OneToMany(mappedBy = "host", fetch = FetchType.LAZY)
    private List<Room> rooms;
    
    
    
    
    private void setRooms(List<Room> rooms) {
		if (rooms == null) return;
		if (this.getRooms() == null) {
			this.rooms = new ArrayList<>();
		}
		this.getRooms().clear();
		this.getRooms().addAll(rooms);
	}
    
    
    
    /******** Builder 클래스 선언 **********/
    @Getter
    @ToString(exclude = { "password", "retypePassword" })
    public static class Builder {
        private Integer id;
        @NotBlank
        @Size(max = 150)
        @Email
        @UniqueUsername
        private String username;
        @NotBlank(message = "")
        @Size(min = 8, max = 30, message = "")
        @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$", message = "영문자로 시작하여 8자 이상이어야 합니다.")
        private String password;
        private String retypePassword;
        @NotBlank
        @Size(max = 30, message = "Up to 30 characters")
        private String firstName;
        @NotBlank
        @Size(max = 30, message = "Up to 30 characters")
        private String lastName;
        private MultipartFile avatarFile;
        private Avatar avatar;
        @Size(max = 100)
        private String bio;
        @DateTimeFormat(pattern = Common.DATE_FORMAT)
        private Date birthdate;
        private Gender gender = Gender.OTHER;
        private Language language = Language.KOREAN;
        private Currency currency = Currency.KRW;
        @NotNull
        private Boolean superhost = false;
        @NotNull
        private Role role = Role.USER;
        private Boolean emailVerified = false;
        private String emailSecret = UUID.randomUUID().toString().replace("-", "");
        @NotNull
        private LoginMethod loginMethod = LoginMethod.EMAIL;
        private List<Room> rooms;
        private Date created;
        private Date updated;
        
        
        
        public Builder setId(Integer id) {
        	this.id = id;
        	return this;
        }
        
        
        
        public Builder setUsername(String username) {
			this.username = username;
			return this;
		}



		public Builder setPassword(String password) {
			this.password = password;
			return this;
		}
		
		
		
		public Builder setRetypePassword(String retypePassword) {
			this.retypePassword = retypePassword;
			return this;
		}



		public Builder setFirstName(String firstName) {
			this.firstName = firstName;
			return this;
		}



		public Builder setLastName(String lastName) {
			this.lastName = lastName;
			return this;
		}



		public Builder setAvatarFile(MultipartFile file) {
			this.avatarFile = file;
			return this;
		}
		
		
		
		public Builder setAvatar(Avatar avatar) {
			this.avatar = avatar;
			return this;
		}



		public Builder setBio(String bio) {
			this.bio = bio;
			return this;
		}



		public Builder setBirthdate(Date birthdate) {
			this.birthdate = birthdate;
			return this;
		}



		public Builder setGender(Gender gender) {
			this.gender = gender;
			return this;
		}



		public Builder setLanguage(Language language) {
			this.language = language;
			return this;
		}



		public Builder setCurrency(Currency currency) {
			this.currency = currency;
			return this;
		}



		public Builder setSuperhost(Boolean superhost) {
			this.superhost = superhost;
			return this;
		}
		
		
		
		public Builder setRole(Role role) {
			this.role = role;
			return this;
		}
		
		
		
		public Builder setEmailVerified(Boolean emailVerified) {
			this.emailVerified = emailVerified;
			return this;
		}
		
		
		
		public Builder setEmailSecret(String emailSecret) {
			this.emailSecret = emailSecret;
			return this;
		}
		
		
		
		public Builder setLoginMethod(LoginMethod loginMethod) {
			this.loginMethod = loginMethod;
			return this;
		}
		
		
		
		public Builder setRooms(List<Room> rooms) {
			this.rooms = rooms;
			return this;
		}
		
		
		
		public Builder setCreated(Date created) {
			this.created = created;
			return this;
		}
		
		
		
		public Builder setUpdated(Date updated) {
			this.updated = updated;
			return this;
		}
		
		
		
		public User build() {
			return new User(this);
        }
		
    }
    
    
    
    
    
    public static Builder builder() {
    	return new Builder();
    }
    
    
    
    
    
    private User(Builder builder) {
    	this.setId(builder.getId());
		this.setUsername(builder.getUsername());
		if (ValidUtils.isValid(builder.getPassword())) {
			this.setPassword(
					((PasswordEncoder) BeanUtils.getBean(PasswordEncoder.class)).encode(builder.getPassword()));
		}
		this.setFirstName(builder.getFirstName());
		this.setLastName(builder.getLastName());
		this.setBio(builder.getBio());
		this.setBirthdate(builder.getBirthdate());
		this.setGender(builder.getGender());
		this.setLanguage(builder.getLanguage());
		this.setCurrency(builder.getCurrency());
		this.setSuperhost(builder.getSuperhost());
		if (ValidUtils.isValid(builder.getAvatarFile())) {
			this.setAvatar(Avatar.builder()
					.setFile(builder.getAvatarFile())
					.build());
		} else {
			this.setAvatar(builder.getAvatar());
		}
		this.setRole(builder.getRole());
		this.setEmailVerified(builder.getEmailVerified());
		this.setEmailSecret(builder.getEmailSecret());
		this.setLoginMethod(builder.getLoginMethod());
		this.setRooms(builder.getRooms());
		this.setCreated(builder.getCreated());
		this.setUpdated(builder.getUpdated());
    }
    
    
    
    
    @Override
    public void override(User s) {
    	if (s.getId() 				!= null) this.setId(s.getId());
    	if (s.getUsername()			!= null) this.setUsername(s.getUsername());
    	if (s.getPassword()			!= null) this.setPassword(s.getPassword());
    	if (s.getFirstName()		!= null) this.setFirstName(s.getFirstName());
    	if (s.getLastName()			!= null) this.setLastName(s.getLastName());
    	if (s.getBio() 				!= null) this.setBio(s.getBio());
    	if (s.getBirthdate()		!= null) this.setBirthdate(s.getBirthdate());
    	if (s.getGender() 			!= null) this.setGender(s.getGender());
    	if (s.getLanguage()			!= null) this.setLanguage(s.getLanguage());
    	if (s.getCurrency()			!= null) this.setCurrency(s.getCurrency());
    	if (s.getSuperhost() 		!= null) this.setSuperhost(s.getSuperhost());
    	if (s.getRole()				!= null) this.setRole(s.getRole());
    	if (s.getLoginMethod()		!= null) this.setLoginMethod(s.getLoginMethod());
    	if (s.getRooms()			!= null) this.setRooms(s.getRooms());
    	if (s.getCreated()			!= null) this.setCreated(s.getCreated()); 
    	if (s.getUpdated()			!= null) this.setUpdated(s.getUpdated());
    	this.setAvatar(s.getAvatar()); /* 이미지 삭제를 위해  null 체크하지 않음 */
    }
    
    
    
    
    
    
    @Override
    public User deepClone() {
    	return this.toBuilder().build();
    }
    
    
    
    
    public Builder toBuilder() {
    	return builder()
    			.setId(this.getId())
    			.setUsername(this.getUsername())
    			.setPassword(this.getPassword())
    			.setFirstName(this.getFirstName())
    			.setLastName(this.getLastName())
    			.setBio(this.getBio())
    			.setBirthdate(this.getBirthdate())
    			.setGender(this.getGender())
    			.setLanguage(this.getLanguage())
    			.setCurrency(this.getCurrency())
    			.setSuperhost(this.getSuperhost())
    			.setAvatar(this.getAvatar())
    			.setRole(this.getRole())
    			.setEmailVerified(this.getEmailVerified())
    			.setEmailSecret(this.getEmailSecret())
    			.setLoginMethod(this.getLoginMethod())
    			.setRooms(this.getRooms())
    			.setCreated(this.getCreated())
    			.setUpdated(this.getUpdated());
    }
    
    
    
    
    public static User toUser(SafeUser safeUser) {
    	if (safeUser == null) return null;
    	
    	return builder()
			.setId(safeUser.getId())
			.setUsername(safeUser.getUsername())
			.setFirstName(safeUser.getFirstName())
			.setLastName(safeUser.getLastName())
			.setBio(safeUser.getBio())
			.setBirthdate(safeUser.getBirthdate())
			.setGender(safeUser.getGender())
			.setLanguage(safeUser.getLanguage())
			.setCurrency(safeUser.getCurrency())
			.setSuperhost(safeUser.getSuperhost())
			.setAvatar((Avatar) safeUser.getAvatar())
			.setRole(safeUser.getRole())
			.setEmailVerified(safeUser.getEmailVerified())
			.setEmailSecret(safeUser.getEmailSecret())
			.setLoginMethod(safeUser.getLoginMethod())
			.setRooms(safeUser.getRooms())
			.setCreated(safeUser.getCreated())
			.setUpdated(safeUser.getUpdated())
			.build();
    }
    
    
    
    
    public static Set<User> toUsers(Set<SafeUser> safeUsers) {
    	if (safeUsers == null) return null;
    	
    	Set<User> users = new HashSet<>();
    	for (SafeUser u : safeUsers) {
    		users.add(toUser(u));
    	}
    	
    	return users;
    }
    
    
    public static Set<User> toUsers(List<SafeUser> safeUsers) {
    	if (safeUsers == null) return null;
    	return toUsers(new HashSet<SafeUser>(safeUsers));
    }
    
}
