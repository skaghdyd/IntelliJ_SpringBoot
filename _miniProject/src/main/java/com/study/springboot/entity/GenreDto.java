package com.study.springboot.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
public class GenreDto {
	private Long gid;
	private String genreName;
}
