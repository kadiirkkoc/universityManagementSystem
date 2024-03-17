package service.impl;

import dtos.FacultyDto;
import loggers.MainLogger;
import loggers.messages.FacultyMessage;
import model.Faculty;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import repository.DepartmentRepository;
import repository.FacultyRepository;
import service.FacultyService;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class FacultyServiceImpl implements FacultyService {


    private final MainLogger LOGGER = new MainLogger(FacultyServiceImpl.class);
    private final FacultyRepository facultyRepository;

    public FacultyServiceImpl(FacultyRepository facultyRepository, DepartmentRepository departmentRepository) {
        this.facultyRepository = facultyRepository;
    }


    @Override
    public List<FacultyDto> getAll() {
        return  facultyRepository.findAll().stream()
                .map(faculty -> FacultyDto.builder()
                        .name(faculty.getName())
                        .campus(faculty.getCampus())
                        .build()).collect(Collectors.toList());
    }

    @Override
    public FacultyDto getById(Long id) {
        Optional<Faculty> faculty = facultyRepository.findById(id);
        return faculty.map(f -> FacultyDto.builder()
                    .name(f.getName())
                    .campus(f.getCampus())
                    .build())
                .orElseGet(() -> {
                    LOGGER.log(FacultyMessage.NOT_FOUND, HttpStatus.NOT_FOUND);
                    return null;
                });

    }

    public FacultyDto getByUuid(String uuid){
        return facultyRepository.findByUuid(uuid)
                .map(faculty -> {
                    FacultyDto facultyDto = new FacultyDto();
                    facultyDto.setName(faculty.getName());
                    facultyDto.setCampus(faculty.getCampus());
                    return facultyDto;
                })
                .orElseGet(() -> {
                    LOGGER.log(FacultyMessage.NOT_FOUND_WITH_UUID,HttpStatus.NOT_FOUND);
                    return null;
                });
    }

    @Override
    public String addFaculty(FacultyDto facultyDTO) {
        Faculty faculty = Faculty.builder()
                .uuid(UUID.randomUUID().toString())
                .name(facultyDTO.getName())
                .campus(facultyDTO.getCampus())
                .build();
        return FacultyMessage.CREATE + facultyRepository.save(faculty);
    }

    @Override
    public String updateFaculty(Long id, FacultyDto facultyDTO) {
        Optional<Faculty> faculty = facultyRepository.findById(id);
        faculty.ifPresent(f -> {
            faculty.get().setName(facultyDTO.getName());
            faculty.get().setCampus(facultyDTO.getCampus());
        });

        facultyRepository.save(faculty.get());
        return FacultyMessage.UPDATE;
    }

    @Override
    public String deleteFaculty(Long id) {
        facultyRepository.deleteById(id);
        return FacultyMessage.DELETE + id;
    }
}
