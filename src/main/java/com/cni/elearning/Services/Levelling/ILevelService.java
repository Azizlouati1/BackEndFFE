package com.cni.elearning.Services.Levelling;

import com.cni.elearning.Models.Levelling.Level;
import com.cni.elearning.Models.Users.Student;

import java.util.List;

public interface ILevelService {
    List<Level> getAllLevels();

    Level getLevelById(int id);

    Level saveLevel(Level level);

    Level updateLevel(Level level, int id);

    void deleteLevel(int id);

    Level addXP(int XP, int id);

    List<Object[]> getTop5();
}
