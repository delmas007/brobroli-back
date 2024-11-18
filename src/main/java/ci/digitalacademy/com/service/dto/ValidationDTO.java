package ci.digitalacademy.com.service.dto;

import ci.digitalacademy.com.model.Customer;
import ci.digitalacademy.com.model.Provider;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
@Getter
@Setter
public class ValidationDTO {
    private int id;

    private Instant creation;

    private Instant expiration;

    private String code;

    private ProviderDTO provider;
    private CustomerDTO customer;

}
