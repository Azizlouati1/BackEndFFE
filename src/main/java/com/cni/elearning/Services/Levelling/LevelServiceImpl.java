package com.cni.elearning.Services.Levelling;

import com.cni.elearning.Models.Levelling.Level;
import com.cni.elearning.Repositories.Levelling.LevelRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LevelServiceImpl implements ILevelService {
    private final LevelRepository levelRepository;

    public LevelServiceImpl(LevelRepository levelRepository) {
        this.levelRepository = levelRepository;
    }

    @Override
    public List<Level> getAllLevels() {
        return levelRepository.findAll();
    }

    @Override
    public Level getLevelById(int id) {
        return levelRepository.findById(id).get();
    }

    @Override
    public Level saveLevel(Level level) {
        return levelRepository.save(level);
    }

    @Override
    public Level updateLevel(Level level, int id) {
        Level updatedLevel = levelRepository.findById(id).orElse(null);
        if (updatedLevel != null && updatedLevel.getId() == level.getId()) {
            return levelRepository.save(level);
        }
        throw new RuntimeException("Level not found");
    }

    @Override
    public void deleteLevel(int id) {
        levelRepository.deleteById(id);
    }

    @Override
    public Level addXP(int XP, int id) {
        Level level = levelRepository.findById(id).orElse(null);

        if (level != null) {
            int levelXP = level.getLevelXP();
            int levelMaxXP = level.getMaxLevelXP();

            int XPTotal = levelXP + XP;

            while (XPTotal >= levelMaxXP) {
                XPTotal -= levelMaxXP;
                level.setMaxLevelXP(levelMaxXP + 100);
                level.setLevel(level.getLevel() + 1);
                levelMaxXP += 100;
            }

            level.setLevelXP(XPTotal);

            return levelRepository.save(level);
        }

        throw new RuntimeException("Level not found");
    }
    @Override
    public List<Object[]> getTop5(){
        return levelRepository.findTop5StudentsWithLevelsAndXP();
    }

    @Override
    public Level getLevelByStudentId(int StudentId){
        return levelRepository.findLevelByStudentId(StudentId);
    }

}