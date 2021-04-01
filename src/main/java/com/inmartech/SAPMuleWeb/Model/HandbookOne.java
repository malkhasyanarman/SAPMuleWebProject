package com.inmartech.SAPMuleWeb.Model;

import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "handbook", schema = "business")
public class HandbookOne {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<HandbookOneData> handbookOneDataList = new ArrayList<>();

    public HandbookOne() {

    }

    public HandbookOne(Long id, String name, List<HandbookOneData> handbookOneDataList) {
        this.id = id;
        this.name = name;
        this.handbookOneDataList = handbookOneDataList;
    }

    public List<HandbookOneData> getHandbookOneDataList() {
        return handbookOneDataList;
    }

    public void setHandbookOneDataList(List<HandbookOneData> handbookOneDataList) {
        this.handbookOneDataList = handbookOneDataList;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
