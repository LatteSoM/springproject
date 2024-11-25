package ru.mpt.springproject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.mpt.springproject.entity.Department;
import ru.mpt.springproject.repository.DepartmentRepository;

import java.util.List;
import java.util.Optional;

@Service
public class DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;

    public List<Department> getAllDepartments() {
        return departmentRepository.findAll();
    }

    public Optional<Department> getDepartmentById(Long id) {
        return departmentRepository.findById(id);
    }

    public Department createDepartment(Department department) {
        return departmentRepository.save(department);
    }

    public Department updateDepartment(Long id, Department departmentDetails) throws Exception {
        Department department = departmentRepository.findById(id).orElseThrow(() -> new Exception("Department not exist with id :" + id));
        department.setName(departmentDetails.getName());
        department.setDescription(departmentDetails.getDescription());
        return departmentRepository.save(department);
    }

    public void deleteDepartment(Long id) throws Exception {
        Department department = departmentRepository.findById(id).orElseThrow(() -> new Exception("Department not exist with id :" + id));
        departmentRepository.delete(department);
    }
}
