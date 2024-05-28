package com.ptt.spzx.manager.service;

import com.ptt.spzx.model.entity.product.Category;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * ClassName: CategoryService
 * Package: com.ptt.spzx.manager.service
 * Description:
 *
 * @Author ptt
 * @Create 2024/4/10 15:45
 * @Version 1.0
 */
public interface CategoryService {
    List<Category> findByParentId(Long parentId);

    void exportData(HttpServletResponse response);

    void importData(MultipartFile file);
}
