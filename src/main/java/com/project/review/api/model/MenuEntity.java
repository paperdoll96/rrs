package com.project.review.api.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;

@Builder
@Getter
@Table(name = "menu")
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class MenuEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;

    private  Long restaurantId;
    private String name;
    private Integer price;
    private ZonedDateTime createdAt;
    private ZonedDateTime updatedAt;
}
