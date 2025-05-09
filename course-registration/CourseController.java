@Controller
public class CourseController {

  @Autowired
  private JdbcTemplate jdbcTemplate;

  @GetMapping("/courses")
  public String showCourses(Model model) {
      List<Map<String, Object>> courses = jdbcTemplate.queryForList("SELECT * FROM courses");
      model.addAttribute("courses", courses);
      return "courses";
  }

  @PostMapping("/register/{courseId}")
  public String registerCourse(@PathVariable int courseId, HttpServletRequest req) {
      int studentId = (int) req.getSession().getAttribute("studentId");

      int count = jdbcTemplate.queryForObject(
        "SELECT COUNT(*) FROM registrations WHERE student_id=? AND course_id=?", Integer.class, studentId, courseId);

      if (count == 0) {
          jdbcTemplate.update("INSERT INTO registrations (student_id, course_id, date) VALUES (?, ?, CURDATE())",
              studentId, courseId);
      }

      return "success";
  }
}
