package ru.mpt.springproject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.mpt.springproject.entity.Position;
import ru.mpt.springproject.repository.PositionRepository;

import java.util.List;
import java.util.Optional;

@Service
public class PositionService {

    @Autowired
    private PositionRepository positionRepository;

    public List<Position> getAllPositions() {
        return positionRepository.findAll();
    }

    public Optional<Position> getPositionById(Long id) {
        return positionRepository.findById(id);
    }

    public Position createPosition(Position position) {
        return positionRepository.save(position);
    }

    public Position updatePosition(Long id, Position positionDetails) throws Exception {
        Position position = positionRepository.findById(id).orElseThrow(() -> new Exception("Position not exist with id :" + id));
        position.setName(positionDetails.getName());
        position.setDepartment(positionDetails.getDepartment());
        return positionRepository.save(position);
    }

    public void deletePosition(Long id) throws Exception {
        Position position = positionRepository.findById(id).orElseThrow(() -> new Exception("Position not exist with id :" + id));
        positionRepository.delete(position);
    }
}
