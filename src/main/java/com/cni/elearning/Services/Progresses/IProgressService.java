package com.cni.elearning.Services.Progresses;

import com.cni.elearning.Models.Progress.Progress;

import java.util.List;

public interface IProgressService {
    List<Progress> getAllProgresss();

    Progress saveProgress(Progress progress);

    Progress getProgressById(int id);

    void deleteProgress(int id);

    Progress updateProgress(Progress progress, int id);

    Progress AddLessonToProgress(int id, int lessonId);

    void updateLessons();

}
