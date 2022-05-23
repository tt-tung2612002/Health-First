package com.springboot.userservice.entity;

import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
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
@Table(name = "plan", uniqueConstraints = {
        @UniqueConstraint(name = "uni_plan_name", columnNames = "name") })
public class Plan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NonNull
    private String name;

    @NonNull
    private String description;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "state_id", nullable = false)
    private PlanState planState;

    @Column(name = "created_date")
    private Date publishedDate;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "created_user_id", nullable = false)
    private AppUser createdUser;

    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @OneToMany(mappedBy = "plan")
    private Set<Activity> activities = new HashSet<>();

}