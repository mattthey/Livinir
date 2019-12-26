package naumen.livinir.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * Сущность класса Announcement (объявление)
 *
 * @since 17.10.2019
 * @author mattthey
 */

@Entity
@Table(name = Announcement.NAME_TABLE)
public class Announcement implements Serializable
{
    // название таблицы
    public static final String NAME_TABLE = "tbl_announcement";

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    // TODO сформировать сущность адреса или воспользоваться готовым решением

    // Город
    private String city;

    // Район
    private String area;

    // Адрес
    private String address;

    // Сожители
    @OneToMany(mappedBy = "currentHouse", fetch = FetchType.EAGER)
    private List<Resident> residents;

    // Срок аренды
    @Column(name = "lease_date")
    @Temporal(TemporalType.DATE)
    private Date leaseDate;

    // Описание
    @Column(columnDefinition="TEXT")
    private String description;

    // Владелец объявления
    @ManyToOne
    private Resident owner;

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public String getCity()
    {
        return city;
    }

    public void setCity(String city)
    {
        this.city = city;
    }

    public String getArea()
    {
        return area;
    }

    public void setArea(String area)
    {
        this.area = area;
    }

    public String getAddress()
    {
        return address;
    }

    public void setAddress(String address)
    {
        this.address = address;
    }

    public List<Resident> getResidents()
    {
        return residents;
    }

    public void setResidents(List<Resident> residents)
    {
        this.residents = residents;
    }

    public Date getLeaseDate()
    {
        return leaseDate;
    }

    public void setLeaseDate(Date leaseDate)
    {
        this.leaseDate = leaseDate;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public Resident getOwner()
    {
        return owner;
    }

    public void setOwner(Resident owner)
    {
        this.owner = owner;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o)
        {
            return true;
        }
        if (!(o instanceof Announcement))
        {
            return false;
        }
        Announcement that = (Announcement) o;
        return id.equals(that.getId()) &&
                Objects.equals(city, that.getCity()) &&
                Objects.equals(area, that.getArea()) &&
                Objects.equals(address, that.getAddress()) &&
                Objects.equals(residents, that.getResidents()) &&
                Objects.equals(leaseDate, that.getLeaseDate()) &&
                Objects.equals(description, that.getDescription()) &&
                owner.equals(that.getOwner());
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(id, city, area, address, residents, leaseDate, description, owner);
    }
}
