package com.vaempunk.stafftool.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PageDto<T> {
    
    List<T> content;

    long totalPages;
    
    long pageNum;

}
