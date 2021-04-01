package com.inmartech.SAPMuleWeb.Model;

import javax.persistence.*;

@Entity
@Table(name = "handbook_one_data", schema = "business")
public class HandbookOneData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "legal_entity")
    private String legalEntity;

    @Column(name = "storage_location")
    private String storageLocation;

    @Column(name = "customer")
    private String customer;

    @Column(name = "agency")
    private String agency;

    public Long getId() {
        return id;
    }

    public HandbookOneData() {

    }

    public HandbookOneData(Long id, String legalEntity, String storageLocation, String customer, String agency) {
        this.id = id;
        this.legalEntity = legalEntity;
        this.storageLocation = storageLocation;
        this.customer = customer;
        this.agency = agency;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLegalEntity() {
        return legalEntity;
    }

    public void setLegalEntity(String legalEntity) {
        this.legalEntity = legalEntity;
    }

    public String getStorageLocation() {
        return storageLocation;
    }

    public void setStorageLocation(String storageLocation) {
        this.storageLocation = storageLocation;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public String getAgency() {
        return agency;
    }

    public void setAgency(String agency) {
        this.agency = agency;
    }
}
