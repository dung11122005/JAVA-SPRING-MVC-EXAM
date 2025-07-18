package com.example.exam_portal.repository;


import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.exam_portal.domain.ExamSession;

@Repository
public interface TestRepositoty extends JpaRepository<ExamSession, Long> {
    ExamSession findById(long id);

    ExamSession findByClassroomIdAndExamId(Long classId, Long examId);

    Page<ExamSession> findByTeacherId(Long teacherId, Pageable pageable);

    List<ExamSession> findByClassroom_IdIn(List<Long> classIds);

    void deleteByClassroomIdAndExamId(Long classId, Long examId);

}
