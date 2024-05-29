package com.example.moviemingle.models.pages;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Page;

import java.util.List;

@Getter
@Setter
public class PageRes<T> {
    
    private long totalItems;
    private int totalPages;
    private int currentPage;
    private List<T> content;
    
    public PageRes(Page<T> page){
        this.totalItems = page.getTotalElements();
        this.totalPages = page.getTotalPages();
        this.currentPage = page.getNumber();
        this.content = page.getContent();
    }
    
}
