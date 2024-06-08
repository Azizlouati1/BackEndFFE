package com.cni.elearning;

import com.cni.elearning.Controllers.Cours.CourController;
import com.cni.elearning.Controllers.Payments.PaymentsController;
import com.cni.elearning.Controllers.Security.AuthenticationController;
import com.cni.elearning.Dtos.ChatDTO;
import com.cni.elearning.Dtos.Payments.PaymentData;
import com.cni.elearning.Dtos.Payments.PaymentRequest;
import com.cni.elearning.Dtos.Payments.PaymentResponse;
import com.cni.elearning.Dtos.SignUpRequest;
import com.cni.elearning.Models.Chatting.Chat;
import com.cni.elearning.Models.Cours.Cour;
import com.cni.elearning.Models.Cours.Lesson;
import com.cni.elearning.Models.Events.Event;
import com.cni.elearning.Models.Events.Participant;
import com.cni.elearning.Models.FeedBacks.FeedBack;
import com.cni.elearning.Models.Progress.Progress;
import com.cni.elearning.Models.Users.*;
import com.cni.elearning.Repositories.Chatting.ChatRepository;
import com.cni.elearning.Repositories.Cours.CourRepository;
import com.cni.elearning.Repositories.Cours.LessonRepository;
import com.cni.elearning.Repositories.Events.EventRepository;
import com.cni.elearning.Repositories.Events.ParticipantRepository;
import com.cni.elearning.Repositories.FeedBacks.FeedBackRepository;
import com.cni.elearning.Repositories.Payments.RefundRepository;
import com.cni.elearning.Repositories.Progresses.ProgressRepository;
import com.cni.elearning.Repositories.Users.InstructorRepository;
import com.cni.elearning.Repositories.Users.StudentRepository;
import com.cni.elearning.Repositories.Users.UserRepository;
import com.cni.elearning.Services.Chatting.ChatServiceImpl;
import com.cni.elearning.Services.Cours.ICourService;
import com.cni.elearning.Services.Events.ParticipantServiceImpl;
import com.cni.elearning.Services.FeedBacks.FeedBackServiceImpl;
import com.cni.elearning.Services.Levelling.ILevelService;
import com.cni.elearning.Services.Progresses.ProgressServiceImpl;
import com.cni.elearning.Services.Security.IAuthenticationService;
import com.cni.elearning.Services.payments.IPaymentService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import com.cni.elearning.Dtos.Payments.RefundRequest;

