
package pojos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.annotation.Generated;

@Data
@Builder
@AllArgsConstructor
@Generated("jsonschema2pojo")
public class Employee {
    private Integer employeeId;
    private String employeeFirstName;
    private String employeeLastName;
    private String employeeGender;
    private String employeePhone;
    private Boolean isNewEmployee;

}