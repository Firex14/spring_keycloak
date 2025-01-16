package com.yaba.springkeycloak.web.query;

import com.yaba.springkeycloak.dto.CategoryDto;
import com.yaba.springkeycloak.service.query.CategoryQueryService;
import com.yaba.springkeycloak.utils.ApiResponse;
import com.yaba.springkeycloak.utils.ResponseUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(CategoryQueryController.CATEGORY_QUERY_ROUTE)
public class CategoryQueryController {
    public static final String CATEGORY_QUERY_ROUTE = "/query/categories";
    private final CategoryQueryService queryService;

    public CategoryQueryController(CategoryQueryService queryService) {
        this.queryService = queryService;
    }

    @GetMapping
    public ResponseEntity<ApiResponse<Page<CategoryDto>>> getAll(Pageable pageable) {
        Page<CategoryDto> categories = queryService.getAll(pageable);
        return ResponseUtils.buildSuccessResponse(
                categories,
                "Liste des catégories récupérée avec succès",
                HttpStatus.OK
        );
    }

}
