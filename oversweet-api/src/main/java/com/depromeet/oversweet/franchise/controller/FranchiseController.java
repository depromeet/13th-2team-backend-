package com.depromeet.oversweet.franchise.controller;

import com.depromeet.oversweet.common.dto.response.FranchiseInfo;
import com.depromeet.oversweet.franchise.service.FranchiseRedisService;
import com.depromeet.oversweet.franchise.service.FranchiseSearchService;
import com.depromeet.oversweet.response.DataResponse;
import com.depromeet.oversweet.response.MessageResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.HttpStatus.OK;

@Tag(name = "프랜차이즈", description = "프랜차이즈 관련 API")
@RestController
@RequestMapping("/api/v1/franchises")
@RequiredArgsConstructor
public class FranchiseController {
    private final FranchiseSearchService franchiseSearchService;
    private final FranchiseRedisService franchiseRedisService;

    @Operation(summary = "검색 키워드로 해당하는 프랜차이즈 목록 조회", description = "프랜차이즈 검색 API")
    @ApiResponses(@ApiResponse(responseCode = "200", description = "프랜차이즈 검색 성공"))
    @GetMapping("/search")
    public ResponseEntity<DataResponse<List<FranchiseInfo>>> getFranchiseByKeyword(@RequestParam @Parameter(description = "프랜차이즈 검색을 위한 키워드") final String keyword){
        final List<FranchiseInfo> response = franchiseSearchService.getFranchiseByKeyword(keyword);
        return ResponseEntity.ok()
                .body(DataResponse.of(HttpStatus.OK, "검색 키워드로 해당하는 프랜차이즈 목록 조회 성공", response));
    }

    @Operation(summary = "레디스에 프랜차이즈 목록 세팅", description = "레디스에 프랜차이즈 목록 세팅 API")
    @ApiResponses(@ApiResponse(responseCode = "200", description = "레디스에 프랜차이즈 목록 세팅 성공"))
    @PostMapping("redis")
    public ResponseEntity<MessageResponse> createFranchiseAtRedis() {
        franchiseRedisService.createFranchiseAtRedis();
        return ResponseEntity.ok()
                .body(MessageResponse.of(OK, "레디스에 프랜차이즈 목록 세팅 성공"));
    }

    @Operation(summary = "레디스에 저장된 프랜차이즈 목록 조회", description = "레디스에 저장된 프랜차이즈 목록 조회 API")
    @ApiResponses(@ApiResponse(responseCode = "200", description = "레디스에 저장된 프랜차이즈 목록 조회 성공"))
    @GetMapping("redis")
    public ResponseEntity<DataResponse<List<FranchiseInfo>>> getFranchiseAtRedis() {
        final List<FranchiseInfo> franchise = franchiseRedisService.getFranchiseAtRedis();
        return ResponseEntity.ok()
                .body(DataResponse.of(OK, "레디스에 저장된 프랜차이즈 목록 조회 성공", franchise));
    }
}
