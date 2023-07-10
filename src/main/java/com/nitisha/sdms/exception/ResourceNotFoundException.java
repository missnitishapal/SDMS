package com.nitisha.sdms.exception;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ResourceNotFoundException extends RuntimeException {
    
    private String resourceName;
    private String fieldName;
    private long value;

    public ResourceNotFoundException(String resourceName,String fieldName,long value){
        super(String.format("%s not found with %s : %s",resourceName,fieldName,value));
    }
}
