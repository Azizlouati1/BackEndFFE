package com.cni.elearning.Models;

import jakarta.persistence.Entity;
import lombok.Data;


@Data
@Entity(name ="students")
public class Student extends User{

}