import org.springframework.web.servlet.View;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class ElearningApplicationTests {

	@Test
	void contextLoads() {
	}

	@Test
	public void test_successfully_retrieves_course_by_valid_id() {
		// Arrange
		ICourService courService = mock(ICourService.class);
		CourController courController = new CourController(courService);
		Cour expectedCour = new Cour();
		expectedCour.setId(1);
		expectedCour.setTitle("Test Course");
		expectedCour.setViews(0);

		when(courService.getCourById(1)).thenReturn(expectedCour);
		when(courService.saveCour(any(Cour.class))).thenReturn(expectedCour);

		// Act
		Cour actualCour = courController.getCourById(1);

		// Assert
		assertNotNull(actualCour);
		assertEquals(1, actualCour.getId());
		assertEquals("Test Course", actualCour.getTitle());
		assertEquals(1, actualCour.getViews());

		verify(courService, times(1)).getCourById(1);
		verify(courService, times(1)).saveCour(any(Cour.class));
	}

	@Test
	public void test_save_valid_cour() {
		// Arrange
		ICourService courService = mock(ICourService.class);
		CourController courController = new CourController(courService);
		Cour cour = new Cour();
		cour.setTitle("Test Title");
		cour.setDescription("Test Description");
		cour.setCategory("Test Category");
		cour.setDifficulty(1);
		cour.setPremium(false);
		when(courService.saveCour(any(Cour.class))).thenReturn(cour);

		// Act
		Cour savedCour = courController.saveCour(cour);

		// Assert
		assertNotNull(savedCour);
		assertEquals("Test Title", savedCour.getTitle());
		assertEquals("Test Description", savedCour.getDescription());
		assertEquals("Test Category", savedCour.getCategory());
		assertEquals(1, savedCour.getDifficulty());
		assertFalse(savedCour.getPremium());
		verify(courService, times(1)).saveCour(any(Cour.class));
	}

	@Test
	public void test_successfully_creates_new_user_with_valid_input() {
		// Arrange
		IAuthenticationService authenticationService = mock(IAuthenticationService.class);
		AuthenticationController authenticationController = new AuthenticationController(authenticationService);
		SignUpRequest signUpRequest = new SignUpRequest();
		signUpRequest.setFirstname("John");
		signUpRequest.setLastname("Doe");
		signUpRequest.setEmail("john.doe@example.com");
		signUpRequest.setPassword("password123");
		signUpRequest.setImage(null);

		User expectedUser = new User("John", "Doe", "john.doe@example.com", "password123", Role.ADMIN, null);
		when(authenticationService.signup(signUpRequest)).thenReturn(expectedUser);

		// Act
		ResponseEntity<User> response = authenticationController.signup(signUpRequest);

		// Assert
		assertEquals(HttpStatus.CREATED, response.getStatusCode());
		assertEquals(expectedUser, response.getBody());
	}

	@Test
	public void test_create_payment_success() {
		// Arrange
		int studentId = 1;
		PaymentRequest paymentRequest = new PaymentRequest();
		paymentRequest.setAmount(100.0);

		Student student = new Student();
		student.setId(studentId);
		student.setEmail("test@example.com");
		student.setLastname("Doe");
		student.setFirstname("John");
		student.setPhoneNumber("123456789");

		IPaymentService paymentService = mock(IPaymentService.class);
		StudentRepository studentRepository = mock(StudentRepository.class);
		RefundRepository refundRepository = mock(RefundRepository.class);
		RestTemplate restTemplate = mock(RestTemplate.class);

		PaymentsController paymentsController = new PaymentsController(paymentService, studentRepository, refundRepository);

		when(studentRepository.findById(studentId)).thenReturn(Optional.of(student));

		ResponseEntity<PaymentResponse> responseEntity = new ResponseEntity<>(new PaymentResponse(true, "Success", 200, new PaymentData()), HttpStatus.OK);
		when(restTemplate.exchange(anyString(), eq(HttpMethod.POST), any(HttpEntity.class), eq(PaymentResponse.class))).thenReturn(responseEntity);

		// Act
		ResponseEntity<PaymentResponse> response = paymentsController.createPayment(studentId, paymentRequest);

		// Assert
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertNotNull(response.getBody());
		assertTrue(response.getBody().isStatus());
	}
	@Test
	public void test_save_progress_successfully() {
		// Arrange
		ProgressRepository progressRepository = mock(ProgressRepository.class);
		CourRepository courRepository = mock(CourRepository.class);
		StudentRepository studentRepository = mock(StudentRepository.class);
		LessonRepository lessonRepository = mock(LessonRepository.class);
		ILevelService levelService = mock(ILevelService.class);

		ProgressServiceImpl progressService = new ProgressServiceImpl(progressRepository, courRepository, studentRepository, lessonRepository, levelService);

		Student student = new Student();
		student.setId(1);
		student.setIsSubscribed(true);

		Cour cour = new Cour();
		cour.setId(1);
		cour.setPremium(true);
		cour.setLessons(List.of(new Lesson(), new Lesson()));

		Progress progress = new Progress();
		progress.setStudent(student.getId());
		progress.setCour(cour.getId());

		when(studentRepository.findById(1)).thenReturn(Optional.of(student));
		when(courRepository.findById(1)).thenReturn(Optional.of(cour));
		when(progressRepository.findByStudentIdAndCourId(1, 1)).thenReturn(null);
		when(progressRepository.save(any(Progress.class))).thenReturn(progress);

		// Act
		Progress savedProgress = progressService.saveProgress(progress);

		// Assert
		assertNotNull(savedProgress);
		assertEquals(2000, savedProgress.getMaxXP());
	}
	@Test
	public void test_saveChat_successfully_saves_new_chat() {
		// Arrange
		ChatRepository chatRepository = mock(ChatRepository.class);
		InstructorRepository instructorRepository = mock(InstructorRepository.class);
		StudentRepository studentRepository = mock(StudentRepository.class);
		UserRepository userRepository = mock(UserRepository.class);
		View error = mock(View.class);

		ChatServiceImpl chatService = new ChatServiceImpl(chatRepository,error, instructorRepository, studentRepository, userRepository);

		ChatDTO chatDTO = new ChatDTO(1, 1);
		Instructor instructor = new Instructor();
		Student student = new Student();

		when(instructorRepository.findById(1)).thenReturn(Optional.of(instructor));
		when(studentRepository.findById(1)).thenReturn(Optional.of(student));
		when(chatRepository.findChat(1, 1)).thenReturn(null);

		Chat chatToSave = new Chat();
		chatToSave.setInstructor(instructor);
		chatToSave.setStudent(student);

		when(chatRepository.save(any(Chat.class))).thenReturn(chatToSave);

		// Act
		Chat result = chatService.saveChat(chatDTO);

		// Assert
		assertNotNull(result);
		assertEquals(instructor, result.getInstructor());
		assertEquals(student, result.getStudent());

	}
	@Test
	public void test_save_participant_successfully() {
		ParticipantRepository participantRepository = mock(ParticipantRepository.class);
		EventRepository eventRepository = mock(EventRepository.class);
		StudentRepository studentRepository = mock(StudentRepository.class);

		ParticipantServiceImpl participantService = new ParticipantServiceImpl(participantRepository, eventRepository, studentRepository);

		Student student = new Student();
		student.setId(1);

		Event event = new Event();
		event.setId(1);
		event.setStartDate(Date.from(LocalDateTime.now().minusDays(1).atZone(ZoneId.systemDefault()).toInstant()));
		event.setEndDate(Date.from(LocalDateTime.now().plusDays(1).atZone(ZoneId.systemDefault()).toInstant()));

		Participant participant = new Participant();
		participant.setStudent(student.getId());
		participant.setEvent(event.getId());

		when(studentRepository.findById(1)).thenReturn(Optional.of(student));
		when(eventRepository.findById(1)).thenReturn(Optional.of(event));
		when(participantRepository.findByStudentAndEventId(1, 1)).thenReturn(null);
		when(participantRepository.save(any(Participant.class))).thenReturn(participant);

		Participant savedParticipant = participantService.saveParticipant(participant);

		assertNotNull(savedParticipant);
		assertEquals(participant, savedParticipant);
	}
	@Test
	public void test_save_feedback_successfully() {
		// Arrange
		FeedBackRepository feedBackRepository = mock(FeedBackRepository.class);
		CourRepository courRepository = mock(CourRepository.class);
		StudentRepository studentRepository = mock(StudentRepository.class);

		FeedBackServiceImpl feedBackService = new FeedBackServiceImpl(feedBackRepository, courRepository, studentRepository);

		Student student = new Student();
		student.setId(1);

		Cour cour = new Cour();
		cour.setId(1);

		FeedBack feedBack = new FeedBack();
		feedBack.setStudent(student.getId());
		feedBack.setCour(cour.getId());
		feedBack.setRating(5);

		when(studentRepository.findById(1)).thenReturn(Optional.of(student));
		when(courRepository.findById(1)).thenReturn(Optional.of(cour));
		when(feedBackRepository.findByStudentIdAndCourId(1, 1)).thenReturn(null);
		when(feedBackRepository.findRatingByCourId(1)).thenReturn(List.of(5.0f));

		// Act
		FeedBack result = feedBackService.saveFeedBack(feedBack);

		// Assert
		assertNotNull(result);
		assertEquals(5, result.getRating());
		verify(feedBackRepository).save(feedBack);
		verify(courRepository).save(cour);
	}
}
