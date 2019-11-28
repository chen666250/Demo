package com.example.demo.dto;

import com.example.demo.controller.PublishController;
import com.github.pagehelper.Page;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class PageDto {
private List<TopicDto> tiopics;
private boolean showPrevious;
private boolean showFirst;
private boolean showLast;
private boolean showNext;
private Integer currentPage;
private List<Integer> pages=new ArrayList<>();
private Integer totalPages;

public void setPage(Integer totalCount,Integer currentPage,Integer size){
    if(totalCount%size==0){
        totalPages=totalCount/size;
    }else {
        totalPages=totalCount/size +1;
    }
    if(currentPage<1){
        currentPage=1;
    }
    if(currentPage>totalPages){
        currentPage=totalPages;

    }
    this.currentPage=currentPage;
    pages.add(currentPage);
    for(int i=1;i<=3;i++){
        if(currentPage-i>0){
            pages.add(0,currentPage-i);
        }
        if(currentPage+i<=totalPages){
            pages.add(currentPage+i);
        }
    }
    if(currentPage==1){
        showPrevious=false;
    }else {
        showPrevious=true;
    }
    if(currentPage==totalPages){
        showNext=false;
    }else {
        showNext=true;
    }
    if(pages.contains(1)){
        showFirst=false;
    }else{
        showFirst=true;
    }
    if(pages.contains(totalPages)){
        showLast=false;
    }else {
        showLast=true;
    }

}

}
