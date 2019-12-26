package naumen.livinir.entity;

import naumen.livinir.utils.Sex;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

/**
 * Сущность класса Resident (сожитель, клиент)
 *
 * @since 17.10.2019
 * @author mattthey
 */

@Entity
@Table(name = Resident.NAME_TABLE)
public class Resident implements Serializable, UserDetails
{
    // название таблицы
    public static final String NAME_TABLE = "tbl_resident";

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    // имя
    @Column(name = "FIRST_NAME") //, nullable = false)
    private String firstName;

    // фамилия
    @Column(name = "LAST_NAME") //, nullable = false)
    private String lastName;

    // пол
    private Sex sex;

    // день рождения
    @Column(name = "DATE_OF_BIRTH")
    @Temporal(TemporalType.DATE)
    private Date dateOfBirth;

    // email пользователя
    // @Column(nullable = false)
    private String email;

    // Соль + ХЭШ соответсвенно
    private String password;

    @ManyToOne
    private Announcement currentHouse;

    @OneToMany(mappedBy = "owner", fetch = FetchType.EAGER)
    private List<Announcement> myAnnouncements;

    @Override
    public boolean equals(Object o)
    {
        if (this == o)
        {
            return true;
        }
        if (!(o instanceof Resident))
        {
            return false;
        }
        Resident resident = (Resident) o;
        return id.equals(resident.getId()) &&
                firstName.equals(resident.getFirstName()) &&
                lastName.equals(resident.getLastName()) &&
                sex == resident.getSex() &&
                Objects.equals(dateOfBirth, resident.getDateOfBirth()) &&
                email.equals(resident.getEmail()) &&
                password.equals(resident.getPassword());
    }

    public Announcement getCurrentHouse()
    {
        return currentHouse;
    }

    public void setCurrentHouse(Announcement currentHouse) {
        this.currentHouse = currentHouse;
    }

    public List<Announcement> getMyAnnouncements()
    {
        return myAnnouncements;
    }

    public void setMyAnnouncements(List<Announcement> myAnnouncements)
    {
        this.myAnnouncements = myAnnouncements;
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(id, firstName, lastName, sex, dateOfBirth, email, password);
    }

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public String getFirstName()
    {
        return firstName;
    }

    public void setFirstName(String firstName)
    {
        this.firstName = firstName;
    }

    public String getLastName()
    {
        return lastName;
    }

    public void setLastName(String lastName)
    {
        this.lastName = lastName;
    }

    public Sex getSex()
    {
        return sex;
    }

    public void setSex(Sex sex)
    {
        this.sex = sex;
    }

    public Date getDateOfBirth()
    {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth)
    {
        this.dateOfBirth = dateOfBirth;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(new SimpleGrantedAuthority("user"));
    }

    public String getPassword()
    {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

}
