package com.springboot.userservice.entity;

import java.sql.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
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
@Table(name = "activity", uniqueConstraints = {
        @UniqueConstraint(name = "uni_activity_name", columnNames = "name") })
public class Activity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NonNull
    private String name;

    @Column(name = "created_date")
    private Date createdDate;

    @Column(name = "started_date")
    private Date startDate;

    @Column(name = "ended_date")
    private Date endDate;

    @NonNull
    private String conclusion;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "plan_id", nullable = false)
    private Plan plan;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "created_user_id", nullable = false)
    private AppUser createdUser;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "result_id", nullable = false)
    private ActivityResult activityResult;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "state_id", nullable = false)
    private ActivityState activityState;

    // @ManyToOne(cascade = CascadeType.MERGE)
    // @JoinColumn(name = "facility_id", nullable = false)
    // private Facility facility;

    // @OneToMany(mappedBy = "activity")
    // private Set<Sample> samples;

}