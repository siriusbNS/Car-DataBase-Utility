package is.technologies.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Generated;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;
import java.util.List;

@Setter
@Getter
@Data
@Entity
@Table(name = "carbrand",schema = "mydatabase")
public class carBrand {
    public carBrand(int id, String name, Date foundingDate) {
        this.id = id;
        this.name = name;
        this.foundingDate = foundingDate;
    }
    public carBrand(String _name,Date _foundingDate)
    {
        if(_name == null || _foundingDate == null)
            throw new NullPointerException();

        name = _name;
        foundingDate = _foundingDate;
    }
    public carBrand() {

    }

    @Id
    @Column(name = "brandid")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "brandname")
    private String name;
    @Column(name = "foundingdate")
    private Date foundingDate;



}
