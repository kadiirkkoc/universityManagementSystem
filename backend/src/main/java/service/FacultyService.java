package service;

import dtos.FacultyDto;

import java.util.List;

public interface FacultyService {

    List<FacultyDto> getAll();

    FacultyDto getById(Long id);

    String addFaculty(FacultyDto facultyDTO);

    String updateFaculty(Long id, FacultyDto facultyDTO);

    String deleteFaculty(Long id);
}
