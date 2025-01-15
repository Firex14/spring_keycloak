package com.yaba.springkeycloak.entities;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.ZonedDateTime;
import java.util.UUID;

@Getter
@MappedSuperclass // Permet à cette classe d'être héritée par d'autres entités JPA
public abstract class BaseModel {

    @Id
    @GeneratedValue // Génère automatiquement un UUID
    private UUID id;

    @CreationTimestamp // Timestamp automatiquement défini à la création
    private ZonedDateTime createdAt;

    @UpdateTimestamp // Timestamp automatiquement mis à jour
    private ZonedDateTime updatedAt;

    public BaseModel() {}

    public BaseModel(UUID id, ZonedDateTime createdAt, ZonedDateTime updatedAt) {
        this.id = id;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
}
