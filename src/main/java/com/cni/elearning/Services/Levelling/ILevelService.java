package com.cni.elearning.Services.Levelling;

import com.cni.elearning.Models.Levelling.Level;

import java.util.List;

public interface ILevelService {
    List<Level> getAllLevels();

    Level getLevelById(int id);

    Level saveLevel(Level level);

    Level updateLevel(Level level, int id);

    void deleteLevel(int id);
}
