package is.technologies.entities;

import org.apache.ibatis.annotations.*;

import java.util.List;

public interface carModelMapper {
    @Insert("insert into mydatabase.carmodel(modelname,modellength,modelwidth,bodytype,brandid_) values (#{modelName},#{length},#{weidth},#{carBodyType},#{carBrand.id})")
    void save(carModel carModel);
    @Select("Select modelid from mydatabase.carmodel where modelid=(Select MAX(modelid) from mydatabase.carmodel)")
    @Results(value = {@Result(property = "id",column = "modelid")})
    int getLastId(carModel carModel);

    @Delete("Delete from mydatabase.carmodel where modelid = #{id}")
    void deleteById(int id);
    @Delete("Delete from mydatabase.carmodel WHERE mydatabase.carmodel.modelname =#{modelName} AND  mydatabase.carmodel.modellength =#{length} AND  mydatabase.carmodel.modelwidth =#{weidth} AND  mydatabase.carmodel.bodytype =#{carBodyType}")
    void deleteByEntity(carModel carModel);
    @Delete("Delete from mydatabase.carmodel")
    void deleteAll();
    @Update("Update mydatabase.carmodel set modelname=#{modelName},modellength =#{length},modelwidth =#{weidth},bodytype =#{carBodyType} where modelid=#{id}")
    void update(carModel carModel);
    @Select("Select * from mydatabase.carmodel where modelid=#{id}")
    @Results(value = { @Result(property = "id", column = "modelid"), @Result(property = "modelName", column = "modelname"),
            @Result(property = "length", column = "modellength"),@Result(property = "weidth", column = "modelwidth"),@Result(property = "carBodyType", column = "bodytype"),
            @Result(property = "carBrand.id", column = "brandid_")
    })
    carModel selectCarModel(int id);
    @Select("select * from mydatabase.carmodel")
    @ResultType(List.class)
    @Results(value = { @Result(property = "id", column = "modelid"), @Result(property = "modelName", column = "modelname"),
            @Result(property = "length", column = "modellength"),@Result(property = "weidth", column = "modelwidth"),@Result(property = "carBodyType", column = "bodytype"),
            @Result(property = "carBrand.id", column = "brandid_")
    })
    List<carModel> getAll();
    @Select("Select * from mydatabase.carmodel where brandid_=#{id}")
    @Results(value = { @Result(property = "id", column = "modelid"), @Result(property = "modelName", column = "modelname"),
            @Result(property = "length", column = "modellength"),@Result(property = "weidth", column = "modelwidth"),@Result(property = "carBodyType", column = "bodytype"),
            @Result(property = "carBrand.id", column = "brandid_")
    })
    List<carModel> getByVId(int id);
}
