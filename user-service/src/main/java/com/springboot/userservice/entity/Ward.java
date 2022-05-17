package com.springboot.userservice.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "ward", uniqueConstraints = {
        @UniqueConstraint(name = "uni_wardName", columnNames = "name")
})
public class Ward {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NonNull
    private String name;

    // @NonNull
    // @Column(name = "district_id")
    // private Integer districtId;

    // @OneToMany(mappedBy = "ward", fetch = FetchType.EAGER, cascade =
    // CascadeType.ALL)
    // private Set<Address> addresses = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "district_id", nullable = false)
    private District district;
}
