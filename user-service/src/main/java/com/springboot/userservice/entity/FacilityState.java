package com.springboot.userservice.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Table(name = "facility_state", uniqueConstraints = {
        @UniqueConstraint(name = "uni_facility_state", columnNames = "name")
})
public class FacilityState {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NonNull
    @Column(name = "name")
    String name;

    @OneToOne(mappedBy = "facilityState")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Facility facility;

}