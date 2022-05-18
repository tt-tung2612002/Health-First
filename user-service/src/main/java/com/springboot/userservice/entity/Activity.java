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
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "plan", uniqueConstraints = {
        @UniqueConstraint(name = "uni_plan_name", columnNames = "name") })
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

    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinColumn(name = "plan_id", nullable = false)
    private Plan plan;

    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", nullable = false)
    private AppUser createdUser;

    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "activity_result_id", nullable = false)
    private ActivityResult activityResult;

    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "activity_state_id", nullable = false)
    private ActivityState activityState;

    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinColumn(name = "facility_id", nullable = false)
    private Facility facility;

}