package com.example.Springapp.Services;

import com.example.Springapp.Model.Staff;
import org.springframework.data.domain.Page;

import java.util.List;

public interface StaffService {
    List<Staff> getAllStaffs();
    void saveStaff(Staff staff);
    Staff getStaffById(long id);
    void deleteStaffById(long id);
    Page<Staff> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection);

}
