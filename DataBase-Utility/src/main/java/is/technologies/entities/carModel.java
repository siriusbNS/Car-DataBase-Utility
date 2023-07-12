package is.technologies.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@ToString
@Data
@Entity
@Table(name = "carmodel",schema = "mydatabase")
public class carModel {

    public carModel() {
    }
    @Id
    @Column(name = "modelid")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "modelname")
    private String modelName;
    @Column(name = "bodytype")
    private String carBodyType;
    @Column(name = "modellength")
    private int length;
    @Column(name = "modelwidth")
    private int weidth;


    @ManyToOne
    @JoinColumn(name = "brandid_",referencedColumnName = "brandid")
    private carBrand carBrand;


    public String getCarBodyType() {
        return carBodyType;
    }
    public void setCarBodyType(String _carBodyType) {
        this.carBodyType = _carBodyType;
    }
    public void setCarBodyType(carBodyType _carBodyType) {
        this.carBodyType = _carBodyType.toString();
    }

    public carModel(long id, String modelName, String carBodyType, int length, int weidth, is.technologies.entities.carBrand carBrand) {
        this.id = id;
        this.modelName = modelName;
        this.carBodyType = carBodyType;
        this.length = length;
        this.weidth = weidth;
        this.carBrand = carBrand;
    }

    public carModel(String _modelName, int _length, int _weidth, carBodyType _carBodyType, carBrand _carBrand)
    {
        if(_modelName == null || _length == 0 || _weidth == 0 || _carBodyType == null || _carBrand == null)
            throw new NullPointerException();

        modelName = _modelName;
        length = _length;
        weidth = _weidth;
        carBodyType = _carBodyType.toString();
        carBrand = _carBrand;

    }

}
