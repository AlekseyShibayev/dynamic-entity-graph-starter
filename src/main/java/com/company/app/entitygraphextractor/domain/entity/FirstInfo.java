package com.company.app.entitygraphextractor.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = {"id"})
@Entity
@Table(name = "FIRST")
public class FirstInfo {

    @Id
    @GeneratedValue
    @Column(name = "ID", nullable = false)
    private Long id;

    @Column(name = "DESCRIPTION")
    private String description;

    @OneToOne(mappedBy = "firstInfo")
    private First first;

}
