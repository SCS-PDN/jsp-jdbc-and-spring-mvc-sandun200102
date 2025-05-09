public class LoggingInterceptor extends HandlerInterceptorAdapter {
  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
      String uri = request.getRequestURI();
      String email = request.getParameter("email");
      if (uri.contains("/login")) {
          System.out.println("Login attempt with email: " + email);
      }
      if (uri.contains("/register")) {
          System.out.println("Course registration attempt: " + uri);
      }
      return true;
  }
}
