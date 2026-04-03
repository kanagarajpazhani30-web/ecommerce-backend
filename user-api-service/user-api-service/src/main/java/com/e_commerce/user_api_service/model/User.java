

package com.e_commerce.user_api_service.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table("users") // ✅ VERY IMPORTANT
public class User {

    @Id // ✅ VERY IMPORTANT
    private Long id;

    private String username;
    private String email;
    private String password;

    private String roles;

    private Boolean enabled;
    private Boolean accountNonLocked;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}