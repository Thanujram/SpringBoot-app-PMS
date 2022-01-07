package com.example.Springapp.Services;

import com.example.Springapp.Model.Treatment;
import com.example.Springapp.Repository.TreatmentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TreatmentServiceImpl implements TreatmentService{

    @Autowired
    private TreatmentRepo treatmentRepository;

    @Override
    public List<Treatment> getAllTreatment() {
        return treatmentRepository.findAll();
    }

    @Override
    public void saveTreatment(Treatment treatment) {
        this.treatmentRepository.save(treatment);
    }

    @Override
    public Treatment getTreatmentById(long id) {
        Optional<Treatment> optional = treatmentRepository.findById(id);
        Treatment treatment = null;
        if (optional.isPresent()) {
            treatment = optional.get();
        } else {
            throw new RuntimeException(" Treatment not found for id :: " + id);
        }
        return treatment;
    }

    @Override
    public void deleteTreatmentById(long id) {
        this.treatmentRepository.deleteById(id);
    }


    public Page<Treatment> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection) {
        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() :
                Sort.by(sortField).descending();

        Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
        return this.treatmentRepository.findAll(pageable);
    }
}
