package com.erp.backend.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(
        schema = "erp",
        name = "customer"
)
@Entity
public class Customer {
    @Id
    @SequenceGenerator(name = "CUSTOMER_GEN", sequenceName = "CUSTOMER_USER", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CUSTOMER_GEN")
    private Long customerId;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "legalname", nullable = false)
    private String legalName;

    @Column(name = "cif", nullable = false)
    private String cif;

    @Email
    @NotBlank(message = "Email is required")
    @Column(name = "email", nullable = false)
    private String email;

    /*private Address address;*/ /** Pendiente **/
    /*private List<Invoice> invoices*/ /** Pendiente **/
    /*private String email*/ /** Pendiente (debe ser un List)**/
    /*private List<SubCompany> subcompanies*/ /**Pendiente**/

}
