package com.springboot.jpa.Controller;

import com.springboot.jpa.data.dto.ValidRequestDto;
import com.springboot.jpa.data.dto.ValidatedRequestDto;
import com.springboot.jpa.data.group.ValidationGroup1;
import com.springboot.jpa.data.group.ValidationGroup2;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/validation")
public class ValidationController {
    private final Logger logger = LoggerFactory.getLogger(ValidationController.class);

//    @PostMapping("/valid")
//    public ResponseEntity<String> checkValidationByValid(
//            @Valid @RequestBody ValidRequestDto validRequestDto
//            ){
//        logger.info(validRequestDto.toString());
//        return ResponseEntity.status(HttpStatus.OK).body(validRequestDto.toString());
//    }
    @PostMapping("/validated")
    public ResponseEntity<String> checkValidation(
            @Validated @RequestBody ValidatedRequestDto dto
            ){
        logger.info(dto.toString());
        return ResponseEntity.status(HttpStatus.OK).body(dto.toString());
    }
    @PostMapping("/validated/group1")
    public ResponseEntity<String> checkValidation1(
            @Validated(ValidationGroup1.class) @RequestBody ValidatedRequestDto dto
    ){
        logger.info(dto.toString());
        return ResponseEntity.status(HttpStatus.OK).body(dto.toString());
    }
    @PostMapping("/validated/group2")
    public ResponseEntity<String> checkValidation2(
            @Validated(ValidationGroup2.class) @RequestBody ValidatedRequestDto dto
    ){
        logger.info(dto.toString());
        return ResponseEntity.status(HttpStatus.OK).body(dto.toString());
    }
    @PostMapping("/validated/all-group")
    public ResponseEntity<String> checkValidationAll(
            @Validated({ValidationGroup1.class,
                    ValidationGroup2.class}) @RequestBody ValidatedRequestDto
            validatedRequestDto){
        logger.info(validatedRequestDto.toString());
        return ResponseEntity.status(HttpStatus.OK).body(validatedRequestDto.toString());
    }

}
