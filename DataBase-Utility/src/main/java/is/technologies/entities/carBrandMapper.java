package is.technologies.entities;

import org.apache.ibatis.annotations.*;

import java.util.Date;
import java.util.List;

import static java.lang.Math.max;


public interface carBrandMapper {

    @Select("Select * from mydatabase.carbrand where brandid=#{id}")
    @Results(value = { @Result(property = "id", column = "brandid"), @Result(property = "name", column = "brandname"),
            @Result(property = "foundingDate", column = "foundingdate")
    })
    carBrand selectCarBrand(int id);

    @Insert("insert into mydatabase.carbrand(brandname,foundingdate) values (#{name},#{foundingDate})")
    void save(carBrand carBrand);
    @Select("Select brandid from mydatabase.carbrand where brandid=(Select MAX(brandid) from mydatabase.carbrand)")
    @Results(value = {@Result(property = "id",column = "brandid")})
    int getLastId(carBrand carBrand);

    @Delete("Delete from mydatabase.carbrand where brandid = #{id}")
    void deleteById(int id);

    @Delete("Delete from mydatabase.carbrand WHERE mydatabase.carbrand.brandname =#{name} AND  mydatabase.carbrand.foundingdate =#{foundingDate}")
    void deleteByEntity(carBrand carBrand);

    @Delete("Delete from mydatabase.carbrand")
    void deleteAll();

    @Update("Update mydatabase.carBrand set brandname=#{name},foundingdate=#{foundingDate} where brandid=#{id}")
    void update(carBrand carBrand);
    @Select("select * from mydatabase.carbrand")
    @ResultType(List.class)
    @Results(value = { @Result(property = "id", column = "brandid"), @Result(property = "name", column = "brandname"),
            @Result(property = "foundingDate", column = "foundingdate")
    })
    List<carBrand> getAll();
}
