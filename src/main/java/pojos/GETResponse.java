package pojos;

        import lombok.AllArgsConstructor;
        import lombok.Builder;
        import lombok.Data;

        import javax.annotation.Generated;

@Generated("jsonschema2pojo")
public class GETResponse {

    public void GETResponse(){

    }
    public Object employeeId;

    public Object getEmployeeId() {
        return employeeId;
    }

    public String getEmployeeFirstName() {
        return employeeFirstName;
    }

    public String getEmployeeLastName() {
        return employeeLastName;
    }

    public String getEmployeeGender() {
        return employeeGender;
    }

    public String getEmployeePhone() {
        return employeePhone;
    }

    public Boolean getNewEmployee() {
        return isNewEmployee;
    }

    public String employeeFirstName;
    public String employeeLastName;
    public String employeeGender;
    public String employeePhone;
    public Boolean isNewEmployee;

}
