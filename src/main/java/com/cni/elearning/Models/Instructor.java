package com.cni.elearning.Models;

import jakarta.persistence.Entity;
import lombok.Data;

@Data
@Entity(name = "instructors")
public class Instructor extends User{
}
