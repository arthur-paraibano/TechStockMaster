package model.entities;

import java.sql.Date;

public class Repair {
    private int id;
    private TypeRepair type;
    private Double amount;
    private Date date;
    private Sector sector;
    private String osLykos;
    private Equipment equipment;
    private Tag tag;
    private User user;
    private String description;
    private String status;

    public Repair() {
        this.sector = new Sector();
        this.equipment = new Equipment();
        this.tag = new Tag();
        this.user = new User();
    }

    public Repair(int id, TypeRepair type, Double amount, Date date, Sector sector, String osLykos, Equipment equipment,
            Tag tag, User user, String description, String status) {
        this.id = id;
        this.type = type;
        this.amount = amount;
        this.date = date;
        this.sector = sector;
        this.osLykos = osLykos;
        this.equipment = equipment;
        this.tag = tag;
        this.user = user;
        this.description = description;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public TypeRepair getType() {
        return type;
    }

    public void setType(TypeRepair type) {
        this.type = type;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Sector getSector() {
        return sector;
    }

    public void setSector(Sector sector) {
        this.sector = sector;
    }

    public String getOsLykos() {
        return osLykos;
    }

    public void setOsLykos(String osLykos) {
        this.osLykos = osLykos;
    }

    public Equipment getEquipment() {
        return equipment;
    }

    public void setEquipment(Equipment equipment) {
        this.equipment = equipment;
    }

    public Tag getTag() {
        return tag;
    }

    public void setTag(Tag tag) {
        this.tag = tag;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
