package com.stn.hpdp.model.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.stn.hpdp.controller.member.request.UpdateMemberReq;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "members")
@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED, force = true)
@DynamicInsert
@DynamicUpdate
public class Member extends TimeBaseEntity implements UserDetails {

    @Id
    @JsonIgnore
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "members_id")
    private Long id;

    @NonNull
    private String loginId;

    @JsonIgnore
    @NonNull
    private String loginPw;

    @NonNull
    private String name;

    private String phoneNumber;

    private String email;

    private String address;

    @ColumnDefault("0")
    private int point;

    @ColumnDefault("100")
    private int balanceUnit;

    private String profile;

    public void changePw(String encryptedPw) {
        this.loginPw = encryptedPw;
    }

    public void changeInfo(UpdateMemberReq memberUpdateReq) {
        this.name = memberUpdateReq.getName();
        this.phoneNumber = memberUpdateReq.getPhoneNumber();
        this.email = memberUpdateReq.getEmail();
    }
    public void changePoint(int point){
        this.point += point;
    }

    @Column
    @ElementCollection(fetch = FetchType.EAGER)
    @Builder.Default
    private List<String> roles = new ArrayList<>();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.roles.stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        return loginPw;
    }

    @Override
    public String getUsername() {
        return loginId;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}
