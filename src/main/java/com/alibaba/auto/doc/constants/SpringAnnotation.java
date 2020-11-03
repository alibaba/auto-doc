package com.alibaba.auto.doc.constants;

import java.util.Arrays;
import java.util.List;

/**
 * @author ：杨帆（舲扬）
 * @date ：Created in 2020/10/29 10:09 下午
 * @description：
 */
public interface SpringAnnotation {

    String REQUEST_MAPPING = "RequestMapping";
    String REQUEST_MAPPING_FULLY = "org.springframework.web.bind.annotation.RequestMapping";

    String GET_MAPPING = "GetMapping";
    String GET_MAPPING_FULLY = "org.springframework.web.bind.annotation.GetMapping";

    String POST_MAPPING = "PostMapping";
    String POST_MAPPING_FULLY = "org.springframework.web.bind.annotation.PostMapping";

    String PUT_MAPPING = "PutMapping";
    String PUT_MAPPING_FULLY = "org.springframework.web.bind.annotation.PutMapping";

    String PATCH_MAPPING = "PatchMapping";
    String PATCH_MAPPING_FULLY = "org.springframework.web.bind.annotation.PatchMapping";

    String DELETE_MAPPING = "DeleteMapping";
    String DELETE_MAPPING_FULLY = "org.springframework.web.bind.annotation.DeleteMapping";

    List<String> REQUEST_MAPPINGS = Arrays.asList(
        REQUEST_MAPPING,
        REQUEST_MAPPING_FULLY,
        GET_MAPPING,
        GET_MAPPING_FULLY,
        POST_MAPPING,
        POST_MAPPING_FULLY,
        PUT_MAPPING,
        PUT_MAPPING_FULLY,
        PATCH_MAPPING,
        PATCH_MAPPING_FULLY,
        DELETE_MAPPING,
        DELETE_MAPPING_FULLY
    );

    String REQUEST_HEADER = "RequestHeaderParam";
    String REQUEST_HEADER_FULLY = "org.springframework.web.bind.annotation.RequestHeader";


    String REQUEST_PARAM = "RequestParam";
    String REQUEST_PARAM_FULLY = "org.springframework.web.bind.annotation.RequestParam";

    String REQUEST_BODY = "RequestBody";
    String REQUEST_BODY_FULLY = "org.springframework.web.bind.annotation.RequestBody";

    String CONTROLLER = "Controller";
    String CONTROLLER_FULLY = "org.springframework.stereotype.Controller";

    String REST_CONTROLLER = "RestController";
    String REST_CONTROLLER_FULLY = "org.springframework.web.bind.annotation.RestController";

    String COSMO_CONTROLLER = "CosmoController";
    String COSMO_CONTROLLER_FULLY = "com.aliyun.cosmo.boot.web.mvc.annt.CosmoController";

    String PATH_VARIABLE = "PathVariable";
}
