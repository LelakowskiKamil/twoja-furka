package com.lelakowsky.twojafurka.domain.user;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class UserSummary {

    private String email;
    private String username;
    private String password;
    private boolean isEnabled;

}
