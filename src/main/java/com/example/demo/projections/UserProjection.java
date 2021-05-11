package com.example.demo.projections;

import java.util.UUID;

public interface UserProjection {
    UUID getId();
    String getPassportNumber();
    String getFirstName();
    String getSurname();
    String getPatronymic();
}
