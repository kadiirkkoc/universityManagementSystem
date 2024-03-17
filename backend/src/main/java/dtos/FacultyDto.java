package dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import model.Department;

import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FacultyDto {

    private Long id;
    private String uuid;
    private String name;
    private String campus;
    private Set<Department> departments;
}
