package com.springboot.userservice.entity;

import java.sql.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.ToString;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "certificate", uniqueConstraints = {
        @UniqueConstraint(name = "uni_certificate_code", columnNames = "certificate_number")
})
public class Certificate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NonNull
    @Column(name = "certificate_number")
    private String certificateNumber;

    @NonNull
    @Column(name = "published_date")
    private Date publishedDate;

    @NonNull
    @Column(name = "expired_date")
    private Date expiredDate;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn(name = "facility_id", nullable = false)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    // @Transient
    private Facility facility;

    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "state_id")
    private CertificateState certificateState;

}