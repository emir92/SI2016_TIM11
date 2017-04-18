package com.timxyz.models;

import javax.persistence.*;
import java.util.Collection;

@Entity
public class Location extends BaseModel {
    private String name;
    private Collection<Audit> audits;
    private Collection<Item> items;
    private Location parent;
    private Collection<Location> children;
    private LocationType type;

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @OneToMany(mappedBy = "location")
    public Collection<Audit> getAudits() {
        return audits;
    }

    public void setAudits(Collection<Audit> audits) {
        this.audits = audits;
    }

    @OneToMany(mappedBy = "location")
    public Collection<Item> getItems() {
        return items;
    }

    public void setItems(Collection<Item> items) {
        this.items = items;
    }

    @ManyToOne
    @JoinColumn(name = "parentId", referencedColumnName = "id")
    public Location getParent() {
        return parent;
    }

    public void setParent(Location parent) {
        this.parent = parent;
    }

    @OneToMany(mappedBy = "parent")
    public Collection<Location> getChildren() {
        return children;
    }

    public void setChildren(Collection<Location> children) {
        this.children = children;
    }

    @ManyToOne
    @JoinColumn(name = "typeId", referencedColumnName = "id", nullable = false)
    public LocationType getType() {
        return type;
    }

    public void setType(LocationType type) {
        this.type = type;
    }
}