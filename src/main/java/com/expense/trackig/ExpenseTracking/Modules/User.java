package com.expense.trackig.ExpenseTracking.Modules;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
 @Setter
 @Getter
 @NoArgsConstructor
 @AllArgsConstructor
 @Document(collection = "user")
public class User {
    @Id
    private String email;
    private String password;
 }
