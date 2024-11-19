package kr.or.ddit.vo;

import kr.or.ddit.validate.DeleteGroup;
import kr.or.ddit.validate.InsertGroup;

import javax.validation.constraints.NotBlank;
import javax.validation.groups.Default;
import java.io.Serializable;
import java.util.Objects;

/**
 * Domain Layer(Java Bean 규약 적용)
 */
public class PersonVO implements Serializable {
    @NotBlank(groups = {Default.class, DeleteGroup.class})
    private transient String id;
    @NotBlank
    private String name;
    @NotBlank(groups = InsertGroup.class)
    private String gender;
    @NotBlank(groups = InsertGroup.class)
    private String age;
    @NotBlank
    private String address;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PersonVO personVO = (PersonVO) o;
        return Objects.equals(id, personVO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "PersonVO{" +
                "name='" + name + '\'' +
                ", gender='" + gender + '\'' +
                ", age='" + age + '\'' +
                ", address='" + address + '\'' +
                '}';
    }

}
