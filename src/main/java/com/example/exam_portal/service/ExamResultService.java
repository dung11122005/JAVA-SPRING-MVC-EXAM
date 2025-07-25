package com.example.exam_portal.service;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.exam_portal.domain.Exam;
import com.example.exam_portal.domain.ExamResult;
import com.example.exam_portal.repository.ExamResultRepository;


@Service
public class ExamResultService {
    private final ExamResultRepository examResultRepository;

    public ExamResultService(ExamResultRepository examResultRepository){
        this.examResultRepository=examResultRepository;
    }

    public Page<ExamResult> getAllResulrExamPaginationTeacherId(Long id, Pageable page) {
        return this.examResultRepository.findByExamSessionId(id, page);
    }

    public Page<ExamResult> getAllResulrExamPagination(Pageable page) {
        return this.examResultRepository.findAll(page);
    }

    public List<ExamResult> getAllExamResultSessionId(Long SessionId){
        return this.examResultRepository.findByExamSessionId(SessionId);
    }

    public ExamResult handleSaveExam(ExamResult exam) {
        ExamResult eric = this.examResultRepository.save(exam);
        return eric;
    }

    public boolean hasStudentSubmittedExam(Long studentId, Long examId) {
        return examResultRepository.existsByStudentIdAndExamId(studentId, examId);
    }

    public void writeExcelFileExamResult(List<ExamResult> results, OutputStream outputStream) throws IOException {
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("Exam Results");

        // Dòng 0: tên giáo viên (lấy từ dòng đầu tiên của danh sách)
        if (!results.isEmpty()) {
            Exam exam = results.get(0).getExam();
            String teacherName = exam.getUser().getFullName();
            String examName = exam.getName();

            // Dòng 0: Tên bài kiểm tra
            Row examRow = sheet.createRow(0);
            examRow.createCell(0).setCellValue("Bài kiểm tra:");
            examRow.createCell(1).setCellValue(examName);

            // Dòng 1: Tên giáo viên
            Row teacherRow = sheet.createRow(1);
            teacherRow.createCell(0).setCellValue("Giáo viên:");
            teacherRow.createCell(1).setCellValue(teacherName);
        }

        // Tiêu đề bắt đầu từ dòng 3
        Row headerRow = sheet.createRow(2);
        headerRow.createCell(0).setCellValue("ID");
        headerRow.createCell(1).setCellValue("Tên học sinh");
        headerRow.createCell(2).setCellValue("Điểm");
        headerRow.createCell(3).setCellValue("Ngày thi");

        // Dữ liệu bắt đầu từ dòng 4
        int rowNum = 3;
        for (ExamResult result : results) {
            Row row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(result.getId());
            row.createCell(1).setCellValue(result.getStudent().getFullName());
            row.createCell(2).setCellValue(result.getScore());
            row.createCell(3).setCellValue(result.getSubmittedAt().toString());
        }

        workbook.write(outputStream);
        workbook.close();
    }

    
}
