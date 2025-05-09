@Controller
public class LoginController {

  @Autowired
  private JdbcTemplate jdbcTemplate;

  @GetMapping("/login")
  public String showLoginPage() {
      return "login";
  }

  @PostMapping("/login")
  public String validateLogin(HttpServletRequest req, Model model) {
      String email = req.getParameter("email");
      String password = req.getParameter("password");

      List<Map<String, Object>> users = jdbcTemplate.queryForList(
          "SELECT * FROM students WHERE email=? AND password=?", email, password);

      if (!users.isEmpty()) {
          HttpSession session = req.getSession();
          session.setAttribute("studentId", users.get(0).get("student_id"));
          return "redirect:/courses";
      } else {
          model.addAttribute("error", "Invalid credentials");
          return "login";
      }
  }
}
