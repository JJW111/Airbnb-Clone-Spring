package com.clone.airbnb.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.persistence.*;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

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
import com.clone.airbnb.entity.enu.Currency;
import com.clone.airbnb.entity.enu.Gender;
import com.clone.airbnb.entity.enu.Language;
import com.clone.airbnb.entity.enu.LoginMethod;
import com.clone.airbnb.entity.enu.Role;
import com.clone.airbnb.entity.sup.DateTimeModel;
import com.clone.airbnb.utils.FileUtils;
import com.clone.airbnb.utils.ValidUtils;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@EntityForm
@UserEntity
@Entity
@Table(name = "user")
@Getter
@Setter
@EqualsAndHashCode(callSuper = false, of = { "username" })
public class User extends DateTimeModel implements AdminFormEntity<User> {
	
	public final static String PASSWORD_REGEXP = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$";
	
	
    @Id
    @GeneratedValue
    private Integer id;

	
	
    @UsernameForm(maxlength = 150, placeholder = "Email")
    @Column(unique = true, nullable = false)
    @Size(max = 150)
    private String username;
    
    
    
    @PasswordForm(maxlength = 30, placeholder = "알파벳으로 시작해야 하며 8자리에서 30자리까지 입력가능합니다. 최소한 1개의 숫자를 포함해야 합니다.")
    @Column(nullable = false)
    private String password;
    
    
    
    @TextForm(blank = false, maxlength = 30)
    @Column(nullable = false)
    @Size(min = 1, max = 30)
    private String firstName;
    
    
    
    @TextForm(blank = false, maxlength = 30)
    @Column(nullable = false)
    @Size(min = 1, max = 30)
    private String lastName;
    
    
    
    @ImageUploadForm(fileFormName = "avatarFile")
    @OneToOne(targetEntity = Avatar.class, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(referencedColumnName = "id")
    private Avatar avatar;
    
    
    
    @TextAreaForm(maxlength = 100, rows = 5, placeholder = "최대 100자 까지 자기소개를 입력하세요...")
    @Size(max = 100)
    private String bio;
  
    
    
    @DateForm
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = Common.DATE_FORMAT)
    private Date birthdate;
    
    
    
    @SelectBoxForm(blank = false, defaultOption="성별 선택")
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Gender gender = Gender.OTHER;
    
    
    
    @SelectBoxForm(blank = false, defaultOption="언어 선택")
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Language language = Language.KOREAN;
    
    
    
    @SelectBoxForm(blank = false, defaultOption="통화 선택")
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Currency currency = Currency.KRW;

    
    
    @CheckBoxForm
    @Column(nullable = false)
    private Boolean superhost = false;
    
    
    
    @SelectBoxForm(blank = false, defaultOption="권한 선택")
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
	private Role role = Role.USER;
    
    
    
    @Column(nullable = false)
    private Boolean emailVerified = false;
    
    
    
    @Column(nullable = false)
    private String emailSecret = UUID.randomUUID().toString().replace("-", "");
    
    
    
    @SelectBoxForm(blank = false, defaultOption="로그인 유형")
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private LoginMethod loginMethod = LoginMethod.EMAIL;
    
    
    
    
    @OneToMany(mappedBy = "host", fetch = FetchType.LAZY, orphanRemoval = true)
    private List<Room> rooms;
    
    
    
    
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, orphanRemoval = true)
    private List<Message> messages;
    
    
    
    
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
	@JoinTable(
				name = "conversation_user",
			  joinColumns = @JoinColumn(name = "user_id"),
			  inverseJoinColumns = @JoinColumn(name = "conversation_id"))
    private List<Conversation> conversations;
    
	
	
	
	
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
	@JoinTable(
			  name = "watchlist_user",
			  joinColumns = @JoinColumn(name = "user_id"), 
			  inverseJoinColumns = @JoinColumn(name = "watchlist_id"))
	private List<Watchlist> watchlists;
	
    
    
    public void setRooms(List<Room> rooms) {
		if (rooms == null) return;
		if (this.rooms == null) {
			this.rooms = new ArrayList<>();
		}
		this.rooms.clear();
		this.rooms.addAll(rooms);
	}
    
    
    
    
    public void setMessages(List<Message> messages) {
		if (messages == null) return;
		if (this.messages == null) {
			this.messages = new ArrayList<>();
		}
		this.messages.clear();
		this.messages.addAll(messages);
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
    	if (s.getCreated()			!= null) this.setCreated(s.getCreated()); 
    	if (s.getUpdated()			!= null) this.setUpdated(s.getUpdated());
    	this.setAvatar(s.getAvatar()); /* 이미지 삭제를 위해  null 체크하지 않음 */
    }
    
    
    
    @Override
    public void beforeCreate() {
    	/* Avatar */
    	saveFiles(this);
    }
    
    
    
    @Override
    public void beforeDelete() {
    	/* Avatar */
    	deleteFiles(this);
    	
    	/* Rooms */
    	if (ValidUtils.isValid(this.getRooms())) {
    		this.getRooms().forEach(e -> {
    			e.beforeDelete();
    		});
    	}
    }
    
    
    
    @Override
    public void beforeUpdate(Object old) {
    	User oldUser = (User) old;

    	if (saveFiles(this)) {
    		deleteFiles(oldUser);
    	}
    }
    
    
    
    
    private boolean saveFiles(User newUser) {
    	if (ValidUtils.isValid(newUser.getAvatar())) {
    		if (ValidUtils.isValid(newUser.getAvatar().getFile())) {
    			FileUtils.save(newUser.getAvatar().getFile(), newUser.getAvatar().getUploadPath());
    			return true;
    		}
    	}
    	return false;
    }
    
    
    private void deleteFiles(User u) {
    	if (ValidUtils.isValid(u.getAvatar())) {
    		FileUtils.delete(u.getAvatar().getUploadPath());
    	}
    }
    
    
    
    @Override
	public String toString() {
		return "User[id=" + id + ",username=" + username 
				+ ",firstName=" + firstName + ",lastName=" + lastName 
				+ ",avatar=" + (avatar != null ? avatar.getOriginalFilename() : null) + ",bio=" + bio 
				+ ",birthdate=" + birthdate + ",gender=" + gender
				+ ",language=" + language + ",currency=" + currency
				+ ",superhost=" + superhost + ",role=" + role
				+ ",emailVerified=" + emailVerified + ",emailSecret=" + emailSecret
				+ ",loginMethod=" + loginMethod
				+ ",rooms=" + (rooms != null ? rooms.size() + "rooms" : null) + "]";
	}
    
    
}
