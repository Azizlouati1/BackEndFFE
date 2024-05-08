package com.cni.elearning.Services.Users;

import com.cni.elearning.Models.Cours.Cour;
import com.cni.elearning.Models.Users.Instructor;
import com.cni.elearning.Models.Users.Role;
import com.cni.elearning.Repositories.Users.InstructorRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
@Service
public class InstructorServiceImpl implements IInstructorService {
    public final InstructorRepository instructorRepository;
    private final PasswordEncoder passwordEncoder;
    @Autowired
    private DataSource dataSource;
    public InstructorServiceImpl(InstructorRepository instructorRepository, PasswordEncoder passwordEncoder) {
        this.instructorRepository = instructorRepository;
        this.passwordEncoder = passwordEncoder;
    }

        @Transactional
        @Override
        public List<Instructor> findAllInstructors() {
            Connection connection = null;
            try {
                connection = dataSource.getConnection();
                connection.setAutoCommit(false);

                List<Instructor> instructors = instructorRepository.findAll();

                connection.commit(); // If everything goes well, commit the transaction

                return instructors;
            } catch (SQLException e) {
                if (connection != null) {
                    try {
                        connection.rollback(); // Rollback in case of exception
                    } catch (SQLException rollbackEx) {
                        // Handle rollback exception
                    }
                }
                // Handle exception or rethrow it
                throw new RuntimeException("Failed to find all instructors", e);
            } finally {
                if (connection != null) {
                    try {
                        connection.close(); // Close connection in finally block
                    } catch (SQLException closeEx) {
                        // Handle close exception
                    }
                }
            }
        }

        @Transactional
        @Override
        public Optional<Instructor> findInstructorById(int id) {
            Connection connection = null;
            try {
                connection = dataSource.getConnection();
                connection.setAutoCommit(false);

                Optional<Instructor> instructor = instructorRepository.findById(id);

                connection.commit(); // If everything goes well, commit the transaction

                return instructor;
            } catch (SQLException e) {
                if (connection != null) {
                    try {
                        connection.rollback(); // Rollback in case of exception
                    } catch (SQLException rollbackEx) {
                        // Handle rollback exception
                    }
                }
                // Handle exception or rethrow it
                throw new RuntimeException("Failed to find instructor with ID: " + id, e);
            } finally {
                if (connection != null) {
                    try {
                        connection.close(); // Close connection in finally block
                    } catch (SQLException closeEx) {
                        // Handle close exception
                    }
                }
            }
        }
    

    @Override
    public Instructor addInstructor(Instructor instructor){
        instructor.setRole(Role.INSTRUCTOR);
        instructor.setPassword(passwordEncoder.encode(instructor.getPassword()));
        return instructorRepository.save(instructor);
    }

    @Override
    public Instructor findInstructorById2(int id){
        Optional<Instructor> instructor = instructorRepository.findById(id);
        if(instructor.isPresent()){
            return instructor.get();
        }
        return null;
    }
    @Override
    public void deleteInstuctor(int id){
        instructorRepository.deleteById(id);
    }
    @Override
    public Instructor updateInstructor(Instructor instructor, int id) {
        Optional<Instructor> instructorOptional = instructorRepository.findById(id);
        if (instructorOptional.isPresent()) {
            instructor.setRole(Role.INSTRUCTOR);
            instructor.setPassword(passwordEncoder.encode(instructor.getPassword()));
            return instructorRepository.save(instructor);
        }
        throw new RuntimeException("Instructor not found with id: " + id);
    }
    @Transactional
    @Override
    public List<Cour> getCourByInstructorId(int id){
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
            connection.setAutoCommit(false);

            List<Cour> courses = instructorRepository.findCourById(id);

            connection.commit(); // If everything goes well, commit the transaction

            return courses;
        } catch (SQLException e) {
            if (connection != null) {
                try {
                    connection.rollback(); // Rollback in case of exception
                } catch (SQLException rollbackEx) {
                    // Handle rollback exception
                }
            }
            // Handle exception or rethrow it
            throw new RuntimeException("Failed to get courses for instructor with ID: " + id, e);
        } finally {
            if (connection != null) {
                try {
                    connection.close(); // Close connection in finally block
                } catch (SQLException closeEx) {
                    // Handle close exception
                }
            }
        }
    }
}