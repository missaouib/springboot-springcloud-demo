package com.example.springbootspringclouddemo.pojo;

import lombok.Data;

import javax.annotation.sql.DataSourceDefinition;
import java.io.Serializable;

@Data
public class User implements Serializable {

    private Integer id;

    private String username;
}
