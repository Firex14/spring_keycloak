package com.yaba.springkeycloak.web.cmd;

import com.yaba.springkeycloak.dto.CategoryDto;
import com.yaba.springkeycloak.service.cmd.CategoryCmdService;
import com.yaba.springkeycloak.utils.ApiResponse;
import com.yaba.springkeycloak.utils.ResponseUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(CategoryCmdController.CATEGORY_CMD_ROUTE)
public class CategoryCmdController {
    public static final String CATEGORY_CMD_ROUTE = "/cmd/categories";

    private final CategoryCmdService cmdService;

    public CategoryCmdController(CategoryCmdService cmdService) {
        this.cmdService = cmdService;
    }

    @PostMapping
    public ResponseEntity<ApiResponse<CategoryDto>> create(@RequestBody CategoryDto request){
        CategoryDto result = cmdService.save(request);
        return ResponseUtils.buildSuccessResponse(result, "SUCCESS", HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<ApiResponse<CategoryDto>> update(@RequestBody CategoryDto request){
        CategoryDto result = cmdService.update(request);
        return ResponseUtils.buildSuccessResponse(result, "SUCCESS", HttpStatus.OK);
    }
}
