package com.example.Springapp.Services;

import com.example.Springapp.Model.Staff;
import com.example.Springapp.Repository.StaffRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StaffServiceImpl implements StaffService{

    @Autowired
    private StaffRepo staffRepository;

    @Override
    public List<Staff> getAllStaffs() {
        return staffRepository.findAll();
    }

    @Override
    public void saveStaff(Staff employee) {
        this.staffRepository.save(employee);
    }

    @Override
    public Staff getStaffById(long id) {
        Optional<Staff> optional = staffRepository.findById(id);
        Staff staff = null;
        if (optional.isPresent()) {
            staff = optional.get();
        } else {
            throw new RuntimeException("Staff  not found for id :: " + id);
        }
        return staff;
    }

    @Override
    public void deleteStaffById(long id) {
        this.staffRepository.deleteById(id);
    }

    @Override
    public Page<Staff> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection) {
        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() :
                Sort.by(sortField).descending();

        Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
        return this.staffRepository.findAll(pageable);
    }
}
