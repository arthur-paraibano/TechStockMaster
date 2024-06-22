package model.entities;

import java.sql.Date;

public class Movement {
    private int id;
    private TypeMovement type;
    private Double amount;
    private Date date;
    private Sector sector;
    private String osLykos;
    private Equipment equipment;
    private Tag tag;
    private User user;
    private String description;
    private String status;

    public Movement() {
        this.equipment = new Equipment();
        this.sector = new Sector();
        this.tag = new Tag();
        this.user = new User();
    }

    public Movement(TypeMovement type, Double amount, Date date, Sector sector, String osLykos,
            Equipment equipment) {
        this.type = type;
        this.amount = amount;
        this.date = date;
        this.sector = sector;
        this.osLykos = osLykos;
        this.equipment = equipment;
    }

    public Movement(TypeMovement type, Double amount, Date date, Sector sector, Equipment equipment) {
        this.type = type;
        this.amount = amount;
        this.date = date;
        this.sector = sector;
        this.equipment = equipment;
    }

    public Movement(int id, TypeMovement type, Double amount, Date date, Sector sector, String osLykos,
            Equipment equipment, User user) {
        this.id = id;
        this.type = type;
        this.amount = amount;
        this.date = date;
        this.sector = sector;
        this.osLykos = osLykos;
        this.equipment = equipment;
        this.user = user;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public TypeMovement getType() {
        return type;
    }

    public void setType(TypeMovement type) {
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

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((equipment == null) ? 0 : equipment.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Movement other = (Movement) obj;
        if (equipment == null) {
            if (other.equipment != null)
                return false;
        } else if (!equipment.equals(other.equipment))
            return false;
        return true;
    }
}
